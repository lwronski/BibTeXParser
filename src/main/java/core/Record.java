package core;

import java.util.Arrays;

public enum Record {
    BOOK("BOOK"), ARTICLE("ARTICLE"), BOOKLET("BOOKLET"), CONFERENCE("CONFERENCE"), INBOOK("INBOOK"), INCOLLECTION("INCOLLECTION"),
    INPROCEEDINGS("INPROCEEDINGS"), MANUAL("MANUAL"), MASTERSTHESIS("MASTERSTHESIS"), MISC("MISC"), PHDTHESIS("PHDTHESIS"),
    PROCEEDINGS("PROCEEDINGS"), TECHREPORT("TECHREPORT"), UNPUBLISHED("UNPUBLISHED"), STRING("STRING"), PREAMBLE("PREAMBLE"), COMMENT("COMMENT");

    private final String value;

    Record(String value) {
        this.value = value;
    }

    /**
     * @return the Enum representation for the given string.
     */
    public static Record fromString(String s) throws IllegalArgumentException {
        return Arrays.stream(Record.values())
                .filter(v -> v.value.equals(s.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("unknown value: " + s));
    }

}
