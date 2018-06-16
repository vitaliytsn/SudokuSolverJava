/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prokom.sudoku;

import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 *
 * @author Cuba
 */
public abstract class SudokuStructure implements java.io.Serializable, Cloneable{
    
    
    //  Fields
    protected List<SudokuField> list;
    
    
    //  Constructors
    public SudokuStructure(final List<SudokuField> list) {
        this.list = list;
    }
    
    //  Methods
    public int getFieldValue(final int index) {
        //return structure[index].getFieldValue();
        return list.get(index).getFieldValue();
    }
    
    public void setFieldValue(final int index, final int value) {
        //structure[index].setFieldValue(value);
        list.get(index).setFieldValue(value);
    }
    
    public boolean verify(final int value) {
                for (int j=0; j < 9; j++) {
                    /*if (structure[j].getFieldValue() == value) {
                    return false;
                    }*/
                    SudokuField sf = new SudokuField();
                    sf.setFieldValue(value);
                    if (list.get(j).getFieldValue() == value) {
                        return false;
                    }
                }
        return true;
    }
    
    
    //   @Override Methods
    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        SudokuStructure rhs = (SudokuStructure) obj;
        return new EqualsBuilder()
                      .append(list, rhs.list)
                      .isEquals();
    }
    
    @Override
    public int hashCode() {   
        return new HashCodeBuilder(17, 59).append(this).toHashCode();
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).toString(); 
    }
    
    @Override
    public abstract SudokuStructure clone();
}
