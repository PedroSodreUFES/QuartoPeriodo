#ifndef ARVORE_H
#define ARVORE_H

typedef struct arvore tArvore;

tArvore *criaArvore(int valor);

int retornaConteudo(tArvore *a);

int celulaNula(tArvore *a);

void atribuiConteudo(tArvore *a, int conteudo);

tArvore* colocaNaArvore(tArvore *raiz, int valor);

tArvore *buscaValor(tArvore *raiz, int valor);

void liberaArvore(tArvore *raiz);

void printaArvore(tArvore *raiz);

int altura (tArvore* a);

void printPreOrder(tArvore *raiz);

void printInOrder(tArvore *raiz);

void printPostOrder(tArvore *raiz);

#endif