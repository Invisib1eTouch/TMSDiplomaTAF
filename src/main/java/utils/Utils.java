package utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class Utils {

    public static String getRandomAlphaNumericString(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public static int getRandomNumber(int minInclusive, int maxExclusive) {
        return RandomUtils.nextInt(minInclusive, maxExclusive);
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
        var errMes = String.format("Entered word index: %s, number of the words in text: %s", wordIndex, textWords.length);
        log.error(errMes);
        throw new ArrayIndexOutOfBoundsException(errMes);
    }

    public static String getMatchedText(String text, String pattern) {
        Matcher m = Pattern.compile(pattern).matcher(Objects.requireNonNull(text));
        if (m.find())
            return m.group(0);
        var errMes = String.format("No pattern match found in provided text: %s; Pattern: %s", text, pattern);
        log.error(errMes);
        throw new IllegalStateException(errMes);
    }
}