@echo off
ping -n 1 -l 1 %1 >> nul
if %errorlevel%==0 echo Host is UP!
if %errorlevel%==1 echo Host is DOWN!
rem pause