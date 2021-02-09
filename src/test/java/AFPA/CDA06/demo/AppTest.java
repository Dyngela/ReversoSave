package AFPA.CDA06.demo;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import AFPA.CDA06.demo.Entities.Client;
import AFPA.CDA06.demo.Entities.Society;
import AFPA.CDA06.demo.Exception.ExceptionHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Unit test for simple App.
 */
public class AppTest extends Society {

    public AppTest() throws ExceptionHandler{
    }


    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {"patate", "patate.patate", "patate@", "@patete"})
    void invalidFormatMail(String invalidValues){
        assertThrows(ExceptionHandler.class, () -> { new AppTest().setEmail(invalidValues);});
    }

    @Test
    void valueNullOrEmptyCompagnyName(){
        String[] invalidValues = {" ", null, "\r", "\n"};
        for (String invalidValue : invalidValues){
            assertThrows(ExceptionHandler.class, () -> {new AppTest().setCompagnyName(invalidValue);});
        }
    }
}
