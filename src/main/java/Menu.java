import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

  private DatabaseService data = new DatabaseService();

  public void getAnswer() throws SQLException {
    Scanner sc = new Scanner(System.in);
    try {
      while (true) {
        System.out.println("1: add clients");
        System.out.println("2: delete clients");
        System.out.println("3: view clients");
        System.out.println("4: change clients");
        String ss = sc.nextLine();
        int s = Integer.parseInt(ss);
        switch (s) {
          case 1:
            data.addClients();
            break;
          case 2:
            data.deleteClients();
            break;
          case 3:
            data.viewClients();
            break;
          case 4:
            data.changeClients();
            break;
          default:
            return;
        }
      }
    } finally {
      sc.close();
      if (DatabaseService.getConn() != null) {
        DatabaseService.getConn().close();
      }
    }
  }
}