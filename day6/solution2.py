import sys
import copy

import day12.functions as functions

if len(sys.argv) < 2:
    sys.error("You need to specify the input file location!")

map, x, y  = functions.getMap(sys.argv[1])
print(f"Guard is initially located at: {x};{y}")

def moveUp(map, x, y, visited):
    loop = False
    while y > 0 and map[y-1][x] != '#':
        if (x,y-1,"up") in visited:
            loop = True
            break
        y -= 1
        visited.add((x,y,"up"))

    return visited, y, loop

def moveRight(map, x, y, visited):
    loop = False
    while x < len(map[0]) - 1 and map[y][x+1] != '#':
        if (x+1,y,"right") in visited:
            loop = True
            break
        x += 1
        visited.add((x,y,"right"))

    return visited, x, loop

def moveDown(map, x, y, visited):
    loop = False
    while y < len(map) - 1 and map[y+1][x] != '#':
        if (x,y+1,"down") in visited:
            loop = True
            break
        y += 1
        visited.add((x,y,"down"))

    return visited, y, loop

def moveLeft(map, x, y, visited):
    loop = False
    while x > 0 and map[y][x-1] != '#':
        if (x-1,y,"left") in visited:
            loop = True
            break
        x -= 1
        visited.add((x,y,"left"))
    
    return visited, x, loop

def testGuardLoop(map, x, y):
    visited = set()
    visited.add((x,y,"up"))

    while(True):
        visited, y, loop = moveUp(map, x, y, visited)
        #print(x,y,loop)
        if y != 0 and not loop:
            visited, x, loop = moveRight(map, x, y, visited)
            #print(x,y)
            if x != len(map[0]) - 1 and not loop:
                visited, y, loop = moveDown(map, x, y, visited)
                #print(x,y)
                if y != len(map) - 1 and not loop:
                    visited, x, loop = moveLeft(map, x, y, visited)
                    #print(x,y)
                    if x == 0 or loop: 
                        break
                else:
                    break
            else: 
                break
        else:
            break   

    return loop

def howManyObs(map, x, y):
    number = 0

    i = 0
    while i < len(map):
        j = 0
        while j < len(map[0]):
            if map[i][j] != '#' and map[i][j] != '^':
                print(f"Testing obstacle in: {i};{j}!")
                map_clone = copy.deepcopy(map)
                map_clone[i][j] = '#' # Insert new obstacle
                
                if testGuardLoop(map_clone, x, y):
                    print(f"Obstacle in {i};{j} causes a loop!")
                    number += 1
                del map_clone # Free memory associated with cloned map
            j += 1
        i += 1
    return number

result = howManyObs(map, x, y)
print(f"We can insert {result} obstacles in different locations to make the guard stay in a loop!")