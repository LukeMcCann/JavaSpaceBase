DistributedChatApplication

This is a JavaSapces exercise for understanding the implementation of channels. 
This application is under the GNU OpenSource liscence, whilst this code can be used by anyone
it must remain OpenSource. 

To Use this application you must first install ApacheRiver and set up a space on your network.

You will also need to configure run permissions and add the dependencies manually:
    
      See https://river.apache.org for details
      
Please be aware that this is much easier to set up on Linux based systems than windows. 

Java Security Permission:

add to config for both parameter and VM option:

    -Djava.security.policy=policy.all -Djava.rmi.server.useCodebaseOnly=false


Dependencies:

    $RIVER_HOME/lib/jsk-lib.jar

    $RIVER_HOME/lib/jsk-platform.jar

    $RIVER_HOME/lib/outrigger.jar

    $RIVER_HOME/lib/reggie.jar

    $RIVER_HOME/lib-dl/reggie-dl.jar
    

This application was built as a learning exercise and posted in the hopes of helping others to understand
this framework better in the future. There is an inherent lack of documentation for JavaSpaces making
the requirements for entry higher than that of someone who is just getting into computing. 
For reference I have found the following book highly useful:

    JavaSpaces Principles, Patterns and Practice by David Gelernter