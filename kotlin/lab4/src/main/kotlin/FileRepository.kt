import java.io.*
import java.util.ArrayList
import java.util.List
import java.util.Optional
import java.util.function.Predicate
import java.util.stream.Collectors

class FileRepository(private val fileName: String) : Repository {
    private var employees: MutableList<Employee> = ArrayList()

    init {
        if (File(fileName).exists()) {
            reloadData()
        }
    }

    override fun getAll(): kotlin.collections.List<Employee> {
        reloadData()
        return employees
    }

    private fun reloadData() {
        if (File(fileName).exists()) {
            try {
                ObjectInputStream(FileInputStream(fileName)).use { objectInputStream ->
                    employees = objectInputStream.readObject() as MutableList<Employee>
                }
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
    }

    override fun getById(id: Int): Employee? {
        reloadData()
        val foundEmployee: Optional<Employee> = employees.stream()
            .filter { employee -> employee.id == id }
            .findFirst()
        return foundEmployee.orElse(null)
    }

    override fun addEmployee(employee: Employee): Boolean {
        reloadData()
        val id = if (employees.isNotEmpty()) employees.maxByOrNull { it.id }?.id ?: 0 else 0
        employee.id = id + 1
        employees.add(employee)
        try {
            save()
        } catch (e: IOException) {
            return false
        }
        return true
    }

    private fun save() {
        try {
            ObjectOutputStream(FileOutputStream(fileName)).use { objectOutputStream ->
                objectOutputStream.writeObject(employees)
            }
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    override fun deleteEmployee(id: Int): Boolean {
        reloadData()
        val foundEmployee: Optional<Employee> = employees.stream().filter { it.id == id }.findFirst()
        if (foundEmployee.isPresent) {
            val employee = foundEmployee.get()
            employees.remove(employee)
            try {
                save()
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
            return true
        }
        return false
    }

    override fun getByPosition(position: String): kotlin.collections.List<Employee> {
        reloadData()
        return employees.stream()
            .filter { employee -> employee.position.equals(position, ignoreCase = true) }
            .collect(Collectors.toList())
    }

    override fun getByQualification(qualification: String): kotlin.collections.List<Employee> {
        reloadData()
        return employees.stream()
            .filter { employee -> employee.qualification.equals(qualification, ignoreCase = true) }
            .collect(Collectors.toList())
    }
}
