package com.apache.commons.cli;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnagramEvaluatorImpl implements AnagramEvaluator {

    @Override
    public List<String> evaluate(String anagram, String option) {
        AnagramDataReader anagramDataReader = new AnagramDataReaderImpl();
        Set<String> allAnagrams = new HashSet<>();
        anagram = anagram.toUpperCase();
        anagramBuilder(anagram, 0, anagram.length() - 1, allAnagrams);

        List<String> validWords = new ArrayList<>(allAnagrams);
        if(option == "nf") {
//            read the data from the data reader
            Set<String> allowedWords = anagramDataReader.readData();
            allAnagrams.forEach(word -> {
                if(!allowedWords.contains(word))
                    validWords.remove(word);
            });
        }
        return validWords;
    }

    private void anagramBuilder(String str, int start, int end, Set<String> allAnagrams)
    {
        if (start == end)
            allAnagrams.add(str);
        else {
            for (int i = start; i <= end; i++) {
                str = swap(str, start, i);
                anagramBuilder(str, start + 1, end, allAnagrams);
                str = swap(str, start, i);
            }
        }
    }
    public String swap(String a, int i, int j)
    {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }
}
