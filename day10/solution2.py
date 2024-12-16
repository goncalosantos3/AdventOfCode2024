import sys
import day12.functions as functions

if len(sys.argv) < 2:
    sys.exit("You have to specify the location of the input file!")

trails, trailHeads, trailTails = functions.readParseMap(sys.argv[1])

# Here head is the starting position of each call of this function (it doesn't need to be a trail head)
def trailHeadScore(score, head, trails):
    x, y = head
    current = trails[y][x] 

    if current != 9:
        if x-1 >= 0 and trails[y][x-1] == current + 1: # Path to the left
            score = trailHeadScore(score, (x-1,y), trails)
        if x+1 < len(trails[0]) and trails[y][x+1] == current + 1: # Path to the rigth
            score = trailHeadScore(score, (x+1,y), trails)
        if y-1 >= 0 and trails[y-1][x] == current + 1: # Path upwards
            score = trailHeadScore(score, (x,y-1), trails)
        if y+1 < len(trails) and trails[y+1][x] == current + 1: # Path downwards
            score = trailHeadScore(score, (x,y+1), trails)
    elif (x,y): # Trail ended
        return score + 1

    return score + 0 # There is no more path to take


def calculateScoresSum(trails, trailHeads):
    score = 0
    
    for head in trailHeads:
        headScore = trailHeadScore(0, head, trails)
        score += headScore
        print(f"{head} has a score of {headScore}")
    
    return score

score = calculateScoresSum(trails, trailHeads)
print(f"The sum of the trail heads score is: {score}")