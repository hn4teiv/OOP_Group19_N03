public class ContinuousSignal implements Signal {
    private double amplitude;
    private double frequency;

    // Constructor
    public ContinuousSignal(double amplitude, double frequency) {
        this.amplitude = amplitude;
        this.frequency = frequency;
    }


    public double getAmplitude() {
        return amplitude;
    }


    public double getFrequency() {
        return frequency;
    }


    public double getPeriod() {
        // Tính chu kỳ dựa trên tần số
        if (frequency != 0) {
            return 1.0 / frequency;
        }
        return Double.POSITIVE_INFINITY; // Vô cực nếu tần số bằng 0
    }

    // Các phương thức đặc trưng cho tín hiệu liên tục
    public double getValueAt(double time) {
        return amplitude * Math.sin(2 * Math.PI * frequency * time);
    }
}
