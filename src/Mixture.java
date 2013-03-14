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
            Component c = new Component(1.0 / Double.parseDouble(this.data.components() + ""), mean + ((Math.random() - 0.5) * 4), stdev + ((Math.random() - 0.5) * 4));
            this.components[i] = c;
        }
    }

    public void Expectation() {
        for (int i = 0; i < this.data.size(); i++) {
            Double[] probs = new Double[this.data.components()];
            for (int j = 0; j < this.components.length; j++) {
                Component c = this.components[j];
                probs[j] = gaussian(this.data.get(i).val(), c.getMean(), c.getStdev()) * c.getWeight();
            }

            //alpha normalize and set probs
            Double sum = 0.0;
            for (Double p : probs)
                sum += p;
            for (int j = 0; j < probs.length; j++) {
                Double normProb = probs[j]/sum;
                this.data.get(i).setProb(j, normProb);
            }
        }
    }

    public void Maximization() {
        Double newMean = 0.0;
        Double newStdev = 0.0;
        for (int i = 0; i < this.components.length; i++) {
            //MEAN
            for (int j = 0; j < this.data.size(); j++)
                newMean += this.data.get(j).getProb(i) * this.data.get(j).val();
            newMean /= this.data.nI(i);
            this.components[i].setMean(newMean);

            //STDEV
            for (int j = 0; j < this.data.size(); j++)
                newStdev += this.data.get(j).getProb(i) * Math.pow((this.data.get(j).val() - newMean), 2);
            newStdev /= this.data.nI(i);
            newStdev = Math.sqrt(newStdev);
            this.components[i].setStdev(newStdev);

            //WEIGHT
            this.components[i].setWeight(this.data.nI(i) / this.data.size());
        }

    }

    public Double logLike() {
        Double loglike = 0.0;
        for (int i = 0; i < this.data.size(); i++) {
            Double sum = 0.0;
            for (int j = 0; j < this.components.length; j++) {
                Component c = this.components[j];
                Double prob = this.data.get(i).getProb(j);
                Double val = this.data.get(i).val();
                Double gauss = gaussian(val, c.getMean(), c.getStdev());
                if (gauss == 0) {
                    gauss = Double.MIN_NORMAL;
                }
                Double inner = Math.log(gauss)+ Math.log(c.getWeight());
                if (inner.isInfinite() || inner.isNaN()) {
                    return 0.0;
                }
                sum += prob * inner;
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


    /*
        The following two methods courtesy of Robert Sedgewick:
        http://introcs.cs.princeton.edu/java/22library/Gaussian.java.html
        Used to calculate the PDF of a gaussian distribution with mean=mu, stddev=sigma
     */
    public Double standardGaussian(Double x) {
        return Math.exp(-x * x / 2) / Math.sqrt(2 * Math.PI);
    }

    public Double gaussian(Double x, Double mu, Double sigma) {
        return standardGaussian((x - mu) / sigma) / sigma;
    }


}
