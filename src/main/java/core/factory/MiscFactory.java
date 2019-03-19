package core.factory;


import core.Attributes;
import core.BibTeXEntity;
import core.records.Misc;
import exception.IncorrectFields;

import java.util.List;
import java.util.Map;



/**
 * Abstract factory pattern to generate new type of recordFields by implementing them.
 *
 */
public class MiscFactory extends AbstractFactory {

    /**
     * @param attributes is map of attributes and list of values.
     * @param key        associated with concrete recordField.
     * @return RecordField witch implements BitTexEntity
     * @throws IncorrectFields when passed attributes don't have all required fields.
     */
    public static BibTeXEntity createRecord(Map<Attributes, List<String>> attributes, String key) {
        if ( attributes == null || key == null ) throw  new IncorrectFields("argument can not be null");

        if (checkRequiredAttributes(attributes, Misc.requiredAttributes)) {

            deleteUnnecessaryAttributes(attributes, Misc.acceptableAttributes);
            return new Misc(attributes, key);
        } else {
            throw new IncorrectFields("Misc not have all required attributes:" + key);
        }
    }
}