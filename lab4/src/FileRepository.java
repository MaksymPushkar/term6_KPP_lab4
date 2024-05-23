import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FileRepository implements Repository {
    private String fileName;
    private List<Employee> employees;
    // констуктор класу, що дозволяє створити екземпляр
    // репозиторію асоційований з заданою назвою файла
    public FileRepository(String fileName) {
        this.employees = new ArrayList<>();
        this.fileName = fileName;
        if ((new File(fileName)).exists()) {
            this.reloadData();
        }
    }
    // метод для отримання списку усіх працівників
    @Override
    public List<Employee> getAll() {
        reloadData();
        return employees;
    }
    // допоміжний метод виконує “синхронізацію” даних,
    // які викристовує програма з даними у файлі
    private void reloadData() {
        if ((new File(fileName)).exists()) {
            try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
                try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                    employees = (List<Employee>) objectInputStream.readObject();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public Employee getById(int id) {
        reloadData();
        Optional<Employee> foundEmployee = this.employees.stream()
                .filter(new Predicate<Employee>() {
                    @Override
                    public boolean test(Employee employee) {
                        return employee.getId()==id;
                    }
                })
                .findFirst();
        Employee employee = null;
        if(foundEmployee.isPresent()) {
            // якщо працівник з таким id знайдено,
            // то метод поверне відповідниий
            // об'єкт, якшо ні, то значення null
            employee = foundEmployee.get();
        }
        return employee;
    }
    // додавання даних про нового працівника
    @Override
    public boolean addEmployee(Employee employee) {
        int id = 0;
        reloadData();
        if(this.employees.size()>0) {
            OptionalInt maxId = this.employees.stream()
                    .mapToInt(c -> c.getId()).max();
            if (maxId != null) {
                id = maxId.getAsInt();
            }
        }
        employee.setId(id+1);
        employees.add(employee);
        try {
            save();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    // метод для запису списку працівників у файл допомагає
    // “синхронізувати” дані, які містить список з даними
    // у файлі, перезаписуючи його
    private void save() throws IOException {
        try(FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(this.employees);
            }
        }
    }
    @Override
    public boolean deleteEmployee(int id) {
        // оновлюємо список працівників
        reloadData();
        // шукаємо працівника за заданим id
        Optional<Employee> foundEmployee = employees.stream().filter(c->c.getId()==id).findFirst();
        // якщо працівник з таким id є в списку, видаляємо
        // його та зберігаємо змінений мписок у файлі
        if(foundEmployee.isPresent()) {
            Employee employee = foundEmployee.get();
            employees.remove(employee);
            try {
                save();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }
    @Override
    public List<Employee> getByPosition(String position) {
        reloadData();
        List<Employee> result = employees.stream()
                .filter(new Predicate<Employee>() {
                    @Override
                    public boolean test(Employee employee) {
                        return employee.getPosition().equalsIgnoreCase(position);
                    }
                })
                .collect(Collectors.toList());
        return result;
    }
    @Override
    public List<Employee> getByQualification(String qualification) {
        reloadData();
        List<Employee> result = employees.stream()
                .filter(new Predicate<Employee>() {
                    @Override
                    public boolean test(Employee employee) {
                        return employee.getQualification().equalsIgnoreCase(qualification);
                    }
                })
                .collect(Collectors.toList());
        return result;
    }
}