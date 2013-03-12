public class Component {
    private Double weight, mean, stdev;

    public Component(Double weight, Double mean, Double stdev) {
        this.weight = weight;
        this.mean = mean;
        this.stdev = stdev;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getMean() {
        return mean;
    }

    public Double getStdev() {
        return stdev;
    }
}
