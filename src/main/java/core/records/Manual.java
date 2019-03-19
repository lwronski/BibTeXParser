package core.records;

import core.Attributes;
import core.visualize.VisualVisitor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static core.Attributes.*;

public class Manual extends RecordField {

    public static final HashSet<Attributes> requiredAttributes = new HashSet<>(Arrays.asList(TITLE));
    public static final HashSet<Attributes> acceptableAttributes = new HashSet<>(Arrays.asList(TITLE, AUTHOR, ORGANIZATION, ADDRESS, EDITION, MONTH, YEAR, NOTE, KEY));

    public Manual(Map<Attributes, List<String>> attributes, String key) {
        super(attributes, key);
    }

    @Override
    public void accept(VisualVisitor visitor) {
        visitor.visit(this);
    }
}