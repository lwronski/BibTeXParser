package core.factory;

import core.Attributes;
import core.BibTeXEntity;
import core.records.Article;
import exception.IncorrectFields;

import java.util.List;
import java.util.Map;


/**
 * Abstract factory pattern to generate new type of recordFields by implementing them.
 *
 */

public class ArticleFactory extends AbstractFactory {

    /**
     * @param attributes is map of attributes and list of values.
     * @param key        associated with concrete recordField.
     * @return RecordField witch implements BitTexEntity
     * @throws IncorrectFields when passed attributes don't have all required fields.
     */
    public static BibTeXEntity createRecord(Map<Attributes, List<String>> attributes, String key) {
        if (attributes == null || key == null) throw new IncorrectFields("argument can not be null");

        if (checkRequiredAttributes(attributes, Article.requiredAttributes)) {
            deleteUnnecessaryAttributes(attributes, Article.acceptableAttributes);
            return new Article(attributes, key);
        } else {
            throw new IncorrectFields("Article not have all required attributes");
        }
    }


}
