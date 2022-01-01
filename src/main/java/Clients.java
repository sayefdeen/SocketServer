import Client.Client;

public class Clients {
    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.startConnection("127.0.0.1",5000);
    }
}
