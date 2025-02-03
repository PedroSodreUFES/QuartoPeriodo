#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

int main(int argc, char *argv[]) {

    printf("Digite o número de processos filhos: ");
    int N;
    scanf("%d", &N);
    
    if (N <= 0) {
        printf("Digite um número maior que zero!\n");
        exit(1);
    }

    for (int i = 0; i < N; i++) {
        pid_t pid = fork();
        if (pid < 0) {
            printf("Erro no fork\n");
            exit(1);
        } else if (pid == 0) {
            // Código do processo filho
            printf("Processo filho: PID = %d, Parent PID = %d\n", getpid(), getppid());
            exit(0); // Filho termina imediatamente
            sleep(5);
        }
        // Pai continua o loop para criar o próximo filho
    }

    // O pai espera para permitir a análise dos processos no terminal
    printf("Processo pai: PID = %d. Criou %d filhos.\n", getpid(), N);
    sleep(30); // Tempo para verificar os processos criados com o comando ps
    return 0;
}
