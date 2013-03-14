public class Main {

    public static void main(String[] args) {
        if (args.length <= 0) {
            System.out.println("Usage: java Main <filename.txt>");
            System.exit(1);
        }
        Mixture mix = new Mixture(new DataSet(args[0], 5));

        mix.printStats();

        Double oldLog = 0.0;
        int count = 1;
        do {
            oldLog = mix.logLike();
            mix.Expectation();
            mix.Maximization();
            System.out.println(count + "," + mix.logLike());
            count++;
        }
        while (Math.abs(mix.logLike() - oldLog) > 0.00001);

        mix.printStats();
    }

}
