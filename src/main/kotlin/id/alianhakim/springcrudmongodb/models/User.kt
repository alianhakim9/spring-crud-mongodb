package id.alianhakim.springcrudmongodb.models

import com.fasterxml.jackson.annotation.JsonIgnore
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Document
class User {
    @Id
    var id: ObjectId? = null
    var name: String = ""

    @Indexed(unique = true)
    var email: String = ""

    @JsonIgnore
    var password: String = ""
        get() = field
        set(value) {
            val passwordEncoder = BCryptPasswordEncoder()
            field = passwordEncoder.encode(value)
        }

    fun comparePassword(password: String): Boolean {
        return BCryptPasswordEncoder().matches(password, this.password)
    }
}
