set fname=result.txt
set lan=192.168.2
(for /l %%A in (1,1,254) do @ping -a -n 1 %lan%.%%A&arp -a|find " %lan%.%%A ") > %fname%
findstr /c:"�����" %fname% >> log.tmp
rem del %fname%
rem move log.tmp %fname%

rem type %fname% | findstr /i /v "�������᪨�" > temp.txt
rem del %fname%
rem rename temp.txt %fname%