#include <iostream>
#include <time.h>
#include <chrono>

#define KB 1024
#define MB 1024 * 1024

const int steps =  64 * 1024 * 1024; // abitrary large number of steps
const int sizes[] = { 1 * KB, 4 * KB, 8 * KB, 16 * KB, 32 * KB, 64 * KB, 128 * KB, 192 * KB, 256 * KB, 512 * KB, 
        1 * MB, 2 * MB, 3 * MB, 4 * MB, 5 * MB, 6 * MB, 7 * MB, 8 * MB, 9 * MB, 10 * MB, 11 * MB, 12 * MB, 13 * MB,
        14 * MB, 15 * MB};

using namespace std;
using namespace std::chrono;

void measureCache(int size, char arr[]) {
    int lengthMod = size - 1;

    for (int s = 0; s < steps; s++)
        arr[(s * 64) & lengthMod]++; 
}

int main(int argc, char* argv[]) {
    int lengthMod;
    char* arr = new char[64 * 1024 * 1024];

    for(int j = 0; j < sizeof(arr); j++)
            arr[j] = (char)1;

    for(int i = 0; i < sizeof(sizes)/sizeof(int); i++) {

         
        lengthMod = sizes[i] - 1;

        // begin measuring access time
        auto start = high_resolution_clock::now();

        for (int s = 0; s < steps; s++)
            arr[(s * 64) & lengthMod]++; 
        auto stop = high_resolution_clock::now();

        // record time
        auto duration = duration_cast<nanoseconds>(stop - start);
        cout << duration.count() << endl;
    }

    delete[] arr;

    return 0;
}