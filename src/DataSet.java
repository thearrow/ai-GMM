import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DataSet implements Iterable{
    private ArrayList<Datum> data;
    private Double stdev, mean;
    private int components;

    public DataSet(String fileName, int components) {
        this.components = components;
        this.data = new ArrayList<Datum>();
        //read in data from file
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                this.data.add(new Datum(line, components));
            }
        } catch (FileNotFoundException e) {
            System.out.println("error: " + e);
        } catch (IOException e) {
            System.out.println("error: " + e);
        }
    }

    public Double getStdev() {
        Double mean = this.getMean();
        Double stdev = 0.0;
        for (Datum d : this.data)
            stdev += Math.pow(d.val()-mean,2);

        stdev /= this.data.size();
        stdev = Math.sqrt(stdev);
        return stdev;
    }

    public Double getMean() {
        Double mean = 0.0;
        for (Datum d : this.data )
            mean += d.val();

        mean /= this.data.size();
        return mean;
    }

    public int components() {
        return this.components;
    }

    public void normalizeProbs() {
        for (Datum d : this.data) {
            Double sum = d.sumProbs();
            for (int i = 0; i < d.numProbs(); i++) {
                d.setProb(i,d.getProb(i)/sum);
            }
        }
    }

    public Double nI(int i) {
        Double sum = 0.0;
        for (Datum d : this.data) {
            sum += d.getProb(i);
        }
        return sum;
    }

    @Override
    public Iterator iterator() {
        return this.data.iterator();
    }
}
