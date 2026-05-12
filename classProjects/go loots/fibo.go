// fibonacci.go
// Calculates fibonacci numbers recursively with no memoization
// which means it takes a lot of time even at 40 and above.

package main

import (
	"fmt"
	"sync"
	"time"
)


func main() {
    var wg sync.WaitGroup
    printFib := func( which int) {
        defer wg.Done()
        fmt.Println( FibonacciOf( which))
    }
    // By calling printElapsedTime here, but defering it, we will
    // evaluate the arguments time.Now() and the string "All four fibs"
    // right away but the function itself won't be called until main()
    // is complete.
    defer printElapsedTime( time.Now(), "All four fibs")
    wg.Add(4)
    go printFib(4)
	go printFib(44)
    go printFib(43)
    go printFib(25)
	wg.Wait()   // this function does not return until all four threads
	            // have called 'wg.Done()'
	fmt.Println("Main is done")
}

// prints the time elapsed since start.
func printElapsedTime( start time.Time, message string) {
    var elapsed = time.Since( start)
    fmt.Println( message, "took", elapsed)
}


// FibonacciOf calculates the fibonacci number of the given value.
func FibonacciOf(whichOne int) int {
	if whichOne < 3 {
		return 1
	} else {
		return FibonacciOf(whichOne-1) + FibonacciOf(whichOne-2)
	}
}