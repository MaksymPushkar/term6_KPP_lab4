import java.util.Scanner

fun main() {
    val repository: Repository = FileRepository("employees.dat")
    var employees = repository.getAll()
    val input = Scanner(System.`in`)
    var choice: Int
    while (true) {
        println("Оберіть потрібну дію:")
        println("1 - переглянути всі записи")
        println("2 - додати працівника")
        println("3 - сформувати відомості для оплати праці")
        println("4 - шукати працівників за посадою (позицією)")
        println("5 - шукати працівників за кваліфікацією")
        println("6 - вилучити дані про працівника")
        println("0 - завершити роботу програми")
        choice = input.nextLine().toInt()
        if (choice == 0) break
        when (choice) {
            1 -> {
                employees = repository.getAll()
                employees.forEach(::println)
            }
            2 -> {
                val employee = inputNewEmployee(input)
                repository.addEmployee(employee)
            }
            3 -> {
                employees = repository.getAll()
                employees.forEach { empl ->
                    println("${empl.id} Працівник ${empl.surnameAndInitials} заробив: $" +
                            "${empl.hoursWorkedNumber * empl.hourPay}")
                }
            }
            4 -> {
                println("Задайте посаду (позицію):")
                val employeePosition = input.nextLine()
                employees = repository.getByPosition(employeePosition)
                employees.forEach(::println)
            }
            5 -> {
                println("Задайте кваліфікацію:")
                val employeeQualification = input.nextLine()
                employees = repository.getByQualification(employeeQualification)
                employees.forEach(::println)
            }
            6 -> {
                println("Вкажіть id працівника, дані про якого потрібно вилучити:")
                val id = input.nextLine().toInt()
                val found = repository.getById(id)
                if (found == null) {
                    println("Працівника з таким id немає")
                } else {
                    if (repository.deleteEmployee(id)) {
                        println("Дані успішно вилучено")
                    }
                }
            }
        }
    }
}

private fun inputNewEmployee(input: Scanner): Employee {
    println("Введіть дані про працівника.")
    println("Відділ:")
    val department = input.nextLine()
    println("Прізвище та ініціали:")
    val surnameAndInitials = input.nextLine()
    println("Посада (позиція):")
    val position = input.nextLine()
    println("Кваліфікація:")
    val qualification = input.nextLine()
    println("Кількість відпрацьованих годин:")
    val hoursWorkedNumber = input.nextLine().toDouble()
    println("Оплата за годину:")
    val hourPay = input.nextLine().toDouble()
    return Employee(department, surnameAndInitials, position, qualification, hoursWorkedNumber, hourPay)
}
