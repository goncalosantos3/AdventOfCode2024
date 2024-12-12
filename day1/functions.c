#include <stdlib.h>

#include "functions.h"

#define MAX_LINE_LENGTH 30

void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

int partition(int arr[], int low, int high) {
    int pivot = arr[high]; 
    int i = low - 1;     

    for (int j = low; j < high; j++) {
        if (arr[j] < pivot) {
            i++;            
            swap(&arr[i], &arr[j]);
        }
    }
    swap(&arr[i + 1], &arr[high]); 
    return i + 1;
}

void quickSort(int arr[], int low, int high) {
    if (low < high) {
        int pi = partition(arr, low, high); 

        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}

int readParseInputFile(FILE *f, int **listOne, int **listTwo){
    char line[MAX_LINE_LENGTH];
    int currentSize = 100;
    int current = 0;

    while(fgets(line, sizeof(line), f)){
        int left, right;
        if(sscanf(line, "%d\t%d\n", &left, &right) == 2){
            if(current >= currentSize){
                currentSize += 100;
                *listOne = realloc(*listOne, currentSize*sizeof(int));
                *listTwo = realloc(*listTwo, currentSize*sizeof(int));
            }
            (*listOne)[current] = left;
            (*listTwo)[current] = right;
            current++;
        }else{
            perror("Invalid input!");
            return 1;
        }
    }

    return current;
}