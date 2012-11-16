
set fname=result.txt
findstr /c:"динамический" %fname% >log.tmp
del %fname%
move log.tmp %fname%