public class DiscreteSignal {
    private int[] impulseResponse;

    // Constructor: Khởi tạo dãy xung đơn vị
    public DiscreteSignal(int size) {
        impulseResponse = new int[size];
        for (int i = 0; i < size; i++) {
            impulseResponse[i] = (i == 0) ? 1 : 0; // Dãy xung đơn vị
        }
    }

    // Phương thức lấy giá trị tại thời điểm n
    public int getValueAt(int n) {
        if (n >= 0 && n < impulseResponse.length) {
            return impulseResponse[n];
        }
        return 0; // Giá trị ngoài dãy
    }

    // Phương thức thay đổi giá trị tại thời điểm index
    public void setValueAt(int index, int value) {
        if (index >= 0 && index < impulseResponse.length) {
            impulseResponse[index] = value;
        }
    }

    // Phương thức in dãy xung đơn vị
    public void printImpulseResponse() {
        for (int value : impulseResponse) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    // Phương thức tính giá trị tín hiệu tại thời điểm n (ví dụ với điều kiện đơn giản)
    public int calculateSignalValue(int n) {
        if (n < 0 || n >= impulseResponse.length) {
            return 0;
        }
        return impulseResponse[n];
    }

    // Phương thức tương tác với một tín hiệu khác (ví dụ: cộng hai tín hiệu)
    public DiscreteSignal interactWithOtherSignal(DiscreteSignal otherSignal) {
        int length = Math.min(this.impulseResponse.length, otherSignal.impulseResponse.length);
        DiscreteSignal resultSignal = new DiscreteSignal(length);
        for (int i = 0; i < length; i++) {
            resultSignal.setValueAt(i, this.impulseResponse[i] + otherSignal.getValueAt(i));
        }
        return resultSignal;
    }

    // Ví dụ phương thức đệ quy (cộng giá trị tại thời điểm n với một giá trị cụ thể)
    public int recursiveAddition(int n, int value) {
        if (n < 0) {
            return 0;
        }
        return impulseResponse[n] + recursiveAddition(n - 1, value);
    }
}
