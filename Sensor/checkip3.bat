@echo off
ping -n 1 -l 1 %1 >> nul
if %errorlevel%==0 echo %1
if %errorlevel%==1 echo.
rem pause