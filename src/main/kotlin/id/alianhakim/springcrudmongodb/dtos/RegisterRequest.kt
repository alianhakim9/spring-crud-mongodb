package id.alianhakim.springcrudmongodb.dtos

import id.alianhakim.springcrudmongodb.models.Employee

class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
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
