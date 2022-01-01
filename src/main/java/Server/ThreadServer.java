package Server;

import DAO.Course;
import DAO.Students;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ThreadServer extends Thread {
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private BufferedReader stringIn;
    private PrintWriter stringOut;

    ThreadServer(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    public void run(){
        try
        {
            out =  new ObjectOutputStream(clientSocket.getOutputStream());
            in =  new ObjectInputStream(clientSocket.getInputStream());
            stringIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            stringOut = new PrintWriter(clientSocket.getOutputStream(), true);


            while(clientSocket.isConnected()){
                String order = stringIn.readLine();
                order = order.replaceAll("[^a-zA-Z0-9]","");
                choices(order);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void choices(String order) throws Exception {
        Students user = (Students) in.readObject();
        Services.Students sService = new Services.Students();
        switch (order){
            case "register":
                sService.add(user);
                stringOut.println("Please register with your account");
                break;
            case "singIn":
                Students student = new Services.Students().getUser(user.getName());
                if(student != null){
                    if(sService.checkPass(user.getPassword(),student.getPassword())){
                        stringOut.println("Next");
                        while(clientSocket.isConnected()){
                            String message =  stringIn.readLine();
                            studentChoices(message,student.getId());
                        }

                    }else{
                        stringOut.println("Wrong password/userName");
                    }
                }else{
                    stringOut.println("No user with this userName");
                }
                break;
        }
    }

    private void studentChoices(String choice,String userId) throws Exception {
        String message = choice.split(",")[0];
        switch (message){
            case "seeALL":
                ArrayList<Course> course = new Services.Course().getCourses(userId);
                out.writeObject(course);
                break;
            case "select":
                String courseId = choice.split(",")[1];
                Course course1 = (Course) new Services.Course().get(courseId);
                out.writeObject(course1);
                break;
            case "exit":
                stringOut.println("exit");
                break;
        }
    }

    private void closeClient(){

    }
}
