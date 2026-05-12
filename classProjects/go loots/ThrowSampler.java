/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidart;

import java.util.Random;

/**
 *
 * @author erik
 */
public class ThrowSampler extends Thread {
    
    private final Random generator = new Random();
    private final int numberOfThrows;
    private int numberInTheCircle = 0;
    
    public ThrowSampler( int numberOfThrows) {
        this.numberOfThrows = numberOfThrows;
    }
    
    public double getProportion() {
        return 4.0 * (double)numberInTheCircle / (double)numberOfThrows;
    }
    
    @Override
    public void run() {
        for( int i = 0; i < numberOfThrows; i++) {
            double x = generator.nextDouble();
            double y = generator.nextDouble();
            if( x * x + y * y < 1.0) {
                numberInTheCircle++;
            }
        }
    }
}
