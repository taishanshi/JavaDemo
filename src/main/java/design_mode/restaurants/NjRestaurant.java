package design_mode.restaurants;

import design_mode.food.IFood;
import design_mode.food_factory.NjFoodFactory;

public class NjRestaurant extends Restaurant {
    public NjRestaurant(int id, String name, String address) {
        setId(id);
        setName(name);
        setAddress(address);
        this.foodFactory = new NjFoodFactory();
    }

    @Override
    public IFood getFood(String foodName) {
        return foodFactory.makeFood(foodName);
    }
}
