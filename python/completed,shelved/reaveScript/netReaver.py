"""
Specialized scraper for images. Given a target URL and some keywords (which users
will have to find themselves to initialize the process), it will find the URLs of
each subgallery that host root URL has on its servers. Users are respsonsible for
tuning regex and 'scouting' initial host URL, but all other elements are automated

Completed   3/21/2023
Shelved     3/24/2023
"""
import re
import requests
import os

###     SET TARGET URL HERE     ###
global url
url = ""


def grabSource(character: str):
    # Go to root website and grab gallery listing. Also create subfolder
    # Creates rawhtml file pullImage() uses

    url = url + '/.+?/'+character    # Target character; regex curr for DA gall
    page = requests.get(url)
    os.mkdir('./'+character+'/')
    outFile = open('./'+character+'/rawhtml', 'wb')  # Note bytes permission
    # The HTML file has now been dumped
    outFile.write(page.content)


def pullImages(character: str):
    """
    Extract gallery URLs from a baseline HTML listing

    Parameters
    ----------
    character   :   str
        Subfolder from root to start the collection/an element from a list of targets you supply
    """
    # From root folder, enter target subfolder root, open its HTML
    # HTML file is output by grabSource
    inFile = open('./' + character + '/rawhtml')

    # Log results
    logFile = open('./' + character + '/log', 'w')

    # Read manifest, itemize
    data = inFile.read()
    data = data.replace("<", "\n").replace(
        ">", "\n").replace("\"", "\n").replace("\ ", "\n")

    # Set RegEx
    galPattern = re.compile(re.escape(url) + "/.+?/" +
                            re.escape(character) + "/.+?/")
    matches = galPattern.findall(data)  # Multiple-match

    # Sort (It's strings, but oh well; easy adjustment)
    matches = sorted(matches)
    for element in matches:
        # Note the new target gallery set beginning in log
        logFile.write(element + '\n')


###     SPECIFY TARGETED CHARACTERS/HOST URL SUBDIRECTORY     ###
targets = []

# Create HTML sources
for target in targets:
    grabSource(target)

# Reave 'em
for target in targets:
    pullImages(target)


print("Done.")
