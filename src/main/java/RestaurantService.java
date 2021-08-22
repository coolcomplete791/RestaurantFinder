import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException{
        for(Restaurant restaurant : restaurants)
        {
            if(restaurant.getName().toLowerCase().contains(restaurantName.toLowerCase()))
                return  restaurant;
        }
        throw new restaurantNotFoundException(restaurantName);
    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public double getSelectedMenuItemsTotal(String restaurantName, List<String> menuItemsName) throws restaurantNotFoundException {
        Restaurant restaurant = findRestaurantByName(restaurantName);
        double totalPrice=0.0;
        List<Item> menuItems = restaurant.getMenu();
        for(Item item: menuItems)
        {
            if(menuItemsName.stream().filter(n-> n.equals(item.getName())).count() > 0) {
                totalPrice += item.getPrice();
            }
        }
        return totalPrice;
    }
}
