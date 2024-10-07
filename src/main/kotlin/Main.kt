package org.example

fun main() {
    val score = arrayListOf(
        Score("Москва", true, arrayListOf()),
        Score("Питер", false, arrayListOf())
    )
    val phones = arrayListOf<Phone>()
    for (i in 1..20) {
        if ((1..5).random() == 1) {
            phones.add(Phone(Model.randomModal(), Phone.randomPrice(), true))
        } else {
            phones.add(Phone(Model.randomModal(), Phone.randomPrice()))
        }
    }
    for (i in 1..100) {
        score[(0..1).random()].addPhone(phones[(0..19).random()])
    }
    Score.allPhones.forEach{
        println("${it.key} (${it.value})")
    }
    score.forEach{
        it.printListPhone()
    }

}