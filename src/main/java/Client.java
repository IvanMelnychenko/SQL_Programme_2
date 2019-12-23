public class Client {
  private int id;
  private String name;
  private int age;

  public Client() {
  }

  public Client(int id, String name, int age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

  @Override
  public String toString() {
   return "Client [" + id + "\t" + name + "\t" + age + "]";
  }
}
