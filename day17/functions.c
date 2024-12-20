#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#include "functions.h"

// Returns the length of the program
int readParseInput(char *filePath, Device *device){
    int length = 0;
    int program = 0;
    char line[1024];
    FILE *f = fopen(filePath, "r");

    while(fgets(line, sizeof(line), f)){ // Read file content line by line  
        if(program == 0 && strcmp(line, "\n") != 0){
            int value;
            if(sscanf(line, "Register A: %d", &value) == 1){
                device->registerA = value;
            }else if(sscanf(line, "Register B: %d", &value) == 1){
                device->registerB = value;
            }else if(sscanf(line, "Register C: %d", &value) == 1){
                device->registerC = value;
            }else{
                perror("Invalid input file!");
                return 0;
            }
        }else if(strcmp(line, "\n") == 0){
            program = 1;
        }else{
            char p[1024];
            if(sscanf(line, "Program: %s", p) == 1){
                length = (strlen(p)+1)/2;
                int *program = malloc(length*sizeof(int));
                int j = 0;
                for(int i=0; i<strlen(p); i++){
                    if(i%2 == 0){
                        program[j++] = p[i] - '0';
                    }
                }
                device->program = program;
            }else{
                perror("Invalid input file!");
                return 0;
            }
        }
    }
    return length;
}