/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prokom.sudoku;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Vitalii
 */
public class Sudoku {
        

        public SudokuBoard RozwiazSudoku() {
                SudokuBoard sb;
            sb = new SudokuBoard();
            for (int i=0; i < 9; i++) {
                    for (int j=0; j < 9; j++) {
                        sb.set(i, j, 0);
                    }
                }
              //  sb.ZbudujTabele();
                sb.fillBoard();
                return sb;
        }
      /*  public SudokuBoard RozwiazSudoku(final int[][] SudoStart) {
                SudokuBoard sb = new SudokuBoard();
                for (int i=0; i < 9; i++) {
                    for (int j=0; j < 9; j++) {
                        sb.set(i, j, SudoStart[i][j]);
                    }
                }
                sb.fillBoard();
                return sb;
        }*/
    

}
