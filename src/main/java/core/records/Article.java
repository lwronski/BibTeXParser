package core.records;

import core.Attributes;
import core.visualize.VisualVisitor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static core.Attributes.*;

public class Article extends RecordField {

    public static final HashSet<Attributes> requiredAttributes = new HashSet<>(Arrays.asList(AUTHOR, TITLE, JOURNAL, YEAR));
    public static final HashSet<Attributes> acceptableAttributes = new HashSet<>(Arrays.asList(VOLUME, NUMBER, PAGES, MONTH, NOTE, KEY, AUTHOR, TITLE, JOURNAL, YEAR));

    public Article(Map<Attributes, List<String>> records, String key) {
        super(records, key);
    }

    @Override
    public void accept(VisualVisitor visitor) {
        visitor.visit(this);
    }
}
