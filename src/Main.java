public class Main {

    public static void main(String[] args) {
        Mixture mix = new Mixture(new DataSet("data1.txt", 3));

        for (int i = 0; i < 100; i++) {
            mix.Expectation();
            mix.Maximization();
        }
    }

}
