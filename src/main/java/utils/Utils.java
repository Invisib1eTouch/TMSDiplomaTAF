package utils;

import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    /**
     * @param n - number of needed characters in the generated string
     * @return - alphanumeric string with fixed size
     */
    public static String getRandomAlphaNumericString(int n) {
        RandomStringGenerator randomStringGenerator =
                new RandomStringGenerator.Builder()
                        .withinRange('0', 'z')
                        .filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS)
                        .build();
        return randomStringGenerator.generate(n);
    }

    /**
     * @param text      - text that is needed to be split by spaces
     * @param wordIndex - number of the word in the splitted array to return
     * @return - the word in the splitted array with selected index
     */
    public static String getPartOfSplitBySpacesText(String text, int wordIndex) {
        String[] textWords = text.split(" ");
        if (wordIndex <= textWords.length) {
            return textWords[wordIndex];
        }
        throw new ArrayIndexOutOfBoundsException("Entered word index: " + wordIndex + ", number of the words in text: " + textWords.length);
    }

    public static String getMatchedText(String text, String pattern){
        Matcher m = Pattern.compile(pattern).matcher(Objects.requireNonNull(text));
        if(m.find())
            return m.group(0);
        throw new IllegalStateException("No pattern match found in provided text");
    }

}
