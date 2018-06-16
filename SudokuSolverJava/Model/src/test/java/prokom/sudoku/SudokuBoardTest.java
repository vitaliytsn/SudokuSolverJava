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
public class SudokuBoardTest {
    
    public SudokuBoardTest() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    @Test
    public void testEquals() {
        SudokuBoard obj = new SudokuBoard();
        SudokuBoard instance = new SudokuBoard();
        obj.set(0, 0, 1);
        obj.set(5, 4, 1);
        obj.set(3, 7, 1);
        instance.set(0, 0, 1);
        instance.set(5, 4, 1);
        instance.set(3, 7, 1);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
}
