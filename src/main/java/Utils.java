
public class Utils {

    public static String arrayToString(int [][] array){

        StringBuilder stringBuilder = new StringBuilder("");

        for (int i = 0; i <array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                stringBuilder.append(array[i][j]);
                if (j != array.length - 1){
                    stringBuilder.append(",");
                }
            }
            stringBuilder.append("||||");
        }
        return stringBuilder.toString();
    }

    public static int[][] getArrayFromString(String data){
        String[] rows = data.split("||||");
        int[][] result = new int[rows.length][rows.length];
        for (int i = 0; i < rows.length; i++) {

            String[] columns = rows[i].split(",");
            for (int j = 0; j < columns.length; j++) {
                result[i][j] = Integer.parseInt(columns[j]);
            }
        }
        return result;
    }
}
