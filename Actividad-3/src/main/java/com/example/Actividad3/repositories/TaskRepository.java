package com.example.Actividad3.repositories;

import com.example.Actividad3.entities.JdbcConnection;
import com.example.Actividad3.entities.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    public List<Task> getAllTasks() {
        JdbcConnection jdbcConnection = new JdbcConnection();
        List<Task> allTasks = new ArrayList<>();

        try (Connection conn = jdbcConnection.getConnection()) {
            String sql = "SELECT * FROM Task";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String title = resultSet.getString("title");
                    String description = resultSet.getString("description");
                    Boolean completed = resultSet.getBoolean("completed");
                    Date until = resultSet.getDate("until");

                    Task task = new Task(id, title, description, completed, until);
                    allTasks.add(task);
                }
                System.out.println(stmt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error");
        }

        return allTasks;
    }

    public void addTask(Task task) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        // Obtener una conexión a la base de datos
        try (Connection conn = jdbcConnection.getConnection()) {

            //Insertar la tarea en la base de datos
            String sql = "INSERT INTO Task (title, description, completed, created , until) VALUES (?, ?, ?, ? , ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, task.getTitle());
                stmt.setString(2, task.getDescription());
                stmt.setBoolean(3, task.getCompleted());
                stmt.setDate(4 , new java.sql.Date(task.getCreated().getTime()));
                stmt.setDate(5, new java.sql.Date(task.getUntil().getTime()));
                System.out.println(stmt);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error");
        }
    }

    public boolean deleteTask(Long taskId) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        try (Connection conn = jdbcConnection.getConnection()) {
            String sql = "DELETE FROM Task WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setLong(1, taskId);
                int rowsAffected = stmt.executeUpdate();
                System.out.println(stmt);
                return rowsAffected > 0; // Devuelve true si se eliminó al menos una fila
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error");
            return false; // Devuelve false en caso de error
        }
    }

    public List<Task> getIncompleteTasks() {
        List<Task> incompleteTasks = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        try (Connection conn = jdbcConnection.getConnection()) {
            String sql = "SELECT * FROM Task WHERE completed = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setBoolean(1, false);
                try (ResultSet resultSet = stmt.executeQuery()) {
                    while (resultSet.next()) {
                        Task task = new Task(
                                resultSet.getLong("id"),
                                resultSet.getString("title"),
                                resultSet.getString("description"),
                                resultSet.getBoolean("completed"),
                                resultSet.getDate("created"),
                                resultSet.getDate("until")
                        );
                        incompleteTasks.add(task);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error");
        }

        return incompleteTasks;
    }

    public void updateTask(Boolean completed , Long id) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        try (Connection conn = jdbcConnection.getConnection()) {
            String sql = "UPDATE Task SET completed = ? WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setBoolean(1, completed);
                stmt.setLong(2, id);
                System.out.println(stmt);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error");
        }
    }
}
