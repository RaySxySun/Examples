#! /usr/bin/env python
# -*- coding: utf-8 -*-

import os
import sys
import commands

if len(sys.argv) < 2:
    print "Usage: svtlist.py [name]"
    print "Usage: svtlist.py ls"
    exit(1)
script_real_path = os.path.split(os.path.realpath(__file__))[0]  
if sys.argv[1] == 'ls':
    (status, output) = commands.getstatusoutput("ls -ltr %s/doc "%(script_real_path))
    print output
    exit(0)
if sys.argv[1] == 'grep':
    (status, output) = commands.getstatusoutput("grep -i -r \"%s\" %s/doc "%(sys.argv[2], script_real_path))
    print output
    exit(0)
if sys.argv[1] == 'vim':
    os.system("vim %s/doc/%s "%(script_real_path, sys.argv[2]))
    exit(0)
if sys.argv[1] == 'less':
    os.system("less %s/doc/%s "%(script_real_path, sys.argv[2]))
    exit(0)

(status, output) = commands.getstatusoutput("cat %s/doc/%s "%(script_real_path,  sys.argv[1]))
print output 
