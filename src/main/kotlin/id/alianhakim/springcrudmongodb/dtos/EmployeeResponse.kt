package id.alianhakim.springcrudmongodb.dtos

import id.alianhakim.springcrudmongodb.models.Employee

class EmployeeResponse constructor(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val company: CompanyResponse?
) {
    companion object {
        fun fromEntity(employee: Employee): EmployeeResponse {
            return EmployeeResponse(
                id = employee.id!!.toHexString(),
                firstName = employee.firstName,
                lastName = employee.lastName,
                email = employee.email,
                company = employee.company?.let { CompanyResponse.fromEntity(it) }
            )
        }
    }
}