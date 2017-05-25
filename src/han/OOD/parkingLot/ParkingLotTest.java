package han.OOD.parkingLot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zh355245849 on 2017/5/25.
 */
public class ParkingLotTest {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(3, 10);
        List<Vehicle> vehicles = new ArrayList<>();
        //Test parkgit
        for (int i = 0; i < 50; i++) {
            Vehicle v = (i % 2 == 0) ? new Car() : new Truck();
            vehicles.add(v);
            boolean hasSpot = parkingLot.hasSpot(v);
            if (i < 30) {
                System.out.println(hasSpot);
                assert hasSpot;
                System.out.println(parkingLot.park(v));
                assert parkingLot.park(v);
            } else {
                System.out.println("in else");
                System.out.println(hasSpot);
                assert !hasSpot;
                System.out.println(parkingLot.park(v));
                assert !parkingLot.park(v);
            }
        }
        assert vehicles.size() == 50;
        int i = 0;
        //Test leave
        for (Vehicle v : vehicles) {
            assert i >= 30 || parkingLot.leave(v);
            i++;
        }
    }
}
