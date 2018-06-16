/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prokom.sudoku;

import java.util.Arrays;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vitalii
 */
public class SudokuStructureTest {
    
    public SudokuStructureTest() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

   
    @Test
    public void testEquals() {
        SudokuField[] list = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            list[i] = new SudokuField();
            list[i].setFieldValue(i);
        }
        SudokuRow sr = new SudokuRow(Arrays.asList(list));
        SudokuRow sr1 = new SudokuRow(Arrays.asList(list));  
        boolean expResult = true;
        boolean result = sr.equals(sr1);
        assertEquals(expResult, result);
    }
    
    
}
