import java.util.List;
// Інтерфейс описує набір методів, якими
// повинен володіти клас, щоб програма могла
// використовувати його в якості репозиторію
public interface Repository {
    // усі методи за замовчуванням вважаються відкритими
    // та абстрактними (не містять реалізації)
    List<Employee> getAll();
    Employee getById(int id);
    boolean addEmployee(Employee employee);
    boolean deleteEmployee(int id);
    List<Employee> getByPosition(String position);
    List<Employee> getByQualification(String qualification);
}