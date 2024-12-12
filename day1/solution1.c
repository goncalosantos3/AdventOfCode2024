#include <stdio.h>
#include <stdlib.h>

#include "functions.h"

void printArray(int arr[], int size) {
    for (int i = 0; i < size; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

int getDistanceBetweenLists(int *listOne, int *listTwo, int size){
    int distance = 0;

    for(int i=0; i<size; i++){
        distance += abs(listOne[i]-listTwo[i]);
    }   

    return distance;
}

int main(int argc, char *argv[]){
    if(argc < 2){
        perror("You need to input the path to the input file!");
        return 1;
    }
    
    int *listOne = malloc(100*sizeof(int));
    int *listTwo = malloc(100*sizeof(int));

    FILE *f = fopen(argv[1], "r");
    int size = readParseInputFile(f, &listOne, &listTwo);
    fclose(f);

    quickSort(listOne, 0, size-1);
    quickSort(listTwo, 0, size-1);

    int distance = getDistanceBetweenLists(listOne, listTwo, size);
    printf("The answer is: %d!\n", distance);

    return 0;
}