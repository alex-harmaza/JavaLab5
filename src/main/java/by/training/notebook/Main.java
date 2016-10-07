package by.training.notebook;

import by.training.notebook.bean.*;
import by.training.notebook.controller.Controller;

import java.util.Date;

/**
 * Created by Aliaksandr_Harmaza on 10/5/2016.
 */
public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
