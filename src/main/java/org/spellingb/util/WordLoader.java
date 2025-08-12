package org.spellingb.util;

import org.spellingb.model.Word;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WordLoader {

    public static List<Word> loadWords() {
        List<Word> words = new ArrayList<>();

        try {
            InputStream is = WordLoader.class.getResourceAsStream("/words/words.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 3) {
                    String wordText = parts[0].trim();
                    int length = Integer.parseInt(parts[1].trim());
                    String difficulty = parts[2].trim();

                    words.add(new Word(wordText, length, difficulty));
                }
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return words;
    }
}
