package org.example

class Phone(
    private val model: Model,
    var price: Double,
    val defective: Boolean = false
) {
    companion object {
        fun randomPrice(): Int {
            return (10..100).random() * 1000
        }
    }

    override fun toString(): String {
        var result = "$model, цена: $price\n"
        result += if (defective) {
            "Необходим ремонт."
        } else {
            "Телефон исправен."
        }
        return result
    }
}