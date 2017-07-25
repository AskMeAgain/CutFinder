## A program to solve the traveling salesman problem for Trackmania.

made in ~2015

Trackmania is a computer game where you can create your own maps. To finish a map you need to drive through every checkpoint (CP) and then over a finish line. In which order you drive through every CP is not important.

This opens up ways to drive a map which is not intended from the author. For example instead of driving: 

##### Start -> CP 1 -> CP 2 -> Finish

it could be faster to drive 

##### Start -> CP 2 -> CP 1 -> Finish.

For short maps it is possible to do this by hand, but for long maps (50CPs+) this way is not sufficient. It is impossible to find all possible combinations. Maybe a combination is faster then the original way?

Here is where my program comes into play. 

![alt text](https://puu.sh/wRSph/79ec2742dc.png "Main Page")

You check for every CP which other CPs can be reached. You enter these into a grid and the program will output every possible combination on how to drive it.

You still need to check for yourself if the new way is faster. I added a way to enter numbers, but flying start would improve the time.

#### How to use the program:

1. Download the Jar from the Jar Folder here on github
2. Execute
3. Enter the number of checkpoints
4. Fill out the grid.
5. Click on compute 
6. Enter name
7. Done

Filling out the grid is easy:

If you can drive from CP1 -> CP5, CP7, CP10, you go into Column 1 and enter a number in Row 5, 7 & 10. Dont forget the finish lines or else the program wont find a correct route.

#### How is the program working:

Using a divide and conquere algorithm to check for every possible combination. Since not even 10% of the grid is filled most of the time, we dont need to do any fancy algorithm and can just check for every possible way.

The algorithm takes an adjacense list (two dimensional array) and a list to show which route we already went. The algorithm calls itself for every possible CP the route could follow up (we just check for the last element in our route list and see if we have any numbers in our adjacense list. It will also return the same array, but changed the choosen CP to -1. This way we dont go to an CP we already used before.

The algorithm stops when the size of our list is equal to the number of all CPs + 1 (we still need to go to a finish line!). We dont need to check for a correct CP order because we can only add CPs which are correct anyway (due to our adjacence list). We also dont need to check if a route ends in a finish line, because if we have a route which is the number of the CPs in the map plus one, we have automatically end in a finish line because every finish line ends the algorithm (only because there is no way to go from a Finish line to a CP due to the design of the grid).

#### Options

There are multiple options to provde an easier/faster work flow

![alt text](https://puu.sh/wRSwZ/6f9b91b48e.png "Main Page")

#### File Storage

Your grid can be saved to file for later use. The result can be saved too.


