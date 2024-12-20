#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>

#include "functions.h"

char *toBaseTwo(int n) {
    char *binaryString = malloc(33*sizeof(char));
    binaryString[32] = '\0'; // Null-terminate the string

    for (int i = 31; i >= 0; i--) {
        binaryString[i] = (n & 1) ? '1' : '0'; 
        n >>= 1; 
    }

    return binaryString;
}

int toDecimal(char *bin){
    int result = 0;
 
    for(int i=0; i<33; i++){
        if(bin[i] == '1'){
            result += pow(2,31-i);
        }
    }
    printf("%d\n", result);
    return result;
}

char *bitWiseXOR(char *bin1, char *bin2){
    char *bin = malloc(33*sizeof(char)); bin[32] = '\0';
    for(int i=0; i<32; i++){
        if(bin1[i] == bin2[i])
            bin[i] = '0';
        else
            bin[i] = '1';
    }
    return bin;
}

int comboOperand(int operand, Device *device){
    if(operand >= 0 && operand <= 3){
        return operand;
    }
    if(operand == 4){
        return device->registerA;
    }
    if(operand == 5){
        return device->registerB;
    }
    if(operand == 6){
        return device->registerC;
    }
    if(operand == 7){
        perror("Invalid combo operand 7!");
        exit(-1);
    }
}

void inverseComboOperand(int expected, int operand, Device *device){
    if(operand >= 0 && operand <= 3){ // This gives ous 0 information
        return;
    }else if(operand == 4){
        device->registerA = expected;
    }else if(operand == 5){
        device->registerB = expected;
    }else if(operand == 6){
        device->registerC = expected;
    }else if(operand == 7){
        perror("Invalid combo operand 7!");
        exit(-1);
    }
}

void executeProgram(Device *device, int program_length){
    // We know that device->output == device->program
    int output = 0; // Represents the index of the output produced
    for(int i=0; i<program_length; i += 2){
        if(device->program[i] == 5){
            int expected_output = device->program[output];
            inverseComboOperand(expected_output, device->program[i+1], device);
        }else if(device->program[i] == 3){
            i = device->program[i+1] - 2;
        }

        // printf("%d\n", i);
        // if(device->program[i] == 0){ // adv instruction
        //     printf("ADV: %d, %d\n", device->registerA, comboOperand(device->program[i+1], device));
        //     int adv = (int) device->registerA / pow(2, comboOperand(device->program[i+1], device));
        //     device->registerA = adv;
        //     printf("Stored in register A: %d\n-----------\n", device->registerA);
        // }else if(device->program[i] == 1){ // bxl instruction
        //     printf("BXL: %d, %d\n", device->registerB, device->program[i+1]);
        //     char *bin1 = toBaseTwo(device->registerB);
        //     char *bin2 = toBaseTwo(device->program[i+1]);
        //     char *bin = bitWiseXOR(bin1, bin2);
        //     device->registerB = toDecimal(bin); 
        //     printf("Bitwise XOR: %s XOR %s = %s\n", bin1, bin2, bin);
        //     printf("Stored in register B: %d\n---------------\n", device->registerB);
        // }else if(device->program[i] == 2){ // bst instruction
        //     printf("BST: %d -> ", device->program[i+1]);
        //     printf("%d\n", comboOperand(device->program[i+1], device)%8);
        //     device->registerB = comboOperand(device->program[i+1], device)%8;
        //     printf("Stored in register B: %d\n-----------------\n", device->registerB);
        // }else if(device->program[i] == 3){ // jnz instruction
        //     if(device->registerA != 0){
        //         printf("JNZ: %d\n", device->program[i+1]);    
        //         i = device->program[i+1] - 2; // -2 to counter the increment of the i in the for loop
        //     }
        // }else if(device->program[i] == 4){ // bxc instruction
        //     printf("BXC: %d, %d\n", device->registerB, device->registerC);  
        //     char *bin1 = toBaseTwo(device->registerB);
        //     char *bin2 = toBaseTwo(device->registerC);
        //     char *bin = bitWiseXOR(bin1, bin2);
        //     device->registerB = toDecimal(bin);
        //     printf("Bitwise XOR: %s XOR %s = %s\n", bin1, bin2, bin);
        //     printf("Stored in register B: %d\n------------\n", device->registerB);
        // }else if(device->program[i] == 5){ // out instruction
        //     printf("OUT: %d, %d\n------------\n", device->program[i+1], comboOperand(device->program[i+1], device)%8);  
        //     if(device->output_size - strlen(device->output) < 10){
        //         device->output_size += 100;
        //         device->output = realloc(device->output, device->output_size*sizeof(char));
        //     }
        //     char aux[10];
        //     sprintf(aux, "%d", comboOperand(device->program[i+1], device)%8);
        //     device->output[strlen(device->output)] = aux[0];
        // }else if(device->program[i] == 6){ // bdv instruction
        //     printf("BDV: %d, %d\n", device->registerA, comboOperand(device->program[i+1], device)); 
        //     int bdv = (int) device->registerA / pow(2, comboOperand(device->program[i+1], device));
        //     device->registerB = bdv;
        //     printf("Stored in register B: %d\n-----------\n", device->registerB);
        // }else if(device->program[i] == 7){ // cdv instruction
        //     printf("CDV: %d, %d\n", device->registerA, comboOperand(device->program[i+1], device)); 
        //     int cdv = (int) device->registerA / pow(2, comboOperand(device->program[i+1], device));
        //     device->registerC = cdv;
        //     printf("Stored in register C: %d\n-----------\n", device->registerC);
        // }
    }

    // if(strlen(device->output) > 1){
    //     device->output_size += strlen(device->output)-1;
    //     device->output = realloc(device->output, device->output_size*sizeof(char)); // Add space memory for the commas
    //     // Setup output of the program
    //     for(int i=0; i<strlen(device->output); i+=2){
    //         for(int j=strlen(device->output); j>i+1; j--){
    //             device->output[j] = device->output[j-1];
    //         }
    //         device->output[i+1] = ',';
    //     }
    //     device->output[strlen(device->output)-1] = '\0';
    // }
}

int main(int argc, char *argv[]){
    if(argc < 2){
        perror("You have to provide the input file path!");
        exit(-1);
    }

    Device *device = malloc(sizeof(Device));
    device->output = malloc(100*sizeof(char));
    device->output_size = 100;
    int program_length = readParseInput(argv[1], device);
    device->registerA = -1; // Register A value is corrupted

    if(program_length != 0){
        printf("%d; %d; %d\n", device->registerA, device->registerB, device->registerC);
        for(int i=0; i<program_length; i++){
            printf("%d ", device->program[i]);
        }
        printf("\n\n");
    }else 
        printf("Failed to read the input file!");

    executeProgram(device, program_length);
    printf("Program execution output: %s\n", device->output);
    return 0;
}