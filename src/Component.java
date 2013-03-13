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

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setMean(Double mean) {
        this.mean = mean;
    }

    public void setStdev(Double stdev) {
        this.stdev = stdev;
    }
}
