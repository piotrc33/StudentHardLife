package com.example.studenthardlife

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

object ProblemLists {
    var data: ArrayList<ProblemList> = arrayListOf();

    fun initialize() {
        var list1Problems : ArrayList<Problem> = arrayListOf()
        list1Problems.add(Problem(1, "problem1", "today", "create chess app"))
//        data["List1"] = list1Problems;
        val list1 = ProblemList("kurrwaaa", list1Problems)
        data.add(list1)

        var list2Problems : ArrayList<Problem> = arrayListOf()
        list2Problems.add(Problem(2, "problem2", "today", "create chess app"))
//        data["List1"] = list1Problems;
        val list2 = ProblemList("kurrwaaa2", list2Problems)
        data.add(list2)
//        for(i in 1..20) {
//            val current = LocalDateTime.now()
//            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
//            val formatted = current.format(formatter)
//            CrimeList.crimeList.add(Crime(i, "crime #$i", formatted, "crime description #$i", Random.nextBoolean() ))
//        }
    }
}