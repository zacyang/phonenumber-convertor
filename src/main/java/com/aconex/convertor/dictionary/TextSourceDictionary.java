package com.aconex.convertor.dictionary;

import com.aconex.convertor.config.ApplicationConfig;
import com.aconex.convertor.query.FullMatchQuery;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class TextSourceDictionary implements DictionarySource {
    private final String dictionaryPath;

    public TextSourceDictionary(String specifiedDicPath) {
        if (specifiedDicPath == null || "".equals(specifiedDicPath)) {
            this.dictionaryPath = FullMatchQuery.class.getClassLoader()
                    .getResource(ApplicationConfig.DEFAULT_DICT).getFile();
        } else {
            this.dictionaryPath = specifiedDicPath;
        }
    }

    public List<String> getWords() {
        File file = new File(this.dictionaryPath);
        BufferedReader in = null;
        List<String> words = new LinkedList<>();
        try {
            in = new BufferedReader(new FileReader(file));
            String word = "";
            while ((word = in.readLine()) != null) {
                words.add(word);
            }
        } catch (Exception e) {
            System.err.println("Can not read dictionary file :" + dictionaryPath);
            return null;
        } finally {
            try {
                if (in != null) in.close();
            } catch (Exception e) {
                System.err.println("CAN NOT CLOSE STREAM DUE TO UNKNOW ISSUE:" + e.getStackTrace());
            }
        }

        return words;
    }

}
