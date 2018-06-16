/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prokom.sudoku;

/**
 *
 * @author Vitalii male litery
 */
public class BacktrackingSudokuSolver implements SudokuSolver {

    private SudokuBoard sb;

    /**
     *
     * @param s
     * @return
     */
    @Override
    public SudokuBoard solve(final SudokuBoard s) {
        sb = s;
        back(0, 0);
        return sb;
    }

    public boolean back(final int x1, final int y1) {
        int value = 1;
        int x = x1;
        int y = y1;
        // + (int)(Math.random() * 9);
        //System.out.println("loh");
        //boolean[] available = new boolean[9];
        if (x > 8) {
            x = 0;
            y = y + 1;
            // System.out.println("x "+x+" y "+y);  
        }
        boolean goodjob;
        if (x == 8 && y == 8) {
            // System.out.println("Je fserfvderfd");  
            while (!sb.checkBoard(x, y, value)) {
                value++;
                if (value > 9) {
                    return false;
                }
            }
            sb.set(x, y, value);
            // System.out.println(" chodzi jhgfkfkgvkhgckhgckhgckhcgj111111");
            return true;
        } else {
            do {
                //  System.out.println(goodjob);
                if (value > 9) {
                    return false;
                }
                while (!sb.checkBoard(x, y, value)) {
                    value++;    // = 1 + (int)(Math.random() * 9);
                    if (value > 9) {
                        return false;
                    }// = 1 + (int)(Math.random() * 9);
                }
                //System.out.println(" value " + value);
                sb.set(x, y, value);
                goodjob = back(x + 1, y);
                if (!goodjob) {
                    if (value > 9) {
                        return false;
                        // Jak skończą się liczby do 9, to algorytm kończy działanie.
                    } else {
                        value++;    //1 + (int)(Math.random() * 9);
                    }
                    sb.set(x, y, 0);
                } else {
                    //       System.out.println(" chodzi jhgfkfkgvkhgckhgckhgckhcgj22222222");
                    return true;
                }
            } while (!goodjob);
        }
        return true;
    }
}
