"""
Simple program to look for indenticality between files. Only used on
images, to date, but theoretically should work on anything that's a file

Current configuration will take a root directory, make a list of all its
subdirectories, and then process each subdirectory by checking every image
in it against every other image in that subdirectory

Future iterations will simply take the os.walk() function used below on a 
given directory and compare every image in the directory given. Use as 
necessary on every dir user suspects may contain duplicate files.
Good excuse to make a GUI element/frontend

IMPROVEMENT IDEAS
Threadpool functionality to make it quicker. It's processed ~1,000 files 
reasonably quickly on weaker hardware, but it doesn't take long for numbers
to become unteneble, obviously. Threadpool should widen limit

Possible to adapt mergeSort logic to file->bytearray comparisons? Threadpool
the elements out of the list, then recursively compare that item to every 
other item in that thread?

Completed   03/22/2023
Shelved     03/24/2023
"""
import os
import sys


def say(content: str = ''):
    # Print and Record function
    print(content)
    log.write(content+'\n')


# Report file
global log
log = open('./report', 'w')

# Set root directory anchor; highest level
# As usual, assumes .py file is in root dir of all images to compare
mainRoot = os.path.abspath(sys.argv[0])

# Enumerate candidates
# I know the listdir()->isdir()->add(element) trick
# Will change this to that when I come back to imp multithreading
# Change this to an os.walk() for simplicity; can add hooks for file formats
group = os.listdir(mainRoot)
group.remove('report')
group.remove('imageCompare.py')
group.remove('copyConfirmer.py')
group.remove('dirRedeemer.py')


# Commence
for person in group:
    charRoot = mainRoot + '/' + person   # This gallery's root
    allImages = []
    infoBuffer = ''         # My solution for repeats

    # Walk the entirety of the directory
    for root, dirs, files in os.walk(charRoot, topdown=False):
        for name in files:
            # Present full path of every image in directory
            allImages.append(os.path.join(root, name))

    for image in sorted(allImages):

        # Bloaty, but avoids aliasing issues; investigate later
        relativeImages = allImages.copy()
        relativeImages.remove(image)
        tBytes = bytearray(open(image, 'rb').read())

        for candidate in relativeImages:
            oBytes = bytearray(open(candidate, 'rb').read())

            if tBytes == oBytes:
                if ((image + ' and ' + candidate) and (candidate + ' and ' + image)) not in infoBuffer:
                    say(image + ' and ' + candidate)
                    infoBuffer += (image + ' and ' + candidate) + '\n'
