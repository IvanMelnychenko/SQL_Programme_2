import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionService {

  private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/dbmelnychenko?verifyServerCertificate=false&useSSL=true";
  private static final String DB_USER = "root";
  private static final String DB_PASSWORD = "13856889";


  public Connection getConnection() throws SQLException {
    Connection conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
    return conn;
  }

  public void initDb() throws SQLException {
    Statement st = getConnection().createStatement();
    try {
      st.execute("DROP TABLE IF EXISTS  Clients");
      st.execute(
          "CREATE TABLE Clients (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR (20) NOT NULL, age INT )");
    } finally {
      st.close();
    }
  }
}
