import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

  private DatabaseService data = new DatabaseService();

  public void getAnswer() throws SQLException {
    Scanner sc = new Scanner(System.in);
    System.out.println("1: add clients");
    System.out.println("2: delete clients");
    System.out.println("3: view clients");
    System.out.println("4: change clients");

    String ss = sc.nextLine();
    int s = Integer.parseInt(ss);
    try {
      switch (s) {
        case 1:
          data.addClients(sc);
          break;
        case 2:
          data.deleteClients(sc);
          break;
        case 3:
          data.viewClients(sc);
        case 4:
          data.changeClients(sc);
          break;
        default:
          return;
      }
    } finally {
      sc.close();
    }
  }
}
