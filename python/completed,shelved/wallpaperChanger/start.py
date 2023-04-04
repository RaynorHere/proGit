"""
Just the start wrapper for paperChanger
Assumes both paperChange and start are in the same directory

Completed   3/21/2023
Shelved     3/24/2023
"""
import subprocess
import sys
import os

subprocess.run(
    [sys.executable, os.path.dirname(sys.argv[0])+'/paperChange.py'])
