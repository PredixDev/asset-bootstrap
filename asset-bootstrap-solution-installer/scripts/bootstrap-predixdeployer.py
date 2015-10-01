'''
Created on Jan 20, 2014
Running
    mvn clean install pre-site
will unpack the solution-installer dependency which contains the core predixdeployer.py script.

The script below will import that script and then will execute it

See that script in target/scripts/predixdeployer.py for comments

Run this script from the directory containing this script, e.g.

python ./bootstrap-predixdeployer.py .. ~/Documents/workspace/containers/dsp1.5wPM1.0/dsp-k-1.5.0 ~/Documents/workspace/containers/dsp1.5wPM1.0/dsp-k-1.5.0pm/ BootstrapCommon


@author: tturner
'''
import os
import sys

def import_path(fullpath):
    """ 
    Import a file with full path specification. Allows one to
    import from anywhere, something __import__ does not do. 
    """
    print fullpath
    path, filename = os.path.split(fullpath)
    print path, filename
    filename, ext = os.path.splitext(filename)
    print filename
    sys.path.append(path)
    module = __import__(filename)
    print module
    reload(module) # Might be out of date
    del sys.path[-1]
    return module

import_path(os.getcwd() + '/../target/scripts/predixdeployer.py')
   