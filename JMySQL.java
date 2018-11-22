package JMySQL;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WebSofter
 */
public class JMySQL {
    Connection connection;
    Statement statement;
	/** Connect to the DB on server*/
    public String connect(String driver,String server,String db,String user, String password){
        String status = null;
        try {
            
    		// Driver name
			String driverName = driver;//By default it is "com.mysql.jdbc.Driver"; 

			Class.forName(driverName);//Driver register

			// Create a connection to the database
			String serverName = server;//For example "mysql.examplehost.ru";
			String mydatabase = db;//For example "root";
			String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
			String username = user;//"User password";
			String psword = password;//"parol";

			this.connection = (Connection) DriverManager.getConnection(url, username, psword);//Getting connection
                        
			System.out.println("is connect to DB" + this.connection);
                        status = "ok";//Successfully connection
		} // end try
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			// Could not find the database driver
                        System.out.println("Could not find the database driver");
                        status = "Could not find the database driver";
		} catch (SQLException e) {
			e.printStackTrace();
			// Could not connect to the database
                        System.out.println("Could not connect to the database");
                        status = "Could not connect to the database";
		}
                
        return status;
    }
	/** Insert new data to table over new arrays column name and her value*/
    public String insert(String tableName,String[] colNames,String[] colValues)
    {
        String status = null;
        String collumns = "";
        String values = "";
      try {
            for(int i=0;i<=(colNames.length-1);i++)
            {
                if(i!=0)
                {
                  collumns=collumns + ", " + colNames[i];
                  values = values + ", " +"'"+ colValues[i]+"'";
                }else
                {
                    collumns=collumns + colNames[i];
                    values=values +"'"+ colValues[i]+"'";
                }
            }

            String query = "INSERT INTO "+tableName+"("+collumns+") VALUES("+values+")";
            System.out.println(query);
            this.connection.setCharacterEncoding("UTF-8");
            Statement stmt = (Statement) this.connection.createStatement();
            
            if(stmt.execute(query)) status = "ok";
            else status = "ok";
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
            status = ex.getMessage();
        }
        return status;
    }
	/** Update data in table over new arrays column name and her value*/
public boolean update(String tableName,String[] colNames,String[] colValues,String where)
    {
        String collumns = "";
        String values = "";
        String queryData = "";
      try {
            for(int i=0;i<=(colNames.length-1);i++)
            {
                if(i!=0)
                {
                  collumns=collumns + ", " + colNames[i];
                  values = values + ", " +"'"+ colValues[i]+"'";
                }else
                {
                    //collumns=collumns + colNames[i];
                    //values=values +"'"+ colValues[i]+"'";
                    queryData = queryData + colNames[i] + "=" + "'" + colValues[i] + "'";
                }
            }
            
            String query = "UPDATE "+tableName+" SET "+queryData+" WHERE "+where+"";
            System.out.println(query);
            Statement stmt = (Statement) this.connection.createStatement();


            if(stmt.execute(query))
            {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
	/** Reading data from ResultSet class by MySQL over WHERE*/
public ResultSet read(String select,String tableName,String where){
      try {
            if(!where.equals("")){ where="WHERE "+where+"";}else{where="";}
            String query = "SELECT "+select+" FROM "+tableName+" "+where+"";
            System.out.println(query);
            Statement stmt = (Statement) this.connection.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.getResultSet ();
            /*
            int count = 0;
            while (rs.next ())
            {
                int idVal = rs.getInt ("id");
                String checkVal = rs.getString ("check_info");
                System.out.println (
                        "id = " + idVal
                        + ", name = " + checkVal
                        );
                ++count;
            }
            rs.close ();
            System.out.println (count + " rows were retrieved");
        */

                return rs;
            
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
	/** Delete from table over WHERE*/
public boolean delete(String tableName,String where)
    {

      try {
            String query = "DELETE FROM "+tableName+" WHERE "+where+"";
            System.out.println(query);
            Statement stmt = (Statement) this.connection.createStatement();

            if(stmt.execute(query))
            {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
	/** Check contains in table by coll name*/
    public boolean contains(String tableName,String colName, String eqValue){
      try{
        String query = "Select * FROM "+tableName+"";
			Statement stmt = (Statement) this.connection.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			String value;
			while (rs.next()) {
				value = rs.getString(colName);
                                if(value.equals(eqValue)){
                                    return true;
                                }
			} // end while 
                        
      }// end try
       catch (SQLException e) {
			e.printStackTrace();
			// Could not connect to the database
                        System.out.println("Could not connect to the database");
		}
         return false;
    }
	/** Check contains in table by coll number*/
    public boolean contains(String tableName,int colNumber, String eqValue){
      try{
        String query = "Select * FROM "+tableName+"";
			Statement stmt = (Statement) this.connection.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			String value;
			while (rs.next()) {
				value = rs.getString(colNumber);
                                if(value.equals(eqValue)){
                                    return true;
                                }
			} // end while 
                        
      }// end try
       catch (SQLException e) {
			e.printStackTrace();
			// Could not connect to the database
                        System.out.println("Could not connect to the database");
		}
         return false;
    }
	/** Close current connection*/
    public void close()
    {
        try {
            this.connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
