#include <stdio.h>
#include <stdlib.h>

#include "functions.h"

int calculateCombs(Combination **combs, int n1, int n2, int n3, int size){
    int divsN1 = n3/n1;
    int divsN2 = n3/n2;

    int m=0;
    for(int i=0; i<=divsN1; i++){
        for(int j=divsN2; j>=0; j--){
            if(n1*i + n2*j == n3){
                Combination c;
                c.occr_n1 = i;
                c.occr_n2 = j;
                c.tokens = 3*i + j;

                (*combs)[m++] = c;
            }

            if(m == size){
                size += 30;
                *combs = realloc(*combs, size*sizeof(Combination));
            }
        }
    }

    return m; // Number of possible combinations
}

// Pressing A costs 3 tokens while pressing B costs 1 token
int calculateMinimumTokens(ClawMachine machine){
    int minimumTokens = 0;

    int size = 30;
    Combination *combs_X = malloc(size*sizeof(Combination));
    Combination *combs_Y = malloc(size*sizeof(Combination));
    int nr_combs_X = calculateCombs(&combs_X, machine.aX, machine.bX, machine.prizeX, size);
    int nr_combs_Y = calculateCombs(&combs_Y, machine.aY, machine.bY, machine.prizeY, size);

    for(int i=0; i<nr_combs_X; i++){
        for(int j=0; j<nr_combs_Y; j++){
            if(combs_X[i].occr_n1 == combs_Y[j].occr_n1 && 
                combs_X[i].occr_n2 == combs_Y[j].occr_n2){ // Found a possible combination of A and B
                minimumTokens = combs_X[i].tokens;
            }
        }
    }

    return minimumTokens;
}

int calculateTotalMinimumTokens(ClawMachine *machines, int nr_machines){
    int tokens = 0;
    
    for(int i=0; i<nr_machines; i++){
        tokens += calculateMinimumTokens(machines[i]);
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

    // To check the machines that were read
    // for(int i=0; i<nr_machines;i++){
    //     printf("Machine %d:\n", i);
    //     printf("%d; %d\n%d; %d\n%d; %d\n\n", machines[i].aX, machines[i].aY, machines[i].bX, machines[i].bY, machines[i].prizeX, machines[i].prizeY);
    // }

    int min_tokens = calculateTotalMinimumTokens(machines, nr_machines);
    printf("The mininum of tokens needed to win all possible prizes is: %d\n", min_tokens);
    return 0;
}