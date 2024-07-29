import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleCache {

    private final Map<String, Vehicle> cache = new HashMap<>();

    public VehicleCache() {
        Car car = new Car("Bugatti", "Chiron", "Blue", 261);
        Bus bus = new Bus("Mercedes", "Setra", "White", 48);

        cache.put("Bugatti Chiron", car);
        cache.put("Mercedes Setra", bus);
    }

    public Vehicle get(String key) {
        return cache.get(key).clone();
    }

    public void putList(List<Vehicle> vehicles) {
        vehicles.forEach(vehicle -> cache.put(vehicle.getBrand() + " " + vehicle.getModel(), vehicle));
    }

    public void put(Vehicle vehicle) {
        cache.put(vehicle.getBrand() + " " + vehicle.getModel(), vehicle);
    }

}