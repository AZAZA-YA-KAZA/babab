package com.example.cooking_book

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Главный класс приложения «Кулинарная книга».
 * * Аннотация [SpringBootApplication] включает автоконфигурацию, сканирование компонентов
 * и позволяет рассматривать этот класс как конфигурационный для контекста Spring.
 */
@SpringBootApplication
class CookingBookApplication

/**
 * Точка входа в приложение.
 * * Запускает Spring-контекст и инициализирует веб-сервер для обработки
 * запросов к API кулинарной книги.
 * * @param args Аргументы командной строки, передаваемые при запуске приложения.
 */
fun main(args: Array<String>) {
	runApplication<CookingBookApplication>(*args)
}