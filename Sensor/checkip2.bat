set fname=result.txt
set lan=192.168.2
(for /l %%A in (1,1,254) do @ping -a -n 1 %lan%.%%A&arp -a|find " %lan%.%%A ") > %fname%
findstr /c:"динамический" %fname% >> log.tmp
del %fname%
move log.tmp %fname%

rem type %fname% | findstr /i /v "динамический" > temp.txt
rem del %fname%
rem rename temp.txt %fname%