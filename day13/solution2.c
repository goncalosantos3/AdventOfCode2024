#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <time.h>

#include "functions.h"

// Pressing A costs 3 tokens while pressing B costs 1 token
long long int calculateTokens(ClawMachine machine){
    long long int prizeX = machine.prizeX + 10000000000000;
    long long int prizeY = machine.prizeY + 10000000000000;

    // We know that the equation to get the number of A presses and B presses is:
    // A * a_X + B * b_X = prize_X
    // A * a_Y + B * b_Y = prize_Y
    // Where A and B are the button presses;

    // And applying to these 2 equations the Cramer's Rule we get:
    // A = (p_X*b_Y - prize_Y*b_X) / (a_X*b_Y - a_Y*b_X)
    // B = (a_X*p_Y - a_Y*p_X) / (a_X*b_Y - a_Y*b_X)

    int det = (machine.aX * machine.bY - machine.aY * machine.bX);
    long long int a_presses = ((prizeX*machine.bY) - (prizeY*machine.bX)) / det;
    long long int b_presses = ((machine.aX*prizeY) - (machine.aY*prizeX)) / det;

    if(a_presses*machine.aX + b_presses*machine.bX == prizeX && a_presses*machine.aY + b_presses*machine.bY == prizeY){
        printf("A presses: %lld; B presses: %lld\n", a_presses, b_presses);
        return 3*a_presses + b_presses;
    }
    return 0;
}

long long int calculateTotalMinimumTokens(ClawMachine *machines, int nr_machines){
    long long int tokens = 0;
    
    for(int i=0; i<nr_machines; i++){
        tokens += calculateTokens(machines[i]);
    }

    return tokens;
}

int main(int argc, char *argv[]){
    if(argc < 2){
        perror("You need to specify the input file location!");
        return -1;
    }

    int size = 1000;
    ClawMachine *machines = malloc(1000*sizeof(ClawMachine));
    int nr_machines = readParseInput(argv[1], &machines, size);

    long long int tokens = calculateTotalMinimumTokens(machines, nr_machines);
    printf("The mininum of tokens needed to win all possible prizes is: %lld\n", tokens);
    return 0;
}