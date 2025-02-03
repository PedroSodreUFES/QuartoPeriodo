#include <time.h>
#include <stdlib.h>
#include <stdio.h>
#include "item.h"
#include <string.h>

void selectionSort(long int vet[], long int n)
{
    long int i, j;
    for (i = 0; i < n; i++)
    {
        for (j = i + 1; j < n; j++)
        {
            if (vet[i] > vet[j])
            {
                long int aux = vet[i];
                vet[i] = vet[j];
                vet[j] = aux;
            }
        }
    }
}

void insertionSort(long int vet[], long int n)
{
    long int i, j;
    // expande o tamanho do vetor
    for (i = 1; i < n; i++)
    {
        long int valor = vet[i];
        for (j = i - 1; j > -1; j--)
        {
            if (valor < vet[j])
            {
                vet[j + 1] = vet[j];
                vet[j] = valor;
            }
            else
            {
                break;
            }
        }
    }
}

void bubbleSort(long int vet[], long int n)
{
    long int i, j;
    for (i = n; i > 0; i--)
    {
        for (j = 0; j < i - 1; j++)
        {
            if (vet[j] > vet[j + 1])
            {
                long int aux = vet[j + 1];
                vet[j + 1] = vet[j];
                vet[j] = aux;
            }
        }
    }
}

void shakerSort(long int vet[], long int n)
{
    long int bottom, top, swapped, i, aux;
    bottom = 0;
    top = n - 1;
    swapped = 0;
    while (swapped == 0 && bottom < top)
    {
        swapped=1;
        for (i = bottom; i < top; i = i + 1) // bubble sort
        {
            if (vet[i] > vet[i + 1])
            {
                aux = vet[i];
                vet[i] = vet[i + 1];
                vet[i + 1] = aux; 
                swapped = 0;
            }
        }
        top = top - 1;
        for (i = top; i > bottom; i = i - 1) // bubble sort inverso
        {
            if (vet[i] < vet[i - 1])
            {
                aux = vet[i];
                vet[i] = vet[i - 1];
                vet[i - 1] = aux;
                swapped = 0;
            }
        }
        bottom = bottom + 1;
    }
}


int main()
{
    char string[200];
    for(int i = 0 ; i < 4 ; i++)
    {   
        if(i == 0) strcpy(string, "./input/in/nearly_sorted/");
        else if(i == 1) strcpy(string, "./input/in/reverse_sorted/");
        else if(i == 2) strcpy(string, "./input/in/sorted/");
        else strcpy(string, "./input/in/unif_rand/");

        for(int j = 0 ; j < 3 ; j++)
        {
            char string2[200];
            long int *vet, n;
            if(j==0) {sprintf(string2, "%s1K.txt", string); n = 1000 ; }
            else if(j==1) {sprintf(string2, "%s10K.txt", string); n = 10000 ; }
            else { sprintf(string2, "%s100K.txt", string); n = 100000 ; }
            vet = malloc(n * sizeof(long int));

            for(int k=0 ; k < 4 ; k++)
            {
                FILE *f = fopen(string2, "r");
                if(f == NULL)
                {
                    printf("Arquivo não abriu\n");
                    exit(1);
                }
                for(int i =0 ; i<n ; i++)
                {
                    fscanf(f, "%ld", &vet[i]);
                }

                if(k==0)
                {
                    clock_t start = clock();
                    selectionSort(vet, n);
                    clock_t end = clock();
                    double seconds = ((double)end - start);
                    printf("Selection sort demorou %lf na entrada %s\n", seconds, string2);
                }
                else if(k==1)
                {
                    clock_t start = clock();
                    insertionSort(vet, n);
                    clock_t end = clock();
                    double seconds = ((double)end - start);
                    printf("Insertion sort demorou %lf na entrada %s\n", seconds, string2);
                }
                else if(k==2)
                {
                    clock_t start = clock();
                    bubbleSort(vet, n);
                    clock_t end = clock();
                    double seconds = ((double)end - start);
                    printf("Bubble sort demorou %lf na entrada %s\n", seconds, string2);
                }
                else
                {
                    clock_t start = clock();
                    selectionSort(vet, n);
                    clock_t end = clock();
                    double seconds = ((double)end - start);
                    printf("Shaker sort demorou %lf na entrada %s\n", seconds, string2);
                }
                fclose(f);
            }
            printf("\n");
            free(vet);
        }
        printf("\n\n\n");
    }
}
