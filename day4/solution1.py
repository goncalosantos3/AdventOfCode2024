import sys

if len(sys.argv) < 2:
    sys.exit("You have to pass the input file location!")

salad = []

with open(sys.argv[1], "r") as file:
    for line in file:
        salad.append(list(line.strip()))


def checkBelow(salad, line, column):
     
    if salad[line][column] == 'X':
        if salad[line+1][column] == 'M' and salad[line+2][column] == 'A' and salad[line+3][column] == 'S':
            return 1
        else:
            return 0
    

def checkVertical(salad, line, column):

    if line == 0:
        return checkBelow(salad, line, column)
    elif line == len(salad):
        return checkAbove(salad, line, column)
    
    return checkAboveAndBelow(salad, line, column) 

def wordSearch(salad):
    found = 0
    i = 0
    j = 0

    for line in salad:    
        for letter in line:
            found += checkVertical(salad, i, j) + checkHorizontal(salad, i, j) + checkDiagonal(salad, i, j)
            j += 1
        i += 1
    
    return found


print(salad)