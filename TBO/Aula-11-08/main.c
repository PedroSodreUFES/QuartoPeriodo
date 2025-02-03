#include "arvore.h"
#include "time.h"

int main()
{
    int matricula;
    tArvore *raiz=criaArvoreVazia();
    int n;
    FILE *arquivo = fopen("numeros.txt", "r");
    if (arquivo != NULL)
    {
        printf("Abriu o arquivo !!!!!!!!!!!!\n");
    }
    while(fscanf(arquivo, "%d%*c", &n) == 1)
    {
        raiz = insereArvore(raiz, n);
    }
    imprimeArvore(raiz);
    liberaArvore(raiz);
    fclose(arquivo);
    return 0;
}