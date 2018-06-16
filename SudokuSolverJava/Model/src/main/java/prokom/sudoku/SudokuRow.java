/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prokom.sudoku;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Cuba
 */
public class SudokuRow extends SudokuStructure {
    
    public SudokuRow(final List<SudokuField> list) {
        super(list);
    }
    
    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
    
    @Override
    public SudokuRow clone() {
        SudokuField[] listClone = new SudokuField[9];
        for (int i=0; i < 9; i++) {
            listClone[i] = new SudokuField(this.getFieldValue(i));
        }
        return new SudokuRow(Arrays.asList(listClone));
    }
    
}