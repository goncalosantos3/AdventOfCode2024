import sys

import functions

if len(sys.argv) < 2:
    sys.error("You need to specify the input file location!")

map, x, y  = functions.getMap(sys.argv[1])
print(f"Guard is initially located at: {x};{y}")

def moveUp(map, x, y, visited):
    while map[y-1][x] != '#' and y > 0:
        y -= 1
        visited.add((x,y))

    return visited, y

def moveRight(map, x, y, visited):
    while map[y][x+1] != '#' and x < len(map[0]) - 1:
        x += 1
        visited.add((x,y))

    return visited, x

def moveDown(map, x, y, visited):
    while map[y+1][x] != '#' and y < len(map) - 1:
        y += 1
        visited.add((x,y))

    return visited, y

def moveLeft(map, x, y, visited):
    while map[y][x-1] != '#' and x > 0:
        x -= 1
        visited.add((x,y))
    
    return visited, x

def visitedLocations(map, x, y):
    visited = set()
    visited.add((x,y))

    while(True):
        visited, y = moveUp(map, x, y, visited)
        if y != 0:
            visited, x = moveRight(map, x, y, visited)
            if x != len(map[0]) - 1:
                visited, y = moveDown(map, x, y, visited)
                if y != len(map) - 1:
                    visited, x = moveLeft(map, x, y, visited)
                    if x == 0:
                        break
                else:
                    break
            else: 
                break
        else:
            break   
    
    return visited

result = visitedLocations(map, x, y)
print(f"The guard visited a total of {len(result)} places")