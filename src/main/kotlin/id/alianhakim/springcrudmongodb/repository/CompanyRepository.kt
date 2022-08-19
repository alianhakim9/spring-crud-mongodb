package id.alianhakim.springcrudmongodb.repository

import id.alianhakim.springcrudmongodb.models.Company
import org.springframework.data.mongodb.repository.MongoRepository

interface CompanyRepository : MongoRepository<Company, String>