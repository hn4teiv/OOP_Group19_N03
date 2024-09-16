package com.mycompany.app;

// Lớp trừu tượng
abstract class Instrument {
    abstract void play();
}

class Piano extends Instrument {
    @Override
    void play() {
        System.out.println("Piano is playing");
    }
}

class Guitar extends Instrument {
    @Override
    void play() {
        System.out.println("Guitar is playing");
    }
}

public class PolymorphismExercise17 {
    public static void main(String[] args) {
        Instrument piano = new Piano();
        Instrument guitar = new Guitar();

        piano.play();
        guitar.play();
    }
}
