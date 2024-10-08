package org.example

import kotlin.time.times

class Phone(
    val model: Model,
    var price: Int,
    val defective: Boolean = false
) {
    companion object {
        fun randomPrice(): Int {
            return ((10..100).random() * 1000)
        }
    }

//    override fun compareTo(other: Phone) = model.toString().compareTo(model.toString())

    override fun toString(): String {
        var result = "$model, цена: $price"
        result += if (defective) {
            " (присутствует деффект)"
        } else {
            ""
        }
        return result
    }
}