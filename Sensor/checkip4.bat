set fname=result.txt
set lan=192.168.2
(for /l %%A in (22,1,22) do @ping -a -n 1 %lan%.%%A&arp -a|find " %lan%.%%A ") > %fname%
findstr /c:"динамический" /c:"Обмен" %fname% >> log.tmp

