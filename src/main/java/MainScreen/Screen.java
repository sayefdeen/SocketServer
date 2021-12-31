package MainScreen;

import Models.Students;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Screen {

    private static Screen screen;
    private static ObjectOutputStream out;

    private Scanner input = new Scanner(System.in);

    private Screen(){}

    public static Screen getScreen(Socket clientSocket) throws IOException {
        if(screen == null){
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            screen = new Screen();
        }
        return screen;
    }

    public String mainMessage(){
        System.out.println("Please type one of the following \n" +
                "1- 'register' To register \n" +
                "2- 'singin' To signIn");
        return input.next();
    }

    public Students logIn(String choice){
        System.out.println("Please Enter your userName");
        String userName = input.next();
        System.out.println("Please Enter your password");
        String password = input.next();

        return new Students(userName,password,choice.equals("register"));
    }

    public void choices(){
        System.out.println("Please select from the following \n" +
                "1 To see all your marks" +
                "2 To select one course " +
                "0 to logout");
    }
}
