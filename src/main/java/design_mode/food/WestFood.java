package design_mode.food;

public class WestFood implements IFood {

    @Override
    public void prepare() {
        System.out.println("WestFood prepare");
    }

    @Override
    public void bakeFood() {
        System.out.println("WestFood bakeFood");
    }

    @Override
    public void box() {
        System.out.println("WestFood box");
    }

    @Override
    public void showFood() {

    }
}
