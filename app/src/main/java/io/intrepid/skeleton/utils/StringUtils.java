package io.intrepid.skeleton.utils;

import android.support.annotation.Nullable;

public class StringUtils {
    /**
     * Abbreviate the input string down tot he character limit by truncating the middle and replacing
     * it with "..."
     *
     * @param s              the input string
     * @param characterLimit the maximum number of characters the resulting string will have
     * @return the truncated string with the middle truncated
     */
    public static String abbreviateString(String s, int characterLimit) {
        if (characterLimit < 4) {
            throw new IllegalArgumentException("Character limit must be greater than 4");
        }
        if (s.length() < characterLimit) {
            return s;
        }

        String ellipsize;
        int halfSize;
        if (characterLimit % 2 == 0) {
            ellipsize = "..";
            halfSize = (characterLimit - 2) / 2;
        } else {
            ellipsize = "...";
            halfSize = (characterLimit - 3) / 2;
        }

        return s.substring(0, halfSize) + ellipsize + s.substring(s.length() - halfSize);
    }

    /**
     * Returns true if the string is null or 0-length.
     *
     * @param str the string to be examined
     * @return true if str is null or zero length
     */
    public static boolean isEmpty(@Nullable CharSequence str) {
        return str == null || str.length() == 0;
    }

    /**
     * Returns true if a and b are equal, including if they are both null.
     * <p><i>Note: In platform versions 1.1 and earlier, this method only worked well if
     * both the arguments were instances of String.</i></p>
     *
     * @param a first CharSequence to check
     * @param b second CharSequence to check
     * @return true if a and b are equal
     */
    public static boolean equals(CharSequence a, CharSequence b) {
        if (a == b) {
            return true;
        }
        int length;
        if (a != null && b != null && (length = a.length()) == b.length()) {
            if (a instanceof String && b instanceof String) {
                return a.equals(b);
            } else {
                for (int i = 0; i < length; i++) {
                    if (a.charAt(i) != b.charAt(i)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static String getEmptyStringForNull(String str) {
        return (str != null) ? str : "";
    }
}
