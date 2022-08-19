package id.alianhakim.springcrudmongodb.dtos

import id.alianhakim.springcrudmongodb.models.Company


class CompanyResponse constructor(
    val id: String,
    val name: String,
    val address: String
) {

    companion object {
        fun fromEntity(company: Company): CompanyResponse {
            return CompanyResponse(
                id = company.id!!,
                name = company.name,
                address = company.address
            )
        }
    }
}