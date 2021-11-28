package com.mobile.micosecha.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

object Time {

    fun timeStamp(): String {

        val timeStamp = Timestamp(System.currentTimeMillis())
        val sdf = SimpleDateFormat("HH:mm")
        val time = sdf.format(Date(timeStamp.time))

        return time.toString()
    }

    fun getTime(): String {

        val timeStamp = Timestamp(System.currentTimeMillis())
        val sdf = SimpleDateFormat("HH:mm")
        val time = sdf.format(Date(timeStamp.time))

        return "It's $time"
    }

    fun getDate(): String {

        val timeStamp = Timestamp(System.currentTimeMillis())
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val date = sdf.format(Date(timeStamp.time))

        return "It's $date"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDay(): String {

        val current = LocalDateTime.now()
        val fullLocaleFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        val fullLocaleTime = current.format(fullLocaleFormat)

        return "It's $fullLocaleTime"
    }
}