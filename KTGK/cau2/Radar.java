public class Radar {
    private double[] signalPattern;

    // Constructor: Khởi tạo mẫu tín hiệu
    public Radar() {
        signalPattern = new double[16]; // Chúng ta cần 16 phần tử cho n từ 0 đến 15
        for (int n = 0; n <= 15; n++) {
            signalPattern[n] = 1 - (n / 15.0);
        }
    }

    // Phương thức lấy giá trị tín hiệu tại thời điểm n
    public double getSignalAt(int n) {
        if (n >= 0 && n < signalPattern.length) {
            return signalPattern[n];
        }
        return 0; // Giá trị ngoài dãy
    }

    // Phương thức in mẫu tín hiệu
    public void printSignalPattern() {
        for (int i = 0; i < signalPattern.length; i++) {
            System.out.print("f(" + i + ") = " + signalPattern[i] + " ");
        }
        System.out.println();
    }
}

