/*99
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prokom.sudoku;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 * @author Vitalii
 */
public class SudokuBoard implements java.io.Serializable, Cloneable {

    private static final Logger LOGGER = Logger.getLogger(SudokuBoard.class.getName());
    //private final SudokuField[][] sudo;
    private final List<List<SudokuField>> sudo;
    private final List<SudokuRow> rows;
    private final List<SudokuColumn> columns;
    private final List<SudokuBox> boxes;

    public SudokuBoard() {

        sudo = Arrays.asList((List<SudokuField>[]) new List[9]);
        for (int i = 0; i < 9; i++) {
            //List<SudokuField> row = Arrays.asList(new SudokuField[0]);
            sudo.set(i, Arrays.asList(new SudokuField[9]));
        }
        //sudo = new SudokuField[9][9];
        rows = Arrays.asList(new SudokuRow[9]);
        columns = Arrays.asList(new SudokuColumn[9]);
        boxes = Arrays.asList(new SudokuBox[9]);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudo.get(i).set(j, new SudokuField());
            }
        }
        assignStructures();
    }

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
        SudokuBoard rhs = (SudokuBoard) obj;
        return new EqualsBuilder().append(sudo, rhs.sudo).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 59).append(sudo).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(sudo).toString();
    }

    public void ZbudujTabele() {
        int x, y, i;
        for (int k = 0; k < 9; k += 3) {
            for (int j = 0; j < 9; j += 3) {
                x = j;
                y = k;
                i = ThreadLocalRandom.current().nextInt(1, 9 + 1);
                if (checkBoard(x, y, i)) {
                    set(x, y, i);
                }
            }
        }
    }

    public boolean checkBoard(final int x, final int y, final int value) {

        boolean onbox;
        boolean onrow;
        boolean oncolumn;
        onrow = rows.get(y).verify(value);
        oncolumn = columns.get(x).verify(value);
        int boxnum = 0;
        if (x >= 0 && x < 3 && y >= 0 && y < 3) {
            boxnum = 0;
        }
        if (x > 2 && x < 6 && y >= 0 && y < 3) {
            boxnum = 1;
        }
        if (x > 5 && x < 9 && y >= 0 && y < 3) {
            boxnum = 2;
        }

        if (x >= 0 && x < 3 && y > 2 && y < 6) {
            boxnum = 3;
        }
        if (x > 2 && x < 6 && y > 2 && y < 6) {
            boxnum = 4;
        }
        if (x > 5 && x < 9 && y > 2 && y < 6) {
            boxnum = 5;
        }

        if (x >= 0 && x < 3 && y > 5 && y < 9) {
            boxnum = 6;
        }
        if (x > 2 && x < 6 && y > 5 && y < 9) {
            boxnum = 7;
        }
        if (x > 5 && x < 9 && y > 5 && y < 9) {
            boxnum = 8;
        }
        onbox = boxes.get(boxnum).verify(value);
        // Boxes[boxnum].setFieldValue(insertedBox, 0);
        // System.out.println(onbox+" "+onrow+" "+oncolumn+"   "+(onbox && onrow && oncolumn));
        //onbox = true;
        // oncolumn=true;
        //  onrow=true;
        return onbox && onrow && oncolumn;
    }

    public int get(final int x, final int y) {
        //    System.out.println("GET  x "+x+" y "+y+" value "+Sudo[x][y].getFieldValue());
        return sudo.get(x).get(y).getFieldValue();
    }

    public void fillBoard() {
        SudokuSolver s = new BacktrackingSudokuSolver();
        s.solve(this);
    }

    public void set(final int x, final int y, final int value) {
        sudo.get(x).get(y).setFieldValue(value);
    }

    public void set(final int x, final int y, final String value) throws MyExeptions {
        try {
            int val = Integer.parseInt(value);
            if (val < 1 || val > 9) {
                throw new Exception();
            }
            sudo.get(x).get(y).setFieldValue(val);
        } catch (Exception e) {
            throw new MyExeptions("nie prawidłowo wprowadzone dane");
        }
    }

    public SudokuRow getRow(final int x) {
        return rows.get(x);
    }

    public SudokuColumn getColumn(final int x) {
        return columns.get(x);
    }

    private void assignStructures() {
        for (int i = 0; i < 9; i++) {
            assignRow(i);
            assignColumn(i);
            assignBox(i);
        }
    }

    private void assignRow(final int rowNo) {
        SudokuField[] list = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            list[i] = sudo.get(i).get(rowNo);
        }
        rows.set(rowNo, new SudokuRow(Arrays.asList(list)));
        //rows.set(rowNo, new SudokuRow(sudo.get(rowNo)));
    }

    private void assignColumn(final int columnNo) {
        /*SudokuField[] list = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            list[i] = sudo.get(i).get(columnNo);
        }
        columns.set(columnNo, new SudokuColumn(Arrays.asList(list)));*/
        columns.set(columnNo, new SudokuColumn(sudo.get(columnNo)));
    }

    private void assignBox(final int boxNo) {
        SudokuField[] list = new SudokuField[9];
        int cellNo = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                list[cellNo] = sudo.get(i + ((boxNo % 3)) * 3).get(j + ((boxNo - (boxNo % 3))));
                cellNo++;
            }
        }
        boxes.set(boxNo, new SudokuBox(Arrays.asList(list)));
    }

    @Override
    public SudokuBoard clone() throws CloneNotSupportedException {
        SudokuBoard clone = new SudokuBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                clone.sudo.get(i).set(j, this.sudo.get(i).get(j).clone());
            }
        }
        return clone;
    }

    public void clearFields(final int number) {
        int[] indexesToClear;
        indexesToClear = new int[number];
        int i = 0;
        boolean repeats;
        while (i < number) {
            repeats = false;
            indexesToClear[i] = (int) (Math.random() * 81);
            for (int j = 0; j < number; j++) {
                if (i != j && indexesToClear[i] == indexesToClear[j]) {
                    repeats = true;
                }
            }
            if (!repeats) {
                i++;
            }
        }

        for (int j = 0; j < number; j++) {
            int x, y;
            x = indexesToClear[j] % 9;
            y = indexesToClear[j] / 9;
            set(x, y, 0);
        }
    }

    public void initializeGame(final Difficulty difficulty) {

        ZbudujTabele();
        fillBoard();
        switch (difficulty) {
            case EASY:
                clearFields(56);
                break;
            case MEDIUM:
                clearFields(60);
                break;
            case HARD:
                clearFields(63);
                break;
            default:
                clearFields(80);
                break;
        }
    }
}
