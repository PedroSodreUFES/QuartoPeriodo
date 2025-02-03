#include "arvore.h"
#include <stdlib.h>
#include <stdio.h>

struct arvore
{
    int valor;
    struct arvore *esq, *dir;
};

tArvore *criaArvore(int valor)
{
    tArvore *a = malloc(1 * sizeof(tArvore));
    a->dir = NULL;
    a->esq = NULL;
    a->valor = valor;
    return a;
}

int retornaConteudo(tArvore *a)
{
    return a->valor;
}

int celulaNula(tArvore *a)
{
    if (a == NULL)
    {
        return 1;
    }
    else
    {
        return 0;
    }
}

void atribuiConteudo(tArvore *a, int conteudo)
{
    a->valor = conteudo;
}

tArvore *colocaNaArvore(tArvore *raiz, int valor)
{
    if (raiz == NULL)
    {
        raiz = (tArvore *)malloc(sizeof(tArvore));
        raiz->valor = valor;
        raiz->esq = raiz->dir = NULL;
    }
    else if (valor < raiz->valor)
        raiz->esq = colocaNaArvore(raiz->esq, valor);
    else /* v < a->info */
        raiz->dir = colocaNaArvore(raiz->dir, valor);
    return raiz;
}

tArvore *buscaValor(tArvore *raiz, int valor)
{
    if (raiz == NULL)
        return NULL;
    if (retornaConteudo(raiz) == valor)
        return raiz;
    else if (valor < retornaConteudo(raiz))
        return buscaValor(raiz->esq, valor);
    else if (valor > retornaConteudo(raiz))
        return buscaValor(raiz->dir, valor);
}

void liberaArvore(tArvore *raiz)
{
    if (raiz != NULL)
    {
        liberaArvore(raiz->esq);
        liberaArvore(raiz->dir);
        free(raiz);
    }
}

void printaArvore(tArvore *raiz)
{
    if (raiz != NULL)
    {
        printaArvore(raiz->esq);
        printaArvore(raiz->dir);
        printf("Valor na árvore: %d\n", retornaConteudo(raiz));
    }
}

int altura (tArvore* a)
{
    if(!celulaNula(a))
    {
        if(a->dir == NULL && a->esq == NULL) return 0;
        int altEsq = altura(a->esq) + 1;
        int altDir = altura(a->dir) + 1;
        if(altEsq > altDir) return altEsq;
        return altDir;
    }
    return -1;
}

void printPreOrder(tArvore *raiz)
{
    if (raiz != NULL)
    {
        printPreOrder(raiz->esq);
        printPreOrder(raiz->dir);
    }
}

void printInOrder(tArvore *raiz)
{
    if (raiz != NULL)
    {
        printInOrder(raiz->esq);
        printInOrder(raiz->dir);
    }
}

void printPostOrder(tArvore *raiz)
{
    if (raiz != NULL)
    {
        printPostOrder(raiz->esq);
        printPostOrder(raiz->dir);
    }
}