package com.mainaud.exo.mowitnow;

import com.google.inject.AbstractModule;

public class MowItNowModule extends AbstractModule {

    @Override
    protected void configure() {
        // Bind the MowItNow implementation to the interface.
        bind(MowerSystemControl.class).to(MowItNowSystemControl.class);
    }

}
