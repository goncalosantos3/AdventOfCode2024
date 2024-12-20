import sys

import functions

if len(sys.argv) < 2:
    sys.exit("You need to provide the input file path!")

map, start, end = functions.readInput(sys.argv[1])
print(map, "\n", start, "\n", end)

def getMinScore(score_list):
    min_value = sys.maxsize

    for score in score_list:
        if score < min_value and score >= 0: # Score negative means it's an invalid path
            min_value = score
    return min_value

# The strategy is to calculate all the possible paths and get the lowest scoring one/one's
def getLowestPossibleScore(map, start, end, orientation, visited, score):
    path = []
    path.append((start, orientation))
    visited.add((start, orientation))

    x, y = start
    xOrientation, yOrientation = orientation

    if map[y + yOrientation][x + xOrientation] == '#' and ((x+xOrientation, y+yOrientation), orientation) not in visited:
        # Going forwards hits a Wall 
        visited.add(((x+xOrientation, y+yOrientation), orientation))
        score_list = []
        if orientation == (1, 0) or orientation == (-1, 0): # East or West
            scoreNorth, visitedNorth, pathNorth = getLowestPossibleScore(map, start, end, (0, -1), visited.copy(), score+1000) # Rotate to North
            score_list.append(scoreNorth)
            scoreSouth, visitedSouth, pathSouth = getLowestPossibleScore(map, start, end, (0, 1), visited.copy(), score+1000) # Rotate to South
            score_list.append(scoreSouth)

            score = getMinScore(score_list)
            if score == sys.maxsize: # There are no valid paths ahead
                return -1, set(), []
            if score == scoreNorth:
                visited.update(visitedNorth)
                path += pathNorth
            elif score == scoreSouth:
                visited.update(visitedSouth)
                path += pathSouth
        else: # North or South
            scoreWest, visitedWest, pathWest = getLowestPossibleScore(map, start, end, (-1, 0), visited.copy(), score+1000) # Rotate to West
            score_list.append(scoreWest)
            scoreEast, visitedEast, pathEast = getLowestPossibleScore(map, start, end, (1, 0), visited.copy(), score+1000) # Rotate to East
            score_list.append(scoreEast)

            score = getMinScore(score_list)
            if score == sys.maxsize: # There are no valid paths ahead
                return -1, set(), []
            if score == scoreWest:
                visited.upate(visitedWest)
                path += pathWest
            elif score == scoreEast:
                visited.upate(visitedEast)
                path += pathEast
    elif map[y + yOrientation][x + xOrientation] != 'E' and ((x+xOrientation, y+yOrientation), orientation) not in visited: 
        # Not a Wall, not the End and not yet visited
        score_list = []
        scoreForward, visitedForward, pathForward = getLowestPossibleScore(map, (x+xOrientation, y+yOrientation), end, orientation, visited.copy(), score+1)
        score_list.append(scoreForward)
        if orientation == (1, 0) or orientation == (-1, 0): # East or West
            print(f"Go North!: {start, orientation}")
            scoreNorth, visitedNorth, pathNorth= getLowestPossibleScore(map, start, end, (0, -1), visited.copy(), score+1000) # Rotate to North
            score_list.append(scoreNorth)
            scoreSouth, visitedSouth, pathSouth = getLowestPossibleScore(map, start, end, (0, 1), visited.copy(), score+1000) # Rotate to South
            score_list.append(scoreSouth)
            score = getMinScore(score_list)
            if score == sys.maxsize: # There are no valid paths ahead
                return -1, set(), []
            if score == scoreForward:
                visited.update(visitedForward)
                path += pathForward
            elif score == scoreNorth:
                visited.update(visitedNorth)
                path += pathNorth
            elif score == scoreSouth:
                visited.update(visitedSouth)
                path += pathSouth
        else: # North or South
            print(f"Go West!: {start, orientation}")
            scoreWest, visitedWest, pathWest= getLowestPossibleScore(map, start, end, (-1, 0), visited.copy(), score+1000) # Rotate to West
            score_list.append(scoreWest)
            scoreEast, visitedEast, pathEast = getLowestPossibleScore(map, start, end, (1, 0), visited.copy(), score+1000) # Rotate to East
            score_list.append(scoreEast)

            score = getMinScore(score_list)
            if score == sys.maxsize: # There are no valid paths ahead
                return -1, set(), []
            if score == scoreForward:
                visited.update(visitedForward)
                path += pathForward
            elif score == scoreWest:
                visited.update(visitedWest)
                path += pathWest
            elif score == scoreEast:
                visited.update(visitedEast)
                path += pathEast
    elif map[y + yOrientation][x + xOrientation] != 'E': # Already visited
        return -1, set(), [] # Negative score means path is invalid
    elif map[y + yOrientation][x + xOrientation] == 'E': # Reached the End!
        path.append(((x+xOrientation, y+yOrientation),(orientation)))
        visited.add(((x+xOrientation, y+yOrientation),(orientation)))
        return score + 1, visited, path
    else: 
        sys.exit("Invalid map!")

    return score, visited, path

# Orientation starts to East -> (1, 0) increase X in 1 and mantain Y
lowest_score, path = getLowestPossibleScore(map, start, end, (1, 0), set(), 0)
print(f"The lowest possible score is: {lowest_score}")
print(path)

def drawTakenPath(map, path):
    for pos, orientation in path:
        x, y = pos

        if (1, 0) == orientation: # East
            map[y][x] = '>'
        elif (-1, 0) == orientation: # West
            map[y][x] = '<'
        elif (0, 1) == orientation: # South
            map[y][x] = 'v'
        else: # North
            map[y][x] = '^'

drawTakenPath(map, path)
for row in map:
    for c in row:
        print(c, end='')
    print()

