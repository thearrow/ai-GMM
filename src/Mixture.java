import org.apache.commons.math3.distribution.NormalDistribution;

public class Mixture {
    private Component[] components;
    private DataSet data;

    public Mixture(DataSet data) {
        this.data = data;
        this.components = new Component[this.data.components()];
        Double mean = this.data.getMean();
        Double stdev = this.data.getStdev();
        //random initialization of component parameters
        for (int i = 0; i < this.data.components(); i++) {
            Component c = new Component(1.0/Double.parseDouble(this.data.components() + ""),mean+(mean*(Math.random()-0.5)), stdev+(stdev*(Math.random()-0.5)));
            this.components[i] = c;
        }
    }

    public void Expectation() {
        for (int i = 0; i < this.components.length; i++) {
            NormalDistribution dist = new NormalDistribution(this.components[i].getMean(), this.components[i].getStdev());
            for (Object d : this.data) {
                Datum dat = (Datum)d;
                Double prob = dist.cumulativeProbability(dat.val()) * this.components[i].getWeight();
                dat.setProb(i,prob);
            }
        }
    }



}
