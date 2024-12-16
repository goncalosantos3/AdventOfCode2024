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

def getPerimeter(region_plots):
    perimeter = 0
    
    for x1, y1 in region_plots:
        local_perimeter = 4
        for x2, y2 in region_plots:
            if x1 != x2 or y1 != y2: # Verify that it's not the same plot
                if x1+1 == x2 and y1 == y2: # Second plot on the right 
                    local_perimeter -= 1
                elif x1-1 == x2 and y1 == y2: # Second plot on the left
                    local_perimeter -= 1
                elif x1 == x2 and y1+1 == y2: # Second plot on top
                    local_perimeter -= 1
                elif x1 == x2 and y1-1 == y2:
                    local_perimeter -= 1
        perimeter += local_perimeter
    
    return perimeter

# This function calculates the area and perimeter for a certain region (the region of the (x,y) plot)
def getRegionAreaPerimeter(map, x, y, visited_plots):
    area, region_plots = getArea(map, x, y, visited_plots, set())
    perimeter = getPerimeter(region_plots)

    return area, perimeter


def calculateFencePrice(map):
    fence_price = 0
    visited_plots = set()

    for y, line in enumerate(map):
        for x, plots in enumerate(line):
            # If the region of this plot has not yet been analised
            if (x,y) not in visited_plots:
                area, perimeter = getRegionAreaPerimeter(map, x, y, visited_plots)
                fence_price += area * perimeter
    
    return fence_price

price = calculateFencePrice(map)
print(f"The total fence price is: {price}")