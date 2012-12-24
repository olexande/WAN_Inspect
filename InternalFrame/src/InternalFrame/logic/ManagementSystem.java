package  InternalFrame.logic;

import java.sql.*;
import java.util.ArrayList;
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
	
	public List<Region> getRegions() throws SQLException {
        List<Region> regions = new ArrayList<Region>();

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT id, Region_name, Region_Description FROM Regions_Table");
            while (rs.next()) {
                Region gr = new Region();
                gr.setRegionId(rs.getInt(1));
                gr.setNameRegion(rs.getString(2));
                gr.setRegion_Description(rs.getString(3));
                regions.add(gr);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return regions;
    }
}