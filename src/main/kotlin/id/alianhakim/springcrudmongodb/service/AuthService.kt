package id.alianhakim.springcrudmongodb.service

import id.alianhakim.springcrudmongodb.dtos.LoginRequest
import id.alianhakim.springcrudmongodb.dtos.RegisterRequest
import id.alianhakim.springcrudmongodb.models.User
import id.alianhakim.springcrudmongodb.repository.UserRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class AuthService constructor(
    private val userRepository: UserRepository
) {
    fun register(request: RegisterRequest): User {
        val user = User()
        with(user) {
            name = request.name
            email = request.email
            password = request.password
        }
        return userRepository.save(user)
    }

    fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    fun login(request: LoginRequest): User? {
        return findByEmail(request.email)
    }

    fun getById(id: String): User {
        return userRepository.findById(ObjectId(id)).get()
    }
}