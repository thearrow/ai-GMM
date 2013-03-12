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

    public void setProb(int i, Double val) {
        this.probs[i] = val;
    }
}
