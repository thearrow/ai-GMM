public class Datum {
    private Double val;
    private Double[] probs;

    public Datum(String value, int components) {
        this.val = Double.parseDouble(value);
        this.probs = new Double[components];
    }

    public Double val(){
        return this.val;
    }

    public Double sumProbs() {
        Double sum = 0.0;
        for (Double prob : probs)
            sum += prob;
        return sum;
    }

    public void setProb(int i, Double val) {
        this.probs[i] = val;
    }

    public Double getProb(int i) {
        return this.probs[i];
    }

    public int numProbs() {
        return this.probs.length;
    }
}