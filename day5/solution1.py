import sys

import day12.functions as functions

if len(sys.argv) < 2:
    sys.exit("You need to specify the input file location!")

rules, updates = functions.readAndParseInput(sys.argv[1])
correctUpdates = functions.getCorrectUpdates(rules, updates)

def getResult(correctUpdates, updates):
    result = 0
    for index in correctUpdates:
        result += updates[index][int((len(updates[index]))//2)]
    
    return result

result = getResult(correctUpdates, updates)

print(f"The result is: {result}!")