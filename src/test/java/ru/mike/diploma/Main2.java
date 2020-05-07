/*
package ru.mike.diploma;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.sql.*;

public class Main2 {


    static Connection con;
    static String connectionString = "jdbc:hsqldb:file:db-data/mydatabase";

    public static void main(String[] args) throws Exception {

        String createContacts = readToString("C:\\intership\\diploma\\src\\main\\resources\\init_HSQLDB.sql");
       String populateContacts = readToString("C:\\intership\\diploma\\src\\main\\resources\\populateHSQLDB.sql");
        System.out.println("Attempting to create contacts DB ... ");


        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (ClassNotFoundException e) {
            throw e;
        }

        try {
            // will create DB if does not exist
            // "SA" is default user with hypersql
            con = DriverManager.getConnection(connectionString, "SA", "");

            // create table
            con.createStatement()
                    .executeUpdate(createContacts);

            // add contacts
            con.createStatement()
                    .executeUpdate(populateContacts);

            // select everything
            PreparedStatement pst = con.prepareStatement("select * from user");
            pst.clearParameters();
            ResultSet rs = pst.executeQuery();


            while (rs.next()) {
                String name = rs.getString("name");
                System.out.println(name);
                */
/*Integer id = rs.getInt("id");
                Integer price = rs.getInt("price");
                Date date = rs.getDate("datemenu");*//*




            }











	   */
/*     List<Contact> contacts = new ArrayList<>();
	        while(rs.next()){
	        	contacts.add(new Contact(
	        			rs.getString(1),
	        			rs.getString(2),
	        			rs.getString(3)
	        		)
    			);
	        }

	        for(Contact c : contacts) {
	        	System.out.println(c);
	        }*//*


        } catch (SQLException e) {
            throw e;
        } finally {
            con.close();
        }
    }

    public static String readToString(String fname) throws Exception {
        File file = new File(fname);
        String string = FileUtils.readFileToString(file, "utf-8");
        return string;
    }


}
*/
