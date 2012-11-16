import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractList;
import java.util.StringTokenizer;

public class fread{
	public static void main(String[] args) {
		String[] st = new String[3];
		Comp cp1 = new Comp();
		String str;
		try
		{
		BufferedReader br = new BufferedReader(
             new InputStreamReader(
				new FileInputStream("result.txt"),"UTF8"));
			
			while ((str = br.readLine()) != null) {
			
			//str = br.readLine();
			// String[] sp = str.split(" ");
			// System.out.println(sp[0]);
				
			StringTokenizer token = new StringTokenizer (str);
			//int i = 0;
			//while (token.hasMoreTokens()) {
				//st[i] = token.nextToken();
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
			}
				// i++;
			//}
		
		// for (i=0; i==3; i++)
		// {
			// System.out.println(st[i]);
		// }
		
		// BufferedReader reader = new BufferedReader(new FileInputStream("result.txt"));
		// String line;
		// while ((str = br.readLine()) != null) {
			// if (line.contains(stopWord)) {
			// strings.add(line);
			// if (/*(str == "\r") | (str == "\n") | */(str == "\r\n") | (str == "\n\r"))
			// {}  
			// else
			// {
			// System.out.println(str);
			// }
		// }
		}
		catch
		(Exception e) {
            e.printStackTrace();
        }
	}
}