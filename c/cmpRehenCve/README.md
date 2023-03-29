# cmpRehenCve Description  
cmpRehenCve ("Comprehensive/Comprehen-C-ve") is designed to read a file (currently hardcoded as being in colocated with the cmp's .exe file, labeled 'manifest') of local (i.e., on-disk) file URLs into an array. After which, the program will examine each file's entry on the disk and compare that size with that of every other file located in the manifest file. Upon finding two identically-sized entries, program will compare files byte by byte to determine if they are identical or not.

Inspiration: the logical conclusion of "which language would baseline run the fastest on a Windows machine to do memory comparisons?" Also an excuse to improve C/probe into C++ if it comes to that.  
  
Currently in 1.0; unit tests complete. Manual data comparison (byte by byte) providing considerable time returns. Python's average iterations of ~25 minutes each are condensed to an average of 90 seconds (~1:17 runtime compression) here, although it's inconsistent. 

## Improvement Ideas  
1) Multithreading. Haven't done multithreading in C in a while, and it might be enough of an excuse to port to C++; in any case, most obvious direct improvement to runtime will be multithreading

2) Parameterization. Now that the program's been brough to a working format, can change programmatic statements to functions/definitions, in order to parameterize/condense/clean up code flow. This is a proof of concept; still in need of refinement.