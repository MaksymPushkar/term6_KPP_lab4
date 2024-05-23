interface Repository {
    fun getAll(): List<Employee>
    fun getById(id: Int): Employee?
    fun addEmployee(employee: Employee): Boolean
    fun deleteEmployee(id: Int): Boolean
    fun getByPosition(position: String): List<Employee>
    fun getByQualification(qualification: String): List<Employee>
}
