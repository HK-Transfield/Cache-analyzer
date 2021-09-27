#include <stdio.h> 
#include <stdlib.h>
#include <time.h>

#define KB 1024
#define MB 1024 * 1024

long long wall_clock_time() {
	#ifdef __linux__
		struct timespec tp;
		clock_gettime(CLOCK_REALTIME, &tp);
		return (long long)(tp.tv_nsec + (long long)tp.tv_sec * 1000000000ll);
	#else
		#warning "Your timer resoultion might be too low. Compile on Linux and link with librt"
		struct timeval tv;
		gettimeofday(&tv, NULL);
		return (long long)(tv.tv_usec * 1000 + (long long)tv.tv_sec * 1000000000ll);
	#endif
}

int main() {
    unsigned int steps = 64 * 1024 * 1024;
    static int arr[64 * 1024 * 1024];
    int lengthMod;
    unsigned int i;
    int sizes[] = { 1 * KB, 4 * KB, 8 * KB, 16 * KB, 32 * KB, 64 * KB, 128 * KB, 192 * KB, 256 * KB, 512 * KB, 
        1 * MB, 2 * MB, 3 * MB, 4 * MB, 5 * MB, 6 * MB, 7 * MB, 8 * MB, 9 * MB, 10 * MB, 11 * MB, 12 * MB, 13 * MB,
        14 * MB, 15 * MB};
    int results[sizeof(sizes)/sizeof(int)];
    int s;
    long long start, end;
    float timeTaken;

    // for each size to test for ... 
    for (s = 0; s < sizeof(sizes)/sizeof(int); s++) {
	    lengthMod = sizes[s] - 1;
	    start = wall_clock_time();
	    for (i = 0; i < steps; i++) {
	        arr[(i * 16) & lengthMod]++;
	    }
	    end = wall_clock_time();
	    timeTaken = ((float)(end - start))/1000000000;
        printf("%d, %1.4f \n", sizes[s] / 1024, timeTaken);
    }

    return 0;  
}