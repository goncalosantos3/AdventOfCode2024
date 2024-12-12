#include <stdio.h>
#include <string.h>

#include "functions.h"

#define MAX_LINE_LENGTH 100

// Not the most optimal
int getReport(FILE *f, int *report){
    int safe = 0;
    char line[MAX_LINE_LENGTH];

    while(fgets(line, sizeof(line), f)){
        printf(line);
        if(line != NULL){
            int i = 0;
            char *token = strtok(line, " ");
            while(token != NULL){
                report[i++] = atoi(token);
                token = strtok(NULL, " ");
            }
            return i;
        }else{
            return 0;
        }
    }

    return -1;
}