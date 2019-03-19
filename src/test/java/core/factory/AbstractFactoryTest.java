package core.factory;

import core.Attributes;
import core.records.RecordField;
import exception.IncorrectFields;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static core.Attributes.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AbstractFactoryTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    public static final Map<Attributes, List<String>> attributesListMap = new HashMap<>(){ {
        put(AUTHOR, List.of("David J. Lipcoll", "D. H. Lawrie", " A. H. Sameh" ));
        put(PUBLISHER, List.of("Fast Computers"));
        put(YEAR, List.of("1997"));
        put(ORGANIZATION, List.of("test"));
        put(TITLE, List.of("High Speed Computer and Algorithm Organization"));
    }};


    @Test
    public void bookFactoryTest(){

        String key = "whole-collection";
        Map<Attributes, List<String>>  map = new HashMap<>();
        map.put(AUTHOR, attributesListMap.get(AUTHOR));
        map.put(PUBLISHER, attributesListMap.get(PUBLISHER));
        map.put(YEAR,attributesListMap.get(YEAR));
        map.put(ORGANIZATION,attributesListMap.get(ORGANIZATION));
        map.put(TITLE,attributesListMap.get(TITLE));

        RecordField book = (RecordField) BookFactory.createRecord(map,key);

        assertEquals(attributesListMap.get(AUTHOR), book.getRecords().get(AUTHOR) );
        assertEquals( attributesListMap.get(TITLE), book.getRecords().get(TITLE) );
        assertEquals(attributesListMap.get(PUBLISHER), book.getRecords().get(PUBLISHER) );
        assertEquals(attributesListMap.get(YEAR), book.getRecords().get(YEAR) );
        assertNull(book.getRecords().get(ORGANIZATION));
    }

    @Test
    public void invalidBookFactoryTest(){

        String key = "whole-collection";
        Map<Attributes, List<String>>  map = new HashMap<>();
        map.put(ORGANIZATION,attributesListMap.get(ORGANIZATION));
        map.put(TITLE,attributesListMap.get(TITLE));

        thrown.expect(IncorrectFields.class);

        BookFactory.createRecord(map,key);
    }

    @Test
    public void nullBookFactoryTest(){
        thrown.expect(IncorrectFields.class);
        thrown.expectMessage("argument can not be null");

        BookFactory.createRecord(null,null);
    }

    @Test
    public void i(){
        thrown.expect(IncorrectFields.class);
        thrown.expectMessage("argument can not be null");

        BookFactory.createRecord(null,null);
    }

}