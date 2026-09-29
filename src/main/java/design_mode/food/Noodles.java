package design_mode.food;

public class Noodles extends ChineseFood{
    public Noodles(String tasty) {
        this.tasty = tasty;
    }

    @Override
    public void showFood() {
        System.out.println("一碗面条，味道是老" + tasty + "味道！");
    }
}
