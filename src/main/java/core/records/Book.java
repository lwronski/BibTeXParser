package core.records;

import core.Attributes;
import core.visualize.VisualVisitor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static core.Attributes.*;

public class Book extends RecordField {


    public static final HashSet<Attributes> requiredAttributes = new HashSet<>(Arrays.asList(TITLE, PUBLISHER, YEAR));
    public static final HashSet<Attributes> acceptableAttributes = new HashSet<>(Arrays.asList(AUTHOR, EDITOR, TITLE, PUBLISHER, YEAR, VOLUME, SERIES, ADDRESS, EDITION, MONTH, NOTE, KEY));

    public Book(Map<Attributes, List<String>> attributes, String key) {
        super(attributes, key);
    }

    @Override
    public void accept(VisualVisitor visitor) {
        visitor.visit(this);
    }
}
