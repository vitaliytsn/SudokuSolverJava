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
public interface Dao<T> extends AutoCloseable{
    public void write(T obj) throws FileNotFoundException, IOException;
    public T read() throws FileNotFoundException, IOException;
}
