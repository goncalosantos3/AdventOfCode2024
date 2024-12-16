import sys

import functions

if len(sys.argv) < 2:
    sys.exit("You have to specify the location of the input file!")

map = functions.readInput(sys.argv[1])

def getArea(map, x, y, visited_plots, region_plots):
    area = 1
    region_plots.add((x,y))
    visited_plots.add((x, y))

    if y-1 >= 0 and map[y-1][x] == map[y][x] and (x, y-1) not in visited_plots:
        aux_area, region_plots = getArea(map, x, y-1, visited_plots, region_plots)
        area += aux_area
    if y+1 < len(map) and map[y+1][x] == map[y][x] and (x, y+1) not in visited_plots:
        aux_area, region_plots = getArea(map, x, y+1, visited_plots, region_plots)
        area += aux_area
    if x-1 >= 0 and map[y][x-1] == map[y][x] and (x-1, y) not in visited_plots:
        aux_area, region_plots = getArea(map, x-1, y, visited_plots, region_plots)
        area += aux_area
    if x+1 < len(map[0]) and map[y][x+1] == map[y][x] and (x+1, y) not in visited_plots:
        aux_area, region_plots = getArea(map, x+1, y, visited_plots, region_plots)
        area += aux_area

    return area, region_plots

def getSides(region_plots):
    sides = 0
    
    for x, y in region_plots:
        
    
    return sides

# This function calculates the area and perimeter for a certain region (the region of the (x,y) plot)
def getRegionAreaSides(map, x, y, visited_plots):
    area, region_plots = getArea(map, x, y, visited_plots, set())
    sides = getSides(region_plots)
    print(map[y][x], area, sides)

    return area, sides


def calculateFencePrice(map):
    fence_price = 0
    visited_plots = set()

    for y, line in enumerate(map):
        for x, plots in enumerate(line):
            # If the region of this plot has not yet been analised
            if (x,y) not in visited_plots:
                area, sides = getRegionAreaSides(map, x, y, visited_plots)
                fence_price += area * sides
    
    return fence_price

price = calculateFencePrice(map)
print(f"The total fence price is: {price}")