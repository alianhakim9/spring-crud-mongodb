package id.alianhakim.springcrudmongodb.dtos

data class EmployeeRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val companyId: String?
)
