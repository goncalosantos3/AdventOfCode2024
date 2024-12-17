#include <stdlib.h>
#include <stdio.h>

#include "functions.h"

int readParseInput(char *filePath, ClawMachine **machines, int size){
    FILE *f = fopen(filePath, "r");
    char line[1024];

    int i = 0;
    while(fgets(line, sizeof(line), f)){
        int x, y;
        if(sscanf(line, "Button A: X+%d, Y+%d\n", &x, &y) == 2){ // Button A
            if(i >= size){ // Increase size of dynamic array if needed
                size += 100;
                *machines = realloc(*machines, size*sizeof(ClawMachine));
            }
            ClawMachine cm;
            cm.aX = x;
            cm.aY = y;

            (*machines)[i] = cm;
        }else if(sscanf(line, "Button B: X+%d, Y+%d\n", &x, &y) == 2){ // Button B
            (*machines)[i].bX = x;  
            (*machines)[i].bY = y;
        }else if(sscanf(line, "Prize: X=%d, Y=%d\n", &x, &y) == 2){ // Prize
            ClawMachine cm = (*machines)[i];
            (*machines)[i].prizeX = x;  
            (*machines)[i].prizeY = y;
        }else if(line[0] == '\n'){ // Invalid line
            i++;
        }else{
            printf("%s", line);
            perror("Invalid input!");
            exit(-1);
        }
    }

    i++;
    return i;
}