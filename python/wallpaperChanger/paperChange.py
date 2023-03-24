#!/usr/bin/env python3

# Initialize through wrapper
# LOOP:
# Terminate calling process (term/prev iter)
# Create list object of elements in folder              # IMPROVEMENT: Make recursive/os.walk() method or similar
# Generate random number between 0 and n-1 of photos
# Use index to set disk URL as GNOME settings background
# Sleep
# Call new execution
# Stop methods are detailed in stop.py wrapper


import os
from time import sleep
import subprocess
import sys
import random
import signal


def core():

    # Initial execute
    mepath = os.getcwd()    # Get current working directory
    # Terminates the parent process, to avoid memleaks/htop-bloat
    os.kill(os.getppid(), signal.SIGTERM)

    #####       DESIRED DIRECTORY IS SET HERE       ####
    mepath = ""
    # IMPROVEMENT: Make it a GUI element to prompt for dir on first start
    #   or, give it a built-in flag to start it with a gui to specify on-fly, if desired
    #   Regardless, probably make this at least a argv call instead of a hardcode
    os.chdir(mepath)

    # PREP
    theList = os.listdir()      # Candidate list
    # Total valid indices; not this time, OBO error
    totalPop = len(theList) - 1

    # Gen next wallpaper index
    nextOne = theList[random.randrange(0, totalPop)]
    # Handily provides full path of queried file
    nOPath = os.path.abspath(nextOne)
    # Note run() expects args as list; in future pass as """/bin/gsettings set org.gnome.desktop.background picture-uri""".split(" ")?
    #   Can then append , nOPath after?
    #   Failing that, type command as would in bash as string -> .split() into list var; .append(nOPath), pass that
    subprocess.run(['/bin/gsettings', 'set', 'org.gnome.desktop.background',
                   'picture-uri', nOPath])          # Settum

    ####        TIME LEFT DISPLAYED        ####
    sleep(1800)

    ####        START NEW        ####
    # sys.executable is the abs path program was originally ex'd with. Here, should be something like /usr/local/bin/python
    subprocess.run(
        [sys.executable, mepath])


core()
