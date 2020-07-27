package com.apache.commons.cli;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class AnagramDataReaderImpl implements AnagramDataReader {

    @Override
    public Set<String> readData() {
        Set<String> allWords = new HashSet<>();

        //Here you have to write
        File file = new File("/Users/rishabmehra/Downloads/Anagrammer/src/com/apache/commons/cli/anagram_data.txt");
        FileReader fr = null;
        try {
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String s;
            while ((s = br.readLine()) != null)
                allWords.add(s);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Size of the words array: " + allWords.size());
        return allWords;
    }
}
