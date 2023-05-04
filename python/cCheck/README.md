# cCheck.py Description  ( imageCompare.py, Version 2.0  )
cCheck is a multithreaded (more technically: multiprocessed) solution for 
comparing elements of a directory, including its subdirectories, against each 
other to check for duplicate files. The comparison is comprehensive, and every 
step of the process is meant to be programmatic: that is, place it in a directory,
run it, and let it work.  

Shelved 05/04/2023.  
Remaining task: find optimal MProc paradigm/tool to process list combinationally  
Will return before end of summer  
See note on cCheck.py Line 222  
  
  
## CHANGELOG:  
1) List Pop  
    Rather than processing lists by indices, program now calls pop() on the
    list given in order to remove the item being compared before beginning
    comparisons. This solves the 'redundant comparison' problem much more 
    simply  
  
2) Size First Then Open  
    Files are now sized with os.stat() calls before a candidate file is opened.
    This is much faster than opening, reading, and sizing data of each one  
  
3) Char->Word Comparison  
    When files are compared, they are compared in 8-kilobyte words, instead of
    byte-by-byte. Many files of similar type share headers and other bits of
    structure. By comparing larger words, differences are found much faster  
    
4) Multiprocessing  
    Program now divides manifest (list of files to be compared) into equal sub-
    lists (divisor is host machine CPU core count). Each sublist is farmed out
    to its own process, allowing directory to be processed much more quickly.  
  
    
## IMPROVEMENT IDEAS:  
1) Combine copyComb Suite  
    Roll the rest of the copyComb suite into a single program. Prompt users 
    to 'continue' function after finding and listing all duplicates. Hand data 
    off directly to copyConfirmer or dirRedeemer to process copies immediately 
    and directly, else exit as normal and just leave Report file  
    
2) Remove Globals  
    Don't like using global variables. Feels sloppy and imprecise. Want to think
    of a better way to log progress in the program that doesn't involve globals
    
3) GUI  
    There's really no two ways about it. A full GUI that allows users to operate 
    on data WITHIN the program, remote from the directories it's manipulating,
    entirely encapsulated, is pretty much the only destiny this could have. If
    we were going to 'productize' it, that's what it would need. Perhaps someday  
  
  
## KNOWN ISSUES:  
1) Windows Portability  
    Due to the lack of fork() on Windows architecture, the multiprocessing 
    solution for Windows eludes me. No amount of module gating changes the fact
    that Windows child Python processes will always run an entire module (not
    insurmountable) and child processes DO NOT inherit ANY environment data from
    parent processes (very possibly insurmountable). As it stands, with a Python
    memory checker *largely* being mostly a fun challenge/exercise (since C or
    almost any other compiled language will outpace it), I see no reason to 
    overcome this. I would instead use it as a reason to learn how to multithread
    or multiprocess in C and/or begin learning C++, which fit Windows applications 
    far better. This is exactly what I intend to do
