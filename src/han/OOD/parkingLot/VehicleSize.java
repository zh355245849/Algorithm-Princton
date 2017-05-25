package han.OOD.parkingLot;

/**
 * Created by zh355245849 on 2017/5/25.
 */
public enum VehicleSize {
    Small(1),
    Large(2);

    private final int size;

    VehicleSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}