#include <pthread.h>
#include <unistd.h>

#include <stdio.h>

void *myFunc(void *foo) {
    int *x = (int *) foo;
    printf("Thread called with %d\n", *x);
    printf ("Inside myFunc %d\n", getpid());
    return (void *)NULL;
}


int main(){
    printf ("Starting\n");
    int attr = 9;
    pthread_t tid;
    if ((pthread_create(&tid, NULL, myFunc, &attr)) != 0) {
        printf ("Oops .. Thread creation error\n");
    }
    printf ("Inside main %d\n", getpid());
    //pthread_join(tid, NULL);
    printf ("Done waiting for thread\n");
}
