package id.alianhakim.springcrudmongodb.controller

import id.alianhakim.springcrudmongodb.dtos.EmployeeRequest
import id.alianhakim.springcrudmongodb.dtos.EmployeeResponse
import id.alianhakim.springcrudmongodb.service.EmployeeService
import org.bson.types.ObjectId
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/employee")
class EmployeeController constructor(
    private val employeeService: EmployeeService
) {

    @PostMapping
    fun createEmployee(
        @RequestBody request: EmployeeRequest
    ): ResponseEntity<EmployeeResponse> {
        val createdEmployee = employeeService.createEmployee(request)
        return ResponseEntity.ok(EmployeeResponse.fromEntity(createdEmployee))
    }

    @GetMapping
    fun findAllEmployees(): ResponseEntity<List<EmployeeResponse>> {
        val employees = employeeService.findAll()
        return ResponseEntity.ok(employees.map {
            EmployeeResponse.fromEntity(it)
        })
    }

    @GetMapping("/{id}")
    fun findEmployeeById(
        @PathVariable id: ObjectId
    ): ResponseEntity<EmployeeResponse> {
        val employee = employeeService.findById(id)
        return ResponseEntity.ok(EmployeeResponse.fromEntity(employee))
    }

    @GetMapping("/company/{companyId}")
    fun findAllByCompanyId(
        @PathVariable companyId: String
    ): ResponseEntity<List<EmployeeResponse>> {
        val employees = employeeService.findAllByCompanyId(companyId)
        return ResponseEntity.ok(
            employees.map {
                EmployeeResponse.fromEntity(it)
            }
        )
    }

    @PutMapping("/{id}")
    fun updateEmployee(
        @PathVariable id: ObjectId,
        @RequestBody request: EmployeeRequest
    ): ResponseEntity<EmployeeResponse> {
        val updatedEmployee = employeeService.updateEmployee(id, request)
        return ResponseEntity.ok(
            EmployeeResponse.fromEntity(updatedEmployee)
        )
    }

    @DeleteMapping("/{id}")
    fun deleteEmployee(
        @PathVariable id: ObjectId
    ): ResponseEntity<Any> {
        employeeService.deleteById(id)
        return ResponseEntity.noContent().build()
    }

}