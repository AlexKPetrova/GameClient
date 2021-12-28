import java.io.IOException;
import java.util.Scanner;

public class ClientGameManager {

    private final ClientSocketManager socket;
    private final Scanner scanner;
    private final String CLIENT_PREFIX = "Client reporting. ";

    public ClientGameManager(ClientSocketManager socket) {
        this.socket = socket;
        scanner = new Scanner(System.in);
    }

    public void startGame() throws IOException, ClassNotFoundException {
        while (true) {
            String userInput;
            System.out.println(CLIENT_PREFIX + "Please enter msg for server:");

            userInput = scanner.nextLine();
            Command command = Command.valueOf(userInput);
            socket.sendMessageToServer(command);

            GameData gameData = socket.getNextServerMessage();
            System.out.println(gameData);
        }
    }

    private String parseServerMessage(String nextServerMessage) {
        String[] data = nextServerMessage.split(";");
        Command command = Command.valueOf(data[0]);

        switch (command) {

            case NEXTTURN:
                return doTurnAndGetNextMessage(data[1], data[2]);
            case WIN:
                System.out.println(data[3]);
                return null;
            default:
                return null;
        }
    }

    private String doTurnAndGetNextMessage(String field, String number) {
        int numberInt = Integer.parseInt(number);
        int[][] fieldAsArray = Utils.getArrayFromString(field);
        System.out.println(field);
        System.out.println("Give me index of turn csv style, number is " + numberInt);
        String userInput = scanner.nextLine();
        String[] split = userInput.split(",");
        fieldAsArray[Integer.parseInt(split[0])][Integer.parseInt(split[1])] = 1;

        return "NEXTTURN;" + Utils.arrayToString(fieldAsArray) + "; ; ";
    }
}