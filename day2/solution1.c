#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>  

#include "functions.h"

#define MAX_NUMS_PER_REPORT 30

bool check(int *report, int size){
    bool increasing = false;
    if(size > 1){
        if(report[0] < report[1]){
            increasing = true;
        }

        for(int i=0; i<size-1; i++){
            int diff = report[i] - report[i+1];
            if(increasing && diff >= 0){
                return false;
            }else if(!increasing && diff <= 0){
                return false;
            }else{
                int absDiff = abs(diff);
                if(absDiff < 1 || absDiff > 3){
                    return false;
                }
            }
        }
    }
    return true;
}

int main(int argc, char *argv[]){
    if(argc < 2){
        perror("You need to specify the input file location!");
        return 1;
    }

    FILE *f = fopen(argv[1], "r");

    int safe = 0;
    int *report = malloc(MAX_NUMS_PER_REPORT*sizeof(int)); 
    int i = 0;
    while(true){
        int size = getReport(f, report);
        printf("%d\n", i++);
        if(size > 0){
            bool status = check(report, size);
            if(status){
                safe++;
            }   
        else if(size == 0) break; // EOF
        }else{
            perror("Invalid line input!");
            return 1;
        }
    }

    printf("There are %d safe reports!\n", safe);
    return 0;
}   