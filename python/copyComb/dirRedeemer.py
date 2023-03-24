"""
Deletes duplicate images as found by imageCompare by walking the entirety
of the report full of duplicate URLs and deleting entry A of each A-B pair

That is, imageCompare produces a manifest of entries, each 'A and B' of two
files which are identical. This splits each entry into a list of 'A', 'and',
and 'B', and deletes 'A'

This will always leave one copy of files behind, as imageCompare produces
entries where given n duplicates, duplicate 1 will show up n-1
times as the A element of an entry. Duplicate n will never show up 
as the A element of a comparison, because it's already been compared with 
every other item and entered into the list. Therefore, duplicate element n
will never be deleted, and copies will be reduced to 1 unique file each

Completed   03/22/2023
Shelved     03/24/2023
"""
import os

repData = open('report', 'r').readlines()

for entry in repData:
    elements = entry.split(" ")

    # Avoid redundant delete calls
    if os.path.isfile(elements[0]):
        os.remove(elements[0])


"""
Handy little trick, leaving for reference: 
Sets only contain unique elements. If you make a set out of a list, and the 
lengths of the list and the set made from it are the same, every element of 
that original list is unique

    if(len(set(firstHalves)) == len(firstHalves)):
        print("Each element is unique")     
    else:
        print("There are duplicates")
"""
