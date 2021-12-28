import java.io.IOException;

public class Launcher {

    public static final int PORT = 1024;
    public static final String IP = "127.0.0.1";

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ClientSocketManager clientSocket = new ClientSocketManager();
        clientSocket.setUpConnection(IP, PORT);
        ClientGameManager gameManager = new ClientGameManager(clientSocket);

            gameManager.startGame();

        /* COMMAND;FIELD;NUMBER;MESSAGE
         1. Сервер: Создай поле, сделай первый ход, сгенерь номер, отдай поле и номер игроку
         2.  Игрок: принять поле и число,вывести поле юезру,  сделать ход, вывести поле после хода, отправить серверу.
         3. Сервер: принять поле, проверить выигрышь, если никто не выиграл:
              3а.сделать ход, проверить выигрышь, если никто не выиграл отдать игроку поле и новое число
              если после хода кто то выиграл отдать игроку информ. сообщение
              3б. если кто то выиграл, отдать игроку сообщение.
         */

    }
}