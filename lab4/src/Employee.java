import java.io.Serializable;
public class Employee implements Serializable {
    // статичне поле, що забезпечує визначення належності
    // до цього класу десервалізованих з файлу об'єктів
    public static final long serialVersionUID = 1110111;
    // поля класу, що міститимуть дані про окремого працівника
    private int id;
    private String department;
    private String surnameAndInitials;
    private String position;
    private String qualification;
    private double hoursWorkedNumber;
    private double hourPay;
    // методи класу забезпечують доступ до
    // полів класу (запис і зчитування даних)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getSurnameAndInitials() {
        return surnameAndInitials;
    }
    public void setSurnameAndInitials(String surnameAndInitials) {
        this.surnameAndInitials = surnameAndInitials;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getQualification() {
        return qualification;
    }
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
    public double getHoursWorkedNumber() {
        return hoursWorkedNumber;
    }
    public void sepHoursWorkedNumber(int hours) {
        this.hoursWorkedNumber = hours;
    }
    public double getHourPay() {
        return hourPay;
    }
    public void setHourPay(int hourPay) {
        this.hourPay = hourPay;
    }
    // метод toString описує та забезпечує подання
    // інформації, що міститься в екземплярі класу
    // в текстовому вигляді, зокрема, при виводі на консоль
    @Override
    public String toString() {
        return id + ") " + department + ", " + surnameAndInitials + ", " + position + ", " + qualification +
                ", Кількість відпрацьваних годин: " + hoursWorkedNumber + ", оплата за годину: $" + hourPay;
    }
    // конструктори класу дозволяють створювати
    // екземпляри класу (об'єкти) для збергіння
    // даних про окремого працівника
    public Employee() {
    }
    public Employee(String department, String surnameAndInitials, String position, String qualification,
                    double hoursWorkedNumber, double hourPay) {
        this.id = 0;
        this.department = department;
        this.surnameAndInitials = surnameAndInitials;
        this.position = position;
        this.qualification = qualification;
        this.hoursWorkedNumber = hoursWorkedNumber;
        this.hourPay = hourPay;
    }
    public Employee(int id, String department, String surnameAndInitials, String position, String qualification,
                    double hoursWorkedNumber, double hourPay) {
        this.id = id;
        this.department = department;
        this.surnameAndInitials = surnameAndInitials;
        this.position = position;
        this.qualification = qualification;
        this.hoursWorkedNumber = hoursWorkedNumber;
        this.hourPay = hourPay;
    }
}