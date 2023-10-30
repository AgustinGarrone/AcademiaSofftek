import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.menu();
    }

    public void menu() {
        boolean running = true;
        System.out.println("Bienvenido.");
        TodoList todoList = new TodoList();
        while (running) {
            System.out.println();
            System.out.println(" 1 - Listar Tareas");
            System.out.println(" 2 - Agregar Tarea");
            System.out.println(" 3 - Eliminar Tarea");
            System.out.println(" 4 - Salir");
            Scanner scanner = new Scanner(System.in);
            int userOption = scanner.nextInt();
            switch (userOption) {
                case 1:
                    List<Task> tasks = todoList.getTasks();
                    for (Task task : tasks) {
                        System.out.println("-------");
                        System.out.println("Nombre: " + task.getTitle());
                        System.out.println(task.getDescription());
                        System.out.println("Completada: " +task.getCompleted());
                        System.out.println("Expiración: " +task.getUntil());
                    }
                    break;
                case 2:
                    todoList.addTask();
                    break;
                case 3:
                    todoList.deleteTask();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Opción no válida. Introduce una opción válida.");
            }
        }
    }
}
