import re

def readAndParseInput(filePath):  
    rules = {}  
    updates = []
    part2 = False

    with open(filePath, "r") as file:
        for line in file:
            if line != "\n":
                if part2:
                    updates.append(list(map(int, line.split(","))))
                else:
                    res = re.findall("\d+", line)
                    if len(res) != 2:
                        sys.exit("Invalid input line!")
                    
                    if not int(res[0]) in rules:
                        rules[int(res[0])] = []
                    rules[int(res[0])].append(int(res[1]))
            else:
                part2 = True
    
    return rules, updates

def getCorrectUpdates(rules, updates):
    correct = []

    for i, update in enumerate(updates):
        valid = True
        for j, elem in enumerate(update):
            if set(update[:j]) & set(rules[elem]):
                valid = False
                break
        
        if valid:
            correct.append(i)

    return correct