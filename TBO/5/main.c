#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include <stdbool.h>

void verificaOrdenado(int vet[], int n) {
    bool estaOrdenado = true;

    for (int i = 0; i < n - 1; i++) {
        if (vet[i] > vet[i + 1]) {
            estaOrdenado = false;
            break;
        }
    }

    if (estaOrdenado) {
        printf("Ordenado\n");
    } else {
        printf("Nao esta ordenado\n");
    }
}

void printArray(int vet[], int size) {
    for (int i = 0; i < size; i++)
        printf("%d ", vet[i]);
    printf("\n");
}

void insertionSort(int vet[], int lo, int hi)
{
    for (int i = lo + 1; i <= hi; i++)
    {
        int key = vet[i];
        int j = i - 1;

        // Mover elementos maiores que a chave para frente
        while (j >= lo && vet[j] > key)
        {
            vet[j + 1] = vet[j];
            j--;
        }
        vet[j + 1] = key;
    }
}

// merge skip
void mergeSkip(int vet[], int lo, int mid, int hi)
{
    // Verifica se já está ordenado para evitar o merge
    if (vet[mid] <= vet[mid + 1])
    {
        return;
    }

    int n1 = mid - lo + 1; // Tamanho do subarray esquerdo
    int n2 = hi - mid;     // Tamanho do subarray direito

    // Arrays temporários
    int leftArr[n1], rightArr[n2];

    // Copiar dados para os arrays temporários
    for (int i = 0; i < n1; i++)
        leftArr[i] = vet[lo + i];
    for (int j = 0; j < n2; j++)
        rightArr[j] = vet[mid + 1 + j];

    // Mesclar os arrays temporários de volta no vetor original
    int i = 0, j = 0, k = lo;
    while (i < n1 && j < n2)
    {
        if (leftArr[i] <= rightArr[j])
        {
            vet[k] = leftArr[i];
            i++;
        }
        else
        {
            vet[k] = rightArr[j];
            j++;
        }
        k++;
    }

    // Copiar os elementos restantes de leftArr[], se houver
    while (i < n1)
    {
        vet[k] = leftArr[i];
        i++;
        k++;
    }

    // Copiar os elementos restantes de rightArr[], se houver
    while (j < n2)
    {
        vet[k] = rightArr[j];
        j++;
        k++;
    }
}

// Função para mesclar dois subarrays em ordem
void merge(int vet[], int lo, int mid, int hi)
{
    int n1 = mid - lo + 1; // Tamanho do subarray esquerdo
    int n2 = hi - mid;     // Tamanho do subarray direito

    // Arrays temporários
    int leftArr[n1], rightArr[n2];

    // Copiar dados para os arrays temporários
    for (int i = 0; i < n1; i++)
        leftArr[i] = vet[lo + i];
    for (int j = 0; j < n2; j++)
        rightArr[j] = vet[mid + 1 + j];

    // Mesclar os arrays temporários de volta no array original
    int i = 0, j = 0, k = lo;
    while (i < n1 && j < n2)
    {
        if (leftArr[i] <= rightArr[j])
        {
            vet[k] = leftArr[i];
            i++;
        }
        else
        {
            vet[k] = rightArr[j];
            j++;
        }
        k++;
    }

    // Copiar os elementos restantes de leftArr[], se houver
    while (i < n1)
    {
        vet[k] = leftArr[i];
        i++;
        k++;
    }

    // Copiar os elementos restantes de rightArr[], se houver
    while (j < n2)
    {
        vet[k] = rightArr[j];
        j++;
        k++;
    }
}

void mergeSortNormal(int vet[], int lo, int hi)
{
    if (lo < hi)
    {
        int mid = lo + (hi - lo) / 2; // Encontrar o ponto do meio

        // Ordenar a primeira e a segunda metade recursivamente
        mergeSortNormal(vet, lo, mid);
        mergeSortNormal(vet, mid + 1, hi);

        // Mesclar as metades ordenadas
        merge(vet, lo, mid, hi);
    }
}

void mergeSortSkip(int vet[], int lo, int hi)
{
    if (lo < hi)
    {
        int mid = lo + (hi - lo) / 2; // Encontrar o ponto do meio

        // Ordenar a primeira e a segunda metade recursivamente
        mergeSortNormal(vet, lo, mid);
        mergeSortNormal(vet, mid + 1, hi);

        // Mesclar as metades ordenadas
        mergeSkip(vet, lo, mid, hi);
    }
}

void mergeSortInsertionSort(int vet[], int lo, int hi)
{
    if (hi - lo <= 10)
    { // cutoff
        insertionSort(vet, lo, hi);
        return;
    }

    if (lo < hi)
    {
        int mid = lo + (hi - lo) / 2; // Encontrar o ponto do meio

        // Ordenar a primeira e a segunda metade recursivamente
        mergeSortInsertionSort(vet, lo, mid);
        mergeSortInsertionSort(vet, mid + 1, hi);

        // Mesclar as metades ordenadas
        merge(vet, lo, mid, hi);
    }
}
void mergeSortSkipInsertionSort(int vet[], int lo, int hi)
{
    if (hi - lo <= 10) { // cutoff
        insertionSort(vet, lo, hi);
        return;
    }

    if (lo < hi) {
        int mid = lo + (hi - lo) / 2; // Encontrar o ponto do meio

        // Ordenar a primeira e a segunda metade recursivamente
        mergeSortSkipInsertionSort(vet, lo, mid);
        mergeSortSkipInsertionSort(vet, mid + 1, hi);

        // Mesclar as metades ordenadas
        mergeSkip(vet, lo, mid, hi);
    }
}

void mergeSortBottomUp(int vet[], int n) {
    for (int width = 1; width < n; width *= 2) 
    { // Incrementa o tamanho dos subarrays
        for (int i = 0; i < n; i += 2 * width) { // Processa pares de subarrays
            int lo = i;
            int mid = i + width - 1;
            int hi = (i + 2 * width - 1 < n) ? i + 2 * width - 1 : n - 1;

            if (mid < hi) { // Realiza o merge apenas se houver dois subarrays
                merge(vet, lo, mid, hi);
            }
        }
    }
}

void mergeSortBottomUpCutoff(int vet[], int n) {
    int cutoff = 10; // Limite para usar o Insertion Sort

    // Use Insertion Sort para subarrays pequenos
    for (int i = 0; i < n; i += cutoff) {
        int hi = (i + cutoff - 1 < n) ? i + cutoff - 1 : n - 1;
        insertionSort(vet, i, hi);
    }

    for (int width = 1; width < n; width *= 2) 
    { // Incrementa o tamanho dos subarrays
        for (int i = 0; i < n; i += 2 * width) { // Processa pares de subarrays
            int lo = i;
            int mid = i + width - 1;
            int hi = (i + 2 * width - 1 < n) ? i + 2 * width - 1 : n - 1;

            if (mid < hi) { // Realiza o merge apenas se houver dois subarrays
                merge(vet, lo, mid, hi);
            }
        }
    }
}

void mergeSortBottomUpSkip(int vet[], int n) {
    for (int width = 1; width < n; width *= 2) 
    { // Incrementa o tamanho dos subarrays
        for (int i = 0; i < n; i += 2 * width) { // Processa pares de subarrays
            int lo = i;
            int mid = i + width - 1;
            int hi = (i + 2 * width - 1 < n) ? i + 2 * width - 1 : n - 1;

            if (mid < hi) { // Realiza o merge apenas se houver dois subarrays
                mergeSkip(vet, lo, mid, hi);
            }
        }
    }
}

int main()
{
    char string[200];
    for (int i = 0; i < 4; i++)
    {
        if (i == 0)
            strcpy(string, "./input/nearly_sorted/");
        else if (i == 1)
            strcpy(string, "./input/reverse_sorted/");
        else if (i == 2)
            strcpy(string, "./input/sorted/");
        else
            strcpy(string, "./input/unif_rand/");

        for (int j = 0; j < 2; j++)
        {
            char string2[200];
            int *vet, n;
            if (j == 0)
            {
                sprintf(string2, "%s100000.txt", string);
                n = 100000;
            }
            else if (j == 1)
            {
                sprintf(string2, "%s1000000.txt", string);
                n = 1000000;
            }
            vet = malloc(n * sizeof(long int));

            for (int k = 0; k < 7; k++)
            {
                FILE *f = fopen(string2, "r");
                if (f == NULL)
                {
                    printf("%s\n", string2);
                    printf("Arquivo não abriu\n");
                    exit(1);
                }
                for (int i = 0; i < n; i++)
                {
                    fscanf(f, "%d", &vet[i]);
                }

                if (k == 0)
                {
                    clock_t start = clock();
                    mergeSortNormal(vet, 0, n - 1);
                    clock_t end = clock();
                    double seconds = ((double)end - start);
                    printf("Merge sort normal demorou %lf na entrada %s\n", seconds, string2);
                }
                else if (k == 1)
                {
                    clock_t start = clock();
                    mergeSortInsertionSort(vet, 0, n - 1);
                    clock_t end = clock();
                    double seconds = ((double)end - start);
                    printf("Merge sort Insertion sort demorou %lf na entrada %s\n", seconds, string2);
                }
                else if (k == 2)
                {
                    clock_t start = clock();
                    mergeSortSkip(vet, 0, n - 1);
                    clock_t end = clock();
                    double seconds = ((double)end - start);
                    printf("Merge sort Merge Skip demorou %lf na entrada %s\n", seconds, string2);
                }
                else if (k == 3)
                {
                    clock_t start = clock();
                    mergeSortSkipInsertionSort(vet,0, n-1);
                    clock_t end = clock();
                    double seconds = ((double)end - start);
                    printf("Merge sort fusion demorou %lf na entrada %s\n", seconds, string2);
                }
                else if (k == 4)
                {
                    clock_t start = clock();
                    mergeSortBottomUp(vet, n);
                    clock_t end = clock();
                    double seconds = ((double)end - start);
                    printf("Merge sort bottom up demorou %lf na entrada %s\n", seconds, string2);
                }
                else if (k == 5)
                {
                    clock_t start = clock();
                    mergeSortBottomUpCutoff(vet, n);
                    clock_t end = clock();
                    double seconds = ((double)end - start);
                    printf("Merge sort bottom-up cut-off demorou %lf na entrada %s\n", seconds, string2);
                }
                else if (k == 6)
                {
                    clock_t start = clock();
                    mergeSortBottomUpSkip(vet, n);
                    clock_t end = clock();
                    double seconds = ((double)end - start);
                    printf("Merge sort bottom-up skip demorou %lf na entrada %s\n", seconds, string2);
                }
                verificaOrdenado(vet, n);
                fclose(f);
            }
            printf("\n");
            free(vet);
        }
        printf("\n");
    }
}