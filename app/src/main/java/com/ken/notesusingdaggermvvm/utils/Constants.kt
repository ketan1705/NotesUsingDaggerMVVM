package com.ken.notesusingdaggermvvm.utils

import android.graphics.Color
import java.text.SimpleDateFormat
import kotlin.random.Random

object Constants {
    fun getCurrentDateTime(): String {
        val currentTimeMillis = System.currentTimeMillis()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val dateTime = dateFormat.format(currentTimeMillis)
        return dateTime
    }

    fun getColor(): Int {
        val red = Random.nextInt(200, 256)
        val green = Random.nextInt(200, 256)
        val blue = Random.nextInt(200, 256)
        return Color.argb(255, red, green, blue)
    }
}