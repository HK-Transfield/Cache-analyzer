# Side-channel Attacks — Last-Level Cache Analyzer

The CPU cache can speed up memory references. Spectre attacks expolit the system by mapping the
value of a sensitve piece of information to whether or not a specific cache line exists on the cache.

The purpose of this script is to infer the size of the Last Level Cache (LLC) of a system via timing
memory accesses. Such a side-channel algorithm exploits similar vulnerabilities as those used by Spectre
and Meltdown.

The overall idea in designing this script is deciding how much memory can be maximally maintained in the
system by timing memory reads/writes and comparing fast (in the cache) with slow (not in the cache) accesses.
Once the size of the LLC is crossed, the system will start evicting existing memory objects from the cache;
thus, if you try to access them again, that would be a cache-miss: a slow memory access.

---

## Getting the Program to Work

To clone and compile the program:

```
git clone https://github.com/HK-Transfield/Cache-analyzer.git
cd Cache-analyzer/
javac CacheAnalyzer.java
```

To run the program:

```
java CacheAnalyzer
```

---

## Alternative Testing Configurations

Inside the program are commented out lines of code used as alternative testing configurations.
This includes:

- An array of sizes that increases linearly from 1MB to 20MB, rather than exponential array sizes
- Random array access, rather than sequential array access.

If you wish to test these other parameters, uncomment them and remember to comment out any
other conflicting parameters. Then recompile the program and run using the above mentioned commands.
