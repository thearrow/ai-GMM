public class Datum {
    private Double val;
    private Double[] probs;

    public Datum(String value, int components) {
        this.val = Double.parseDouble(value);
        this.probs = new Double[components];
        for (int i = 0; i < probs.length; i++) {
            this.probs[i] = 0.0;
        }
    }

    public Double val(){
        return this.val;
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
