package com.jameel.wordmaster;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class App {

    Set<String> knownWords = new HashSet<String>();
    Set<String> inputWords = new HashSet<String>();

    public App(String fileName) {
        initializeKnownWords();

        processInputFile(readFile(fileName));

        inputWords.removeAll(knownWords);

        for (String string : inputWords) {
            System.out.println(string);
        }
    }

    private List<String> readFile(String fileName) {
        List<String> lines = new LinkedList<String>();

        File file = new File(fileName);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lines;
    }

    private void processInputFile(List<String> lines) {
        for (String line : lines) {
            String[] words = line.split(" ");
            for (String word : words) {
                word = word.toUpperCase();
                if (word.matches("^[A-Z]*$")) {
                    inputWords.add(word);
                }
            }
        }
    }

    private void processCSV(List<String> lines) {
        for (String line : lines) {
            String[] words = line.split(",");
            for (String word : words) {
                knownWords.add(word.trim().toUpperCase());
            }
        }
    }

    private void processSpaceFile(List<String> lines) {
        for (String line : lines) {
            String[] words = line.split(" ");
            for (String word : words) {
                knownWords.add(word.trim().toUpperCase());
            }
        }
    }

    private void processFile(List<String> lines) {
        for (String line : lines) {
            knownWords.add(line.trim().toUpperCase());
        }
    }

    private void initializeKnownWords() {
        this.processCSV(readFile("country-related.txt"));
        this.processSpaceFile(readFile("names.txt"));
        this.processFile(readFile("oxford3000.txt"));
        this.processFile(readFile("time-related.txt"));
        this.processFile(readFile("number-related.txt"));
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Invalid command line, exactly one argument required");
            System.exit(1);
        }

        new App(args[0]);
    }
}
