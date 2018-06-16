package prokom.viewproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import prokom.sudoku.*;

public class FXMLController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleEasyMode(ActionEvent event) {
        System.out.println("Selected: Easy Mode");
        SudokuBoard sb = new SudokuBoard();
        Difficulty diff = Difficulty.EASY;
        sb.initializeGame(diff);
    }
    
    @FXML
    private void handleMediumMode(ActionEvent event) {
        System.out.println("Selected: Medium Mode");
        SudokuBoard sb = new SudokuBoard();
        Difficulty diff = Difficulty.MEDIUM;
        sb.initializeGame(diff);
    }
    
    @FXML
    private void handleHardMode(ActionEvent event) {
        System.out.println("Selected: Hard Mode");
        SudokuBoard sb = new SudokuBoard();
        Difficulty diff = Difficulty.HARD;
        sb.initializeGame(diff);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
