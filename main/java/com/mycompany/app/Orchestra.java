package com.mycompany.app;

abstract class Instrument {
    abstract void play();
}

class StringInstrument extends Instrument {
    @Override
    void play() {
        System.out.println("String instrument is playing");
    }
}

class WindInstrument extends Instrument {
    @Override
    void play() {
        System.out.println("Wind instrument is playing");
    }
}

class Drum extends Instrument {
    @Override
    void play() {
        System.out.println("Drum is playing");
    }
}

public class Orchestra {
    public static void main(String[] args) {
        Instrument[] instruments = {
            new StringInstrument(),
            new WindInstrument(),
            new Drum()
        };

        for (Instrument instrument : instruments) {
            instrument.play();
        }
    }
}
