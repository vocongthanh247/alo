package TTCHC8165.ChiHuyGiaoThong.BanDoPhuongTien;

public class Vehicle {
    private String vehicleType;
    private String licensePlate;
    private String status;
    private String recordedTime;

    public Vehicle(String vehicleType, String licensePlate, String status, String recordedTime) {
        this.vehicleType = vehicleType;
        this.licensePlate = licensePlate;
        this.status = status;
        this.recordedTime = recordedTime;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getStatus() {
        return status;
    }

    public String getRecordedTime() {
        return recordedTime;
    }
    private int runningVehicles;
    private int stoppedVehicles;
    private int disconnectedVehicles;

    // Constructor, getters và setters...

    public int getTotalVehicleCount() {
        return runningVehicles + stoppedVehicles + disconnectedVehicles;
    }

    public int getRunningVehicles() {
        return runningVehicles;
    }

    public int getStoppedVehicles() {
        return stoppedVehicles;
    }

    public int getDisconnectedVehicles() {
        return disconnectedVehicles;
    }
}
