#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "functions.h"

char *getInstructions(FILE *f){
    // Find the file size
    fseek(f, 0, SEEK_END); // Go to the end of the file
    long fSize = ftell(f); 
    rewind(f); // Go back to the beggining of the file

    char *res = malloc((fSize + 1) * sizeof(char)); // +1 for the '\0' character
    fread(res, sizeof(char), fSize, f); // Read entire file content to res
    res[fSize] = '\0';

    fclose(f);
    return res;
}