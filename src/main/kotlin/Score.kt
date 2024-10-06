package org.example

import kotlin.time.times

class Score(
    private val city: String,
    private val repair: Boolean,
    private val phones: ArrayList<Phone>
) {
    companion object {
        val allPhones = mutableMapOf<Phone, Int>()
        fun addingPhones(phone: Phone) {
            if (allPhones.containsKey(phone)) {
                allPhones[phone] = allPhones[phone]!! + 1
            } else {
                allPhones[phone] = 1
            }
        }

        fun buyingPhones(phone: Phone) {
            if (allPhones[phone] == 1) {
                allPhones.remove(phone)
            } else {
                allPhones[phone] = allPhones[phone]!! - 1
            }
        }
    }

    fun buyPhone(phone: Phone) {
        phones.remove(phone)
        println("Телефон $phone, был куплен в городе $city")
        buyingPhones(phone)
    }

    fun addPhone(phone: Phone) {
        phone.price *= (1 + (1..10).random() / 100)
        phones.add(phone)
        println("Поступил в продажу телефон $phone, в городе $city")
        if (phone.defective) {
            println("Телефону необходим ремонт")
        }
        addingPhones(phone)
    }

    fun printListPhone() {
        println("В наличии:")
        phones.forEach {
            println(it)
        }
    }

    fun repairPhone(phone: Phone) {
        if (repair) {
            println("Телефон отправлен на ремонт.")
            if (phone.defective) {
                println("Телефон отремонтирован.")
            } else {
                println("Ремонт не требуется.")
            }
        } else {
            println("Ремонт в городе $city недоступен")
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