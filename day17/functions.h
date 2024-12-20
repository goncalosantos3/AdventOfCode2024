#ifndef FUNCTIONS_H
#define FUNCTIONS_H

typedef struct device{
    int registerA;
    int registerB;
    int registerC;
    int *program;
    char *output;
    int output_size;
} Device;

int readParseInput(char *filePath, Device *device);

#endif