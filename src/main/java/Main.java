import Server.Server;

public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.start(5000);
    }
}
