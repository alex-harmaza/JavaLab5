package by.training.notebook;

import by.training.notebook.bean.Request;
import by.training.notebook.bean.Response;
import by.training.notebook.controller.Controller;
import by.training.notebook.view.View;
import by.training.notebook.view.exception.ViewException;
import by.training.notebook.view.factory.ViewFactory;

import java.util.Scanner;

/**
 * Created by Aliaksandr_Harmaza on 10/5/2016.
 */
public class Main {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        Scanner scanner = new Scanner(System.in);
        ViewFactory viewFactory = new ViewFactory();
        Controller controller = new Controller();

        while (true){
            System.out.println("================================");
            System.out.print("Enter the command: ");
            try {
                CommandEnum command = CommandEnum.getEnum(scanner.nextLine().toUpperCase());
                System.out.println("---------------------------------");
                View view = viewFactory.getView(command);
                Request request = view.createRequest(scanner);
                Response response = controller.doRequest(request);
                System.out.println("---------------------------------");
                view.showResponse(response);
            }
            catch (IllegalArgumentException ex){
                System.out.println("---------------------------------");
                System.out.println("Error: incorrect command");
            }
            catch (ViewException ex) {
                System.out.println("---------------------------------");
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

}
