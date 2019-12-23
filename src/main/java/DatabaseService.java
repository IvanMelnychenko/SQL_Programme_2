import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DatabaseService {

  private static Connection conn;


  public void getWay() {
    Menu menu = new Menu();
    ConnectionService connectionService = new ConnectionService();
    try {
      conn = connectionService.getConnection();
      connectionService.initDb();
while (true) {
  menu.getAnswer();
}
    } catch (SQLException e) {
      e.printStackTrace();
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
    ResultSet rs = ps.executeQuery();
    while ((rs.next())) {
      int id = rs.getInt("Clients_id");
      String name = rs.getString("Clients_name");
      int age = rs.getInt("Clients_age");
      Client client = new Client(id, name, age);
      System.out.println(client);
      rs.close();
      ps.close();
    }
  }

  public void changeClients(Scanner sc) throws SQLException {
    System.out.println("Enter client name");
    String name = sc.nextLine();
    System.out.println("Enter new age");
    String sAge = sc.nextLine();
    int age = Integer.parseInt(sAge);
    PreparedStatement ps = conn.prepareStatement("UPDATE  Clients SET age = ? WHERE name = ?");
    try {
      ps.setString(1, name);
      ps.setInt(2, age);
      ps.executeUpdate();
    } finally {
      ps.close();
    }
  }
}