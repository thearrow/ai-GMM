public class Main {

    public static void main(String[] args) {
        Mixture mix = new Mixture(new DataSet("data1.txt", 3));

        mix.printStats();

        Double oldLog = 0.0;
        do {
            oldLog = mix.logLike();
            mix.Expectation();
            mix.Maximization();
            mix.printStats();
            System.out.println("");
        }
        while (Math.abs(mix.logLike() - oldLog) > 0.05);

        mix.printStats();

    }

}
