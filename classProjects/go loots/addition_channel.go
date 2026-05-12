// addition_channel.go
//
// Example of using channels with go routines
//

package main

import "fmt"
import "time"

func main() {
	
	// Create a channel of integers to hold the values
	// from each go routine (which cannot return values)
	ch := make( chan int)
    var start time.Time = time.Now()
	for i := 0; i < 100; i++ {
		// Call a go routine, passing in the channel
		// so that the method can put numbers into the channel
		go addSomeNumbers( ch, i*1000, (i+1) * 1000)
	}
    var elapsed time.Duration = time.Since( start)
	result := 0
	for i := 0; i < 100; i++ {
		// Read each value out of the channel
		// that was placed there in the calls to addSomeNumbers()
		result += <-ch
	}
    var elapsed2 time.Duration = time.Since( start)
    fmt.Println( start)
    fmt.Println( elapsed)
    fmt.Println( elapsed2)
	fmt.Println( result)
    
    
}

func addSomeNumbers( ch chan int, start, end int ) {
	
	sum := 0
	for i := start; i < end; i++ {
		sum += i
	}
	ch <- sum  // put the sum into the channel
}