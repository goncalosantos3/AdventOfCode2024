#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

int readParseInput(char *filePath, int **stones, int size){
    char line[1024];
    
    FILE *f = fopen(filePath, "r");

    while(fgets(line, sizeof(line), f)); // Copy file contents to line 
    
    int i = 0;
    if(line != NULL){
        // printf("%s", line);
    
        char *token = strtok(line, " ");
        while(token != NULL){
            (*stones)[i++] = atoi(token);
            token = strtok(NULL, " ");
        }
    }else{
        perror("Invalid input file!");
        exit(1);
    }

    return i;
}

int blink(int *stones, int num_stones, int num_blinks, int size){
    for(int i=0;i<num_blinks;i++){
        printf("Blink %d!\n", i);
        for(int j=0;j<num_stones;j++){
            char str[20];
            sprintf(str, "%d", stones[j]);

            if(stones[j] == 0){
                stones[j] = 1;
            }else if(strlen(str)%2 == 0){
                char str1[10];
                char str2[10];

                strncpy(str1, str, strlen(str)/2);
                strncpy(str2, str + strlen(str)/2, strlen(str)/2);
                str1[strlen(str)/2] = '\0';
                str2[strlen(str)/2] = '\0';

                if(size - num_stones <= 100){ // Resize dynamic array size
                    size += 300;
                    int *temp = realloc(stones, size * sizeof(int));
                    if (temp == NULL) {
                        perror("Memory reallocation failed");
                        exit(1);
                    }
                    stones = temp;
                }

                stones[j] = atoi(str1);
                for (int k = num_stones; k > j + 1; k--) {
                    stones[k] = stones[k - 1];
                }
                stones[j+1] = atoi(str2);
                num_stones++;
                j += 1;
            }else{
                stones[j] *= 2024;
            }
        }
    }

    return num_stones;
}

// For the second challenge (blinking 75 times) python is too slow
// Let's try C!
int main(int argc, char *argv[]){
    if(argc < 3){
        perror("You have to specify the path to the input file and the number of blinks!");
        return 1;
    }

    int num_blinks = atoi(argv[2]);
    int size = 1500;
    int *stones = malloc(size * sizeof(int));
    int num_stones = readParseInput(argv[1], &stones, size);

    int final_num_stones = blink(stones, num_stones, num_blinks, size);
    printf("The number of stones after blinking %d times is: %d\n", num_blinks, final_num_stones);
    return 0;
}