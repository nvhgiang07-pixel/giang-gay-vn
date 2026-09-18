package baitap;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap kich thuoc ban dau: ");
        int n = sc.nextInt();

        CDList ds = new CDList(n);

        int chon;

        do {
            System.out.println("\n========== MENU ==========");
            System.out.println("1.  Them CD");
            System.out.println("2.  Xuat danh sach");
            System.out.println("3.  So luong CD");
            System.out.println("4.  Tong gia thanh");
            System.out.println("5.  Sap xep giam theo gia");
            System.out.println("6.  Sap xep tang theo tua");
            System.out.println("7.  Tim kiem theo tien to");
            System.out.println("8.  Tim kiem theo hau to");
            System.out.println("9.  Tim kiem gan giong");
            System.out.println("10. Thong ke");
            System.out.println("11. Xoa CD");
            System.out.println("12. Sua CD");
            System.out.println("0.  Thoat");
            System.out.println("==========================");
            System.out.print("Nhap chon: ");

            chon = sc.nextInt();

            switch (chon) {


                case 1:

                    System.out.print("Nhap ma CD: ");
                    int maCD = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Nhap tua CD: ");
                    String tuaCD = sc.nextLine();

                    System.out.print("Nhap so bai hat: ");
                    int soBaiHat = sc.nextInt();

                    System.out.print("Nhap gia thanh: ");
                    double giaThanh = sc.nextDouble();

                    CD cd = new CD(
                        maCD,
                        tuaCD,
                        soBaiHat,
                        giaThanh
                    );

                    if (ds.themCD(cd)) {
                        System.out.println("Them CD thanh cong!");
                    } else {
                        System.out.println(
                            "Them that bai! Ma CD da ton tai."
                        );
                    }

                    break;


                case 2:

                    System.out.println("\n===== DANH SACH CD =====");
                    ds.xuatDanhSach();

                    break;

                

                case 3:

                    System.out.println(
                        "So luong CD: " + ds.getSoLuong()
                    );

                    break;


                case 4:

                    System.out.println(
                        "Tong gia thanh: "
                        + ds.tinhTongGiaThanh()
                    );

                    break;


                case 5:

                    ds.sapXepGiamTheoGia();

                    System.out.println(
                        "\n===== SAP XEP GIAM THEO GIA ====="
                    );

                    ds.xuatDanhSach();

                    break;

                case 6:

                    ds.sapXepTangTheoTua();

                    System.out.println(
                        "\n===== SAP XEP TANG THEO TUA ====="
                    );

                    ds.xuatDanhSach();

                    break;


                case 7:

                    sc.nextLine();

                    System.out.print(
                        "Nhap tien to can tim: "
                    );

                    String tienTo = sc.nextLine();

                    System.out.println(
                        "\n===== KET QUA ====="
                    );

                    ds.timTheoTienTo(tienTo);

                    break;

                
                case 8:

                    sc.nextLine();

                    System.out.print(
                        "Nhap hau to can tim: "
                    );

                    String hauTo = sc.nextLine();

                    System.out.println(
                        "\n===== KET QUA ====="
                    );

                    ds.timTheoHauTo(hauTo);

                    break;

                case 9:

                    sc.nextLine();

                    System.out.print(
                        "Nhap tu khoa gan giong: "
                    );

                    String tuKhoa = sc.nextLine();

                    System.out.println(
                        "\n===== KET QUA ====="
                    );

                    ds.timGanGiong(tuKhoa);

                    break;

                case 10:

                    ds.thongKe();

                    break;

                case 11:

                    System.out.print(
                        "Nhap ma CD can xoa: "
                    );

                    int maXoa = sc.nextInt();

                    if (ds.xoaCD(maXoa)) {
                        System.out.println(
                            "Xoa CD thanh cong!"
                        );
                    } else {
                        System.out.println(
                            "Khong tim thay CD co ma "
                            + maXoa
                        );
                    }

                    break;

     

                case 12:

                    System.out.print(
                        "Nhap ma CD can sua: "
                    );

                    int maSua = sc.nextInt();

                    sc.nextLine();

                 
                    CD cdSua = ds.timTheoMa(maSua);

                    if (cdSua == null) {

                        System.out.println(
                            "Khong tim thay CD!"
                        );

                    } else {

                        System.out.print(
                            "Nhap tua CD moi: "
                        );
                        String tuaMoi = sc.nextLine();

                        System.out.print(
                            "Nhap so bai hat moi: "
                        );
                        int soBaiHatMoi = sc.nextInt();

                        System.out.print(
                            "Nhap gia thanh moi: "
                        );
                        double giaThanhMoi = sc.nextDouble();

                        if (ds.suaCD(
                                maSua,
                                tuaMoi,
                                soBaiHatMoi,
                                giaThanhMoi)) {

                            System.out.println(
                                "Sua CD thanh cong!"
                            );

                        } else {

                            System.out.println(
                                "Sua CD that bai!"
                            );
                        }
                    }

                    break;


                case 0:

                    System.out.println(
                        "Ket thuc chuong trinh."
                    );

                    break;

                default:

                    System.out.println(
                        "Lua chon khong hop le!"
                    );
            }

        } while (chon != 0);

        sc.close();
    }
}
