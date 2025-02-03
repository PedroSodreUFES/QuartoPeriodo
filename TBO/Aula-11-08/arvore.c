#include "arvore.h"

struct arvore{
    struct arvore *esq;
    struct arvore *dir;
    int conteudo;
};

tArvore *inicializaArvore(int conteudo)
{
    tArvore *a = malloc(sizeof(tArvore));
    a->esq = NULL;
    a->dir = NULL;
    a->conteudo = conteudo;
    return a;
}

void *criaArvoreVazia()
{
    return NULL;
}

tArvore *insereArvore(tArvore *raiz, int conteudo)
{
    if(raiz == NULL)
    {
        raiz = inicializaArvore(conteudo);
    }
    else if(raiz->conteudo > conteudo) // maior
    {
        raiz->dir = insereArvore(raiz->dir, conteudo);
    }
    else //menor
    {
        raiz->esq = insereArvore(raiz->esq, conteudo);
    }
    return raiz;
}

//RETORNA O NO COM O CONTEUDO NO PARAMETRO
tArvore *buscaConteudo(tArvore *raiz, int parametro)
{
    if(raiz == NULL) return NULL;
    else if(raiz->conteudo > parametro) return buscaConteudo(raiz->dir, parametro);
    else if(raiz->conteudo < parametro) return buscaConteudo(raiz->esq, parametro);
    else return raiz;
}

//LIBERA TODA A ARVORE
void liberaArvore(tArvore *raiz)
{
    if(raiz != NULL)
    {
        liberaArvore(raiz->esq);
        liberaArvore(raiz->dir);
        free(raiz);
    }
}

//RETORNA O CONTEUDO DA ARVORE CASO NECESSARIO
int retornaConteudo(tArvore *raiz)
{
    return raiz->conteudo;
}

//IMPRIME ESQUERDA PRIMEIRO
void imprimeArvore(tArvore *raiz)
{
    if(raiz != NULL)
    {
        imprimeArvore(raiz->esq);
        imprimeArvore(raiz->dir);
        printf("%d\n", raiz->conteudo);
    }
}

