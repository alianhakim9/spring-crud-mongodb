package id.alianhakim.springcrudmongodb.seeders

import id.alianhakim.springcrudmongodb.service.CompanyService
import id.alianhakim.springcrudmongodb.service.EmployeeService
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class DatabaseSeeder constructor(
    private val employeeService: EmployeeService,
    private val companyService: CompanyService
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
//        for (i in 1..50) {
//            employeeService.createEmployee(
//                EmployeeRequest(
//                    firstName = "First $i",
//                    lastName = "Last $i",
//                    email = "person$i@mail.com",
//                    companyId = null
//                )
//            )
//            companyService.createCompany(
//                CompanyRequest(
//                    name = "Company $i",
//                    address = "Street $i, No ${i + 1}"
//                )
//            )
//        }
    }
}