package com.aconex.convertor;

import com.aconex.convertor.config.ApplicationConfig;

import java.io.IOException;
import java.util.List;

public class Application {
    private static ApplicationConfig applicationConfig = new ApplicationConfig();

    public static void main(String[] args) throws IOException {
        String dictionaryPath = getArgValue(args, "-d", "requires a dictionary path", true);
        String number = getArgValue(args, "-p", "requires number to be converted", false);

        PhoneNumberConvertor phoneNumberConvertor = new PhoneNumberConvertor(dictionaryPath);

        List<String> allPossibleMatch = phoneNumberConvertor.getAllPossibleMatch(number);

        allPossibleMatch.forEach(matchedWords -> System.out.println(matchedWords));
    }

    private static String getArgValue(String[] args, String targetArgs, String errorInfo, boolean optional) {
        String value = null;
        int i = 0;
        String arg;
        while (i < args.length && args[i].startsWith("-")) {
            arg = args[i++];
            if (arg.equals(targetArgs) && i < args.length) {
                value = args[i++];
            }
            i++;
        }
        if (value == null && !optional) {
            String prompt = targetArgs + " :" + errorInfo;
            System.err.println(prompt);
            throw new IllegalArgumentException(prompt);
        }
        return value;
    }

}