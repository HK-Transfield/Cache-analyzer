#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define KB 1024
#define MB 1024 * 1024

int main()
{
    int steps = 64 * 1024 * 1024;
    volatile static char *arr[256 * 1024 * 1024];
    int lengthMod;
    double timeTaken;
    clock_t start;
    int sizes[] = {1 * KB, 4 * KB, 8 * KB, 16 * KB, 32 * KB, 64 * KB, 128 * KB, 192 * KB, 256 * KB, 512 * KB,
                   1 * MB, 2 * MB, 3 * MB, 4 * MB, 8 * MB, 16 * MB, 32 * MB, 64 * MB, 128 * MB, 256 * MB};
    int s;

    // for each size to test for ...
    for (s = 0; s < sizeof(sizes) / sizeof(int); s++)
    {
        //arr = (char *)malloc(sizes[s]);

        start = clock();
        lengthMod = sizes[s] / sizeof(int) - 1;
        for (int i = 0; i < steps; i++)
            arr[(i * 64) & lengthMod]++;

        timeTaken = (double)(clock() - start) / CLOCKS_PER_SEC;
        printf("%.8f \n", timeTaken);
    }

    return 0;
}
