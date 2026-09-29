package design_mode;

import design_mode.restaurants.Restaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantPlatform {
    private static volatile RestaurantPlatform restaurant;

    private RestaurantPlatform(){}

    public static RestaurantPlatform getInstance(){
        if (restaurant == null){
            synchronized (RestaurantPlatform.class){
                if (restaurant == null){
                    restaurant = new RestaurantPlatform();
                }
            }
        }
        return restaurant;
    }

    private final Map<String, Restaurant> restaurants = new HashMap<>();
    private final List<Restaurant> restaurantList = new ArrayList<>();

    public void addRestaurant(Restaurant restaurant){
        restaurants.put(restaurant.getName(), restaurant);
        restaurantList.add(restaurant);
    }

    public Restaurant getRestaurant(String name){
        return restaurants.get(name);
    }

    public Restaurant getRestaurant(int id){
        return restaurants.get(id);
    }

    public List<Restaurant> getRestaurantList(){
        return restaurantList;
    }

    public void removeRestaurant(Restaurant restaurant){
        restaurants.remove(restaurant.getName());
        restaurantList.remove(restaurant);
    }
}
