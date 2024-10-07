package org.example

import kotlin.random.Random

var scores = arrayListOf<Score>()
val phones = arrayListOf<Phone>()
fun main() {
    //menu
    var choise: Int
    var countScore = 2
    var countModelPhone = 20
    var countPhoneInScore = 100
    println("Сеть магазинов по продаже телефонов")
    while (true) {
        println("Желаете ввести данные самостоятельно?")
        println("1.Да\n2.Нет")
        choise = readln().toInt()
        when (choise) {
            1 -> {
                println("Введите кол-во магазинов (не рекомендуется более 2): ")
                countScore = readln().toInt()
                println("Введите кол-во моделей телефонов: ")
                countModelPhone = readln().toInt()
                println("Введите кол-во телефонов в каждом магазине: ")
                countPhoneInScore = readln().toInt()
            }

            2 -> {
                break
            }

            else -> {
                println("Ошибка ввода, повторите ввод")
                continue
            }
        }
    }
    while (true) {
        val (scores, phones) = addingPhoneToScore(countScore, countModelPhone, countPhoneInScore)
        //scores.forEach { println(it) }
        //phones.forEach { println(it) }
        when(mainMenu())
        {
            1->{
                when(menuChoiseScore()){
                    1->{

                    }
                }
            }
        }

        break
    }
}

private fun addingPhoneToScore(
    countScore: Int,
    countModelPhone: Int,
    countPhoneInScore: Int
): Pair<ArrayList<Score>, ArrayList<Phone>> {
    for (i in 0..<countScore) {
        scores.add(Score(Score.citis[i], Random.nextBoolean()))
    }
    for (i in 0..<countModelPhone) {
        if ((1..5).random() == 1) {
            phones.add(Phone(Model.randomModal(), Phone.randomPrice(), true))
        } else {
            phones.add(Phone(Model.randomModal(), Phone.randomPrice()))
        }
    }
    for (i in 0..<countPhoneInScore) {
        scores[(0..<countScore).random()].addPhone(phones[(0..<countModelPhone).random()])
    }
    return Pair(scores, phones)
}

private fun mainMenu(): Int {
    println("Основное меню")
    println("1.Выбор магазина")
    println("2.Посмотреть все телефоны")
    println("3.Поиск телефона")
    println("0.Выход")
    return readln().toInt()
}

private fun menuChoiseScore(): Int {
    println("Выберите магазин")
    var i = 0
    scores.forEach {
        i++
        println("$i.${it.city}")
    }
    println()
    println("0.Выход")
    return readln().toInt()
}

private fun menuScore(choise: Int): Int {
    println("Меню магазина в городе ${scores[choise].city}")
    println("1.Показать ассортимент магазина")
    println("2.Приобрести телефон")
    println("0.Назад")
    return readln().toInt()
}
