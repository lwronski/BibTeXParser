package core.visualize;

import core.Attributes;
import core.BitTexDatabase;
import core.factory.ArticleFactory;
import core.factory.BookFactory;
import core.records.RecordField;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static core.Attributes.*;
import static core.Attributes.TITLE;
import static org.junit.Assert.*;

public class DefaultVisualVisitorTest {


    public static final Map<Attributes, List<String>> attributesListMap = new HashMap<>() {
        {
            put(AUTHOR, List.of("David J. Lipcoll", "D. H. Lawrie", " A. H. Sameh"));
            put(PUBLISHER, List.of("Fast Computers"));
            put(YEAR, List.of("1997"));
            put(ORGANIZATION, List.of("test"));
            put(TITLE, List.of("High Speed Computer and Algorithm Organization"));
            put(TYPE, List.of("generic"));
            put(EDITOR, List.of("Editor"));
        }
    };

    private BitTexDatabase bitTexDatabase;
    private DefaultVisualVisitor defaultVisualVisitor;
    private RecordField book;
    private RecordField article;


    @Before
    public void setUp() throws Exception {

        bitTexDatabase = new BitTexDatabase();
        defaultVisualVisitor = new DefaultVisualVisitor();

        String key = "whole-collection";

        Map<Attributes, List<String>> mapBook = new HashMap<>();
        mapBook.put(AUTHOR, attributesListMap.get(AUTHOR));
        mapBook.put(PUBLISHER, attributesListMap.get(PUBLISHER));
        mapBook.put(YEAR, attributesListMap.get(YEAR));
        mapBook.put(ORGANIZATION, attributesListMap.get(ORGANIZATION));
        mapBook.put(TITLE, attributesListMap.get(TITLE));

        book = (RecordField) BookFactory.createRecord(mapBook, key);

        Map<Attributes, List<String>> mapArticle = new HashMap<>();
        mapArticle.put(AUTHOR, List.of("L[eslie] A. Aamport" ,"Alfred V. Oaho"));
        mapArticle.put(TITLE, attributesListMap.get(TITLE));
        mapArticle.put(YEAR, List.of("1993"));
        mapArticle.put(JOURNAL, List.of("journal"));

        article = (RecordField) ArticleFactory.createRecord(mapArticle, key);


        bitTexDatabase.put(book);
        bitTexDatabase.put(article);

    }

    @Test
    public void findAuthorsInDataBase() {

        assertTrue(defaultVisualVisitor.checkAuthors(article, List.of("Alfred")));
        assertFalse(defaultVisualVisitor.checkAuthors(article, List.of("Lukasz")));
        assertFalse(defaultVisualVisitor.checkAuthors(book, List.of("Alfred")));
    }
}