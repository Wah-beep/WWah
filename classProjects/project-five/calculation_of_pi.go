// calculation_of_pi.go
// Wah Saw Tamalar
// Estimating the value of pi using Monte Carlo method

package main

import (
	"fmt"
	"math/rand"
	"math"
	"time"
	"sync"
)

func main() {
	// Randomized
	rand.Seed(time.Now().UnixNano())
	
	defer printElapsedTime(time.Now(), "Total execution")
	
	// Amount of darts and G
	darts := 10000000000
	workers := 10 // 10G
	
	// Create a channel to hold values
	results := make(chan int, workers)
	
	var wg sync.WaitGroup
	
	// Divide up the works
	dartsPerWorker := darts / workers
	
	// Call goroutines, passing in the channel
	for i := 0; i < workers; i++ {
		wg.Add(1)
		
		go func() {
			defer wg.Done()
			results <- throwDarts(dartsPerWorker)// Throw Darts
		}()
	}
	
	// Close channel
	go func() {
		wg.Wait()
		close(results)
	}()
	
	// Collect results
	totalInside := 0
	for r := range results {
		totalInside += r
	}
	
	pi := calcPi(totalInside, darts)
	fmt.Printf("My Pi: %.10f\n", pi)// Print only 10 float digits
	fmt.Printf("Actual Pi: %.10f\n", math.Pi)// Print only 10 float digits
	fmt.Printf("Compare: %.15f\n", comparePi(pi, math.Pi))// Print only 15 float digits
}

// Calculate the Pi with how many darts are inside
func calcPi(inside int, total int) float64 {
	
	return (float64(inside) / float64(total)) * 4.0 // Equation
}

// Get how many darts are inside randomly
func throwDarts(n int) int {
	inside := 0
	
	// Generate random float between -1, and 1
	for i := 0; i < n; i++ {
		x := 2*rand.Float64() - 1
		y := 2*rand.Float64() - 1
		
		// If the dart land inside the circle
		if x*x+y*y <= 1 {
			inside++ // Increment
		}
	}

	return inside
}

// Comparing to the actual Pi values
func comparePi(myPi float64, actualPi float64) float64 {
	delta := math.Abs(myPi - actualPi)
	return delta
}

// prints the time elapsed since start.
func printElapsedTime( start time.Time, message string) {
    var elapsed = time.Since( start)
    fmt.Println( message, "took", elapsed)
}