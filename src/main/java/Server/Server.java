package Server;

import MainScreen.Screen;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;

    public void start(int port) throws Exception {
        serverSocket = new ServerSocket(port);

        while(true){
            clientSocket = serverSocket.accept();
            new ThreadServer(clientSocket).start();
        }
    }
}
