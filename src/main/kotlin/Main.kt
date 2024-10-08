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
        //Pair(scores, phones) = addingPhoneToScore(countScore, countModelPhone, countPhoneInScore)
        //scores.forEach { println(it) }
        //phones.forEach { println(it) }
        addingPhoneToScore(countScore, countModelPhone, countPhoneInScore)
        val choiseMainMenu = mainMenu()
        when (choiseMainMenu) {
            1 -> {
                val choiseScore = menuChoiseScore()
                when (choiseScore) {
                    in (1..scores.size) -> {
                        while (true) {
                            val choiseMenuScore = menuScore((choiseScore - 1))
                            when (choiseMenuScore) {
                                1 -> {
                                    scores[choiseScore-1].printListPhone()
                                }

                                2 -> {
                                    menuBuyPhone(scores[choiseScore-1])
                                }

                                0 -> {
                                    break
                                }
                            }
                        }
                    }

                    0 -> {

                    }
                }
            }
        }

        break
    }
}

fun menuBuyPhone(score: Score): Int {
    println("Выберите производителя телефона: ")
    val companys = arrayListOf<String>()
    score.phones.groupBy {
        it.first.model.company
    }.keys.forEach {
        companys.add(it)
        println("${companys.size}.${it}")
    }
    println("0.Отмена")
    var choise = readln().toInt()
    if (choise == 0) {
        return -1
    }
    val choiseCompany = companys[choise - 1]
    println("Выберите название телефона ${choiseCompany}:")
    val models = arrayListOf<String>()
    val filt = score.phones.filter {
        it.first.model.company == choiseCompany
    }
    println(filt)
    val gro = filt.groupBy {
        it.first.model.name
    }
    println(gro)
    val kayse = gro.keys
    println(kayse)
    val fo = kayse.forEach {
        //models.add(it)
        println("${models.size}.${it}")
    }
    score.phones.filter {
        it.first.model.company == choiseCompany
    }.groupBy {
        it.first.model.name
    }.keys.forEach {
        models.add(it)
        println("${models.size}.${it}")
    }
    println("0.Отмена")
    choise = readln().toInt()
    if (choise == 0) {
        return -1
    }
    val choiseName = models[choise - 1]
    val model = Model(choiseCompany, choiseName)
    var i = 1
    println(model)
    score.phones.filter {
        it.first.model.company == model.company && it.first.model.name == model.name
    }.groupBy() {
        (it.first.price * it.second).toInt()
    }.values.forEach{
        //println(it)
        if (it.isNotEmpty()){
            println("$i.${it.first().first.model} цена ${(it.first().first.price*it.first().second).toInt()}")
            i++
        }
    }
    println("Вы хотите приобрести телефон $model")
    println("1.Да\n2.Нет")
    choise = readln().toInt()
    when (choise) {
        1 -> {
            score.phones.filter {
                it.first.model == model
            }
        }
    }

    return 0
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
    var i = 1
    scores.forEach {
        println("$i.${it.city}")
        i++
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
