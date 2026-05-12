/* Main.java
 *
 */
package pidart;

import java.util.Random;

/**
 *
 * @author erik
 */
public class Main {

    private static final Random generator = new Random( 0);
    
    /**
     * @param args the command line arguments
     */ /*
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("        1,000: " + estimatePi( 1000));
        System.out.println("       10,000: " + estimatePi( 10_000));
        System.out.println("      100,000: " + estimatePi( 100_000));
        System.out.println("    1,000,000: " + estimatePi( 1_000_000));
        System.out.println("   10,000,000: " + estimatePi( 10_000_000));
        System.out.println("  100,000,000: " + estimatePi( 100_000_000));
        System.out.println("1,000,000,000: " + estimatePi( 1_000_000_000));
    }
    */
    
    /**
     * Calculates an estimate of pi based on stochastic darts into the unit circle.
     * @param numberOfDartsToThrow The number of random points to create
     * @return The estimated value of pi
     */
    public static double estimatePi( int numberOfDartsToThrow) {
        int numberOfDartsThrown = 0;
        int numberOfDartsInside = 0;
        while( numberOfDartsToThrow > numberOfDartsThrown++) {
            // Throw a dart at the upper right quadrant of the unit square
            double x = generator.nextDouble();
            double y = generator.nextDouble();
            // Determine if the dart is inside the unit circle
            if( x * x + y * y < 1.0) {
                numberOfDartsInside++;
            }
        }
        double insideProportion = (double)numberOfDartsInside / (double)numberOfDartsToThrow;
        return insideProportion * 4;
    }
    
    
    
    public static void main(String[] args) {
        int NUM_THREADS = 10;
        int NUM_THROWS = 100_000;
        ThrowSampler[] samples = new ThrowSampler[NUM_THREADS];
        for( int i = 0; i < samples.length; i++) {
            samples[i] = new ThrowSampler( NUM_THROWS);
            samples[i].run();
        }
        try {
            for( Thread t : samples) t.join();
        }
        catch( Exception e) {}
        double propTotal = 0;
        for( ThrowSampler t : samples) {
            propTotal += t.getProportion();
        }
        propTotal /= NUM_THREADS;
        System.out.println("Estimate is " + propTotal);
    }

}
