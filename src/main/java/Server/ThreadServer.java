package Server;

import MainScreen.Screen;
import Models.Students;

import java.io.*;
import java.net.Socket;

public class ThreadServer extends Thread {
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private BufferedReader stringIn;

    ThreadServer(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    public void run(){
        try
        {
            out =  new ObjectOutputStream(clientSocket.getOutputStream());
            in =  new ObjectInputStream(clientSocket.getInputStream());
            stringIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String order = stringIn.readLine();

            order = order.replaceAll("[^a-zA-Z0-9]","");

            choices(order);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void choices(String order) throws Exception {

        switch (order){
            case "register":
            case "singin":
                Students user = (Students) in.readObject();
                Services.Students studentsService = new Services.Students();
                studentsService.add(user);
        }
    }

    private void registerOrLogin(Students user){

    }

}
