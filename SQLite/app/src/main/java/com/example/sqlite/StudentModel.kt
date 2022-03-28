package com.example.sqlite

import java.util.*

data class StudentModel(
    var id: Int = getAutoId(),
    var name: String = "",
    var email: String = "",
    var contact: String = "",
    var address: String = ""
) {
    fun getAutoId(): Int {
        val random = Random()
        return random.nextInt()
    }
}