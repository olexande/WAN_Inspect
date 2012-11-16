SELECT * FROM IP_table 
WHERE IP_table.IP_adress not in 
(SELECT IP_adress FROM IP_table_new_scan)

SELECT * FROM IP_table_new_scan 
WHERE IP_adress not in 
(SELECT IP_adress FROM IP_table)