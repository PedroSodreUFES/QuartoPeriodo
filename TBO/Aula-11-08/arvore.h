#ifndef ARVORE_H
#define ARVORE_H

#include <stdlib.h>
#include <stdio.h>

typedef struct arvore tArvore;

typedef void (*imprimeConteudo)(void*);
typedef void (*desalocaConteudo)(void*);
/*
Primeiro > Segundo --> retorna 1
Primeiro < Segundo --> retorna -1
Primeiro == Segundo --> retorna 0
*/
typedef int (*Maior)(void*,void*);

void *criaArvoreVazia();

tArvore *inicializaArvore(int conteudo);

tArvore *insereArvore(tArvore *raiz, int conteudo);

tArvore *buscaConteudo(tArvore *raiz, int parametro);

void liberaArvore(tArvore *raiz);

int retornaConteudo(tArvore *raiz);

void imprimeArvore(tArvore *raiz);

#endif