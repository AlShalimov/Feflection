public class PersonClassForTest {
    private int id;
    private String name = "default";

    public void PersonClassForTest(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getNumber() {
        return id;
    }

    public void setNumber(int id) {
        this.id = id;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void printData() {
        System.out.println(id + name);
    }

    final void printName() {
        System.out.println(name);
    }

    static void Id() {
    }

    final void hello() {
        System.out.println("hello");
    }

    public void method1() {

    }

    public void method2(Object object) {

    }

    public void method3() {

    }

}
