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
public class SudokuTest {
    
    public SudokuTest() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of RozwiazSudoku method, of class Sudoku.
     */
    
    @Test
    public void testRozwiazSudoku() {  
        int[][] SudoStart;
        SudoStart = new int[9][9];
        
         int[][] Sudo= new int [9][9];
         int[][] SudoOut = new int[9][9];
        Sudoku s = new Sudoku();
        SudokuBoard sbr=s.RozwiazSudoku();
     for(int i=0;i<9;i++){
         for(int j=0;j<9;j++){
             Sudo[j][i]=sbr.get(j, i);
         }
     }
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                 System.out.print(Sudo[j][i]+"  ");
                if(Sudo[j][i]==0)Sudo[j][i]=-1;
                else Sudo[j][i]=0;
            
            }
        System.out.println("");
        }
      String ss= sbr.toString();
      //  System.out.println(ss);
      assertArrayEquals(Sudo, SudoOut);
    }

}
