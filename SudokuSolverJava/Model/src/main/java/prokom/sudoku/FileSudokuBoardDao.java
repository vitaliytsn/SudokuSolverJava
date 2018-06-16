/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prokom.sudoku;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vitalii
 */
public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    FileInputStream fis;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    FileOutputStream fos;
    String nazwaPliku;

    public FileSudokuBoardDao(final String nazwaPliku) throws FileNotFoundException, IOException {

        this.nazwaPliku = nazwaPliku;

    }

    @Override
    public void close() throws Exception {
        if (fis != null) {
            fis.close();
            ois.close();
        }
        if (fos != null) {
            fos.close();
            oos.close();
        }
        System.out.println("Nastapilo wywolanie metody finalize()");
    }

    @Override
    public SudokuBoard read() throws FileNotFoundException, IOException {
        
        fis = new FileInputStream(nazwaPliku);
        ois = new ObjectInputStream(fis);
        SudokuBoard sb;
        try {
            sb = (SudokuBoard) ois.readObject();
            return sb;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileSudokuBoardDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void write(final SudokuBoard obj) throws FileNotFoundException, IOException {
        fos = new FileOutputStream(nazwaPliku);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            close();
        } finally {
            super.finalize();
        }
    }
}
