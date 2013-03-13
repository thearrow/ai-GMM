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
        this.data.normalizeProbs();
    }

    public void Maximization() {
        Double newMean = 0.0;
        Double newStdev = 0.0;
        for (int i = 0; i < this.components.length; i++) {
            for (int j = 0; j < this.data.size(); j++) {
                newMean += this.data.get(j).getProb(i)*this.data.get(j).val();
            }
            newMean /= this.data.nI(i);
            this.components[i].setMean(newMean);

            for (int j = 0; j < this.data.size(); j++) {
                newStdev += this.data.get(j).getProb(i)*Math.pow((this.data.get(j).val() - newMean), 2);
            }
            newStdev /= this.data.nI(i);
            this.components[i].setStdev(newStdev);

            this.components[i].setWeight(this.data.nI(i)/this.data.size());
        }

    }

    public Double logLike() {
        Double loglike = 0.0;

        for (int i = 0; i < this.data.size(); i++) {
            Double sum = 0.0;
            for (int j = 0; j < this.components.length; j++) {
                Component c = this.components[j];
                NormalDistribution dist = new NormalDistribution(c.getMean(), c.getStdev());
                sum += this.data.get(i).getProb(j) *
                        (Math.log(dist.cumulativeProbability(this.data.get(i).val())) + Math.log(c.getWeight()));
            }
            loglike += sum;
        }

        return loglike;
    }



}
