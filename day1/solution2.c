#include <stdio.h>
#include <stdlib.h>

#include "functions.h"

int search(int num, int index, int size, int *listTwo){
    int matches = 1;

    for(int i=index-1; i>=0; i--){
        if(listTwo[i] == num){
            matches++;
        }
    }

    for(int i=index+1; i<size; i++){
        if(listTwo[i] == num){
            matches++;
        }
    }

    return matches;
}

int binarySearch(int num, int *listTwo, int size){    
    int low = 0;
    int high = size-1;

    while(low <= high){ 
        int mid = low + (high-low) / 2;

        if(listTwo[mid] == num){
            return search(num, mid, size, listTwo);
        }

        if(listTwo[mid] < num){
            low = mid + 1;
        }else{
            high = mid - 1;
        }
    }

    return 0;
}

int calculateSimilarity(int *listOne, int *listTwo, int size){
    int similarity = 0;

    for(int i=0; i<size; i++){
        similarity += binarySearch(listOne[i], listTwo, size) * listOne[i];
    }
    return similarity;
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

    int similarity = calculateSimilarity(listOne, listTwo, size);
    printf("The calculated similarity is: %d!\n", similarity);

    return similarity;
}