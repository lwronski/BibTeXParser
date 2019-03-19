package core.records;

import core.Attributes;
import core.visualize.VisualVisitor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static core.Attributes.*;

public class Unpublished extends RecordField {


    public static final HashSet<Attributes> requiredAttributes = new HashSet<>(Arrays.asList(AUTHOR, TITLE, NOTE));
    public static final HashSet<Attributes> acceptableAttributes = new HashSet<>(Arrays.asList(AUTHOR, TITLE, NOTE, MONTH, YEAR, KEY));

    public Unpublished(Map<Attributes, List<String>> attributes, String key) {
        super(attributes, key);
    }

    @Override
    public void accept(VisualVisitor visitor) {
        visitor.visit(this);
    }
}
