package org.example

class Phone(
    private val model: Model,
    var price: Double
) {
    companion object {
        fun randomPrice(): Int {
            return (10..100).random() * 1000
        }
    }

    override fun toString(): String {
        return "$model, цена: $price"
    }
}