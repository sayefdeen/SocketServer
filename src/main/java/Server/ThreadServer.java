package Server;

import DAO.Course;
import DAO.Students;
import Log.Logger;
import Services.CourseService;
import Services.StudentsService;

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
            Logger.getLogger().addLog("Something went Bad!! " + e.getMessage());
        }
    }

    private void choices(String order) {
        try {
            Students user = (Students) in.readObject();
            StudentsService sService = new StudentsService();
            switch (order){
                case "register":
                    sService.add(user);
                    stringOut.println("Please register with your account");
                    Logger.getLogger().addLog("New User has been created " + user.getId());
                    break;
                case "singIn":
                    Students student = new StudentsService().getUser(user.getName());
                    if(student != null){
                        if(sService.checkPass(user.getPassword(),student.getPassword())){
                            stringOut.println("Next");
                            while(clientSocket.isConnected()){
                                String message =  stringIn.readLine();
                                studentChoices(message,student.getId());
                            }

                        }else{
                            Logger.getLogger().addLog("Wrong password/userName");
                            stringOut.println("Wrong password/userName");
                        }
                    }else{
                        Logger.getLogger().addLog("No user with this userName " + user.getName() );
                        stringOut.println("No user with this userName");
                    }
                    break;
            }
        }catch (Exception e){
            Logger.getLogger().addLog("Something went Bad!! " + e.getMessage());
        }

    }

    private void studentChoices(String choice,String userId) throws Exception {
        String message = choice.split(",")[0];
        switch (message){
            case "seeALL":
                ArrayList<Course> course = new CourseService().getCourses(userId);
                Logger.getLogger().addLog("Course for this students is requested " + userId );
                out.writeObject(course);
                break;
            case "select":
                String courseId = choice.split(",")[1];
                Course course1 = (Course) new CourseService().get(courseId);
                Logger.getLogger().addLog("This course is being called " + courseId );
                out.writeObject(course1);
                break;
            case "exit":
                Logger.getLogger().addLog("User Logout");
                stringOut.println("exit");
                break;
        }
    }
}
