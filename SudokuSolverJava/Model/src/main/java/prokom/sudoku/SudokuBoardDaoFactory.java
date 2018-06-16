/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prokom.sudoku;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Vitalii
 */
public class SudokuBoardDaoFactory {
    public static Dao<SudokuBoard> getFileDao(final String fileName) throws FileNotFoundException, IOException {
        Dao<SudokuBoard> dao = new FileSudokuBoardDao(fileName);
        return dao;
    }
}
