package design_mode.food;

public class ChineseFood implements IFood {
    String tasty = "";

    @Override
    public void prepare() {
        System.out.println("ChineseFood prepare");
    }

    @Override
    public void bakeFood() {
        System.out.println("ChineseFood bakeFood");
    }

    @Override
    public void box() {
        System.out.println("ChineseFood box");
    }

    @Override
    public void showFood() {

    }
}
