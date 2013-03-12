import java.util.ArrayList;

public class Mixture {
    private ArrayList<Component> components;
    private ArrayList<Double> data;

    public Mixture(ArrayList<Double> data, int components) {
        this.data = data;
        this.components = new ArrayList<Component>();
        Double mean = getMean(data);
        Double stdev = getStdev(data, mean);
        //random initialization of component parameters
        for (int i = 0; i < components; i++) {
            Component c = new Component(1.0/Double.parseDouble(components + ""),mean+(mean*(Math.random()-0.5)), stdev+(stdev*(Math.random()-0.5)));
            this.components.add(c);
        }

    }

    private Double getStdev(ArrayList<Double> data, Double mean) {
        Double stdev = 0.0;
        for (Double d : data)
            stdev += Math.pow(d-mean,2);

        stdev /= data.size();
        stdev = Math.sqrt(stdev);
        return stdev;
    }

    private Double getMean(ArrayList<Double> data) {
        Double mean = 0.0;
        for (Double d : data )
            mean += d;

        mean /= data.size();
        return mean;
    }

}
