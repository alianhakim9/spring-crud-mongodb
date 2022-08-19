package id.alianhakim.springcrudmongodb.controller

import id.alianhakim.springcrudmongodb.dtos.CompanyRequest
import id.alianhakim.springcrudmongodb.dtos.CompanyResponse
import id.alianhakim.springcrudmongodb.service.CompanyService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/company")
class CompanyController constructor(
    private val companyService: CompanyService
) {
    @PostMapping
    fun createCompany(
        @RequestBody request: CompanyRequest
    ): ResponseEntity<CompanyResponse> {
        val createdCompany = companyService.createCompany(request)

        return ResponseEntity.ok(
            CompanyResponse.fromEntity(createdCompany)
        )
    }

    @GetMapping
    fun findAllCompanies(): ResponseEntity<List<CompanyResponse>> {
        val companies = companyService.findAll()
        return ResponseEntity.ok(
            companies.map {
                CompanyResponse.fromEntity(it)
            }
        )
    }

    @GetMapping("/{id}")
    fun findCompanyById(
        @PathVariable id: String
    ): ResponseEntity<CompanyResponse> {
        val company = companyService.findById(id)

        return ResponseEntity.ok(CompanyResponse.fromEntity(company))
    }

    @PutMapping("/{id}")
    fun updateCompany(
        @PathVariable id: String,
        @RequestBody request: CompanyRequest
    ): ResponseEntity<CompanyResponse> {
        val updatedCompany = companyService.updateCompany(id, request)
        return ResponseEntity.ok(CompanyResponse.fromEntity(updatedCompany))
    }

    @DeleteMapping("/{id}")
    fun deleteCompany(
        @PathVariable id: String
    ): ResponseEntity<Any> {
        companyService.deleteById(id)

        return ResponseEntity.noContent().build()
    }
}