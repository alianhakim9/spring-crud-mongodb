package id.alianhakim.springcrudmongodb.service

import id.alianhakim.springcrudmongodb.dtos.CompanyRequest
import id.alianhakim.springcrudmongodb.models.Company
import id.alianhakim.springcrudmongodb.repository.CompanyRepository
import id.alianhakim.springcrudmongodb.repository.EmployeeRepository
import id.alianhakim.springcrudmongodb.utils.NotFoundException
import org.springframework.stereotype.Service

@Service
class CompanyService constructor(
    private val companyRepository: CompanyRepository,
    private val employeeRepository: EmployeeRepository
) {
    fun createCompany(request: CompanyRequest): Company {
        return companyRepository.save(
            Company(
                name = request.name,
                address = request.address
            )
        )
    }

    fun findAll(): List<Company> = companyRepository.findAll()

    fun findById(id: String): Company {
        return companyRepository.findById(id)
            .orElseThrow { NotFoundException("Company with id $id not found") }
    }

    fun updateCompany(id: String, request: CompanyRequest): Company {
        val companyToUpdate = findById(id)

        val updatedCompany = companyRepository.save(
            companyToUpdate.apply {
                name = request.name
                address = request.address
            }
        )

        return updatedCompany
    }

    fun deleteById(id: String) {
        val companyToDelete = findById(id)

        companyRepository.delete(companyToDelete)
    }

    private fun updateCompanyEmployees(updatedCompany: Company) {
        employeeRepository.saveAll(
            employeeRepository.findByCompanyId(updatedCompany.id!!).map {
                it.apply {
                    company = updatedCompany
                }
            }
        )
    }
}