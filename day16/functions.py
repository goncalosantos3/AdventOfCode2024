
def readInput(filePath):    
    start = (0, 0)
    end = (0, 0)
    map =  []

    with open(filePath, "r") as file:
        i = 0
        for line in file:
            j = 0
            map.append(list(line.strip()))
            for c in line:
                if c == 'S':
                    start = (j, i)
                elif c == 'E':
                    end = (j, i)
                j += 1
            i += 1

    return map, start, end