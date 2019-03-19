package core;

import exception.IncorrectFields;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BitTexCoreTest {

    private BitTexDatabase bitTexDatabase;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void resolve() {
        bitTexDatabase = new BitTexDatabase();
    }

    @Test
    public void resolveVariable(){
        bitTexDatabase.putString("test", "value");

        Attributes attribute = Attributes.MONTH;
        String value = "test # aug # 1992";

        List<String> actual = BitTexCore.parseAttributeValue(attribute,value,bitTexDatabase,0);
        List<String> expected = Arrays.asList("value August 1992");

        assertThat(actual, is(expected));
    }


    @Test
    public void  parseAttributeValue() {
        Attributes attribute = Attributes.MONTH;
        String value = "jun # aug # 1992";

        List<String> actual = BitTexCore.parseAttributeValue(attribute,value,bitTexDatabase,0);
        List<String> expected = Arrays.asList("June August 1992");

        assertThat(actual, is(expected));

    }

    @Test
    public void parseInvalidAttributeValue() {
        Attributes attribute = Attributes.MONTH;
        String value = "jun # test";

        thrown.expect(IncorrectFields.class);
        thrown.expectMessage("Invalid variable: test");

        BitTexCore.parseAttributeValue(attribute,value,bitTexDatabase,0);
    }
}