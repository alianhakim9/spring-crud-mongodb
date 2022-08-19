package id.alianhakim.springcrudmongodb.service

import id.alianhakim.springcrudmongodb.dtos.EmployeeRequest
import id.alianhakim.springcrudmongodb.models.Employee
import id.alianhakim.springcrudmongodb.repository.EmployeeRepository
import id.alianhakim.springcrudmongodb.utils.NotFoundException
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class EmployeeService constructor(
    private val companyService: CompanyService,
    private val employeeRepository: EmployeeRepository
) {
    fun createEmployee(request: EmployeeRequest): Employee {
        val company = request.companyId?.let { companyService.findById(it) }
        return employeeRepository.save(
            Employee(
                firstName = request.firstName,
                lastName = request.lastName,
                email = request.email,
                company = company
            )
        )
    }

    fun findAll(): List<Employee> {
        return employeeRepository.findAll()
    }

    fun findAllByCompanyId(id: String): List<Employee> {
        return employeeRepository.findByCompanyId(id)
    }

    fun findById(id: ObjectId): Employee {
        return employeeRepository.findById(id)
            .orElseThrow { NotFoundException("Employee with id : $id not found") }
    }

    fun updateEmployee(id: ObjectId, request: EmployeeRequest): Employee {
        val employeeToUpdate = findById(id)
        val foundCompany = request.companyId?.let { companyService.findById(it) }

        return employeeRepository.save(
            employeeToUpdate.apply {
                firstName = request.firstName
                lastName = request.lastName
                email = request.email
                company = foundCompany
            }
        )
    }

    fun deleteById(id: ObjectId) {
        val employeeToDelete = findById(id)
        employeeRepository.delete(employeeToDelete)
    }
}