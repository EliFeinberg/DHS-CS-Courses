PROJECT TITLE: Traveling Salesperson Problem
PURPOSE OF PROJECT: Implement two greedy heuristics in the attempt to find
an optimal solution to the Traveling Salesperson Problem
DATE: 10-12-18
AUTHOR: Eli Feinberg and Brooke Wu 
ALGORITHM DESCRIPTIONS: 
    insertNearest()
    Using a forloop checks each position to determine the distance between the point added
    and the point in that current position. If the distance is smaller than any compared or 
    it is the first iteration then the distance is saved to compare with the next iteration 
    and the position is saved. Which ever position is set at the end of the forloop is set as
    the position the new point is added to. Big O(N^2)
    
    insertSmallest()
    Using a forloop goes through each position on the linkedlist check the distance added by
    adding point at that location. This works by deleting subtracting the current distance 
    between the node and adding the possible new distance adding between the node. This is done
    and the position that would add the least overall distance is chose and the point is added. Big O(N^2)
    
    EXTRA: Fixed the image issue by going to the draw method and drawing the list in reverse.