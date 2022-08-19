package id.alianhakim.springcrudmongodb.controller

import id.alianhakim.springcrudmongodb.dtos.LoginRequest
import id.alianhakim.springcrudmongodb.dtos.Message
import id.alianhakim.springcrudmongodb.dtos.RegisterRequest
import id.alianhakim.springcrudmongodb.dtos.RegisterResponse
import id.alianhakim.springcrudmongodb.service.AuthService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api/auth")
class AuthController constructor(
    private val authService: AuthService
) {
    @PostMapping("/register")
    fun register(
        @RequestBody request: RegisterRequest
    ): ResponseEntity<RegisterResponse> {
        val createdUser = authService.register(request)
        return ResponseEntity.ok(
            RegisterResponse.fromEntity(createdUser)
        )
    }

    @PostMapping("/login")
    fun login(
        @RequestBody request: LoginRequest,
        response: HttpServletResponse
    ): ResponseEntity<Any> {
        val user = authService.login(LoginRequest(email = request.email, password = request.password))
            ?: return ResponseEntity.badRequest().body(Message("User not found"))

        if (!user.comparePassword(request.password)) {
            return ResponseEntity.badRequest().body(Message("Invalid password"))
        }

        val issuer = user.id.toString()

        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) // 1 day
            .signWith(SignatureAlgorithm.HS512, "strongSecretKey")
            .compact()
        Cookie("jwt", jwt).also {
            it.isHttpOnly = true
            response.addCookie(it)
        }
        return ResponseEntity.ok(Message("Success"))
    }

    @GetMapping("/user")
    fun user(
        @CookieValue("jwt") jwt: String?
    ): ResponseEntity<Any> {
        try {
            if (jwt == null) {
                return ResponseEntity.status(401).body(Message("Unauthorized"))
            }

            Jwts.parser().setSigningKey("strongSecretKey").parseClaimsJws(jwt).body.also {
                return ResponseEntity.ok(authService.getById(it.issuer))
            }
        } catch (e: Exception) {
            return ResponseEntity.status(401).body(Message("unauthenticated"))
        }
    }

    @PostMapping("/user/logout")
    fun logout(
        response: HttpServletResponse
    ): ResponseEntity<Any> {
        Cookie("jwt", null).also {
            it.isHttpOnly = true
            it.path = "/api/auth"
            it.maxAge = 0
            response.addCookie(it)
        }
        return ResponseEntity.ok(Message("Logout success"))
    }
}