#!/usr/bin/env python3

import os
import signal
import sys

# This is a stop wrapper for wallpaper changer. Run it (assuming from BASH terminal) with the arg:
# $(ps -ef | grep wallpaperChanger | grep -v grep | awk '{print $2}')
# That will cause it to pick (at least one, if you have multiple instances running for some reason)
# the correct process and kill it. Repeat as necessary


def killer(pid):
    os.kill(pid, signal.SIGTERM)


# Has to be an int from what I can tell, but I can't ever see having 'too many' processes for int
killer(int(sys.argv[1]))
