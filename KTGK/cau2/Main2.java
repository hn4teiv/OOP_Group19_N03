public class Main2 {
    public static void main(String[] args) {
        // Khởi tạo đối tượng Radar
        Radar radar = new Radar();
        
        // In toàn bộ mẫu tín hiệu
        System.out.println("Mẫu tín hiệu radar:");
        radar.printSignalPattern();
        
        // In giá trị tín hiệu tại thời điểm n = 4
        int n = 4;
        System.out.println("Giá trị tín hiệu tại n = " + n + " là: " + radar.getSignalAt(n));
    }
}
