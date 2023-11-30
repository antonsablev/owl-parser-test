package org.example;

import org.example.handlers.OwlReaderRunner;

public class Main {
    public static void main(String[] args) {
        String uri = "https://raw.githubusercontent.com/FoodOntology/foodon/master/foodon.owl";
        OwlReaderRunner parserRunner = new OwlReaderRunner();
        parserRunner.run(uri);
    }
}