# Coding Journal
## Name: Jim "JCIII" Crowell
## Lab: Module 11
## Entries: 1
### November 13th, 2021
This is a bit of a wonky one, to be sure. On the one hand, I actually picked it up pretty well; definitely had some discourse with a couple of my buddies in the class, but the difference between "I have no idea what to do" and "I know what I want to do, I'm just not sure of the verbage" is quite a chasm, and I'm always very happy to be in the latter case rather than the former (instance: I wanted to have my default filter not show hidden files, which I figured meant "check character 0 on the file's name and if it's ".", return 0, else 1, and that turned out to be right on the money. I just needed to be reminded that the syntax for that is "if (current->d_name[0] == '.')").

That said, this solution isn't perfect yet. The way I've elected to show only files is to basically check the filename and if it contains a "." at any point past character indices 0 or 1 (which makes it exclude "." and ".."), then return it. This works because files have extensions, and folders do not. The issue is that A) there are some files which do not have extensions; raw text files and the Makefile, for instance, and B) it's still showing folders SOMETIMES, like the .vscode folder, and I'm not sure why or what to do about it. In other cases, we'd #include the C lib that allowed the program to run the ISFILE or ISDIR functions, but I can't monkey with the Makefile to be used during testing, so that option's out. Therefore, I'm at a bit of a loss.

I had multiple exams this week, so I was unable to give this as much time as I'd like. I will see if inspiration strikes between now and bedtime, but I don't think it too likely I'll solve that issue, which is aggravating. I will have to  see if I can send off an email about it, just to get the question answered, even if I can't secure the points for screwing up that element of the program.
