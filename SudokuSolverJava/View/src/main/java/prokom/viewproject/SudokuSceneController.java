/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prokom.viewproject;

import java.net.URL;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import prokom.sudoku.Difficulty;
import prokom.sudoku.SudokuBoard;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import prokom.sudoku.Dao;
import java.util.Locale;
import prokom.sudoku.SudokuBoardDaoFactory;
import prokom.sudoku.MyExeptions;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import prokom.sudoku.JDBSudokuBoardDao;

/**
 * FXML Controller class
 *
 * @author Cuba
 */
public class SudokuSceneController implements Initializable {

    private final Locale locale1;
    private final Locale locale2;
    private ResourceBundle bundle;
    private SudokuBoard sb;
    private SudokuBoard sb2;
    private List<List<TextField>> values;
    @FXML
    private Button medium_button;
    @FXML
    private Button hard_button;
    @FXML
    private Button easy_button;
    @FXML
    private Label label;
    @FXML
    private Button checkButton;
    @FXML
    private Button ImportButton;
    @FXML
    private Button ExportButton;
    @FXML
    private GridPane sudokuPane;
    @FXML
    private Button LanguageButton;

    @FXML
    private Button ExportButtonDB;

    @FXML
    private Button ImportButtonDB;

    private List<Integer> propertyList = new ArrayList<>();
    private List<Integer> propertyList2 = new ArrayList<>();

    public SudokuSceneController() {
        locale1 = new Locale.Builder().setLanguage("pl").setRegion("PL").build();
        locale2 = new Locale.Builder().setLanguage("en").setRegion("EN").build();
        bundle = ResourceBundle.getBundle("bundles.lang", locale1);
    }

    //  Handle Methods
    @FXML
    private void handleEasyMode(ActionEvent event) {
        sb = new SudokuBoard();
        System.out.println("Selected: Easy Mode");
        Difficulty diff = Difficulty.EASY;
        sb.initializeGame(diff);
        try {
            sb2 = sb.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(SudokuSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bindFields();
        normalizeButtons();
    }

    @FXML
    private void handleMediumMode(ActionEvent event) {
        sb = new SudokuBoard();
        System.out.println("Selected: Medium Mode");
        Difficulty diff = Difficulty.MEDIUM;
        sb.initializeGame(diff);
        try {
            sb2 = sb.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(SudokuSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bindFields();
        normalizeButtons();
    }

    @FXML
    private void handleHardMode(ActionEvent event) {
        sb = new SudokuBoard();
        System.out.println("Selected: Hard Mode");
        Difficulty diff = Difficulty.HARD;
        sb.initializeGame(diff);
        try {
            sb2 = sb.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(SudokuSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bindFields();
        normalizeButtons();
    }

    @FXML
    private void handleCheckBoard(ActionEvent event) {
        if (updateSudo()) {
            message((String) bundle.getObject("Message_Win"));
        } else {
            message((String) bundle.getObject("Message_Fail"));
        }
    }

    @FXML
    private void handleImport(ActionEvent event) {
        try {
            reading();
            bindFields();
        } catch (Exception ex) {
            Logger.getLogger(SudokuSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleImportDB(ActionEvent event) {
        try {
            readingDB();
            bindFields();
        } catch (Exception ex) {
            Logger.getLogger(SudokuSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleExportDB(ActionEvent event) {
        try {
            writingDB();
        } catch (Exception ex) {
            Logger.getLogger(SudokuSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleExport(ActionEvent event) {
        try {
            writing();
        } catch (Exception ex) {
            Logger.getLogger(SudokuSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleChangeLang(ActionEvent event) {
        if (bundle.getLocale().getCountry() == "PL") {
            bundle = ResourceBundle.getBundle("bundles.lang_en_GB", locale2);
        } else {
            bundle = ResourceBundle.getBundle("bundles.lang_pl_PL", locale1);
        }
        normalizeButtons();
    }

    private void bindFields() {
        values = Arrays.asList((List<TextField>[]) new List[9]);

        for (int i = 0; i < 9; i++) {
            values.set(i, Arrays.asList(new TextField[9]));
        }

        sudokuPane.getChildren().clear();
        propertyList.clear();
        propertyList2.clear();

        for (int i = 0; i < 81; i++) {
            int x = i % 9;
            int y = i / 9;
            propertyList.add(sb.get(x, y));
            propertyList2.add(sb2.get(x, y));
            values.get(x).set(y, new TextField(propertyList.get(i).toString()));
            if (propertyList2.get(i)
                    != 0) {
                values.get(x).get(y).setEditable(false);
                values.get(x).get(y).getStyleClass().add("custom");
            } else {
                values.get(x).get(y).getStyleClass().add("def");
                if (propertyList.get(i) == 0) {
                    values.get(x).get(y).setText("");
                }
                values.get(x).get(y).setOnKeyPressed(eventHandler);

            }

            sudokuPane.add(values.get(x).get(y), x, y);
        }
    }

    final EventHandler<javafx.scene.input.KeyEvent> eventHandler = new EventHandler<javafx.scene.input.KeyEvent>() {
        @Override
        public void handle(javafx.scene.input.KeyEvent event) {
            int jj = 0, ii = 0;
            // String s =event.getCode().getName().toString();
            //      message(event.getCode().getName().toString());
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (values.get(j).get(i).toString().equals(event.getSource().toString())) {
                        jj = j;
                        ii = i;
                        values.get(j).get(i).setText(event.getCode().getName());
                    }
                    //   break;
                }
            }
            try {
                //  message(values.get(ii).get(jj).getText());
                checkFields(jj, ii);
            } catch (MyExeptions ex) {
                Logger.getLogger(SudokuSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    public void normalizeButtons() {
        checkButton.setText((String) bundle.getObject("Button_Check"));
        ImportButton.setText((String) bundle.getObject("Button_Import"));
        ExportButton.setText((String) bundle.getObject("Button_Export"));
        easy_button.setText((String) bundle.getObject("Difficulty_Easy"));
        medium_button.setText((String) bundle.getObject("Difficulty_Medium"));
        hard_button.setText((String) bundle.getObject("Difficulty_Hard"));
        LanguageButton.setText((String) bundle.getObject("Button_Language"));
        label.setText((String) bundle.getObject("Label"));
    }

    public void message(String text) {
        final Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Text t = new Text(text);
        Button btnok = new Button("Ok.");
        VBox vbox = new VBox(t, btnok);
        btnok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialogStage.close();
            }
        });
        vbox.setAlignment(Pos.CENTER);
        dialogStage.setScene(new Scene(vbox));
        dialogStage.show();
    }

    public void checkFields(final int j, final int i) throws MyExeptions {
        try {
            if (!values.get(j).get(i).getText().equals("")) {
                sb.set(j, i, values.get(j).get(i).getText());
            }
            values.get(j).get(i).setText("");
        } catch (MyExeptions mye) {
            message(mye.getMessage() + " Czyli coś odjebałeś "
                    + String.valueOf(j) + " " + String.valueOf(i));
            values.get(j).set(i, new TextField(""));
            values.get(j).get(i).getStyleClass().add("def");
            values.get(j).get(i).setOnKeyPressed(eventHandler);

            sudokuPane.add(values.get(j).get(i), j, i);
            System.out.println(mye.toString());
        }
    }

    public void reading() throws Exception {
        try (Dao<SudokuBoard> fsbd1 = SudokuBoardDaoFactory.getFileDao("dane.txt")) {
            sb = (SudokuBoard) fsbd1.read();
        }
        try (Dao<SudokuBoard> fsbd1 = SudokuBoardDaoFactory.getFileDao("dane2.txt")) {
            sb2 = (SudokuBoard) fsbd1.read();

        }
        bindFields();
    }

    public void writing() throws Exception {
        try (Dao<SudokuBoard> fsbd = SudokuBoardDaoFactory.getFileDao("dane.txt")) {
            fsbd.write(sb);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try (Dao<SudokuBoard> fsbd = SudokuBoardDaoFactory.getFileDao("dane2.txt")) {
            fsbd.write(sb2);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void readingDB() throws Exception {
        try (Dao<SudokuBoard> fsbd = new JDBSudokuBoardDao("firstdb")) {
            sb = (SudokuBoard) fsbd.read();
        }
        try (Dao<SudokuBoard> fsbd = new JDBSudokuBoardDao("seconddb")) {
            sb2 = (SudokuBoard) fsbd.read();
        }
        bindFields();
    }

    public void writingDB() throws Exception {
        try (Dao<SudokuBoard> fsbd = new JDBSudokuBoardDao("firstdb")) {
            fsbd.write(sb);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
         try (Dao<SudokuBoard> fsbd = new JDBSudokuBoardDao("seconddb")) {
            fsbd.write(sb2);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private boolean updateSudo() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int val = Integer.parseInt(values.get(i).get(j).getText());
                if (!sb.checkBoard(i, j, val)) {
                    return false;
                }
                sb.set(i, j, val);
            }
        }

        return true;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
