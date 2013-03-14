public class Main {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Main <filename> <clusters>");
            System.exit(1);
        }
        Mixture mix = new Mixture(new DataSet(args[0], Integer.parseInt(args[1])));

        mix.printStats();

        Double oldLog = mix.logLike();
        Double newLog = oldLog - 100.0;
        do {
            oldLog = newLog;
            mix.Expectation();
            mix.Maximization();
            newLog = mix.logLike();
            System.out.println(newLog);
        }
        while (newLog!=0 && Math.abs(newLog - oldLog) > 0.00001);

        mix.printStats();
    }

}
