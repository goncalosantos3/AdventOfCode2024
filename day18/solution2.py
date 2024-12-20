import sys

import functions

if len(sys.argv) < 4:
    sys.exit("You have to provide the input file location and the x and y dimensions!")

corrupted_coordinates = functions.readParseInput(sys.argv[1])

print(corrupted_coordinates)
dimX = int(sys.argv[2])
dimY = int(sys.argv[3])

functions.printCorruptedMemorySpace(corrupted_coordinates, dimX, dimY)

corrupted_bytes = 1024 # We already know that the first 1024 bytes dont block the path
while corrupted_bytes < len(corrupted_coordinates):
    print(f"{corrupted_bytes} corrupted bytes!")
    steps, path = functions.aStar(corrupted_coordinates[:corrupted_bytes], (0, 0), (dimX-1, dimY-1))
    if steps == -1: # There is no path to the end!
        break
    
    while corrupted_bytes <= len(corrupted_coordinates) and corrupted_coordinates[corrupted_bytes-1] not in path:
        corrupted_bytes += 1

print(f"The coordinates of the corrupted memory that blocks the path to the end is: {corrupted_coordinates[corrupted_bytes-1]}")