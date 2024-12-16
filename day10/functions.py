
def readParseMap(filePath):
    trails = []
    trailHeads = set()
    trailTails = set()

    with open(filePath, "r") as file:
        i = 0 
        for line in file:
            trails.append(list(map(int, line.strip())))

            j = 0
            for step in line:
                if step == '0':
                    trailHeads.add((j, i))
                elif step == '9':
                    trailTails.add((j, i))
                j += 1
            i += 1
    
    return trails, trailHeads, trailTails