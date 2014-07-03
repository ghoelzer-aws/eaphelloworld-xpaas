/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the 
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.helloworld;
import javax.naming.*;
import javax.sql.DataSource;

import java.sql.*;

/**
 * A simple CDI service that returns data from a passed in table that has "id" and "name" columns
 * 
 * @author Greg Hoelzer
 * 
 */
public class Test123DBService {

   String createHelloMessage(String tableName) throws SQLException {

	   DataSource ds = null;  
	   Connection con = null;  
	   Statement stmt = null;  
	   InitialContext ic;  
	   String OutputHTML="";
	      
	   try {  
		   ic = new InitialContext();  
		   ds = (DataSource) ic.lookup("java:jboss/datasources/PostgreSQLDS");  
		   con = ds.getConnection();  
		   stmt = con.createStatement();  
		   ResultSet rs = stmt.executeQuery("select * from " + tableName);  
	    
		   while (rs.next()) {  
			   OutputHTML = "<br> " + rs.getString("id") + " | " + rs.getString("name") + "</br>" ;  
		   }  
		   rs.close();  
		   stmt.close();  
	   } catch (Exception e) {  
		   OutputHTML = "Exception thrown :/" +  e.getMessage();  
	   } finally {  
		   if (con != null) {  
			   con.close();  
		   }  
	   } 
	   
      return "Hello " + tableName + " " +OutputHTML;
   }

}
