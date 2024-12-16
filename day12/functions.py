
def readInput(filePath):
    map = []

    with open(filePath, "r") as file:
        for line in file:
            map.append(list(line.strip()))

    return map