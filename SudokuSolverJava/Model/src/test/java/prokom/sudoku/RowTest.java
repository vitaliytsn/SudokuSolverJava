/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import prokom.sudoku.SudokuBoard;
import prokom.sudoku.SudokuRow;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vitalii
 */
public class RowTest {
       @Test
       public void testSudoRow()
       {
           SudokuBoard s= new SudokuBoard();
           s.set(0, 0, 1);
           s.set(1, 0, 2);
           s.set(2, 0, 3);
           s.set(3, 0, 4);
           s.set(4, 0, 5);
           s.set(5, 0, 6);
           s.set(6, 0, 7);
           s.set(7, 0, 8);
           SudokuRow row = s.getRow(0);
           boolean d=row.verify(8);
           assertFalse(d);
       }
}
