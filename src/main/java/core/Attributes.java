package core;

import java.util.Arrays;

public enum Attributes {
    AUTHOR("AUTHOR"), TITLE("TITLE"), JOURNAL("JOURNAL"), YEAR("YEAR"), VOLUME("VOLUME"), NUMBER("NUMBER"),
    PAGES("PAGES"), MONTH("MONTH"), NOTE("NOTE"), KEY("KEY"), EDITOR("EDITOR"), PUBLISHER("PUBLISHER"),
    SERIES("SERIES"), ADDRESS("ADDRESS"), EDITION("EDITION"), HOWPUBLISHED("HOWPUBLISHED"), BOOKTITLE("BOOKTITLE"),
    ORGANIZATION("ORGANIZATION"), CHAPTER("CHAPTER"), TYPE("YPE"), SCHOOL("SCHOOL"), INSTITUTION("INSTITUTION"), NONE("NONE");

    private final String value;

    Attributes(String value) {
        this.value = value;
    }

    /**
     * @return the Enum representation for the given string.
     */
    public static Attributes fromString(String s) throws IllegalArgumentException {
        return Arrays.stream(Attributes.values())
                .filter(v -> v.value.equals(s.toUpperCase()))
                .findFirst()
                .orElse(NONE);
    }
}
