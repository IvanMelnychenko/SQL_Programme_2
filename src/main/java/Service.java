import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Service {

  private Connection conn;

  public void initDB() throws SQLException {
    Statement st = conn.createStatement();
    try {
      st.execute("DROP TABLE IF EXISTS  Clients");
      st.execute(
          "CREATE TABLE Clients (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR (20) NOT NULL, INT age )");
    } finally {
      st.close();
    }
  }

  public void addClients(Scanner sc) throws SQLException {
    System.out.println("Enter client name");
    String name = sc.nextLine();
    System.out.println("Enter client age");
    String sAge = sc.nextLine();
    int age = Integer.parseInt(sAge);
    PreparedStatement ps = conn.prepareStatement("INSERT  INTO Clients(name, age) VALUES(?,?)");
    try {
      ps.setString(1, name);
      ps.setInt(2, age);
      ps.executeUpdate();
    } finally {
      ps.close();
    }
  }

  public void deleteClients(Scanner sc) throws SQLException {
    System.out.println("Enter client name");
    String name = sc.nextLine();
    PreparedStatement ps = conn.prepareStatement("DELETE  FROM Clients WHERE  name = ?");
    try {
      ps.setString(1, name);
      ps.executeUpdate();
    } finally {
      ps.close();
    }
  }

  public void viewClients(Scanner sc) throws SQLException {
    PreparedStatement ps = conn.prepareStatement("SELECT * FROM Clients ");
    try {
      ResultSet rs = ps.executeQuery();
      try {
        ResultSetMetaData md = rs.getMetaData();

        for (int i = 1; i <= md.getColumnCount(); i++) {
          System.out.println(md.getColumnName(i) + "\t\t");
          System.out.println();

          while (rs.next()) {
            for (int j = 1; j <= md.getColumnCount(); i++) {
              System.out.println(rs.getString(i) + "\t\t");
              System.out.println();

            }
          }
        }
      } finally {
        rs.close();
      }
    } finally {
      ps.close();
    }
  }
  public void changeClients(Scanner sc) throws SQLException {
    System.out.println("Enter client name");
    String name = sc.nextLine();
    System.out.println("Enter new age");
    String sAge = sc.nextLine();
    int age = Integer.parseInt(sAge);
    PreparedStatement ps = conn.prepareStatement("UPDATE  Clients SET age = ? WHERE name = ?)");
    try {
      ps.setString(1, name);
      ps.setInt(2, age);
      ps.executeUpdate();
    } finally {
      ps.close();
    }
  }
}




