package baitap;

public class CDList {
    private CD[] ds;
    private int soLuong;

    // Hệ số mở rộng khi danh sách gần đầy
    private static final double NGUONG_MO_RONG = 0.8;

    // Constructor
    public CDList(int n) {
        if (n < 1) {
            n = 1;
        }

        ds = new CD[n];
        soLuong = 0;
    }

    // =========================
    // 1. TỰ ĐỘNG MỞ RỘNG
    // =========================

    private void kiemTraMoRong() {
        // Khi số lượng đạt từ 80% sức chứa trở lên thì tăng kích thước
        if (soLuong >= ds.length * NGUONG_MO_RONG) {
            int kichThuocMoi = ds.length * 2;

            if (kichThuocMoi == 0) {
                kichThuocMoi = 1;
            }

            CD[] temp = new CD[kichThuocMoi];

            for (int i = 0; i < soLuong; i++) {
                temp[i] = ds[i];
            }

            ds = temp;

            System.out.println(
                "Danh sach da tu dong mo rong len " + ds.length + " phan tu."
            );
        }
    }

    // =========================
    // 2. THÊM CD
    // =========================

    public boolean themCD(CD cd) {
        if (cd == null) {
            return false;
        }

        // Kiểm tra mã CD trùng
        if (timTheoMa(cd.getMaCD()) != null) {
            return false;
        }

        // Kiểm tra và mở rộng trước khi thêm
        kiemTraMoRong();

        ds[soLuong] = cd;
        soLuong++;

        return true;
    }

    // =========================
    // 3. TÌM THEO MÃ
    // =========================

    public CD timTheoMa(int maCD) {
        for (int i = 0; i < soLuong; i++) {
            if (ds[i].getMaCD() == maCD) {
                return ds[i];
            }
        }

        return null;
    }

    // =========================
    // 4. TÌM KIẾM TIỀN TỐ
    // =========================

    public void timTheoTienTo(String tienTo) {
        if (tienTo == null) {
            return;
        }

        tienTo = tienTo.toLowerCase().trim();

        boolean timThay = false;

        for (int i = 0; i < soLuong; i++) {
            String tua = ds[i].getTuaCD().toLowerCase();

            if (tua.startsWith(tienTo)) {
                System.out.println(ds[i]);
                timThay = true;
            }
        }

        if (!timThay) {
            System.out.println("Khong tim thay CD phu hop.");
        }
    }

    // =========================
    // 5. TÌM KIẾM HẬU TỐ
    // =========================

    public void timTheoHauTo(String hauTo) {
        if (hauTo == null) {
            return;
        }

        hauTo = hauTo.toLowerCase().trim();

        boolean timThay = false;

        for (int i = 0; i < soLuong; i++) {
            String tua = ds[i].getTuaCD().toLowerCase();

            if (tua.endsWith(hauTo)) {
                System.out.println(ds[i]);
                timThay = true;
            }
        }

        if (!timThay) {
            System.out.println("Khong tim thay CD phu hop.");
        }
    }

    // =========================
    // 6. TÌM KIẾM GẦN GIỐNG
    // =========================
    
    public void timGanGiong(String tuKhoa) {
        if (tuKhoa == null) {
            return;
        }

        tuKhoa = tuKhoa.toLowerCase().trim();

        boolean timThay = false;

        for (int i = 0; i < soLuong; i++) {
            String tua = ds[i].getTuaCD().toLowerCase();

            // Gần giống:
            // - Chứa từ khóa
            // - Hoặc khoảng cách Levenshtein <= 2
            if (tua.contains(tuKhoa)
                    || khoangCachLevenshtein(tua, tuKhoa) <= 2) {

                System.out.println(ds[i]);
                timThay = true;
            }
        }

        if (!timThay) {
            System.out.println("Khong tim thay CD gan giong.");
        }
    }

    // Tính khoảng cách Levenshtein
    private int khoangCachLevenshtein(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= b.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {

                int chiPhi;

                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    chiPhi = 0;
                } else {
                    chiPhi = 1;
                }

                dp[i][j] = Math.min(
                    Math.min(
                        dp[i - 1][j] + 1,
                        dp[i][j - 1] + 1
                    ),
                    dp[i - 1][j - 1] + chiPhi
                );
            }
        }

        return dp[a.length()][b.length()];
    }

    // =========================
    // 7. XUẤT DANH SÁCH
    // =========================

    public void xuatDanhSach() {
        if (soLuong == 0) {
            System.out.println("Danh sach rong.");
            return;
        }

        for (int i = 0; i < soLuong; i++) {
            System.out.println(ds[i]);
        }
    }

    // =========================
    // 8. SỐ LƯỢNG CD
    // =========================

    public int getSoLuong() {
        return soLuong;
    }

    // =========================
    // 9. TỔNG GIÁ THÀNH
    // =========================

    public double tinhTongGiaThanh() {
        double tong = 0;

        for (int i = 0; i < soLuong; i++) {
            tong += ds[i].getGiaThanh();
        }

        return tong;
    }

    // =========================
    // 10. GIÁ TRỊ TRUNG BÌNH
    // =========================

    public double tinhGiaTrungBinh() {
        if (soLuong == 0) {
            return 0;
        }

        return tinhTongGiaThanh() / soLuong;
    }

    // =========================
    // 11. CD CÓ GIÁ CAO NHẤT
    // =========================

    public CD timCDGiaCaoNhat() {
        if (soLuong == 0) {
            return null;
        }

        CD max = ds[0];

        for (int i = 1; i < soLuong; i++) {
            if (ds[i].getGiaThanh() > max.getGiaThanh()) {
                max = ds[i];
            }
        }

        return max;
    }

    // =========================
    // 12. CD CÓ GIÁ THẤP NHẤT
    // =========================

    public CD timCDGiaThapNhat() {
        if (soLuong == 0) {
            return null;
        }

        CD min = ds[0];

        for (int i = 1; i < soLuong; i++) {
            if (ds[i].getGiaThanh() < min.getGiaThanh()) {
                min = ds[i];
            }
        }

        return min;
    }

    // =========================
    // 13. TỔNG SỐ BÀI HÁT
    // =========================

    public int tinhTongSoBaiHat() {
        int tong = 0;

        for (int i = 0; i < soLuong; i++) {
            tong += ds[i].getSoBaiHat();
        }

        return tong;
    }

    // =========================
    // 14. THỐNG KÊ
    // =========================

    public void thongKe() {
        if (soLuong == 0) {
            System.out.println("Danh sach rong.");
            return;
        }

        System.out.println("\n===== THONG KE =====");

        System.out.println("So luong CD: " + soLuong);

        System.out.println(
            "Tong so bai hat: " + tinhTongSoBaiHat()
        );

        System.out.println(
            "Tong gia thanh: " + tinhTongGiaThanh()
        );

        System.out.println(
            "Gia thanh trung binh: " + tinhGiaTrungBinh()
        );

        System.out.println("\nCD co gia cao nhat:");
        System.out.println(timCDGiaCaoNhat());

        System.out.println("\nCD co gia thap nhat:");
        System.out.println(timCDGiaThapNhat());
    }

    // =========================
    // 15. XÓA CD THEO MÃ
    // =========================

    public boolean xoaCD(int maCD) {
        int viTri = -1;

        for (int i = 0; i < soLuong; i++) {
            if (ds[i].getMaCD() == maCD) {
                viTri = i;
                break;
            }
        }

        if (viTri == -1) {
            return false;
        }

        // Dồn phần tử phía sau lên
        for (int i = viTri; i < soLuong - 1; i++) {
            ds[i] = ds[i + 1];
        }

        ds[soLuong - 1] = null;
        soLuong--;

        return true;
    }

    // =========================
    // 16. SỬA CD
    // =========================

    public boolean suaCD(
            int maCD,
            String tuaMoi,
            int soBaiHatMoi,
            double giaThanhMoi) {

        CD cd = timTheoMa(maCD);

        if (cd == null) {
            return false;
        }

        cd.setTuaCD(tuaMoi);
        cd.setSoBaiHat(soBaiHatMoi);
        cd.setGiaThanh(giaThanhMoi);

        return true;
    }

    // =========================
    // 17. SẮP XẾP GIẢM THEO GIÁ
    // =========================

    public void sapXepGiamTheoGia() {
        for (int i = 0; i < soLuong - 1; i++) {
            for (int j = i + 1; j < soLuong; j++) {

                if (ds[i].getGiaThanh() < ds[j].getGiaThanh()) {

                    CD temp = ds[i];
                    ds[i] = ds[j];
                    ds[j] = temp;
                }
            }
        }
    }

    // =========================
    // 18. SẮP XẾP TĂNG THEO TỰA
    // =========================

    public void sapXepTangTheoTua() {
        for (int i = 0; i < soLuong - 1; i++) {
            for (int j = i + 1; j < soLuong; j++) {

                if (ds[i].getTuaCD()
                        .compareToIgnoreCase(ds[j].getTuaCD()) > 0) {

                    CD temp = ds[i];
                    ds[i] = ds[j];
                    ds[j] = temp;
                }
            }
        }
    }
}
