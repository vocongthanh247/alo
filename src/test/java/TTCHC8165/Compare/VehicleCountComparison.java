package TTCHC8165.Compare;

import TTCHC8165.ChiHuyGiaoThong.BanDoPhuongTien.VehicleStatisticsTest;
import TTCHC8165.Dashboard.SoLuongPhuongTien;
import org.testng.annotations.Test;

public class VehicleCountComparison {
    @Test
    public void VehicleCountComparison() {
        VehicleStatisticsTest vehicleStatisticsTest = new VehicleStatisticsTest();
        SoLuongPhuongTien soLuongPhuongTien = new SoLuongPhuongTien();

        // Khởi động trước khi gọi phương thức
        vehicleStatisticsTest.setUp();
        soLuongPhuongTien.setUp();

        // Lấy số lượng phương tiện từ cả hai class
        int vehicleCountFromStats = vehicleStatisticsTest.getVehicleCounts().getRunningVehicles() +
                vehicleStatisticsTest.getVehicleCounts().getStoppedVehicles() +
                vehicleStatisticsTest.getVehicleCounts().getDisconnectedVehicles();

        int vehicleCountFromDashboard = soLuongPhuongTien.getTotalVehicleCount();

        // So sánh số lượng
        System.out.println("Số lượng phương tiện từ VehicleStatisticsTest: " + vehicleCountFromStats);
        System.out.println("Số lượng phương tiện từ SoLuongPhuongTien: " + vehicleCountFromDashboard);

        if (vehicleCountFromStats == vehicleCountFromDashboard) {
            System.out.println("Số lượng phương tiện từ cả hai nguồn là bằng nhau: " + vehicleCountFromStats);
        } else {
            System.out.println("Số lượng phương tiện không bằng nhau.");
        }

        // Dọn dẹp
        vehicleStatisticsTest.tearDown();
        soLuongPhuongTien.tearDown();
    }
}
