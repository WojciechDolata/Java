

public class A{
    protected int number = new int;
    package String name = new String;
    public A(int numberToBe, String nameToBe){
        this.number = numberToBe;
        this.name = nameToBe;
    }

    public void callDecrement() {
        this.decrement();
    }

    public void callIncrement() {
        this.increment();
    }

    public void callChangeName(String nameToBe) {
        this.changeName(nameToBe);
    }

    private void increment() {
        number++;
    }

    protected void decrement() {
        number--;
    }

    package void changeName(String nameToBe){
        this.name = nameToBe;
    }
}