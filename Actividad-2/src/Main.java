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
            System.out.println(" 4 - Marcar completada");
            System.out.println(" 5 - Salir");
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
                    tasks = todoList.getTasks();
                    for (int i = 0; i < tasks.size(); i++) {
                        //i + 1 para evitar mostrar a usuario indice 0
                        System.out.println(i+1 + " - " + tasks.get(i).getTitle() + "( " + tasks.get(i).getCompleted() + " )");
                    }
                    System.out.println();
                    System.out.println(tasks.size() + 1 + " - Volver atras");
                    //Inicializamos numero que ingresa usuario
                    int completedTask;
                    //Verificamos que ingrese un numero o atrapamos excepciones
                    try {
                        completedTask = scanner.nextInt();
                    } catch (Exception e) {
                        System.out.println("Error. Intente nuevamente");
                        break;
                    }
                    //Verificamos que el numero ingresado corresponda a la opcion de salir o a una tarea válida
                    if (completedTask == tasks.size()+ 1) {
                        break;
                    } else if (completedTask <= tasks.size()) {
                        //Inicializamos tarea a actualizar y la actualizamos
                        Task task = tasks.get(completedTask - 1);
                        task.markAsCompleted();
                        break;
                    } else {
                        System.out.println("Indice de tarea no valido.");
                    }

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
