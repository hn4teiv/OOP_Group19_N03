public class DiscreteSignal implements Signal {
    private double[] samples; // Mảng chứa các mẫu của tín hiệu rời rạc
    private double samplingInterval; // Khoảng thời gian lấy mẫu giữa các điểm

    // Constructor
    public DiscreteSignal(double[] samples, double samplingInterval) {
        this.samples = samples;
        this.samplingInterval = samplingInterval;
    }


    public double getAmplitude() {
        double maxAmplitude = Double.NEGATIVE_INFINITY;
        for (double sample : samples) {
            if (sample > maxAmplitude) {
                maxAmplitude = sample;
            }
        }
        return maxAmplitude;
    }


    public double getFrequency() {
        // Tính tần số dựa trên số mẫu và khoảng thời gian lấy mẫu
        // Giả sử tín hiệu là một chuỗi mẫu liên tục, và khoảng thời gian lấy mẫu là đồng đều
        return 1.0 / (samples.length * samplingInterval);
    }


    public double getPeriod() {
        return 1.0 / getFrequency(); // Tính chu kỳ dựa trên tần số
    }

    // Các phương thức đặc trưng cho tín hiệu rời rạc
    public double getSample(int index) {
        if (index >= 0 && index < samples.length) {
            return samples[index];
        }
        throw new IndexOutOfBoundsException("Invalid sample index");
    }
}
