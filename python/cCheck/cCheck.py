"""
cCheck.py Description
imageCompare.py, Version 2.0

cCheck is a multithreaded (more technically: multiprocessed) solution for 
comparing elements of a directory, including its subdirectories, against each 
other to check for duplicate files. The comparison is comprehensive, and every 
step of the process is meant to be programmatic: that is, place it in a directory
run it, and let it work.

Completed 04/03/2023


CHANGELOG:
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

    
IMPROVEMENT IDEAS:
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
    

KNOWN ISSUES:
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
"""
import multiprocessing as mproc
import os
import time
import glob
from math import ceil


def core():
    """Setup function
        Locates directory where program is located, populates manifest

    Returns:
    A list of lists, each an equal proportion of the total population
    of a directory cCheck.py is run in"""

    # Set master time stamp
    global absTimeTop
    absTimeTop = time.time()

    # Set root directory to .py file's directory
    # Works as intended in Windows; Linux truncates
    # Linux solution: os.path.abspath(os.path.dirname())?
    mainRoot = os.path.dirname(__file__)
    say("Main root directory set to " + mainRoot)

    # Report file
    global log
    log = open(mainRoot + '/report', 'a')

    # File list
    manifest = []
    print("Populating...")

    ####        INTERNALIZE THIS        ####
    for file in glob.glob(mainRoot + "/**/*", recursive=True):
        if os.path.isfile(file):
            manifest.append(file)
    ####        ////////////////        ####

    say("Manifest completed. Dividing.\n")
    log.close()

    # Number of elements per core
    procLoad = len(manifest)/os.cpu_count()

    # If not even divide
    if int(procLoad) != procLoad:
        procLoad = int(ceil(procLoad))
    # If even divide
    procLoad = int(procLoad)

    # Break manifest into sublists, return
    subLists = [manifest[x:x+procLoad]
                for x in range(0, len(manifest), procLoad)]
    return subLists


def say(content: str = ''):
    """Print and Record function"""
    print(content)
    log.write(content+'\n')


def compItem(candidates: list):
    """Compares every element of a list against every other element, to check for 
        duplicate files

    Dynamic Objects:
    candidates -- List of items to be compared

    Returns:
    A string representing made up of pairs of file URLS, separated by newlines.
    Each pair are two files that are identical which are held in different locations
    on the filesystem
    """

    # Internal timing, results buffer
    itBTime = -1
    resBuf = ''

    # Iterate every item
    for item in candidates:
        itTimeT = time.time()   # Iteration top timestamp
        item = candidates.pop()  # Faster redundant fix
        itemFile = open(item, 'rb')
        itemSize = os.stat(item).st_size  # Faster size compare

        # Copy list after pop call
        candCopy = candidates[:]
        for cand in candCopy:

            # Check size first
            candSize = os.stat(cand).st_size

            # If same, compare
            if itemSize == candSize:
                candFile = open(cand, 'rb')
                print("Found two identically-sized files")

                while True:

                    # Compare 8KB/iter, not 1B
                    itByt = itemFile.read(8192)
                    caByt = candFile.read(8192)

                    # If ever different, stop
                    if itByt != caByt:
                        break

                    # If reach EOF, then same file
                    if not itByt:
                        # Log workaround for multiproc: str buffer
                        print("{} and {}\n".format(item, cand))
                        resBuf += "{} and {}\n".format(item, cand)
                        break   # Exit comp loop

        itBTime = time.time()  # Iter bottom timestamp
        print("Iter complete at {:.4f} seconds".format(itBTime - itTimeT))
    return resBuf


####                MAIN FLOW                ####

# Set up sublists for process farming
dutyLists = core()

# Gate multiprocess entry point
if __name__ == "__main__":

    # Calls compItem on every item in dutyLists
    # Also calls join() on resulting processes, returning each process'
    # resBuf, and places them in a list, results
    with mproc.Pool() as pool:
        results = pool.map(compItem, dutyLists)

        # If a process returns a nonempty buffer, open a file and log it
        if results:
            log = open(os.path.dirname(__file__)+'/report', 'a')
            for element in results:
                log.write("{}\n".format(element))

# Total time elapsed
log.write("Process complete, after {:.4f} seconds.".format(
    time.time()-absTimeTop))
log.close()
####                /////////                ####
