package Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadServer extends Thread {
    private Socket clientSocket;

    ThreadServer(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    public void run(){
        try(PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true); BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
          String greeting = in.readLine();

          if("hello server".equalsIgnoreCase(greeting)){
              System.out.println("WOWOOW");
              out.println("Hello Client");
          }else{
              out.println("Nothing");
          }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
