package utils;

import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

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
}
