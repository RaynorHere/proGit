


def wordCounter(s):

    # Create file object, and "r" sets it to "read mode"? I think?
    # This is extremely Bambi-learning-to-walk stuff here
    targetFile = open(s , "r")

    # Instantiate dictionary
    allWords = dict()

    for line in targetFile:

        # Remove case as a factor
        line = line.lower()

        # Python's equivalent to Java's .trim()
        line = line.strip()

        # I think this'll work: I wanna remove all punctuation (commas, periods, quotation marks, etc)
        punctuations = '''!()-[]{};:'"\,<>./?@#$%^&*_`'''

        for piece in line:
            if piece in punctuations:
                line = line.replace(piece, "")

        # Gotta say (if this works), skipping the "two-scanner-tagteam setup" is kinda rad
        wordTokens = line.split(" ")

        # Iterate through each line, one word at a time, and either add it to the dictionary,
        # or bump its count
        for oneWord in wordTokens:

            if oneWord == "":
                continue

            if oneWord in allWords:
                allWords[oneWord] = allWords[oneWord] + 1
            else:
                allWords[oneWord] = 1

    # Sort words in dictionary by count
    sortCount = sorted(allWords.items(), key = lambda kv: kv[1], reverse=True)

    sortedAllWords = dict(sortCount)
            
    # Dump dictionary to screen
    #for element in list(allWords.keys()):
     #   print(element, ": ", allWords[element])

    for key, value in sortedAllWords.items():
        print(key, ": ", value)


print('Enter full name of a .txt file (extension included) that you would like read.')
print('The default file the program will read is Alice-in-Wonderland.txt')
print('Please note: these files either need to be in the same directory as wf.py OR you must include the subdirectory')
print('That is, if there is a folder titled "docs" in the program\'s directory which contains the documents,')
print('the command would be \'docs\<filename>.txt\' \n')
s = input()

if s == '':
    s = 'Alice-in-Wonderland.txt'

wordCounter(s)

# Okay, this stuff is pretty rad. I already knew I would be here for it, but lemme say: I am here for it