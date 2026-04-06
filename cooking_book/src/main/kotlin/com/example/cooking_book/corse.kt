package com.example.cooking_book
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/api/**")
            .allowedOrigins(
                "http://localhost:3000",   // ← Compose Web фронтенд
                "http://127.0.0.1:3000",
                "http://localhost:5500",   // ← твой старый HTML-фронт через Live Server
                "http://localhost:8080"    // ← если вдруг откроешь index.html напрямую
            )
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true)
    }
}