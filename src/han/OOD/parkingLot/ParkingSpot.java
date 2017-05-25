package han.OOD.parkingLot;

/**
 * Created by zh355245849 on 2017/5/25.
 */
class ParkingSpot {
    private final VehicleSize size;
    private Vehicle vehicle;

    public ParkingSpot(VehicleSize size) {
        this.size = size;
    }

    public boolean isFit(Vehicle v) {
        return vehicle == null && v.getSize().getSize() <= size.getSize();
    }

    public void park(Vehicle v) {
        vehicle = v;
    }

    public void leave() {
        vehicle = null;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}


