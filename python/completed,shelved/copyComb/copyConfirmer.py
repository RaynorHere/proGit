"""
For the faint of heart, copyConfirmer walks the copy manifest output by imageCompare
and (ironically?) makes copies of those copies into their own subfolders, allowing 
users to examine the suspects themselves

Completed   03/22/2023
Shelved     03/24/2023
"""
import os
import shutil


repData = open('report', 'r').readlines()
os.mkdir('copies')
counter = 1

for entry in repData:
    duplicates = entry.split(" ")
    duplicates.remove('and')
    duplicates[1] = duplicates[1].replace('\n', '')
    os.mkdir('copies/set'+str(counter))
    shutil.copyfile(duplicates[0], 'copies/set'+str(counter)+'/image1.jpg')
    shutil.copyfile(duplicates[1], 'copies/set'+str(counter)+'/image2.jpg')
    counter += 1
