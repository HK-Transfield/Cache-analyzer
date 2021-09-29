#include <iostream>
#include <time.h>
#include <chrono>

#define KB 1024
#define MB 1024 * 1024

using namespace std;
using namespace std::chrono;

int main(int argc, char *argv[])
{
    static char *arr = new char[256 * 1024 * 1024]();
    const unsigned int steps = 64 * 1024 * 1024; // abitrary large number of steps
    const int sizes[] = {1 * KB, 4 * KB, 8 * KB, 16 * KB, 32 * KB, 64 * KB, 128 * KB, 192 * KB, 256 * KB, 512 * KB,
                         1 * MB, 2 * MB, 3 * MB, 4 * MB, 8 * MB, 16 * MB, 32 * MB, 64 * MB, 128 * MB};
    int lengthMod;

    // the program creates a new array for eeach size defined
    for (int i = 0; i < sizeof(sizes) / sizeof(int); i++)
    {
        /*
        Begin recording access times for each array size we are evaluating.
        It will the array an arbitrary number of steps.
        */
        auto start = high_resolution_clock::now();

        lengthMod = sizes[i] - 1;
        /*
        This for loop increments every 16th integer to modify
        the 16 byte cache line (int = 4 bytes)
        */
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