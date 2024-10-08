package org.example

var scores = arrayListOf<Score>()
val phones = arrayListOf<Phone>()
val youPhones = arrayListOf<Phone>()

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
        try {
            choise = readln().toInt()
        } catch (e: Exception) {
            println("Некорректный ввод")
            continue
        }
        when (choise) {
            1 -> {
                while (true) {
                    println("Введите кол-во магазинов: ")
                    try {
                        countScore = readln().toInt()
                    } catch (e: Exception) {
                        println("Некорректный ввод")
                    }
                    if (countScore < 1 || countScore > 10) {
                        println("Количество городов не может быть меньше одного и больше десяти")
                        continue
                    } else break
                }
                while (true) {
                    println("Введите кол-во моделей телефонов: ")
                    try {
                        countModelPhone = readln().toInt()
                    } catch (e: Exception) {
                        println("Некорректный ввод")
                    }
                    if (countModelPhone < 1) {
                        println("Количество моделей не может быть меньше одного")
                        continue
                    } else break
                }
                while (true) {
                    println("Введите кол-во телефонов суммарно во всех магазинах: ")
                    try {
                        countPhoneInScore = readln().toInt()
                    } catch (e: Exception) {
                        println("Некорректный ввод")
                    }
                    if (countPhoneInScore < 1) {
                        println("Количество телефонов не может быть меньше одного")
                        continue
                    } else break
                }
                break
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
    addingPhoneToScore(countScore, countModelPhone, countPhoneInScore)
    while (true) {
        var choiseMainMenu = -1
        try {
            choiseMainMenu = mainMenu()
        } catch (e: Exception) {
            println("Ошибка ввода, повторите ввод")
        }
        when (choiseMainMenu) {
            1 -> {
                while (true) {
                    var choiseScore = -1
                    try {
                        choiseScore = menuChoiseScore()
                    } catch (e: Exception) {
                        println("Ошибка ввода, повторите ввод")
                    }
                    when (choiseScore) {
                        in (1..scores.size) -> {
                            while (true) {
                                var choiseMenuScore = -1
                                try {
                                    choiseMenuScore = menuScore((choiseScore - 1))
                                } catch (e: Exception) {
                                    println("Ошибка ввода, повторите ввод")
                                }
                                when (choiseMenuScore) {
                                    1 -> {
                                        scores[choiseScore - 1].printListPhone()
                                    }

                                    2 -> {
                                        menuBuyPhone(scores[choiseScore - 1])
                                    }

                                    3 -> {
                                        if (scores[choiseScore - 1].repair) {
                                            while (true) {
                                                if (youPhones.isEmpty()) {
                                                    println("У вас нет в наличии телефона")
                                                    break
                                                } else if (youPhones.filter { it.defective == true }.isEmpty()) {
                                                    println("Все ваши телефоны исправны")
                                                    break
                                                } else {
                                                    val phones = arrayListOf<Phone>()
                                                    youPhones.filter { it.defective == true }
                                                        .forEach {
                                                            phones.add(it)
                                                            println("${phones.size}.${it.model}")
                                                        }
                                                    println("0.Отмена")
                                                    var choise: Int
                                                    try {
                                                        choise = readln().toInt()
                                                    } catch (e: Exception) {
                                                        println("Некорректный ввод")
                                                        continue
                                                    }
                                                    if (choise == 0) {
                                                        break
                                                    } else {
                                                        scores[choiseScore - 1].repairPhone(phones[choise - 1])
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    0 -> {
                                        break
                                    }

                                    else -> continue
                                }
                            }
                        }

                        0 -> break
                        else -> continue
                    }
                }
            }

            2 -> {
                Score.allPhones.toSortedMap(compareBy {
                    it.model.toString()
                }).forEach {
                    println("${it.key.model}")
                }
            }

            3 -> {
                findPhone()
            }

            4 -> {
                if (youPhones.isNotEmpty()) {
                    youPhones.forEach {
                        println(it)
                    }
                }
            }

            0 -> break
            else -> continue
        }
    }
}

private fun findPhone(): Boolean {
    var companys = arrayListOf<String>()
    var choise: Int
    while (true) {
        companys.clear()
        println("Выберите производителя: ")
        companys = arrayListOf<String>()
        Score.allPhones.keys.groupBy {
            it.model.company
        }.keys.forEach {
            companys.add(it)
            println("${companys.size}.${it}")
        }
        println("0.Отмена")
        try {
            choise = readln().toInt()
        } catch (e: Exception) {
            println("Некорректный ввод")
            continue
        }
        if (choise == 0) {
            return false
        } else break
    }
    val choiseCompany = companys[choise - 1]
    val models = arrayListOf<String>()
    while (true) {
        models.clear()
        Score.allPhones.keys.filter {
            it.model.company == choiseCompany
        }.groupBy {
            it.model.name
        }.keys.forEach {
            models.add(it)
            println("${models.size}.$it")
        }
        println("0.Отмена")
        try {
            choise = readln().toInt()
        } catch (e: Exception) {
            println("Некорректный ввод")
            continue
        }
        if (choise == 0) {
            return false
        } else break
    }
    val choiseModel = models[choise - 1]
    println("Найдено в следующих городах:")
    scores.forEach {
        if (it.indexFirstOf(Model(choiseCompany, choiseModel)) > 0) {
            println(it.city)
        }
    }
    return true
}


fun menuBuyPhone(score: Score): Boolean {
    val companys = arrayListOf<String>()
    var choise: Int
    while (true) {
        companys.clear()
        println("Выберите производителя телефона: ")
        score.phones.groupBy {
            it.first.model.company
        }.keys.forEach {
            companys.add(it)
            println("${companys.size}.${it}")
        }
        println("0.Отмена")

        try {
            choise = readln().toInt()
        } catch (e: Exception) {
            println("Некорректный ввод")
            continue
        }

        if (choise == 0) {
            return false
        } else {
            break
        }
    }
    val choiseCompany = companys[choise - 1]
    val models = arrayListOf<String>()
    while (true) {
        models.clear()
        println("Выберите название телефона ${choiseCompany}:")
        score.phones.filter {
            it.first.model.company == choiseCompany
        }.groupBy {
            it.first.model.name
        }.keys.forEach {
            models.add(it)
            println("${models.size}.${it}")
        }
        println("0.Отмена")

        try {
            choise = readln().toInt()
        } catch (e: Exception) {
            println("Некорректный ввод")
            continue
        }

        if (choise == 0) {
            return false
        } else {
            break
        }
    }
    val choiseName = models[choise - 1]
    val model = Model(choiseCompany, choiseName)
    val prices = arrayListOf<Int>()
    while (true) {
        prices.clear()
        println("По вашим критериям найдено следующие телефоны")
        score.phones.filter {
            it.first.model.company == model.company && it.first.model.name == model.name
        }.groupBy() {
            (it.first.price * it.second).toInt()
        }.values.forEach {
            if (it.isNotEmpty()) {
                prices.add((it.first().first.price * it.first().second).toInt())
                println("${prices.size}.${it.first().first.model} цена ${(it.first().first.price * it.first().second).toInt()}")
            }
        }
        println("0.Отмена")
        try {
            choise = readln().toInt()
        } catch (e: Exception) {
            println("Некорректный ввод")
            continue
        }
        if (choise == 0) {
            return false
        } else break
    }

    val choisePrice = prices[choise - 1]
    println("Вы приобрели телефон $model цена $choisePrice")
    val phoneInScore = score.phones.first {
        it.first.model.company == model.company
                && it.first.model.name == model.name
                && (it.first.price * it.second).toInt() == choisePrice
    }
    score.buyPhone(phoneInScore)
    addYouPhone(phoneInScore.first)
    return true
}

private fun addingPhoneToScore(
    countScore: Int,
    countModelPhone: Int,
    countPhoneInScore: Int
): Pair<ArrayList<Score>, ArrayList<Phone>> {
    for (i in 0..<countScore) {
        if (i % 2 == 0) {
            scores.add(Score(Score.citis[i], false))
        } else
            scores.add(Score(Score.citis[i], true))
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
    if (youPhones.isNotEmpty()) {
        println("4.Посмотреть свои телефоны")
    }
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
    if (scores[choise].repair) {
        println("3.Ремонт приобретенного телефона")
    }
    println("0.Назад")
    return readln().toInt()
}

private fun addYouPhone(phone: Phone) {
    youPhones.add(phone)
}