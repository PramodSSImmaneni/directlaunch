This example application shows you how to launch an Apex application on the cluster from within your program. It
contains a simple Apex application along with the bootstrap code to launch it.

Before launching the application, the Apex engine code containing support for this feature should be built. In future 
this may not be needed. The steps for this are as follows:

1. Clone apex-core from github using command
**git clone git@github.com:apache/apex-core**
2. In the local workspace, apply the patch containing the launch support using the command
**git pull git@github.com:PramodSSImmaneni/apex-core APEXCORE-405**
3. Build the patched apex-core using
**mvn clean install -DskipTests**

The application _main_ function contains the launch code and is in _Application.java_. The _pom.xml_ build file 
has the necessary configuration to run the application. To launch the application, run the following maven command

**mvn package -Plaunch-app -DskipTests**
