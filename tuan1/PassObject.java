public class PassObject { static void f(Number m) {
m.i = 15; }
public static void main(String[] args) { Number n = new Number();
n.i = 14;
f(n); // what is n.i now?
System.out.println("n.i = " + n.i); // Output: n.i = 15
} }
/*
Assignment1: Đoạn mã này chỉ làm việc với các đối tượng khác nhau. Thay đổi giá trị của một đối tượng không ảnh hưởng đến đối tượng khác.
Assignment2: Đoạn mã này sử dụng việc gán đối tượng, dẫn đến hai biến trỏ đến cùng một đối tượng. Thay đổi giá trị của đối tượng thông qua một biến sẽ ảnh hưởng đến giá trị của đối tượng khi truy cập qua biến khác.
PassObject: Đoạn mã này minh họa việc truyền đối tượng vào phương thức. Thay đổi thuộc tính của đối tượng trong phương thức sẽ ảnh hưởng đến đối tượng ngoài phương thức vì chúng đều trỏ đến cùng một đối tượng. */