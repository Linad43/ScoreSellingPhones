package org.example

import kotlin.time.times

class Phone(
    private val model: Model,
    var price: Int,
    val defective: Boolean = false
) {
    companion object {
        fun randomPrice(): Int {
            return ((10..100).random() * 1000)
        }
    }
    override fun toString(): String {
        var result = "$model, цена: $price"
//        result += if (defective) {
//            "Необходим ремонт."
//        } else {
//            "Телефон исправен."
//        }
        return result
    }
}