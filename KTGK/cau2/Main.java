public class Main {
    public static void main(String[] args) {
        // Khởi tạo đối tượng DiscreteSignal với kích thước 10
        DiscreteSignal signal1 = new DiscreteSignal(10);
        
        // In dãy xung đơn vị ban đầu
        System.out.println("Dãy xung đơn vị ban đầu:");
        signal1.printImpulseResponse();
        
        // Thay đổi một số giá trị trong dãy xung đơn vị
        signal1.setValueAt(3, 5); // Thay đổi giá trị tại thời điểm 3 thành 5
        signal1.setValueAt(7, 2); // Thay đổi giá trị tại thời điểm 7 thành 2
        
        // In dãy xung đơn vị sau khi thay đổi
        System.out.println("Dãy xung đơn vị sau khi thay đổi:");
        signal1.printImpulseResponse();
        
        // In giá trị tại một số thời điểm cụ thể
        System.out.println("Giá trị tại thời điểm 3: " + signal1.getValueAt(3));
        System.out.println("Giá trị tại thời điểm 7: " + signal1.getValueAt(7));
        
        // Tính toán giá trị tín hiệu bằng phương thức calculateSignalValue
        System.out.println("Tính giá trị tín hiệu tại thời điểm 5: " + signal1.calculateSignalValue(5));
        
        // Khởi tạo một tín hiệu khác để tương tác
        DiscreteSignal signal2 = new DiscreteSignal(10);
        signal2.setValueAt(1, 3); // Thay đổi giá trị tại thời điểm 1 thành 3
        signal2.setValueAt(4, 7); // Thay đổi giá trị tại thời điểm 4 thành 7
        
        // Tương tác với tín hiệu khác
        DiscreteSignal resultSignal = signal1.interactWithOtherSignal(signal2);
        System.out.println("Kết quả tương tác với tín hiệu khác:");
        resultSignal.printImpulseResponse();
        
        // Tính tổng giá trị đệ quy
        System.out.println("Tổng giá trị từ 0 đến 3 với giá trị cộng thêm 2: " + signal1.recursiveAddition(3, 2));

     
        
    }
}
