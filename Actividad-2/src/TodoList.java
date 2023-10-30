import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TodoList {

    private List<Task> tasks;


    public TodoList() {
        tasks = new ArrayList<>();
        Date fechaActual = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        // Agrega un día al calendario
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        // Convierte el calendario de nuevo a una fecha
        Date oneMoreDay = calendar.getTime();

        //Carga de tareas por defecto
        Task defaultTask1 = new Task( "Comprar Dominio" , "Comprar dominio para nuestro sitio web" ,   true , oneMoreDay );
        Task defaultTask2 = new Task( "Comprar Hosting" , "Comprar hosting para nuestro sitio web" ,   false ,oneMoreDay );
        Task defaultTask3 = new Task( "Apuntar DNS" , "Apuntar dns de dominio al servidor" ,   false ,oneMoreDay);
        tasks.add(defaultTask1);
        tasks.add(defaultTask2);
        tasks.add(defaultTask3);
    }

    public List<Task> addTask () {
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
            Task task = new Task(title , description , false , fechaLimite);
            tasks.add(task);
        }
       catch (IllegalArgumentException e) {
           System.out.println(e.getMessage());
       }
       catch (ParseException e) {
            System.out.println("Formato de fecha inválido. Utilice dd/MM/yyyy.");
        } catch (Exception e) {
           System.out.println("Error : " + e.getMessage());
       }

        return tasks;
    }


    public List<Task> getTasks () {
        return tasks;
    }

    public boolean  deleteTask () {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce el numero de la tarea a eliminar:");
        List<Task> tasks = getTasks();
        if (tasks.size() > 0) {
            for (int i = 0; i < tasks.size(); i++) {
                //i + 1 para evitar mostrar a usuario indice 0
                System.out.println(i+1 + " - " + tasks.get(i).getTitle());
            }
            System.out.println();
            System.out.println(tasks.size() + 1 + " - Volver atras");
            //Inicializamos numero que ingresa usuario
            int deletedTask;
            //Verificamos que ingrese un numero o atrapamos excepciones
            try {
                deletedTask = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Error. Intente nuevamente");
                return false;
            }
            //Verificamos que el numero ingresado corresponda a la opcion de salir o a una tarea válida
            if (deletedTask == tasks.size()+ 1) {
                return false;
            } else if (deletedTask <= tasks.size()) {
                tasks.remove(deletedTask - 1);
                //Actualizamos las tareas de la to do list.
                this.tasks = tasks;
                return true;
            }
        } else {
            System.out.println("No hay tareas para eliminar.");
        }
        return false;
    }
}
