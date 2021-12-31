package Client;

import DAO.Course;
import MainScreen.Screen;
import DAO.Students;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {

    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private PrintWriter stringOut;
    private BufferedReader stringIn;


    public void startConnection(String ip, int port) throws Exception{
        clientSocket = new Socket(ip,port);
        stringOut = new PrintWriter(clientSocket.getOutputStream(), true);
        stringIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());
        startingPoint();
    }

    private void startingPoint() throws Exception {
        String message = mainMessage();
        Students student =  Screen.getScreen(clientSocket).logIn(message);
        stringOut.println(message);
        out.writeObject(student);
        String nextStep = stringIn.readLine();
        nextSteps(nextStep);
    }


    private String mainMessage() throws Exception{
        return Screen.getScreen(clientSocket).mainMessage();
    }

    private void nextSteps(String nextStep) throws Exception {
        switch (nextStep){
            case "Please register with your account":
            case "No user with this userName" :
                System.out.println("*****Register with your new Account*****");
                startingPoint();
                break;
            case "Wrong password/userName":
                System.out.println("*****Wrong password/userName*****");
                startingPoint();
                break;
            case "Next" :
                NextScreen();
                break;
        }
    }

    private void NextScreen() throws Exception {
        String message = Screen.getScreen(clientSocket).choices();
        if(message.equalsIgnoreCase("select")){
            String courseId = Screen.getScreen(clientSocket).courseId();
            stringOut.println(message+","+courseId);
            printCourse();
        }else{
            stringOut.println(message);
            printCourses();
        }

    }

    private void printCourses() throws Exception{
        ArrayList<Course> courses = (ArrayList<Course>) in.readObject();
        System.out.format("%15s%15s%15s%15s%n", "ID","Name","Section","Result");
        for (Course c : courses) {
            System.out.format("%15s%15s%15s%15s%n", c.getId(),c.getName(),c.getSection(),c.getResult());
        }
    }

    private void printCourse() throws Exception{
        Course course = (Course) in.readObject();
        System.out.format("%15s%15s%15s%15s%15s%15s%15s%n", "ID","Name","Section","Sum","avg","max","min");
        System.out.format("%15s%15s%15s%15s%15s%15s%15s%n", course.getId(),course.getName(),course.getSection(),course.getSum(),course.getAvg(),course.getMin(),course.getMin());
    }


    public void stopConnection() throws Exception{
        in.close();
        out.close();
        clientSocket.close();
    }
}
