package com.example.cooking_book.models

import jakarta.persistence.*

@Entity
@Table(name = "step")
data class Step(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_step")
    val idStep: Long? = null,

    @Column(name = "id_recipe")
    val idRecipe: Long,

    @Column(name = "step_description")
    val stepDescription: String,

    @Column(name = "step_order")
    val stepOrder: Int
) {
    constructor(idRecipe: Long, stepDescription: String, stepOrder: Int) :
            this(null, idRecipe, stepDescription, stepOrder)
}