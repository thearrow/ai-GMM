public class Main {

    public static void main(String[] args) {
        Mixture mix = new Mixture(new DataSet("data1.txt", 3));

        mix.printStats();

        Double oldLog = 0.0;
        do {
            oldLog = mix.logLike();
            mix.Expectation();
            mix.Maximization();
            System.out.println(mix.logLike());
        }
        while (Math.abs(mix.logLike() - oldLog) > 0.001);

        mix.printStats();
    }

}
