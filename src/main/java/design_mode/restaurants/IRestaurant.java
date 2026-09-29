package design_mode.restaurants;

import design_mode.food.IFood;

public interface IRestaurant {
    IFood getFood(String foodName);
}
