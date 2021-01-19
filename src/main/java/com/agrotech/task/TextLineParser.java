package com.agrotech.task;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class TextLineParser {

    public static final String ARROW = "->";

    public static String parseUserNameFromTextLine(String textLine) {
        return textLine.substring(0, textLine.indexOf(" "));
    }

    public static Set<String> parseMailsFromTextLine(String textLine) {
        int startOfMails = textLine.indexOf(ARROW) + 3;
        String[] stringMails = textLine.substring(startOfMails).split(", ");
        return new LinkedHashSet<>(Arrays.asList(stringMails));
    }
}
