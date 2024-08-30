// Tạo một lớp có phương thức protected và phương thức mặc định (package-private).
class BaseClass {
    // Phương thức protected
    protected void protectedMethod() {
        System.out.println("Protected Method in BaseClass");
    }

    // Phương thức mặc định (package-private)
    void packagePrivateMethod() {
        System.out.println("Package-Private Method in BaseClass");
    }
}

// Lớp con mở rộng từ BaseClass trong cùng một gói.
class DerivedClass extends BaseClass {
    public void accessMethods() {
        // Truy cập phương thức protected trong lớp con
        protectedMethod(); // Sẽ hoạt động

        // Truy cập phương thức package-private trong lớp con
        packagePrivateMethod(); // Sẽ hoạt động
    }
}

// Lớp chính chứa phương thức main để kiểm tra truy cập
public class Main {
    public static void main(String[] args) {
        BaseClass base = new BaseClass();
        DerivedClass derived = new DerivedClass();

        // Truy cập phương thức protected từ đối tượng của lớp con
        // Không thể truy cập phương thức protected thông qua đối tượng BaseClass
        // base.protectedMethod(); // Điều này sẽ gây lỗi biên dịch nếu được mở lại

        // Truy cập phương thức package-private
        // Không thể truy cập phương thức package-private từ đối tượng ngoài cùng một gói
        // base.packagePrivateMethod(); // Điều này sẽ gây lỗi biên dịch nếu được mở lại

        // Truy cập phương thức protected thông qua lớp con
        derived.accessMethods(); // Sẽ in ra các thông báo từ cả hai phương thức
    }
}
