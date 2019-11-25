import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

  static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/dbMylnychenko";
  static final String DB_USER = "root";
  static final String DB_PASSWORD = "13856889";
  static Connection conn;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Service service = new Service();
    try {
      try {
        conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        service.initDB();
        while (true) {
          System.out.println("1: add clients");
          System.out.println("2: delete clients");
          System.out.println("3: view clients");
          System.out.println("4: change clients");

          String ss = sc.nextLine();
          int s = Integer.parseInt(ss);
          switch (s) {
            case 1:
              service.addClients(sc);
              break;
            case 2:
              service.deleteClients(sc);
              break;
            case 3:
              service.viewClients(sc);
            case 4:
              service.changeClients(sc);
              break;
            default:
              return;
          }
        }
      } finally {
        sc.close();
        if (conn != null) {
          conn.close();
        }

      }
    } catch (SQLException e) {
      e.printStackTrace();
      return;
    }
  }
}

