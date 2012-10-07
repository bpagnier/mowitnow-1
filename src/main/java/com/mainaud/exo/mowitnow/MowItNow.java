package com.mainaud.exo.mowitnow;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.mainaud.exo.mowitnow.parser.MowItNowFileParser;
import com.mainaud.exo.mowitnow.parser.MowItNowParseException;

public class MowItNow {
    @Parameter(names = { "-c", "--charset" }, description = "Charset")
    private String charsetName;
    @Parameter(names = { "-i", "--input" }, description = "Input file name")
    private String inputFileName;
    @Parameter(names = { "-o", "--output" }, description = "Output file name")
    private String outputFileName;

    private Charset charset;
    private MowerSystemControl msc;

    @Inject
    private MowItNow(MowerSystemControl msc) {
        this.msc = msc;
    }

    private void run() {
        prepareCharset();
        processInput();
        reportFinalState();
    }

    private void prepareCharset() {
        if (charsetName != null) {
            try {
                charset = Charset.forName(charsetName);
            } catch (IllegalCharsetNameException e) {
                System.err.println('"' + charsetName + "\" is not a valid charset name");
                System.exit(1);
            } catch (UnsupportedCharsetException e) {
                System.err.println("Charset \"" + charsetName + "\" is not supported");
                System.exit(1);
            }
        } else {
            charset = Charset.defaultCharset();
        }
    }

    private void processInput() {
        if (inputFileName == null)
            processFromStandardInput();
        else
            processFromFile();
    }

    private void processFromStandardInput() {
        parse(System.in);
    }

    private void processFromFile() {
        try {
            InputStream in = new FileInputStream(inputFileName);
            try {
                parse(in);
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    System.err.println("Error when closing input file " + inputFileName + ": " + e.getCause());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find file " + inputFileName);
            System.exit(1);
        }
    }

    private void parse(InputStream in) {
        try {
            Reader reader = new BufferedReader(new InputStreamReader(in, charset));
            MowItNowFileParser parser = new MowItNowFileParser(msc);
            parser.parse(reader);
        } catch (IOException e) {
            System.err.println("Error while reading data: " + e.getCause());
            System.exit(1);
        } catch (MowItNowParseException e) {
            System.err.println("Invalid data in file: " + e.getCause());
            System.exit(1);
        }
    }

    private void reportFinalState() {
        if (outputFileName == null) {
            reportToStandardOutputStream();
        } else {
            reportToFile();
        }
    }

    private void reportToStandardOutputStream() {
        report(System.out);
    }

    private void reportToFile() {

        try {
            OutputStream out = new FileOutputStream(outputFileName);
            try {
                report(out);
            } finally {
                try {
                    out.close();
                } catch (IOException e) {
                    System.err.println("Error when closing output file " + outputFileName + ": " + e.getCause());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot open output file " + outputFileName);
            System.exit(1);
        }

    }

    private void report(OutputStream out) {
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(out, charset), true);
        for (Location location : msc.mowerLocations()) {
            pw.println(location);
        }
        pw.flush();
    }

    public static void main(String[] args) {
        // OK use Guice here is quite too much be that is to show what could be
        // done.
        Injector injector = Guice.createInjector(new MowItNowModule());
        MowItNow mowItNow = injector.getInstance(MowItNow.class);
        new JCommander(mowItNow, args);
        mowItNow.run();
    }
}
