## Lights-Out
Lights out game implemented using LISP.
User will have the options to select 1 to play the game or 2 to let the AIs battle(Random vs My AI).
If you choose 2, the AIs will run for 50 games print out averages.
If user choose 1, user can enter 1-9 for toggle. User have to turn all the lights out in order to win. 
Toggling also toggle adjacents, above, and below cells if it is valid to.
User will be prompt to enter 1-9 again, and again till the user have solved it. When the user have solved it, it will print out the winning statement as well as the amount of moves it took for user to solved it.
 ## AI Battles
 Random AI pick the moves random 1-9\
 My AI uses Chase the Light method. Link: https://www.youtube.com/watch?v=LnYCcUc4FIo&t=74s \
My AI solves to the top two rows first then depending on the conditions, pick the hard-coded moves.

## AIs' Averages
AIs will run for 50 games\
You can change the amount in the code at the line:
> game-opt then run-ai [amount] '() '()

Random AI Averages: around 31.4 \
My AI Averages: around 7.04

In this folder:\
.gitignore\
README.md\
lights-out-starter.lisp


<ins>How-To-Use<ins>
>Run lights-out-starter.lisp\
Select 1 to play or 2 to let the AIs battle\
If 1, Enter 1-9 till you solved it

Author: Wah Saw Tamalar
