package TTCHC8165.ChiHuyGiaoThong.QLCamera;

public class GetCamera {
    private String statusCamera; // Camera bị ngắt kết nối


    // Hàm khởi tạo
    public GetCamera(String statusCamera) {
        this.statusCamera = statusCamera;
    }

    // Phương thức getter cho disconnectCamera
    public String getStatusCamera() {
        return statusCamera;
    }
}