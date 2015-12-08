package com.aconex.convertor;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {

    public static void main(String[] args) throws IOException {
//        String inputNumber =
//                parseCommandLineArgs(args);
//        Converter converter = ConverterBuilder.getInstance().config().build();
//        List<String> result = converter.getMatchingPhraseFor(inputNumber);
//        printOutResult(result);
        Map<String, String> map = new HashMap<String, String>();
//        /Users/twer/Projects/phonenumber-convertor/src/main/resources/dic.txt
        ClassLoader classLoader = Application.class.getClassLoader();
        File file = new File(classLoader.getResource("dic.txt").getFile());
        FileReader in1 = new FileReader(file);
        BufferedReader in = new BufferedReader(in1);
        String line = "";
        while ((line = in.readLine()) != null) {
//            String parts[] = line.split("\t");
            if(line.length() > 0) {
//                map.put(line.charAt(0), line);
                map.put(line, line);
            }
        }
        in.close();
        System.out.println(map.toString());

    }

    private static void printOutResult(List<String> result) {

    }

    private static String parseCommandLineArgs(String[] args) {
        return null;
    }
}
