package org.example;

import org.example.handlers.OwlParserRunner;

public class Main {
    public static void main(String[] args) {
        String uri = "https://raw.githubusercontent.com/FoodOntology/foodon/master/foodon.owl";
        OwlParserRunner parserRunner = new OwlParserRunner();
        parserRunner.run(uri);
    }
}