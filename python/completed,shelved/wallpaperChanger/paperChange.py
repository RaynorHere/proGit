"""
Autonomous wallpaper changer for Linux systems which don't support the option.

Given a target directory containing images (script is not currently recursive),
will create a list of all items in the directory and randomly set one as
wallpaper. Directory and time intervals between executions are set manually in
script. 

Started through the start.py wrapper, it immediately 'unshackles' the 
script from the executing terminal, allowing the user to close it without 
halting the script, and also without having to wait for iteration 1 to complete.

To halt script once released, execute:
    $ kill $(ps -ef | grep paperChange.py | grep -v grep | awk '{print $2}')

Completed   3/19/2023
Shelved     3/24/2023
"""
import os
from time import sleep
import subprocess
import sys
import random
import signal


def core():
    # Primary function

    # Terminates the parent process, to avoid memleaks/htop-bloat
    os.kill(os.getppid(), signal.SIGTERM)

    ####            SET DIRECTORY HERE            ####
    galleryPath = ""

    # PREP
    theList = os.listdir(galleryPath)   # Candidate list
    totalPop = len(theList) - 1         # Total valid indices

    ####        THE SETTING OF THE IMAGE        ####
    # Gen next wallpaper index
    nextOne = galleryPath + '/' + theList[random.randrange(0, totalPop)]
    subprocess.run(['/bin/gsettings', 'set', 'org.gnome.desktop.background',
                   'picture-uri', nextOne])          # Settum

    ####        TIME LEFT DISPLAYED        ####
    sleep(1800)

    ####        START NEW        ####
    # sys.executable is the abs path program was originally ex'd with
    # Here, should be something like /usr/local/bin/python
    # abspath(argv[0]) should ensure script always locates itself to run,
    # else, may try to run self from dir relative to executing terminal
    subprocess.run(
        [sys.executable, os.path.abspath(sys.argv[0])])


core()
