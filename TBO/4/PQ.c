#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include "event.h"
#include "PQ.h"

// TODO: Aqui você deve implementar uma fila com prioridade mínima para
//       ordenar os eventos por tempo, isto é, o evento com o menor tempo tem
//       a maior prioridade. Veja as funções definidas em 'event.h' para
//       a manipulação das estruturas de evento. A princípio, as funções já
//       existentes em 'event.h' são suficientes para implementar a simulação,
//       isto é, você não precisa implementar mais nada lá.
//
//       Você é livre para implementar a fila com prioridade da forma que quiser
//       mas é recomendado usar um min-heap para obter um bom desempenho. As
//       simulações maiores geram uma quantidade grande de eventos: um
//       limite superior frouxo (mas seguro) para o número máximo simultâneo de
//       eventos é N^3, aonde N é o número de partículas.

// TODO: Crie a struct pq.
struct pq{
    Event **fila;
    int tamanho;
    int hi;
};
/*
 * Cria uma nova fila de prioridade mínima com o limite de elementos informado.
 */
PQ* PQ_create(int max_N) {
    // TODO: Implemente a criação da fila que suporta no máximo o número de
    //       de eventos informados no parâmetro.
    PQ *aaa = malloc(1 * sizeof(PQ));
    aaa->fila = malloc(max_N * sizeof(Event *));
    aaa->tamanho = max_N;
    aaa->hi = 0;
    return aaa;
}

/*
 * Libera a memória da fila.
 */
void PQ_destroy(PQ *pq) {
    // TODO: Implemente essa função que libera toda a memória da fila,
    //       destruindo inclusive os eventos que estavam na fila.
    
    if(pq == NULL) return NULL;

    for(int i =0 ; i< pq->tamanho ; i++)
    {
        destroy_event(pq->fila[i]);
    }

    free(pq->fila);

    free(pq);
}

/*
 * Insere o evento na fila segundo o seu tempo.
 */
void PQ_insert(PQ *pq, Event *e) {
    // TODO: Implemente essa função que insere o evento dado na fila segundo
    //       o tempo do evento.
    //       Assuma que 'e' não é nulo. É importante testar overflow (inserção
    //       em uma fila que já contém o número máximo de eventos) para evitar
    //       dores de cabeça com acessos inválidos na memória.
    if(pq->tamanho == pq->hi) return;

    pq->fila[pq->hi] = e;
    pq->hi +=1;
    fix
}

/*
 * Remove e retorna o evento mais próximo.
 */
Event* PQ_delmin(PQ *pq) {
    // TODO: Implemente essa função que remove o evento com o menor tempo da
    //       fila e o retorna.
}

/*
 * Testa se a fila está vazia.
 */
bool PQ_is_empty(PQ *pq) {
    // TODO: Implemente essa função.
}

/*
 * Retorna o tamanho da fila.
 */
int PQ_size(PQ *pq) {
    // TODO: Implemente essa função.
}


void exchange(PQ * pq, int i, int j){
    Event * aux = pq->fila[i];
    pq->fila[i] = pq->fila[j];
    pq->fila[j] = aux;
}

void fix_up(PQ * pq, int n){
    while(n > 1 && compare(pq->fila[n/2], pq->fila[n]) > 0){
        exchange(pq, n/2, n);
        n /= 2;
    }
}

void fix_down(PQ* pq, int n){
    while(2 * n <= pq->tamanho){
        int i = 2*n;
        if(i < pq->tamanho && compare(pq->fila[i + 1], pq->fila[i]) < 0) i++;

        if(compare(pq->fila[n], pq->fila[i]) > 0) exchange(pq, n, i);
        else break;

        n = i;
    }
}