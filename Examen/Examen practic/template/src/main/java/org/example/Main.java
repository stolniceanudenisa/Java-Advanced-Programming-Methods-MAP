package org.example;

import org.example.ui.Console;

public class Main {
    public static void main(String[] args) {
        Console console = Console.getInstance();
        console.menu();
    }
}