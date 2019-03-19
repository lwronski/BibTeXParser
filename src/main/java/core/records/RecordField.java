package core.records;

import core.Attributes;
import core.BibTeXEntity;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * RecordField - single record bibliography in BitTex.
 */
public abstract class RecordField implements BibTeXEntity {

    final private Map<Attributes, List<String>> records;
    final private String key;

    /**
     *
     * @param records is map of attributes with values
     * @param key associated with recordField
     */
    public RecordField(Map<Attributes, List<String>> records, String key) {
        this.records = records;
        this.key = key;
    }


    /**
     * @return map of attributes with value
     */
    public Map<Attributes, List<String>> getRecords() {
        return Collections.unmodifiableMap(records);
    }


    /**
     * @return key associated with @recordField.
     */
    public String getKey() {
        return key;
    }

}
