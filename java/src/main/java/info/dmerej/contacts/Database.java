package info.dmerej.contacts;

import java.io.File;
import java.sql.*;
import java.util.Optional;

public class Database {
    private final Connection connection;
    private final int insertedCount = 0;

    public Database(File databaseFile) {
        String databasePath = databaseFile.getPath();
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
        } catch (SQLException e) {
            throw new RuntimeException("Could not create connection: " + e);
        }
        // You'll thank me later ;)
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void migrate() {
        System.out.println("Migrating database ...");
        try {
            Statement statement = connection.createStatement();
            statement.execute("""
                CREATE TABLE contacts(
                id INTEGER PRIMARY KEY,
                name TEXT NOT NULL,
                email TEXT NOT NULL
                )
                """
            );
        } catch (SQLException e) {
            throw new RuntimeException("Could not migrate db: " + e);
        }
        System.out.println("Done migrating database");
    }


    public Optional<Contact> findContactByEmail(String email) {
        String query = "SELECT name, email FROM contacts WHERE email = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                var name = result.getString(1);
                return Optional.of(new Contact(name, email));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error when looking up contacts from db: " + e);
        }
    }

    public void close() {
        if (connection == null) {
            return;
        }

        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Could not close db: " + e);
        }
    }

}
