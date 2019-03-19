package core.records;

import core.Attributes;
import core.visualize.VisualVisitor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static core.Attributes.*;

public class InBook extends RecordField {


    public static final HashSet<Attributes> requiredAttributes = new HashSet<>(Arrays.asList(TITLE, PUBLISHER, YEAR));
    public static final HashSet<Attributes> acceptableAttributes = new HashSet<>(Arrays.asList(TITLE, PUBLISHER, YEAR, AUTHOR, EDITOR, CHAPTER, PAGES, VOLUME, NUMBER, SERIES, TYPE, ADDRESS, EDITION, MONTH, NOTE, KEY));

    public InBook(Map<Attributes, List<String>> attributes, String key) {
        super(attributes, key);
    }

    @Override
    public void accept(VisualVisitor visitor) {
        visitor.visit(this);
    }
}
