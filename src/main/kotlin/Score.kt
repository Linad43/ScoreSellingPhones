package org.example

class Score(
    val city: String,
    private val repair: Boolean,
    private val phones: ArrayList<Pair<Phone, Double>> = arrayListOf<Pair<Phone, Double>>()
) {
    companion object {
        val citis = arrayListOf(
            "Москва",
            "Санкт-Петербург",
            "Сочи",
            "Казань",
            "Владивосток",
            "Анапа",
            "Екатеринбург",
            "Нижний Новгород",
            "Калининград",
            "Владимир"
        )
        val allPhones = mutableMapOf<Phone, Int>()
        val sellPhones = mutableMapOf<Phone, Int>()
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
            if (sellPhones.containsKey(phone)) {
                sellPhones[phone] = sellPhones[phone]!! + 1
            } else {
                sellPhones[phone] = 1
            }
        }
    }

    fun buyPhone(phone: Phone) {
        phones.remove(phones[indexFirstOf(phone)])
        println("Телефон $phone, был куплен в городе $city")
        buyingPhones(phone)
    }

    fun addPhone(phone: Phone) {
        val defect: Double
        val procent: Double
        if (phone.defective) {
            defect = 0.7
        } else {
            defect = 1.0
        }
        if (indexFirstOf(phone) == -1) {
            procent = (1 + (1..10).random() / 100.0) * defect
        } else {
            procent = phones[indexFirstOf(phone)].second * defect
        }
        phones.add(Pair(phone, procent))
        println("Поступил в продажу телефон ${phone.model} цена: ${(phone.price * procent).toInt()}, в городе $city")
        if (phone.defective) {
            println("Телефону необходим ремонт")
        }
        addingPhones(phone)
    }

    private fun indexFirstOf(phone: Phone): Int {
        for (i in phones.indices) {
            if (phones[i].first == phone) {
                return i
            }
        }
        return -1
    }

    fun printListPhone() {
        println("Магазин в городе $city")
        println("В наличии:")
        phones.sortBy { it.first.model.toString() }
        phones.groupBy { it.first }//группируем по телефону map<Phone, List<Pair<Phone, Double>>>
            .forEach { it ->
                print("${it.key.model} по цене: ")//вывод каждого телефона
                val pairPrice = mutableMapOf<Int, Int>()
                it.value.forEach {//Группируем цены
                    val priceInScore = (it.first.price * it.second).toInt()
                    if (pairPrice.containsKey(priceInScore)) {
                        pairPrice[priceInScore] = pairPrice[priceInScore]!! + 1
                    } else {
                        pairPrice[priceInScore] = 1
                    }
                }
                var i = 1
                for (priceInScore in pairPrice) {
                    print("${priceInScore.key} (${priceInScore.value})")
                    if (i == pairPrice.count()) {
                        print(".")
                    } else {
                        print(", ")
                    }
                    i++
                }
                println()
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