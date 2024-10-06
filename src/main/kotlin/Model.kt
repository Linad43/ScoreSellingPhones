package org.example

class Model(
    private val company:String,
    private val name:String
) {
    companion object{
        private val COMPANY = listOf(
            "Samsung",
            "Xiaomi",
            "ZUNYI",
            "Honor",
            "Realme",
            "Sony"
        )
        private val LETTER = ('A'..'Z').toList()
        private val NUM = (10..99).toList()
        fun randomModal(): Model {
            val company = COMPANY.random()
            val name = LETTER.random().toString()+NUM.random().toString()
            return Model(company, name)
        }
    }
    override fun toString(): String {
        return "$company $name"
    }
}