package AFPA.CDA06.demo;


import AFPA.CDA06.demo.Entities.Society;
import AFPA.CDA06.demo.Exception.ExceptionHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Unit test for simple App.
 */
public class SocietyTest extends Society {

    public SocietyTest() throws ExceptionHandler {
    }


    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {"patate", "patate.patate", "patate@", "@patete"})
    void invalidFormatMail(String invalidValues){
        assertThrows(ExceptionHandler.class, () -> { new SocietyTest().setEmail(invalidValues);});
    }

    @Test
    void valueNullOrEmptyCompagnyName(){
        String[] invalidValues = {" ", null, "\r", "\n"};
        for (String invalidValue : invalidValues){
            assertThrows(ExceptionHandler.class, () -> {new SocietyTest().setCompagnyName(invalidValue);});
        }
    }
}
