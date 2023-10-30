package com.example.Actividad3;

import com.example.Actividad3.entities.Task;
import com.example.Actividad3.services.TaskService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		Main main = new Main();
		main.menu();
	}

	public void menu() {
		boolean running = true;
		System.out.println("Bienvenido.");
		TaskService taskService = new TaskService();
		while (running) {
			System.out.println();
			System.out.println(" 1 - Listar Tareas");
			System.out.println(" 2 - Agregar Tarea");
			System.out.println(" 3 - Eliminar Tarea");
			System.out.println(" 4 - Marcar completada");
			System.out.println(" 5 - Salir");
			Scanner scanner = new Scanner(System.in);
			int userOption = scanner.nextInt();
			switch (userOption) {
				case 1:
					List<Task> tasks = taskService.getAllTasks();

					break;
				case 2:
					taskService.addTask();
					break;
				case 3:
					taskService.deleteTask();
					break;
				case 4:
					taskService.markAsCompleted();
					break;
				case 5:
					running = false;
					break;
				default:
					System.out.println("Opción no válida. Introduce una opción válida.");
			}
		}
	}

}
