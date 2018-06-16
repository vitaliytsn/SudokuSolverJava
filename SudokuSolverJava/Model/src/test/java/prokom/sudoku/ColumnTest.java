/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import prokom.sudoku.SudokuBoard;
import prokom.sudoku.SudokuColumn;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vitalii
 */
public class ColumnTest {
    @Test
       public void testSudoColumn()
       {
           SudokuBoard s= new SudokuBoard();
           s.set(0, 1, 1);
           s.set(0, 2, 2);
           s.set(0, 3, 3);
           s.set(0, 4, 4);
           s.set(0, 5, 5);
           s.set(0, 6, 6);
           s.set(0, 7, 7);
           s.set(0, 8, 8);
           SudokuColumn column = s.getColumn(0);
           boolean d=column.verify(2);
           assertFalse(d);
       }
}
