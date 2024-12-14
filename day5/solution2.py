import sys
from functools import cmp_to_key

import functions

if len(sys.argv) < 2:
    sys.exit("You need to specify the input file location!")

rules, updates = functions.readAndParseInput(sys.argv[1])
correctUpdates = functions.getCorrectUpdates(rules, updates)

def swap(list, i, j):
    aux = list[i]
    list[i] = list[j]
    list[j] = aux

def makeUpdateCorrect(update, rules):
    corrected = update.copy()

    i = 0
    while i < len(update):
        j = 0
        while j < i:
            if update[j] in rules[update[i]]: 
                swap(corrected, i, j)
            j += 1
        
        j += 1
        while j < len(update):
            if update[i] in rules[update[j]]:
                swap(corrected, i, j)
            j += 1
        i += 1
    
    print(corrected)
    return corrected

def getResult(correctUpdates, updates, rules):
    result = 0

    for i, update in enumerate(updates):
        if i not in correctUpdates: # Only for the incorrect ones
            # correctedUpdate = makeUpdateCorrect(update, rules)
            copy = update.copy()
            def correct(a, b):
                if b in rules[a]:
                    return -1
                elif a in rules[b]:
                    return 1
                return 0
            
            copy.sort(key=cmp_to_key(correct))
            print(copy)
            
            result += copy[int(len(copy)//2)]

    return result


result = getResult(correctUpdates, updates, rules)
print(f"The result is: {result}")
