import heapq

def readParseInput(filePath, num_corrupted = None):
    coordinates = []
    with open(filePath, "r") as file:
        for line in file:
            line = line.strip()
            coordinates_list = line.split(",")
            coordinates.append((int(coordinates_list[0]), int(coordinates_list[1])))

    if num_corrupted is None:
        return coordinates
    return coordinates[:num_corrupted]

def printCorruptedMemorySpace(corrupted_coordinates, dimX, dimY):
    i = 0
    while i < dimY:
        j = 0
        while j < dimX:
            if (j, i) in corrupted_coordinates:
                print('#', end='')
            else:
                print('.', end='')
            j += 1
        print('')
        i += 1

def printCorruptedMemorySpaceWithPath(corrupted_coordinates, dimX, dimY, path):
    i = 0
    while i < dimY:
        j = 0
        while j < dimX:
            if (j, i) in corrupted_coordinates:
                print('#', end='')
            elif (j, i) in path:
                print('O', end='')
            else:
                print('.', end='')
            j += 1
        print('')
        i += 1

# ------------------------------------------- A* --------------------------------------------------------

# (The direct distance between 2 points in a grid)
def manhattan_distance(a, b):
    return abs(a[0] - b[0]) + abs(a[1] - b[1])

# The A* algorithm
def aStar(corrupted_coordinates, start, end):
    endX, endY = end
    open_set = []
    heapq.heappush(open_set, (0, start, 0, [start]))  # Priority queue with tuples (priority, position, g, path)
    visited = {} # Visited nodes with their g (cost to reach them from the start)

    while open_set:
        _, current, g, path = heapq.heappop(open_set)
        if current == end: # Reached the end and found the path
            return g, path

        if current in visited and visited[current] <= g: 
            # If this node was already visited and the path to that node is faster the current one we ignore this path
            continue
        visited[current] = g

        x, y = current
        for dx, dy in [(-1, 0), (1, 0), (0, -1), (0, 1)]: # Try to go Left, right, down and up
            neighbor = (x + dx, y + dy)
            if neighbor not in corrupted_coordinates and 0 <= neighbor[0] <= endX and 0 <= neighbor[1] <= endY:
                new_g = g + 1 # The cost in all the transitions is always the same
                f = new_g + manhattan_distance(neighbor, end) 
                heapq.heappush(open_set, (f, neighbor, new_g, path + [neighbor]))

    return -1, []  # No path found
