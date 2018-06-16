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
public class FileSudokuBoardDaoTest {
    
    public FileSudokuBoardDaoTest() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    @Test
    public void testReadWrite() throws Exception { 
        try (Dao<SudokuBoard> fsbd = SudokuBoardDaoFactory.getFileDao("data.txt")) {
           Sudoku s = new Sudoku();
        SudokuBoard sbr = s.RozwiazSudoku();
        fsbd.write(sbr);
            
        SudokuBoard ss;
        ss=(SudokuBoard)fsbd.read();
        assertEquals(true, ss.equals(sbr));
        }
    }
    
    @Test
    public void testReadWriteRead() throws Exception { 
        try (Dao<SudokuBoard> fsbd = SudokuBoardDaoFactory.getFileDao("data.txt")) {
           Sudoku s = new Sudoku();
        SudokuBoard sbr = s.RozwiazSudoku();
        fsbd.write(sbr);
        }
        try (Dao<SudokuBoard> fsbd = SudokuBoardDaoFactory.getFileDao("data.txt")) {
        SudokuBoard ss;
        ss=(SudokuBoard)fsbd.read();
        } catch (Exception e) {
           fail();
        }
    }
}
