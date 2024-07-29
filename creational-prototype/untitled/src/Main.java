public class Main {
    public static void main(String[] args) {

        Vehicle car = new Car("A", "A", "A", 10);
        Vehicle bus = new Bus("A", "A", "A", 10);

        Vehicle copyCar = car.clone();

        System.out.println("Car = " + car);
        System.out.println("Car clone = " + copyCar);

        VehicleCache cache = new VehicleCache();
        cache.put(car);

        // getting the vehicle from hash
        Vehicle vehicle = cache.get("Mercedes Setra");
        System.out.println("Vehicle cache = " + vehicle);

        // changing the model
        vehicle.setModel("AAAAAAAAAAA");

        // the change wont affect the object in the hash, because i returned the copy of the object
        Vehicle vehicle2 = cache.get("Mercedes Setra");
        System.out.println(vehicle2);


    }
}