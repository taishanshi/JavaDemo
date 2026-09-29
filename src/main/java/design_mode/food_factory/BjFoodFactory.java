package design_mode.food_factory;

import design_mode.food.Dumplings;
import design_mode.food.IFood;
import design_mode.food.Noodles;

public class BjFoodFactory extends FoodFactory {
    @Override
    public IFood makeFood(String food) {
        if(FoodFactory.Dumpling.equals(food)){
            return new Dumplings("北京");
        } else if(FoodFactory.Noodles.equals(food)){
            return new Noodles("北京");
        }
        return null;
    }
}
