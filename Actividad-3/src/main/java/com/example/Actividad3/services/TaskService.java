package com.example.Actividad3.services;

import com.example.Actividad3.entities.Task;
import com.example.Actividad3.repositories.TaskRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TaskService {

    TaskRepository taskRepository = new TaskRepository();

    public TaskService() {
    }

    public List<Task> getAllTasks () {
        List<Task> tasks = taskRepository.getAllTasks();
        for (Task task : tasks) {
            System.out.println("-------");
            System.out.println("Id: " + task.getId());
            System.out.println("Nombre: " + task.getTitle());
            System.out.println(task.getDescription());
            System.out.println("Completada: " +task.getCompleted());
            System.out.println("Expiración: " +task.getUntil());
        }
        return tasks;
    }

    public void addTask() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Introduce el título de la tarea:");
            String title = scanner.nextLine();
            //verificamos que el titulo sea correcto
            if (title.equals("")) {
                throw new IllegalArgumentException("El título no puede estar vacío.");
            }
            System.out.println("Introduce la descripción de la tarea:");
            String description = scanner.nextLine();
            if (description.equals("")) {
                throw new IllegalArgumentException("La descripción no puede estar vacía.");
            }
            System.out.println("Ingrese la fecha límite de la tarea (dd/MM/yyyy):");
            String fechaLimiteStr = scanner.nextLine();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaLimite = dateFormat.parse(fechaLimiteStr);

            // Convierte a sql date
            java.sql.Date sqlFechaLimite = new java.sql.Date(fechaLimite.getTime());
            Task newTask = new Task(title , description , false , sqlFechaLimite);
            // Llama al método addTask con la fecha convertida
            taskRepository.addTask(newTask);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println("Formato de fecha inválido. Utilice dd/MM/yyyy.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public boolean deleteTask() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce el número de la tarea a eliminar:");
        List<Task> tasks = taskRepository.getAllTasks(); // Obtener todas las tareas desde la base de datos

        if (!tasks.isEmpty()) {
            for (Task task : tasks) {
                System.out.println(task.getId() + " - " + task.getTitle());
            }

            System.out.println("0 - Volver atrás");

            int deletedTask;
            try {
                deletedTask = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Error. Intente nuevamente");
                return false;
            }

            if (deletedTask == 0) {
                return false;
            } else {
                // Encuentra la tarea con el ID ingresado y elimínala de la base de datos
                Task taskToDelete = tasks.stream()
                        .filter(task -> task.getId() == deletedTask)
                        .findFirst()
                        .orElse(null);

                if (taskToDelete != null) {
                    // Eliminar la tarea de la base de datos
                    if (taskRepository.deleteTask(taskToDelete.getId())) {
                        // Actualiza la lista de tareas
                        tasks.remove(taskToDelete);
                        return true;
                    }
                }
            }
        } else {
            System.out.println("No hay tareas para eliminar.");
        }
        return false;
    }

    public void markAsCompleted() {
        List<Task> incompleteTasks = taskRepository.getIncompleteTasks();

        if (incompleteTasks.isEmpty()) {
            System.out.println("No hay tareas incompletas para marcar como completadas.");
            return;
        }

        System.out.println("Tareas incompletas:");
        for (Task task : incompleteTasks) {
            System.out.println("ID: " + task.getId() + ", Título: " + task.getTitle());
        }
        System.out.println("0 - Salir");
        System.out.println("Ingrese el ID de la tarea que desea marcar como completada:");
        Scanner scanner = new Scanner(System.in);
        int taskId = scanner.nextInt();
        //Si el usuario no presionó salir
        if (taskId != 0) {

            Task taskToComplete = incompleteTasks.stream()
                    .filter(task -> task.getId() == taskId)
                    .findFirst()
                    .orElse(null);

            if (taskToComplete != null) {
                taskRepository.updateTask(true, taskToComplete.getId());
                System.out.println("La tarea con ID " + taskToComplete.getId() + " ha sido marcada como completada.");
            } else {
                System.out.println("ID de tarea no válido o tarea no encontrada.");
            }
        }
    }






}
