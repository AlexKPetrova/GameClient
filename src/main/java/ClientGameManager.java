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

        String userInput;
        System.out.println(CLIENT_PREFIX + "Please enter msg for server:");

        userInput = scanner.nextLine();
        Command command = Command.valueOf(userInput);
        GameData gameData = new GameData(command);
        socket.sendMessageToServer(gameData);

        while (true) {
            gameData = socket.getNextServerMessage();

            switch (gameData.getCommand()) {

                case NEXTTURN:
                    doTurn(gameData.getField(), gameData.getNumber());
                    socket.sendMessageToServer(gameData);
                    break;

                case WIN:
                    System.out.println(gameData.getNumber() + "WIN");
                    break;

                case LOSE:
                    System.out.println("Lose");
                    break;

                default:
                    System.out.println("Ошибка");
                    break;

            }

        }
    }

    private void doTurn(int[][] arr, int number) {
        System.out.println("Закрась " + number + " клеток");
        System.out.println(viewField(arr));
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < number; i++) {
            String s = scanner.nextLine();
            while (s.length() > 3) {
                System.out.println("Кто так координаты пишет... Давай еще раз");
                s = scanner.nextLine();
            }
            String[] coordTurnClient = s.split(" ");
            int x = Integer.parseInt(coordTurnClient[0]);
            int y = Integer.parseInt(coordTurnClient[1]);

            arr[x][y] = 1;
        }
        System.out.println(viewField(arr));
    }


    public String viewField(int[][] arr) {
        StringBuilder s = new StringBuilder();
        s.append(" \n ");
        for (int[] ints : arr) {
            for (int j = 0; j < arr.length; j++) {
                if (j == arr.length - 1) {
                    s.append(ints[j]).append(" \n ");
                } else {
                    s.append(ints[j]).append(" ");
                }
            }
        }
        return s.toString();
    }

}