/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prokom.sudoku;

/**
 *
 * @author Vitalii
 */
public class MyExeptions extends Exception {
    public MyExeptions() { }

      // Constructor that accepts a message
      public MyExeptions(final String message) {
         super(message);
      }
}
