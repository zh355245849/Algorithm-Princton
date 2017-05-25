package han.OOD.parkingLot;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by zh355245849 on 2017/5/25.
 */
public class Level {

    private final List<ParkingSpot> spots;
    public Level(int numOfSpots) {
        spots = new ArrayList<>();
        for (int i = 0; i < numOfSpots; i++) {
            if (i < numOfSpots / 2) {
                spots.add(new ParkingSpot(VehicleSize.Small));
            } else {
                spots.add(new ParkingSpot(VehicleSize.Large));
            }
        }
    }

    public boolean hasSpot(Vehicle v) {
        for (ParkingSpot ps : spots) {
            if (ps.isFit(v)) {
                return true;
            }
        }
        return false;
    }

    public boolean park(Vehicle v) {
        VehicleSize vehicleSize = v.getSize();
        for (ParkingSpot ps : spots) {
            if (ps.isFit(v)) {
                ps.park(v);
                return true;
            }
        }
        return false;
    }

    public boolean leave(Vehicle v) {
        for (ParkingSpot ps : spots) {
            if (v.equals(ps.getVehicle())) {
                ps.leave();
                return  true;
            }
        }
        return false;
    }
}
