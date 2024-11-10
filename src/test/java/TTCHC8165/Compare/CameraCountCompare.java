package TTCHC8165.Compare;

import TTCHC8165.ChiHuyGiaoThong.QLCamera.GetCamera;
import TTCHC8165.ChiHuyGiaoThong.QLCamera.GetCameraTest;
import TTCHC8165.Dashboard.Camera;
import org.testng.annotations.Test;

import java.util.List;

public class CameraCountCompare {
    @Test
    public void CameraCountCompare(){
        GetCameraTest getCameraTest = new GetCameraTest();
        Camera camera = new Camera();

        // Khởi động camera
        camera.setUp();
        getCameraTest.setup();

        try {
            // Lấy số lượng camera từ lớp Camera
            camera.getCameraDashboard(); // Gọi phương thức này để cập nhật số lượng camera
            int totalCamerasFromCamera = camera.getTotalCameraDashboardCount();

            // Lấy số lượng camera từ lớp GetCameraTest
            List<GetCamera> cameras = getCameraTest.getCamerasFromAllPages();
            int totalCamerasFromGetCameraTest = getCameraTest.getTotalCameraCount(cameras);

            // So sánh số lượng
            System.out.println("Số lượng camera từ GetCameraTest: " + totalCamerasFromGetCameraTest);
            System.out.println("Số lượng camera từ Camera: " + totalCamerasFromCamera);

            if (totalCamerasFromGetCameraTest == totalCamerasFromCamera) {
                System.out.println("Số lượng camera từ cả hai nguồn là bằng nhau: " + totalCamerasFromGetCameraTest);
            } else {
                System.out.println("Số lượng camera không bằng nhau.");
            }
        } catch (Exception e) {
            System.err.println("Có lỗi xảy ra: " + e.getMessage());
        } finally {
            // Dọn dẹp
            getCameraTest.tearDown();
            camera.tearDown();
        }
    }
}