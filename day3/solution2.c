#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <regex.h>
#include <stdbool.h> 

#include "functions.h"

int getResult(char *instrutions){
    bool enabled = true;
    int result = 0;
    regex_t regex1, regex2, regex3;
    regmatch_t match;

    regcomp(&regex1, "mul\\([0-9]+,[0-9]+\\)|do\\(\\)|don't\\(\\)", REG_EXTENDED);
    // regcomp(&regex2, "do()", REG_EXTENDED);
    // regcomp(&regex3, "don't()", REG_EXTENDED);

    while (*instrutions) {
        if (regexec(&regex1, instrutions, 1, &match, 0) == 0) {
            int start = match.rm_so;
            int end = match.rm_eo;
            
            char instruction[end-start];
            strncpy(instruction, instrutions + start, end-start);
            instruction[end-start] = '\0';

            if(strcmp(instruction, "do()") == 0){
                enabled = true;
            }else if(strcmp(instruction, "don't()") == 0){
                enabled = false;
            }else{
                if(enabled){
                    int num1, num2;
                    sscanf(instruction, "mul(%d,%d)", &num1, &num2);
                    result += num1*num2; 
                }    
            }       

            instrutions += end;
        } else {
            break;
        }
    }

    regfree(&regex1);
    // regfree(&regex2);
    // regfree(&regex3);
    return result;
}

int main(int argc, char *argv[]){
    if(argc < 2){
        perror("You have to specify the input file location!");
        return 1;
    }

    FILE *f = fopen(argv[1], "r");
    char *instructions = getInstructions(f);
    
    int result = getResult(instructions);

    printf("The result is: %d\n", result);
    return 0;
}