package students.logic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ManagementSystem {

    private static Connection con;
    private static ManagementSystem instance;

    private ManagementSystem() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://192.168.2.224:3306/lan_db";
            con = DriverManager.getConnection(url, "landb_user", "landb_user");
        } catch (ClassNotFoundException e) {
            throw new Exception(e);
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    public static synchronized ManagementSystem getInstance() throws Exception {
        if (instance == null) {
            instance = new ManagementSystem();
        }
        return instance;
    }

    public List<Lan> getLans() throws SQLException {
        List<Lan> lans = new ArrayList<Lan>();

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT lan_id, description, lan_addr, comment FROM lan_list");
            while (rs.next()) {
                Lan gr = new Lan();
                gr.setLanId(rs.getInt(1));
                gr.setNameLan(rs.getString(2));
                gr.setCurator(rs.getString(3));
                gr.setSpeciality(rs.getString(4));

                lans.add(gr);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return lans;
    }

    public Collection<IP> getAllIPs() throws SQLException {
        Collection<IP> ips = new ArrayList<IP>();

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(
                    "SELECT comp_id, point, mac_adress, ip_address, " +
                    "sex, dateOfBirth, lan_id, educationYear FROM IP_table " +
                    "ORDER BY ip_address, point, mac_adress");
            while (rs.next()) {
                IP st = new IP(rs);
                ips.add(st);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return ips;
    }

    public Collection<IP> getIPsFromLan(Lan lan, int year) throws SQLException {
        Collection<IP> ips = new ArrayList<IP>();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(
                    "SELECT comp_id, point, mac_adress, ip_address, " +
                    "sex, dateOfBirth, lan_id, educationYear FROM IP_table " +
                    "WHERE lan_id=? AND educationYear=? " +
                    "ORDER BY ip_address, point, mac_adress");
            stmt.setInt(1, lan.getLanId());
            stmt.setInt(2, year);
            rs = stmt.executeQuery();
            while (rs.next()) {
                IP st = new IP(rs);

                ips.add(st);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return ips;
    }

    public void moveIPsToLan(Lan oldLan, int oldYear, Lan newLan, int newYear) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(
                    "UPDATE IP_table SET lan_id=?, educationYear=? " +
                    "WHERE lan_id=? AND educationYear=?");
            stmt.setInt(1, newLan.getLanId());
            stmt.setInt(2, newYear);
            stmt.setInt(3, oldLan.getLanId());
            stmt.setInt(4, oldYear);
            stmt.execute();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void removeIPsFromLan(Lan lan, int year) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(
                    "DELETE FROM IP_table WHERE lan_id=? AND educationYear=?");
            stmt.setInt(1, lan.getLanId());
            stmt.setInt(2, year);
            stmt.execute();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void insertIP(IP ip) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(
                    "INSERT INTO IP_table " +
                    "(point, mac_adress, ip_address, sex, dateOfBirth, lan_id, educationYear) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, ip.getFirstName());
            stmt.setString(2, ip.getPatronymic());
            stmt.setString(3, ip.getSurName());
            stmt.setString(4, new String(new char[]{ip.getSex()}));
            stmt.setDate(5, new Date(ip.getDateOfBirth().getTime()));
            stmt.setInt(6, ip.getLanId());
            stmt.setInt(7, ip.getEducationYear());
            stmt.execute();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void updateIP(IP ip) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(
                    "UPDATE IP_table SET " +
                    "point=?, mac_adress=?, ip_address=?, " +
                    "sex=?, dateOfBirth=?, lan_id=?, educationYear=?" +
                    "WHERE comp_id=?");
            stmt.setString(1, ip.getFirstName());
            stmt.setString(2, ip.getPatronymic());
            stmt.setString(3, ip.getSurName());
            stmt.setString(4, new String(new char[]{ip.getSex()}));
            stmt.setDate(5, new Date(ip.getDateOfBirth().getTime()));
            stmt.setInt(6, ip.getLanId());
            stmt.setInt(7, ip.getEducationYear());
            stmt.setInt(8, ip.getIPId());
            stmt.execute();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void deleteIP(IP ip) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(
                    "DELETE FROM IP_table WHERE comp_id=?");
            stmt.setInt(1, ip.getIPId());
            stmt.execute();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}