def getMap(filePath):
    map = []
    
    i = 0
    with open(filePath, "r") as file:
        for line in file:
            if '^' in line:
                x = line.find('^')
                y = i

            map.append(list(line.strip()))
            i += 1
    
    return map, x, y