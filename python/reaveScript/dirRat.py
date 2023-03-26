"""Description

Simple program for running through directories created by netReaver to check
for any blank subfolders. Blank subfolders indicate misses of the Reaver
algorithm, and dirRat creates a report which will allow users to reference the
index of the original manifest file fed to netReaver. This will allow users
easy access to the URL that confounded the pattern recognition of the Reaver
and to correct the issue 

Completed   3/22/23
Shelved     3/24/23
"""
import os
import sys


# Run in root directory created by netReaver
targets = []
for element in os.listdir():
    if os.path.isdir(element):  # Add only folders
        targets.append(element)

# Output file
report = open('report', 'w')

# Should set root path in script to directory containing script
# In current form, note point above about running in root dir from nR
baseDir = os.path.abspath(sys.argv[0])

for target in targets:
    report.write('=====NEW GALLERY=====\n' +
                 target.upper() + '\n')  # Target header
    if not os.listdir(baseDir + '/' + target):   # Populated?
        report.write('Entire miss?? \n\n')
    else:
        targSubs = []
        for element in os.listdir(baseDir + '/' + target):
            if os.path.isdir(element):
                targSubs.append(element)   # Populate subdir

        for sub in targSubs:
            if not os.listdir(baseDir + '/' + target + '/' +
                              sub + '/'):
                # If blank, 'rat out'
                report.write('==SUBGALLERY== ' + sub + '\n')
