
set fname=result.txt
findstr /c:"�������᪨�" %fname% >log.tmp
del %fname%
move log.tmp %fname%