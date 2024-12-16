import sys
import math

import day12.functions as functions

if len(sys.argv) < 3:
    sys.exit("You have to specify the location of the input file and the number of times to blink!")

stones = functions.readParseInput(sys.argv[1])
num_blinks = int(sys.argv[2])

def pre_process(stones):
    map_stones = {}

    for stone in stones:
        if stone not in map_stones.keys():
            map_stones[stone] = 1
        else:
            map_stones[stone] += 1

    return map_stones

def blink(stones, nr_times=25):
    i = 0

    while i < nr_times:
        print(f"Blinked {i} times")
        new_stones = {}
        
        for stone, ocrr in stones.items():
            if stone == 0:
                if 1 not in new_stones.keys():
                    new_stones[1] = 0
                new_stones[1] += ocrr 
                continue
                
            s = str(stone)
            if len(s) % 2 == 0: 
                if int(s[:int(len(s)/2)]) not in new_stones.keys():
                    new_stones[int(s[:int(len(s)/2)])] = 0
                if int(s[int(len(s)/2):]) not in new_stones.keys():
                    new_stones[int(s[int(len(s)/2):])] = 0

                new_stones[int(s[:int(len(s)/2)])] += ocrr
                new_stones[int(s[int(len(s)/2):])] += ocrr
            else:
                if stone*2024 not in new_stones.keys():
                    new_stones[stone*2024] = 0
                new_stones[stone*2024] += ocrr
        del stones
        stones = new_stones
        print(stones)
        i += 1

    return stones

def num_stones(stones):
    num = 0
    for _, ocrr in stones.items():
        num += ocrr
    return num

print(stones)
map_stones = pre_process(stones)
print(map_stones)
stones = blink(map_stones, num_blinks)

print(f"The number of stones after blinking 25 times is: {num_stones(stones)}")