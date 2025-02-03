#include <stdio.h>
#include <stdlib.h>
#include "arvore.h"
#include <time.h>
#include <math.h>

long int calcularMedia(long int numeros[], int n)
{
    long int soma = 0.0;
    for (int i = 0; i < n; i++)
    {
        soma += numeros[i];
    }
    return soma / n;
}

long int calcularDesvioPadrao(long int numeros[], int n)
{
    long int media = calcularMedia(numeros, n);
    long int somaQuadrados = 0.0;

    // Soma dos quadrados das diferenças em relação à média
    for (int i = 0; i < n; i++)
    {
        somaQuadrados += (numeros[i] - media) * (numeros[i] - media);
    }

    // Desvio padrão é a raiz quadrada da soma dividida pelo número de elementos
    return sqrt(somaQuadrados / n);
}

int main()
{
    printf("EXERCICIO 2\n");
    srand(time(NULL));
    long int vet[10];
    tArvore *raiz = NULL;

    /*for (int j = 0; j < 10; j++)
    {
        raiz = NULL;
        for (int i = 0; i < 1000000; i++)
        {
            int n = rand();
            raiz = colocaNaArvore(raiz, n);
        }
        vet[j] = altura(raiz);
        printf("Altura da Arvore %d: %d\n", j + 1, altura(raiz));
        liberaArvore(raiz);
    }

    long int media = calcularMedia(vet, 10), desvio = calcularDesvioPadrao(vet, 10);

    printf("Média: %ld\nDesvio : %ld\n", media, desvio);*/

    printf("\n\n\nEXERCICIO 3");

    raiz = NULL;
    for (int i = 0; i < 10000000; i++)
    {
        int n = rand();
        raiz = colocaNaArvore(raiz, n);
    }

    clock_t start = clock();
    printPreOrder(raiz);
    clock_t end = clock();
    double seconds = ((double)end - start);
    printf("Pre Order: %lf\n ", seconds);

    start = clock();
    printInOrder(raiz);
    end = clock();
    seconds = ((double)end - start);
    printf("In Order: %lf\n ", seconds);

    start = clock();
    printPostOrder(raiz);
    end = clock();
    seconds = ((double)end - start);
    printf("Post Order: %lf\n ", seconds);

    liberaArvore(raiz);

    return 1;
}