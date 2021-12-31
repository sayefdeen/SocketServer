package Client;

import MainScreen.Screen;
import Models.Students;

import java.io.*;
import java.net.Socket;

public class Client {

    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private PrintWriter stringOut;


    public void startConnection(String ip, int port) throws Exception{
        clientSocket = new Socket(ip,port);
        stringOut = new PrintWriter(clientSocket.getOutputStream(), true);
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());
        startingPoint();
    }

    private void startingPoint() throws Exception {
        String message = mainMessage();
        Students student =  Screen.getScreen(clientSocket).logIn(message);
        stringOut.println(message);
        out.writeObject(student);
    }

    public String mainMessage() throws Exception{
        return Screen.getScreen(clientSocket).mainMessage();

    }


    public void stopConnection() throws Exception{
        in.close();
        out.close();
        clientSocket.close();
    }
}
