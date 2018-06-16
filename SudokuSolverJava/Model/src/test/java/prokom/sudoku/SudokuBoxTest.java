/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prokom.sudoku;

import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cuba
 */
public class SudokuBoxTest {
    
    public SudokuBoxTest() {
    }

    @Test
    public void testReferences() {
        SudokuField[] list = new SudokuField[9];
        boolean error = false;
        for (int i=0; i < 9; i++) {
            list[i] = new SudokuField();
            list[i].setFieldValue(i+1);
        }
        SudokuBox box = new SudokuBox(Arrays.asList(list));
        for (int i=0; i < 9; i++) {
            error = list[i].getFieldValue() != box.getFieldValue(i);
            if (error) {
                break;
            }
        }
        assertEquals(error, false);
    }
}
