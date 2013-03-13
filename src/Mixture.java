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
            Component c = new Component(1.0/Double.parseDouble(this.data.components() + ""),mean+((Math.random()-0.5)*10), stdev+((Math.random()-0.5)*10));
            this.components[i] = c;
        }
    }

    public void Expectation() {
        for (int i = 0; i < this.data.size(); i++) {
            Double[] probs = new Double[this.data.components()];
            for (int j = 0; j < this.components.length; j++) {
                NormalDistribution dist = new NormalDistribution(this.components[j].getMean(), this.components[j].getStdev());
                probs[j] = dist.cumulativeProbability(this.data.get(i).val()) * this.components[j].getWeight();
            }

            //alpha normalize and set probs
            Double sum = 0.0;
            for (Double p : probs)
                sum += p;
            for (int j = 0; j < probs.length; j++)
                this.data.get(i).setProb(j, probs[j]/sum);
        }
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
            newStdev = Math.sqrt(newStdev);
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

    public void printStats() {
        for (Component c : this.components) {
            System.out.println("C - mean: " + c.getMean() + " stdev: " + c.getStdev() + " weight: " + c.getWeight());
        }
    }



}
