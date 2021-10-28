# Side-channel Attacks

## Getting the Program to Work
To compile the program, run the command:

```
javac CacheAnalyzer.java
```

To run the program, run the command
```
java CacheAnalyzer
```

## Alternative Testing Configurations
Inside the program are commented out lines of code used as alternative testing configurations. 
This includes:
* An array of sizes that increases linearly from 1MB to 20MB, rather than exponential array sizes
* Random array access, rather than sequential array access.

If you wish to test these other parameters, uncomment them and remember to comment out any
other conflicting parameters. Then recompile the program and run using the above mentioned commands.