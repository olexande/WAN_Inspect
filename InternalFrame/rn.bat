c:
cd C:\WAN_Inspect
javac InternalFrame\frame\*.java
javac InternalFrame\logic\*.java

rem javac *.java

java -cp .;InternalFrame/lib/mysql-connector-java-5.1.13-bin.jar InternalFrame.frame.InternalFrameDemo
rem start javaw -cp .;InternalFrame/lib/mysql-connector-java-5.1.13-bin.jar InternalFrame.frame.InternalFrameDemo

rem pause
cd InternalFrame