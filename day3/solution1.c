#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <regex.h>

#include "functions.h"

int getResult(char *instrutions){
    int result = 0;
    regex_t regex;
    regmatch_t match;

    regcomp(&regex, "mul\\([0-9]+,[0-9]+\\)", REG_EXTENDED);

    while (*instrutions) {
        if (regexec(&regex, instrutions, 1, &match, 0) == 0) {
            int start = match.rm_so;
            int end = match.rm_eo;
            
            char instruction[end-start];
            strncpy(instruction, instrutions + start, end-start);
            instruction[end-start] = '\0';
            
            int num1, num2;
            sscanf(instruction, "mul(%d,%d)", &num1, &num2);
            result += num1*num2;            

            instrutions += end;
        } else {
            break;
        }
    }

    regfree(&regex);
    return result;
}

int main(int argc, char* argv[]){
    if(argc < 2){
        perror("You have to give the input file location!");
        return 1;
    }

    FILE *f = fopen(argv[1], "r");
    if(f == NULL){
        perror("Error opening passed file.");
        return 1;
    }

    char *instrutions = getInstructions(f);
    int result = getResult(instrutions);

    printf("The result is: %d!\n", result);
    return 0;
}