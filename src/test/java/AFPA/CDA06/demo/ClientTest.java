package AFPA.CDA06.demo;

import AFPA.CDA06.demo.Entities.Client;
import AFPA.CDA06.demo.Entities.Society;
import AFPA.CDA06.demo.Exception.ExceptionHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ClientTest extends Client {

    public ClientTest() throws ExceptionHandler{

    }

    @Test
    void numberOfEmployeeWrong(){
        int[] invalidValues = {0, -1, -10};
        for(int value : invalidValues){
        assertThrows(ExceptionHandler.class, () -> {new ClientTest().setNumberOfEmployeesOnAverage(value);});
        }
    }

    @Test
    void numberOfEmployeeTrue(){
        int[] validValues = {1, 5, 50 , 200};
        for(int value : validValues) {
            assertDoesNotThrow(() -> {
                new ClientTest().setNumberOfEmployeesOnAverage(value);
            });
        }
    }

    @Test
    void grossSaleWrong(){
        int[] invalidValues = {0, -1, -10, 150, 199};
        for(int value : invalidValues){
            assertThrows(ExceptionHandler.class, () -> {new ClientTest().setGrossSales(value);});
        }
    }

    @Test
    void grossSaleTrue(){
        int[] validValues = {200, 250, 5010, 25014};
        for(int value : validValues) {
            assertDoesNotThrow(() -> {
                new ClientTest().setGrossSales(value);
            });
        }
    }



}
