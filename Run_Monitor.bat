@echo off
cls
echo Proverka Robocopy

SET RobocopySRC=\\192.168.2.250\Other\Distribution\robocopy\robocopy.exe
SET RobocopyDST=C:\WINDOWS\system32\

if not exist "%RobocopyDST%robocopy.exe" xcopy /E /Q /R /Y %RobocopySRC% %RobocopyDST%

echo Sync Soft
set SRC=\\192.168.2.250\Other\Belovol\WAN_Inspect\
set DST=C:\WAN_Inspect\

robocopy  %SRC% %DST% /V /NP /R:10 /W:30 /MIR

echo Run Monitor
c:
cd C:\WAN_Inspect\Monitor
start javaw -cp .;mysql-connector-java-5.1.13-bin.jar students.frame.IPsFrame
