set fname=%1.txt
set lan=%1
(for /l %%A in (1,1,254) do @ping -a -n 1 %lan%.%%A) > %fname%
findstr /c:"динамический" %fname% >> log.tmp
rem findstr /c:"динамический" %fname% >> log.tmp
del %fname%
move log.tmp %fname%