package com.example.someone.int20htesttask

/**
 * Created by someone on 22.02.17.
 */
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by someone on 22.02.17.
 */
fun takeDateInterval(start: String, end: String): List<String>{
    var results =  ArrayList<String>()

    val formatter = SimpleDateFormat("dd.MM.yyyy")
    val startDate = formatter.parse(start)
    val endDate = formatter.parse(end)

    val start = Calendar.getInstance()
    start.time = startDate
    val end = Calendar.getInstance()
    end.time = endDate

    var date = start.time
    while (!end.before(start)) {
        results.add(formatter.format(date))
        start.add(Calendar.DATE, 1)
        date = start.time
    }
    return results
}