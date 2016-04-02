# JMySQL
Ready and free Java class for the CRUD - operations with MySQL database

Designed by WebSofter(David Amirkhanov)<br/>
Site: www.websofter.ru<br/>
Email: mail.websofter@gmail.com<br/>

String connect (...) - connects to the database;<br/>
String insert (...) - adds a new data into the table of the current connection;<br/>
boolean update (...) - updates the data in the table of the current connection;<br/>
ResultSet read (...) - reads the data from the current connection table;<br/>
boolean delete (...) - deletes the data in the table of the current connection;<br/>
boolean contains (...) - checks the value of the presence on the ordinal number of the column in the table of the current connection;<br/>
boolean contains (...) - checks the value of the presence of the title column in the table of the current connection;<br/>
void close (...) - closes the connection to the current connection.<br/>

#Simple example

БД public String driver = "com.mysql.jdbc.Driver"; <br/>
public String server = "localhost:3306"; <br/>
public String db = "myDbName"; <br/>
public String user = "root"; <br/>
public String password = "123456";<br/>
JMySQL db = new JMySQL();<br/>
String status = db.connect(driver, server, db, user, password);<br/>
String result = db.insert("user", new String[]{"login", "password", "email", "phone", "name", "surname"}, new String[]{"MyLogin", "123456", "mail@mail.com", "8800 888 88 88", "David", "Amirkhanov"});<br/>

