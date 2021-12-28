
import java.io.*;
import java.net.Socket;

public class ClientSocketManager {

    private Socket clientSocket;

    public void setUpConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
    }

    public void sendMessageToServer(GameData gameData) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        objectOutputStream.writeObject(gameData);
    }

    public GameData getNextServerMessage() throws IOException, ClassNotFoundException {
        //десериализация
        ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
        return (GameData) objectInputStream.readObject();
    }

    public void close() throws IOException {
        clientSocket.close();
    }

}