package design_mode.restaurants;

import design_mode.food.IFood;
import design_mode.food_factory.FoodFactory;

public class Restaurant implements IRestaurant {
    private int id;
    private String name;
    private String address;
    protected FoodFactory foodFactory;


    @Override
    public IFood getFood(String foodName) {
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
