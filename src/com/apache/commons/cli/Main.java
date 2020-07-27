package com.apache.commons.cli;

import org.apache.commons.cli.*;

import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        AnagramEvaluator anagramEvaluator = new AnagramEvaluatorImpl();
        CommandLineParser parser = new BasicParser();
        Options options = new Options();

        Option anagram = new Option("a", "anagram", true, "Supplies the anagram to evaluate");
        anagram.setArgName("word");


        options.addOption(anagram);
        options.addOption("nf", "no-filter", false, "output all anagram values with no filter");
        options.addOption("words", "filter-words", false, "output only values that are known words");
        options.addOption("h", "help", false, "displays the help for this program");

        try {
            CommandLine commandLine = parser.parse(options, args);

            if(commandLine.hasOption('h')) {
                HelpFormatter helpFormatter = new HelpFormatter();
                helpFormatter.printHelp("anagrammer", options);
            } else if(commandLine.hasOption('a')) {
                String option = null;
                if(commandLine.hasOption("nf"))
                    option = "nf";

                List<String> allAnagrams = anagramEvaluator.evaluate(commandLine.getOptionValue('a'), option);
                Collections.sort(allAnagrams);  //sorted the returned list
                allAnagrams.forEach(word -> {
                    System.out.println(word);
                });
                System.out.println();
                System.out.println("-- " + allAnagrams.size() + " value(s) found");
            } else if(commandLine.hasOption("nf") || commandLine.hasOption("words")) {
                System.out.println("Missing required option: -a");
            } else if(commandLine.getOptions().length == 0){
                System.out.println("No option has been chosen\nType -h or --help for more information");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        options.addOption("a", "anagram", true, "Supplies the anagram to evaluate");
    }
}
