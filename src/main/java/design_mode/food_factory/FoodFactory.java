package design_mode.food_factory;

import design_mode.food.IFood;

public abstract class FoodFactory {
    public static String Hamburger = "Hamburger";
    public static String Dumpling = "Dumpling";
    public static String Noodles = "Noodles";

    public IFood getFood(String foodName) {
        IFood food;
        food = makeFood(foodName);
        food.prepare();
        food.bakeFood();
        food.showFood();
        return food;

    }
    abstract public IFood makeFood(String food);
}
