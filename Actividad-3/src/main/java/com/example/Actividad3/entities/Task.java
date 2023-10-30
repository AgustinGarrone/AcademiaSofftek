package com.example.Actividad3.entities;


import com.example.Actividad3.repositories.TaskRepository;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Data
@NoArgsConstructor
public class Task {

    private Long id;

    private String title;
    private String description;
    private Boolean completed;
    private Date created;
    private Date until;

    //Constructor de inicializacion de tareas. created va por defecto
    public Task(String title, String description, Boolean completed,  Date until) {
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.created = new Date(System.currentTimeMillis());
        this.until = until;
    }

    //Constructor con ID
    public Task(Long id , String title, String description, Boolean completed,  Date until) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.created = new Date(System.currentTimeMillis());
        this.until = until;
    }
    //Constructor con Date created para traer de la base de datos ese atributo
    public Task(Long id , String title, String description, Boolean completed, Date created,  Date until) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.created = created;
        this.until = until;
    }

}
