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
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class JDBSudokuBoardDao<SudokuBoard> implements Dao<SudokuBoard>, AutoCloseable {

    private static final String SQL_SERIALIZE_OBJECT
            = "INSERT INTO sudoku(name, object) VALUES (?, ?)";
    private static final String SQL_DESERIALIZE_OBJECT
            = "SELECT object FROM sudoku WHERE name = ?";

    final String driver = "com.mysql.jdbc.Driver";
    final String url = "jdbc:mysql://localhost:3306/sudoku";
    final String username = "root";
    final String password = "";

    Connection con;
    String name;

    public String message;
    public static String _message;
    public boolean done = true;

    public JDBSudokuBoardDao(final String name) throws MyExeptions {
        con = getConnection();
        this.name = name;
    }

    Connection getConnection() throws MyExeptions {
        Connection __con = null;
        try {
            Class.forName(driver);
            __con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new MyExeptions(_message);
        }
        return __con;
    }

    @Override
    public SudokuBoard read() throws FileNotFoundException, IOException {
        SudokuBoard temp = null;
        try {
            try {
                PreparedStatement state = con
                        .prepareStatement(SQL_DESERIALIZE_OBJECT);
                state.setString(1, name);
                ResultSet rs = state.executeQuery();
                rs.next();
                byte[] buffer = rs.getBytes(1);
                ObjectInputStream objectIn = null;
                if (buffer != null) {
                    objectIn = new ObjectInputStream(new ByteArrayInputStream(buffer));
                }
                temp = (SudokuBoard) objectIn.readObject();
                rs.close();
                state.close();
            } catch (IOException | ClassNotFoundException | SQLException e) {
                throw new MyExeptions(message);
            }
        } catch (MyExeptions jle) {

        }
        return temp;
    }

    @Override
    public void write(final SudokuBoard obj) throws FileNotFoundException, IOException {
        try {
            try {
                PreparedStatement state = con
                        .prepareStatement(SQL_SERIALIZE_OBJECT);
                state.setString(1, name);
                state.setObject(2, obj);
                state.executeUpdate();
                state.close();
            } catch (SQLException e) {
                done = false;
                throw new MyExeptions(message);
            }
        } catch (MyExeptions jse) {

        }
    }

    @Override
    public void close() throws Exception {
        con.close();
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
