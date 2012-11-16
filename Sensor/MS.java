import java.util.*;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractList;

public class MS {
	private String CompIP;
	private static Comp cp1 = new Comp();
	
	private static Connection con;
	private static MS instance;
	
	PreparedStatement stmt = null;
        ResultSet rs = null;

	public static synchronized MS getInstance() throws Exception {
		if (instance == null) {
			instance = new MS();
		}
		return instance;
	}
	
	public static void insertPCTODB(Comp aComp) throws SQLException {
        PreparedStatement stmt = null;
        try {
			con = DriverManager.getConnection("jdbc:mysql://192.168.2.224:3306/lan_db", "landb_user", "landb_user");
            stmt = con.prepareStatement("INSERT INTO IP_table_new_scan VALUES (?, ?, ?)");
             stmt.setString(1, cp1.getIP_Adress());
             stmt.setString(2, cp1.getMACAdress());
             stmt.setString(3, null);
            stmt.execute();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
	
    public static void main(String[] args) {

		Comp g1 = new Comp();
		Comp g2 = new Comp();
		int size;
		boolean result = false;
		String str;
			
		try
		{
		
		try
		{
			// TODO Добавить получение DNS имени.
			// TODO Переделать чтение из файла с проверкой и использованием правильной кодировки.
			// TODO Добавить проверку на "прочитано пустую строку из файла" //oem866
			BufferedReader br = new BufferedReader(
				new InputStreamReader(
					new FileInputStream("result.txt"),"UTF-8"));
			while ((str = br.readLine()) != null) {
				// TODO Добавить проверку на пустые строки.
				if (/*(str == "\r") | (str == "\n") | */(str == "\r\n") | (str == "\n\r"))
				{}  
				else
				{
					StringTokenizer token = new StringTokenizer (str);
					if (token.hasMoreTokens())
					{
						cp1.setIP_Adress(token.nextToken());
						if (token.hasMoreTokens())
						{
							cp1.setMACAdress(token.nextToken());
						}
					}
					System.out.print(cp1.getIP_Adress());
					System.out.print(" ");
					System.out.print(cp1.getMACAdress());
					System.out.println();	
					insertPCTODB(cp1);
				}
			}
		}
		catch
		(Exception e) {
            e.printStackTrace();
        }
		
		
		}
		catch (Exception e) {
            e.printStackTrace();
        }
		
		HashSet collection = new HashSet ();
		Iterator iterator;
		iterator = collection.iterator();  
		int i2 = 1;
		System.out.println();
		size = collection.size();
		if (collection.isEmpty()){
			//System.out.println("Collection is empty");
		}
		else{
			//System.out.println( "Collection size: " + size);
		}
	  
		g1.setCompId(0);
		g2.setCompId(1);
    }
}
