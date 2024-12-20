import sys

import functions

if len(sys.argv) < 5:
    sys.exit("You have to provide the input file location, the number of corrupted bytes and the x and y dimensions!")

corrupted_coordinates = functions.readParseInput(sys.argv[1], int(sys.argv[2]))

print(corrupted_coordinates)
dimX = int(sys.argv[3])
dimY = int(sys.argv[4])

functions.printCorruptedMemorySpace(corrupted_coordinates, dimX, dimY)

def getMinimumRoute(up, down, left, right):
    min_steps = sys.maxsize
    min_path = []

    for steps, path in [up, down, left, right]:
        if steps < min_steps and steps > 0: # If steps < 0 it's an invalid path!
            min_steps = steps
            min_path = path
    
    return min_steps, min_path

# Recursive implementation
def calculateMinimumSteps(corrupted_coordinates, start, end, visited):
    steps = 1
    path = []
    path.append(start) 

    if start in visited: # This place was already visited!
        return -1, []
    visited.add((start))

    xStart, yStart = start
    xEnd, yEnd = end
 
    if start != end and start not in corrupted_coordinates and xStart >= 0 and xStart <= xEnd and yStart >= 0 and yStart <= yEnd: # Valid position but not the end
        stepsUp, pathUp = calculateMinimumSteps(corrupted_coordinates, (xStart, yStart-1), end, visited.copy()) # Up
        stepsDown, pathDown = calculateMinimumSteps(corrupted_coordinates, (xStart, yStart+1), end, visited.copy()) # Down
        stepsLeft, pathLeft = calculateMinimumSteps(corrupted_coordinates, (xStart-1, yStart), end, visited.copy()) # Left
        stepsRight, pathRight = calculateMinimumSteps(corrupted_coordinates, (xStart+1, yStart), end, visited.copy()) # Right

        min_steps, min_path = getMinimumRoute((stepsUp, pathUp), (stepsDown, pathDown), (stepsLeft, pathLeft), (stepsRight, pathRight))
        if min_steps == sys.maxsize and min_path == []: # No valid path ahead
            return -1, []
        else:
            steps += min_steps
            path = path + min_path
    elif start in corrupted_coordinates or xStart < 0 or xStart > xEnd or yStart < 0 or yStart > yEnd:
        return -1, [] # Invalid path
    elif start == end: # Reached the end!
        return 1, path

    return steps, path

# steps, path = calculateMinimumSteps(corrupted_coordinates, (0, 0), (dimX-1, dimY-1), set())
# print(steps-1, path)
# functions.printCorruptedMemorySpaceWithPath(corrupted_coordinates, dimX, dimY, path)

steps, path = functions.aStar(corrupted_coordinates, (0,0), (dimX-1, dimY-1))
print(f"The shortest path has {steps} steps")
functions.printCorruptedMemorySpaceWithPath(corrupted_coordinates, dimX, dimY, path)