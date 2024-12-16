import sys
import math

import day12.functions as functions

if len(sys.argv) < 2:
    sys.exit("You have to specify the location of the input file!")

stones = functions.readParseInput(sys.argv[1])

def blink(stones, nr_times=25):
    i = 0

    while i < nr_times:
        print(f"Blinked {i} times")
        j = 0

        while j < len(stones):
            if stones[j] == 0:
                stones[j] = 1
                j += 1
                continue
            
            # num_digits = math.floor(math.log10(stones[j])) + 1
            # if num_digits % 2 == 0:
            #     stones.insert(j, int(stones[j]/10**(num_digits/2)))
            #     stones.insert(j+1, int(stones[j] % 10**(num_digits/2)))
            #     stones.pop(j+2) # Remove old stones
            #     j += 1
            s = str(stones[j])
            if len(s) % 2 == 0: 
                stones.insert(j, int(s[:int(len(s)/2)]))
                stones.insert(j+1, int(s[int(len(s)/2):]))
                stones.pop(j+2) # Remove old stones
                j += 1
            else:
                stones[j] *= 2024
            j += 1
        i += 1
    
    return stones

stones = blink(stones, 75)
print(f"The number of stones after blinking 25 times is: {len(stones)}")