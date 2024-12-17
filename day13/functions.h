#ifndef FUNCTIONS_H
#define FUNCTIONS_H

#include <stdio.h>

typedef struct combination{
    int occr_n1;
    int occr_n2; 
    int tokens;
} Combination;

typedef struct clawMachine{
    int aX;
    int bX;
    int aY;
    int bY;
    int prizeX;
    int prizeY;
} ClawMachine;

int readParseInput(char *filePath, ClawMachine **machines, int size);

#endif