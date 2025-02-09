import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class CouponCollector {

    public static void main(String[] args) {
        final int N = 1000;
        final int trials = 10;
        Random rand = new Random();

        // The CSV file will have columns for n, and for each case: x, mean, and std deviation.
        try (PrintWriter writer = new PrintWriter(new File("results.csv"))) {
            writer.println("n,xA,meanA,stddevA,xB,meanB,stddevB,xC,meanC,stddevC");

            // Loop over candidate counts from 1 to N.
            for (int n = 1; n <= N; n++) {
                // For each n, compute the value of x for our three different cases:
                int xA = (int) Math.ceil(Math.log(n) / Math.log(2));  // Case A: x = ceil(log2 n)
                int xB = n;                                          // Case B: x = n
                int xC = n * n * n;                                  // Case C: x = n^3

                double[] statsA = simulate(n, xA, trials, rand);
                double[] statsB = simulate(n, xB, trials, rand);
                double[] statsC = simulate(n, xC, trials, rand);

                // Write the results to the CSV file.
                // The format is: n, xA, meanA, stddevA, xB, meanB, stddevB, xC, meanC, stddevC.
                writer.printf("%d,%d,%.4f,%.4f,%d,%.4f,%.4f,%d,%.4f,%.4f%n",
                        n, xA, statsA[0], statsA[1],
                        xB, statsB[0], statsB[1],
                        xC, statsC[0], statsC[1]);
            }
            
            System.out.println("Results have been written to results.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Simulates the hiring process for a given number of candidates (n) and rating range (0 to x).
     * It repeats the simulation 'trials' times and then computes the average (mean)
     * and standard deviation of the number of times a new candidate is hired.
     *
     * @param n      The number of candidates.
     * @param x      The maximum rating value (ratings are from 0 to x).
     * @param trials The number of simulation runs.
     * @param rand   A Random object to generate random numbers.
     * @return       A double array where index 0 is the mean number of hires, and index 1 is the standard deviation.
     */
    private static double[] simulate(int n, int x, int trials, Random rand) {
        int[] hires = new int[trials];
        
        for (int t = 0; t < trials; t++) {
            int currentBest = -1;  // Start with -1 as the current best rating, since all ratings are >= 0.
            int count = 0;         
            
            for (int i = 0; i < n; i++) {
                int rating = rand.nextInt(x + 1);  // Generate random rating
                
                if (rating > currentBest) {
                    count++;              
                    currentBest = rating;
                }
            }
            hires[t] = count;
        }
        
        // Calculate the mean (average) number of hires.
        double sum = 0;
        for (int h : hires) {
            sum += h;
        }
        double mean = sum / trials;
        
        // Calculate the standard deviation.
        double variance = 0;
        for (int h : hires) {
            variance += (h - mean) * (h - mean);
        }
        double stddev = Math.sqrt(variance / trials);
        
        return new double[]{mean, stddev};
    }
}
