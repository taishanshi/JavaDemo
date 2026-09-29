package design_mode.restaurants;

import design_mode.food.IFood;
import design_mode.food_factory.BjFoodFactory;

public class BjRestaurant extends Restaurant {

    public BjRestaurant(int id, String name, String address) {
        setId(id);
        setName(name);
        setAddress(address);
        this.foodFactory = new BjFoodFactory();
    }

    @Override
    public IFood getFood(String foodName) {
        return this.foodFactory.makeFood(foodName);
    }
}
