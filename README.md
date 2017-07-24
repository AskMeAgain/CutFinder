A program to solve the traveling salesman problem for Trackmania.

Trackmania is a computer game where you can create your own maps. To finish a map you need to drive through every checkpoint (CP) and then over a finish line. In which order you do this is not important.

This opens up ways to drive a map which is not intended from the author. For example instead of driving: 

Start -> CP 1 -> CP 2 -> Finish

it could be faster to drive 

Start -> CP 2 -> CP 1 -> Finish.

For short maps it is possible to do this by hand, but for long maps (50CPs+) this way is not sufficient. Here is where my program comes into play. 

You check for every CP which other CPs can be reached. You enter these into a table and the program will output every possible combination on how to drive it.

Ofcourse you need to check for yourself if the new way is faster. I added a way to enter numbers, but flying start would improve the time.


How to use the program:

1. Download
2. Execute
3. Enter the number of checkpoints
4. Fill out the grid.

Filling out the grid is easy:

If you can drive from CP1 -> CP5, CP7, CP10, you go into Column 1 and enter a 1 in Row 5, 7 & 10. Dont forget the finish lines or else the program wont find a correct way.

