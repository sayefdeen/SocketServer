package MainScreen;

import DAO.Students;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Screen {

    private static Screen screen;

    private Scanner input = new Scanner(System.in);

    private Screen(){}

    public static Screen getScreen(Socket clientSocket) {
        if(screen == null){
            screen = new Screen();
        }
        return screen;
    }

    public String mainMessage(){
        System.out.println("Please type one of the following \n" +
                "1- 'register' To register \n" +
                "2- 'singIn' To signIn");
        return input.next();
    }

    public Students logIn(String choice) throws Exception {
        System.out.println("Please Enter your userName");
        String userName = input.next();
        System.out.println("Please Enter your password");
        String password = input.next();

        return new Students(userName,password,choice.equals("register"));
    }

    public String courseId(){
        System.out.println("Please Enter course ID");
        return input.next();
    }


    public String choices(){
        System.out.println("Please select from the following \n" +
                "seeALL To see all your marks\n" +
                "select To select one course\n" +
                "0 to logout");
        return input.next();
    }
}
