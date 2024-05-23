import java.util.List;
import java.util.Scanner;
public class Program {
    // поле для класу для підключення потрібної
    // імплементації репозиторію
    private static Repository repository;
    // головний метод (програма)
    public static void main(String[] args) {
        repository = new FileRepository("employees.dat");
        // оголошуємо колекцію даних інформаційної системи
        // та заповнююємо її списком з репозиторію,
        // список може бути порожнім, якщо файл репозиторію
        // щойно створено
        List<Employee> employees = repository.getAll();
        // сканер для читання даних з консолі
        Scanner input = new Scanner(System.in);
        // меню програми та діалог з користувачем
        int choice;
        while (true) {
            System.out.println("Оберіть потрібну дію:");
            System.out.println("1 - переглянути всі записи");
            System.out.println("2 - додати працівника");
            System.out.println("3 - сформувати відомості для оплати праці");
            System.out.println("4 - шукати працівників за посадою (позицією)");
            System.out.println("5 - шукати працівників за кваліфікацією");
            System.out.println("6 - вилучити дані про працівника");
            System.out.println("0 - завершити роботу програми");
            choice = Integer.parseInt(input.nextLine());
            if(choice == 0) break;
            switch(choice){
                case 1:
                    // оновлюємо список працівників
                    employees = repository.getAll();
                    // виводимо дані про працівників на консоль
                    for (Employee c: employees){
                        System.out.println(c);
                    }
                    break;
                case 2:
                    Employee employee = inputNewEmployee();
                    repository.addEmployee(employee);
                    break;
                case 3:
                    // оновлюємо список працівників
                    employees = repository.getAll();
                    // виводимо дані для оплати праці на консоль
                    for (Employee empl: employees){
                        System.out.println(empl.getId() + "Працівник " + empl.getSurnameAndInitials() + " заробив: $" +
                                empl.getHoursWorkedNumber() * empl.getHourPay());
                    }
                    break;
                case 4:
                    System.out.println("Задайте посаду (позицію):");
                    String employeePosition = input.nextLine();
                    employees = repository.getByPosition(employeePosition);
                    employees.stream().forEach(System.out::println);
                    break;
                case 5:
                    System.out.println("Задайте кваліфікацію:");
                    String employeeQualification = input.nextLine();
                    employees = repository.getByQualification(employeeQualification);
                    employees.stream().forEach(System.out::println);
                    break;
                case 6:
                    System.out.println("Вкажіть id працівнкиа, дані про якого потрібно вилучити:");
                    int id = Integer.parseInt(input.nextLine());
                    Employee found = repository.getById(id);
                    if(found == null){
                        System.out.println("Працівника з таким id немає");
                    }
                    else{
                        if(repository.deleteEmployee(id)){
                            System.out.println("Дані успішно вилучено");
                        }
                    }
                    break;
            }
        }
    }
    // Допоміжний метод, для вводу з консолі
    // даних про працівника
    private static Employee inputNewEmployee() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть дані про працівника.");
        System.out.println("Відділ:");
        String department = scanner.nextLine();
        System.out.println("Прізвище та ініціали:");
        String surnameAndInitials = scanner.nextLine();
        System.out.println("Посада (позиція):");
        String position = scanner.nextLine();
        System.out.println("Кваліфікація:");
        String qualification = scanner.nextLine();
        System.out.println("Кількість відпрацьованих годин:");
        double hoursWorkedNumber = scanner.nextDouble();
        System.out.println("Оплата за годину:");
        double hourPay = scanner.nextDouble();
        return new Employee(department, surnameAndInitials, position, qualification, hoursWorkedNumber, hourPay);
    }
}