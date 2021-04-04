package com.dkit.oopca5.client;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* This class should contain static methods to verify input in the application
 */

public class RegexChecker
{
        public static boolean checkFormat(String input, String pattern1)
        {
            Pattern pattern = Pattern.compile(pattern1);
            Matcher matcher = pattern.matcher(input);
            boolean matchFound = matcher.find();
            if(matchFound)
            {
                System.out.println("Correct Format Inputted");
            }
            else {
                System.out.println("Format not Correct");
            }
            return matchFound;
        }
}
