package id.alianhakim.springcrudmongodb.dtos

import id.alianhakim.springcrudmongodb.models.User
import org.bson.types.ObjectId

class RegisterResponse constructor(
    val id: ObjectId,
    val name: String,
    val email: String
) {

    companion object {
        fun fromEntity(user: User): RegisterResponse {
            return RegisterResponse(
                id = user.id!!,
                name = user.name,
                email = user.email
            )
        }
    }
}