import java.io.Serializable

class Employee : Serializable {
    companion object {
        const val serialVersionUID: Long = 1110111
    }

    var id: Int = 0
    var department: String = ""
    var surnameAndInitials: String = ""
    var position: String = ""
    var qualification: String = ""
    var hoursWorkedNumber: Double = 0.0
    var hourPay: Double = 0.0

    constructor()

    constructor(
        department: String,
        surnameAndInitials: String,
        position: String,
        qualification: String,
        hoursWorkedNumber: Double,
        hourPay: Double
    ) {
        this.id = 0
        this.department = department
        this.surnameAndInitials = surnameAndInitials
        this.position = position
        this.qualification = qualification
        this.hoursWorkedNumber = hoursWorkedNumber
        this.hourPay = hourPay
    }

    constructor(
        id: Int,
        department: String,
        surnameAndInitials: String,
        position: String,
        qualification: String,
        hoursWorkedNumber: Double,
        hourPay: Double
    ) {
        this.id = id
        this.department = department
        this.surnameAndInitials = surnameAndInitials
        this.position = position
        this.qualification = qualification
        this.hoursWorkedNumber = hoursWorkedNumber
        this.hourPay = hourPay
    }

    override fun toString(): String {
        return "$id) $department, $surnameAndInitials, $position, $qualification, " +
                "Кількість відпрацьованих годин: $hoursWorkedNumber, оплата за годину: $$hourPay"
    }
}
