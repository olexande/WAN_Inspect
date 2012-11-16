import java.io.*;
import java.util.*;

public class Comp {

    private int compId;
    private String nameGroup;
    private String speciality;
	private String CompIP, CompIP2;
	private int IPAdress;
	private String IP_Adress;
	private String MACAdress;
	boolean PCisActive;
	

    public int getCompId() {
        return compId;
    }

    public void setCompId(int compId) {
        this.compId = compId;
	}
	
	public int getIPAdress() {
		return IPAdress;
	}

    public void setIPAdress(int IPAdress) {
        this.IPAdress = IPAdress;
    }
	
	public String getIP_Adress() {
		return IP_Adress;
	}

    public void setIP_Adress(String IP_Adress) {
        this.IP_Adress = IP_Adress;
    }
	
	public String getMACAdress() {
		return MACAdress;
	}

    public void setMACAdress(String MACAdress) {
        this.MACAdress = MACAdress;
    }
	
	public boolean getPCisActive() {
		return PCisActive;
	}

    public void setPCisActive(boolean PCisActive) {
        this.PCisActive = PCisActive;
    }
	
	public boolean checkCompIP(String CompIP) {
		//String shell_command_start = "ping 192.168.88.";
		String shell_command_start = "checkip.bat 192.168.2.";
		//String shell_command_end = " -n 1";
		String shell_command_end = "";
		String shell_command;
		Process proc = null;
		String s1 = null;
        this.CompIP = CompIP;
		// String str = CompIP;
		shell_command = (shell_command_start + CompIP + shell_command_end);
		//shell_command = ("checkip.bat");
		//System.out.println(shell_command);
		try {
			String s;
			proc = Runtime.getRuntime().exec(shell_command, null, null);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			while((s = bufferedReader.readLine()) != null) {
				//System.out.println(s);	
				s1 = s;					
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("s1 = " + s1);
		if (s1.equals("Host is UP!")) return true;
		else  return false; /*if ((s1) <> ("Host is UP!"))*/
		//return true;
    }
	
	public boolean checkCompIPUser(String CompIP2) {
		String shell_command_start = "4.bat  192.168.2.";
		String shell_command_end = "";
		String shell_command;
		Process proc = null;
		String s1 = null;
        this.CompIP2 = CompIP2;
		shell_command = (shell_command_start + CompIP2 + shell_command_end);
		try {
			String s;
			proc = Runtime.getRuntime().exec(shell_command, null, null);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			while((s = bufferedReader.readLine()) != null) {
				s1 = s;					
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		/* if (s1.equals("Host is UP!")) return true;
		else  return false; /*if ((s1) <> ("Host is UP!"))*/
		//return true; */
		return true;
    }
}
