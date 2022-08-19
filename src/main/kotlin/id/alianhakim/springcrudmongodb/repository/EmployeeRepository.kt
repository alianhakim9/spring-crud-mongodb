package id.alianhakim.springcrudmongodb.repository

import id.alianhakim.springcrudmongodb.models.Employee
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface EmployeeRepository : MongoRepository<Employee, ObjectId> {
    fun findByCompanyId(id: String): List<Employee>
}