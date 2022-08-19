package id.alianhakim.springcrudmongodb.repository

import id.alianhakim.springcrudmongodb.models.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, ObjectId> {
    fun findByEmail(email: String): User?
}