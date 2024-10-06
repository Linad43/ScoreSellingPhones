package org.example

class Score(
    private val city: String,
    private val repair: Boolean,
    private val phones: ArrayList<Phone>
) {
    fun buyPhone(phone: Phone){
        phones.remove(phone)
    }
    fun addPhone(phone: Phone){
        phone.price *= (1+(1..10).random()/100.0)
        phones.add(phone)
    }
    fun printListPhone() {
        println("В наличии:")
        phones.forEach {
            println(it)
        }
    }

    override fun toString(): String {
        var result = "Магазин в городе $city"
        result += " возможность ремонта телефона "
        result += if (repair) {
            "есть"
        } else {
            "нет"
        }
        result += "."
        return result
    }
}