#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

int glob = 6;

int main(void) {
    int var;            /* external variable in initialized data */
    pid_t pid;          /* automatic variable on the stack */
    var = 88;

    printf("before fork\n");
    
    if ((pid = fork()) < 0) {
        fprintf(stderr, "Erro no fork\n");
        exit(1);
    } else if (pid == 0) {  /* ***child*** */
        glob++;  /* modify variables */
        var++;
        printf("Processo filho:\n");
        printf("PID = %d, PPID = %d, UID = %d, GID = %d, glob = %d, var = %d\n",
               getpid(), getppid(), getuid(), getpgrp(), glob, var);
    } else {  /* ***parent*** */
        sleep(1);  
        printf("Processo pai:\n");
        printf("PID = %d, PPID = %d, UID = %d, GID = %d, glob = %d, var = %d\n",
               getpid(), getppid(), getuid(), getpgrp(), glob, var);
    }

    return 0;
}
