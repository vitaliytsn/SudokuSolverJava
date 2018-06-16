/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prokom.sudoku;

import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Cuba
 */
public class SudokuField implements java.io.Serializable, Comparable<SudokuField>, Cloneable, ChangeListener<Property> {

    //  Fields
    private Integer value;

    //  Constructors
    public SudokuField() {
        value = 0;
    }

    public SudokuField(final int value) {
        this.value = value;
    }

    //  Methods
    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(final int value) {
        this.value = value;
    }

    //  @Override Methods
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
        SudokuField rhs = (SudokuField) obj;
        return new EqualsBuilder()
                .append(value, rhs.value)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 59).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(value, ToStringStyle.MULTI_LINE_STYLE).toString();
    }

    @Override
    public int compareTo(final SudokuField o) {
        if (this.value < o.value) {
            return -1;
        } else if (this.value > o.value) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public SudokuField clone() throws CloneNotSupportedException {
        return new SudokuField(this.value);
    }

    @Override
    public void changed(final ObservableValue<? extends Property>  observable, final Property oldValue, final Property newValue) {
        this.value = (Integer) newValue.getValue();
    }

}
