# Estimating Pi
Using the Go programming language to calculate the value of pi using a very inefficient stochastic method, and determine if this can be sped up by use of concurrency.

The stochastic method for determining the value of pi is based on throwing virtual darts at the unit circle in x,y coordinate space. The unit square that perfectly encloses the unit circle has an area of 4 square units, while the area of the unit circle is pi square units. So the proportion of random darts that land within the unit circle to the total number of darts is pi/4. From this equation we can estimate the value of pi.

A program that calculates the value of pi using the method. The reports are below. Run the program and change the Amount of Darts each time to see different results. In the result, My Pi is the calculation of Pi using throwing darts(Monte Carlo) method. Actual Pi is the actual value of pi import from math.Pi. Compare is the delta. And the total execution time is how long it took.

# In this folder:
.gitignore	&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Git Ignore\
README.md	&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;This README\
calculation_of_pi.go	&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;throwDarts, and calcPi functions

# <ins>How-To-Use:<ins>
Run these line in Bash
>go build calculation_of_pi.go\
>./calculation_of_pi.exe

# REPORTS:
This is my reports with 10G. I do have gaming laptop which have good hardwares and so I expect to run faster but probably not faster than other gaming laptop or pc.\
Darts: 10,000
>My Pi: 3.1544000000\
>Actual Pi: 3.1415926536\
>Compare: 0.012807346410207\
>Total execution took 506.4µs

Darts: 100,000
>My Pi: 3.1448000000\
>Actual Pi: 3.1415926536\
>Compare: 0.003207346410207\
>Total execution took 550.9µs

Darts: 1,000,000
>My Pi: 3.1433080000\
>Actual Pi: 3.1415926536\
>Compare: 0.001715346410207\
>Total execution took 3.8936ms

Darts: 10,000,000
>My Pi: 3.1419480000\
>Actual Pi: 3.1415926536\
>Compare: 0.000355346410207\
>Total execution took 26.9831ms

Darts: 100,000,000
>My Pi: 3.1416821200\
>Actual Pi: 3.1415926536\
>Compare: 0.000089466410207\
>Total execution took 264.7965ms

Darts: 1,000,000,000
>My Pi: 3.1415848200\
>Actual Pi: 3.1415926536\
>Compare: 0.000007833589793\
>Total execution took 2.6785313s

Darts: 10,000,000,000
>My Pi: 3.1415611388\
>Actual Pi: 3.1415926536\
>Compare: 0.000031514789793\
>Total execution took 27.225839s

# Author:
>Wah Saw Tamalar