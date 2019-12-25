import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DatabaseService {

  private static Connection conn;

  public static Connection getConn() {
    return conn;
  }

  public void getWay() {
    Menu menu = new Menu();
    ConnectionService connectionService = new ConnectionService();
    try {
      conn = connectionService.getConnection();
      connectionService.initDb();
      menu.getAnswer();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void addClients() throws SQLException {
    Scanner sc = new Scanner(System.in);
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

  public void deleteClients() throws SQLException {
    Scanner sc = new Scanner(System.in);
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

  public void viewClients() throws SQLException {

    PreparedStatement ps = conn.prepareStatement("SELECT * FROM Clients ");

    try {
      ResultSet rs = ps.executeQuery();
      try {
        while ((rs.next())) {
          int id = rs.getInt("id");
          String name = rs.getString("name");
          int age = rs.getInt("age");
          Client client = new Client(id, name, age);
          System.out.println(client);
        }
      } finally {
        rs.close();
      }
    } finally {
      ps.close();
    }
  }

  public void changeClients() throws SQLException {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter client name");
    String name = sc.nextLine();
    Scanner sq = new Scanner(System.in);
    System.out.println("Enter new age");
    int age = sq.nextInt();
    PreparedStatement ps = conn.prepareStatement("UPDATE  Clients SET age = ? WHERE name = ?");
    try {
      ps.setInt(1, age);
      ps.setString(2, name);
      ps.executeUpdate();
    } finally {
      ps.close();
    }
  }
}