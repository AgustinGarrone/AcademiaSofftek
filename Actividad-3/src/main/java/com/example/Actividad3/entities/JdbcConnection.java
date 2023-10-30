package com.example.Actividad3.entities;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JdbcConnection {
    private Connection connection;

    public JdbcConnection() {
        try {

            String url = "jdbc:mysql://localhost:3306/todoApp";
            String usuario = "root";
            String contraseña = "";

            connection = DriverManager.getConnection(url, usuario, contraseña);
        } catch (SQLException e) {
            throw new RuntimeException("Error al establecer la conexión a la base de datos", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
