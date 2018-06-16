/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prokom.sudoku;

import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vitalii
 */
public class SudokuFieldTest {
    
    public SudokuFieldTest() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    
    @Test
    public void testEquals() {
            SudokuField sf = new SudokuField();
            SudokuField sf1 = new SudokuField();
            sf.setFieldValue(1);
            sf1.setFieldValue(1);
        assertEquals( sf.equals(sf1), true);
    }
    
}
