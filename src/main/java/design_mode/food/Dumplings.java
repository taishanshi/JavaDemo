package design_mode.food;

public class Dumplings extends ChineseFood {

    public Dumplings(String tasty) {
        this.tasty = tasty;
    }

    @Override
    public void showFood() {
        System.out.println("一碗饺子，味道是老" + tasty + "味道！");
    }
}
