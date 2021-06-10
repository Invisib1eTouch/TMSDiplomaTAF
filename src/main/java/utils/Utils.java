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

    /**
     *
     * @param text - text that is needed to be split by spaces
     * @param wordIndex - number of the word in the splitted array to return
     * @return - the word in the splitted array with selected index
     */
    public static String getPartOfSplitedBySpacesText(String text, int wordIndex){
        String[] splitedText = text.split("\\s+");
        if (wordIndex <=splitedText.length){
            return splitedText[wordIndex];
        }
        throw new ArrayIndexOutOfBoundsException("Entered words number: " + wordIndex + ", number of the words in text: " + splitedText.length );
    }

}
