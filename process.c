#include <stdio.h>
#include <unistd.h>

int main() {
    int pid = 0;
    pid = fork();
    if (pid == 0) {
        printf ("Pid is %d\n", getpid());
    } else {
        printf ("Pid is %d\n", getpid());

    }
}
