import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Double> data = readData("data1.txt");
        System.out.println(data.get(0));
    }

    public static ArrayList<Double> readData(String filePath) {
        ArrayList<Double> data = new ArrayList<Double>();

        //read in data from file
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(Double.parseDouble(line));
            }
        } catch (FileNotFoundException e) {
            System.out.println("error: " + e);
        } catch (IOException e) {
            System.out.println("error: " + e);
        }

        return data;
    }
}
