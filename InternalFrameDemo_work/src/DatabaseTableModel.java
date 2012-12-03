import java.util.*;
import java.sql.*;
import javax.swing.table.*;

public class DatabaseTableModel extends AbstractTableModel 
{
    private static final long serialVersionUID = 1L;
    private ArrayList<String> columnNames = new ArrayList<String>();
    private ArrayList<Class> columnTypes = new ArrayList<Class>();
    private ArrayList data = new ArrayList();
    private Connection con;
    private String currentTable = "";
    public DatabaseTableModel()throws SQLException, ClassNotFoundException
    {
    }
    public void setConnection(ResultSet rs)throws SQLException, ClassNotFoundException
    {
        setDataSource(rs); 
        this.fireTableDataChanged();
    }
    public int getRowCount()
    {
        synchronized(data)
        {
            return data.size();
        }
    }
    public int getColumnCount()
    {
        return columnNames.size();
    }
    
    public Object getValueAt(int row, int col)
    {
        synchronized(data)
        {
            return ((ArrayList)data.get(row)).get(col);
        }
    }
    public String getColumnName(int col)
    {
        return columnNames.get(col);
    }
    public Class getColumnClass(int col)
    {
        return columnTypes.get(col);
    }
    public boolean isEditable()
    {
        return false;
    }
    public void setValueAt(Object obj, int row, int col)
    {
        synchronized(data)
        {
            ((ArrayList)data.get(row)).set(col, obj);
        }
    }
    public void setTable(String table)
    {
        currentTable = table;
    }
    public void setDataSource(ResultSet rs) throws SQLException, ClassNotFoundException
    {
        ResultSetMetaData rsmd = rs.getMetaData();
        columnNames.clear();
        columnTypes.clear();
        data.clear();
        
        int columnCount = rsmd.getColumnCount();
        for(int i = 0; i < columnCount; i++)
        {
            columnNames.add(rsmd.getColumnName(i+1));
            Class type = Class.forName(rsmd.getColumnClassName(i+1));
            columnTypes.add(type);
        }
        fireTableStructureChanged();
        while(rs.next())
        {
            ArrayList rowData = new ArrayList();
            for(int i = 0; i < columnCount; i++)
            {
                if(columnTypes.get(i) == String.class)
                    rowData.add(rs.getString(i+1));
                else
                    rowData.add(rs.getObject(i+1));
            }
            synchronized(data)
            {
                data.add(rowData);
                this.fireTableRowsInserted(data.size() - 1, data.size() - 1);
            }
        }
    }
}
    
