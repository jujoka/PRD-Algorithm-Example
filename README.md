# PRD-Algorithm-Example
Pseudo-random number generation algorithm commonly used for competitive games that include RNG based mechanics like critical strikes

Explanations of the algorithm can be found here:

https://www.fatalerrors.org/a/prd-critical-hit-algorithm-of-the-common-pseudo-random-algorithm-in-the-game.html
https://dota2.fandom.com/wiki/Random_distribution#Definition

How to use:
Run the program, input your choice for what probability to simulate and input the number of attempts to simulate.
The program will print the incrementing probability of the event based on failed attempts provided by the algorithm (0-1), 
the roll (0-1) and any successful events. After the simulation is finished more information will be printed like average 
attempts until success and actual probability of the event based on the simulation (successes / attempts).

**NOTE: User input is not sanitized**

**For Dota 2 players**: the algorithm is working correctly for high chance events like MKB (80%), however the game actually rolls
for the chance of failure instead of success, so in the case of MKB every attack will proc the effect unless a 20% PRD roll would
result in a success.
