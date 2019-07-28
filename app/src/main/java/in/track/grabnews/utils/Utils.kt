package `in`.track.grabnews.utils

import java.text.ParseException
import java.text.SimpleDateFormat

object Utils {
    fun convertTime(isoTime: String?): String? {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        try {
            val date = format.parse(isoTime)
            val f = SimpleDateFormat("HH:mm aa dd:mm:yyyy")
            return f.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return null
    }
}