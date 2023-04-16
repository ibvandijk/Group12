/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Datastorage;

import java.sql.*;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;

/**
 *
 * @author ppthgast
 */
public class DatabaseConnection {

    private Connection connection;

    // The Statement object has been defined as a field because some methods
    // may return a ResultSet object. If so, the statement object may not
    // be closed as you would do when it was a local variable in the query
    // execution method.
    private Statement statement;

    public DatabaseConnection() {
        connection = null;
        statement = null;
    }

    // Opens a connection to the database, so SQL statements can be performed on the
    // database
    public boolean openConnection() {
        boolean result = false;

        if (connection == null) {
            try {
                // This has to remain here so that the jdbc library can use the MSSQL Driver for
                // connecting to the database
                SQLServerDriver driver = new SQLServerDriver();

                // Try to create a connection with the library database
                connection = DriverManager.getConnection(
                        "jdbc:sqlserver://localhost;databaseName=Codecademy;encrypt=true;trustServerCertificate=true;integratedSecurity=true;");

                if (connection != null) {
                    statement = connection.createStatement();
                }

                result = true;
            } catch (SQLException e) {
                System.out.println(e);
                result = false;
            }
        } else {
            // A connection was already initalized.
            result = true;
        }

        return result;
    }

    // Checks whether the connection is open
    public boolean connectionIsOpen() {
        boolean open = false;

        if (connection != null && statement != null) {
            try {
                open = !connection.isClosed() && !statement.isClosed();
            } catch (SQLException e) {
                System.out.println(e);
                open = false;
            }
        }
        // Else, at least one the connection or statement fields is null, so
        // no valid connection.

        return open;
    }

    // Closes the connection to the database
    public void closeConnection() {
        try {
            statement.close();

            // Close the connection
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Executes specifically a select query and returns data from the database as a
    // ResultSet
    public ResultSet executeSQLSelectStatement(String query) {
        ResultSet resultset = null;

        // First, check whether a some query was passed and the connection with
        // the database.
        if (query != null && connectionIsOpen()) {
            // Then, if succeeded, execute the query.
            try {
                resultset = statement.executeQuery(query);
                System.out.println(resultset);
            } catch (SQLException e) {
                System.out.println(e);
                resultset = null;
            }
        }

        return resultset;
    }

    // Executes a specific query on the database, this can be used to create, modify
    // or delete data from the database
    public boolean executeSQLUpdateStatement(String query) {
        boolean result = false;

        if (query != null && connectionIsOpen()) {
            // Then, if succeeded, execute the query.
            try {
                statement.executeUpdate(query);
                result = true;
            } catch (SQLException e) {
                System.out.println(e);
                result = false;
            }
        }

        return result;
    }
}
