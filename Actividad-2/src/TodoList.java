import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
        Task defaultTask1 = new Task( "Comprar Dominio" , "Comprar dominio para nuestro sitio web" ,   true , oneMoreDay );
        Task defaultTask2 = new Task( "Comprar Hosting" , "Comprar hosting para nuestro sitio web" ,   false ,oneMoreDay );
        Task defaultTask3 = new Task( "Apuntar DNS" , "Apuntar dns de dominio al servidor" ,   false ,oneMoreDay);
        tasks.add(defaultTask1);
        tasks.add(defaultTask2);
        tasks.add(defaultTask3);
    }

    public List<Task> addTask (Task task) {
        tasks.add(task);
        return tasks;
    }

    public boolean deleteTask (Task task) {
        int deleteElementIndex = tasks.indexOf(task);
        tasks.remove(deleteElementIndex);
        return true;
    }

}
