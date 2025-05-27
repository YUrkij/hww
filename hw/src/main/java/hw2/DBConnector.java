package hw2;

import hw2.BDDataClasses.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

class DBConnector {
    private static DBConnector instance = null;

    private static String URL = "jdbc:mysql://127.0.0.1/test";
    private static String USERNAME = "root";
    private static String PASSWORD = "MySQL";


    public static DBConnector getInstance() {
        if (instance == null){
            instance = new DBConnector();
        }
        return instance;
    }

    public static void changeConnection(String url, String username, String password){
        if (instance == null) {
            URL = url;
            USERNAME = username;
            PASSWORD = password;
        }
    }

    /**
     * Метод для проверки подключения
     */
    private DBConnector() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

           try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)){
                System.out.println("Connection sucсess!");
                createTables(connection.createStatement());
                System.out.println("Taels created!");
            }

        } catch (Exception ex){
            System.out.println(ex);
        }
    }


    /**
     * Метод для создания таблиц.
     * Возможно стоит вынести в интерфейс.
     * @param statement - подключение к БД
     */
    private void createTables(Statement statement) throws SQLException {
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Departments (" +
                "Id INT PRIMARY KEY AUTO_INCREMENT, " +
                "Name NVARCHAR(50) NOT NULL)");/*Не получилось нормально вставить удалённый ключ в Groups*/

        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Groups (" +
                "Id INT PRIMARY KEY AUTO_INCREMENT, " +
                "Name NVARCHAR(10) NOT NULL UNIQUE CHECK (Name <> ''), " +
                "Year INT NOT NULL CHECK (Year BETWEEN 1 AND 5), " +
                "DepartmentId INT NOT NULL, " +
                "FOREIGN KEY (DepartmentId) REFERENCES Departments(Id))");

        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Subjects (" +
                "Id INT PRIMARY KEY AUTO_INCREMENT, " +
                "Name NVARCHAR(100) NOT NULL UNIQUE CHECK (Name <> ''))");

        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Teachers (" +
                "Id INT PRIMARY KEY AUTO_INCREMENT, " +
                "Name NVARCHAR(50) NOT NULL CHECK (Name <> '')," +
                "Surname NVARCHAR(50) NOT NULL CHECK (Surname <> ''), " +
                "Salary DECIMAL(10,2) NOT NULL CHECK (Salary > 0)");
    }

    public void insert(Department data){
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)){
            System.out.println("Connection sucсess!");
            String sqlQuery = "INSERT INTO Departments (Name) VALUES (?))";
            try (PreparedStatement ps = connection.prepareStatement(sqlQuery)){
                ps.setString(1, data.getName());
                ps.executeUpdate();
            }
        } catch (Exception ex){
            System.out.println(ex);
        }
    }

    public void insert(Group data){
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)){
            System.out.println("Connection sucсess!");
            String sqlQuery = "INSERT INTO Groups (Name, Year, DepartmentId) VALUES (?, ?, ?))";
            try (PreparedStatement ps = connection.prepareStatement(sqlQuery)){
                ps.setString(1, data.getName());
                ps.setInt(2, data.getYear());
                ps.setInt(3, data.getDepartmentId());
                ps.executeUpdate();
            }
        } catch (Exception ex){
            System.out.println(ex);
        }
    }

    public void insert(Subject data){
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)){
            System.out.println("Connection sucсess!");
            String sqlQuery = "INSERT INTO Subjects (Name, Surname, Salary) VALUES (?))";
            try (PreparedStatement ps = connection.prepareStatement(sqlQuery)){
                ps.setString(1, data.getName());
                ps.executeUpdate();
            }
        } catch (Exception ex){
            System.out.println(ex);
        }
    }

    public void insert(Teacher data){
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)){
            System.out.println("Connection sucсess!");
            String sqlQuery = "INSERT INTO Subjects (Name, Surname, Salary) VALUES (?, ?, ?))";
            try (PreparedStatement ps = connection.prepareStatement(sqlQuery)){
                ps.setString(1, data.getName());
                ps.setString(2, data.getSurname());
                ps.setBigDecimal(3, data.getSalary());
                ps.executeUpdate();
            }
        } catch (Exception ex){
            System.out.println(ex);
        }
    }

}
