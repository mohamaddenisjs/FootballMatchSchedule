package dicoding.tech.metamorph.footballmatchschedule.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import dicoding.tech.metamorph.footballmatchschedule.model.db.FavoriteDBHelper
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

@SuppressLint("SimpleDateFormat")
fun strToDate(strDate: String?, pattern: String = "yyyy-MM-dd"): Date{
    val format = SimpleDateFormat(pattern)
    val date = format.parse(strDate)

    return date
}

@SuppressLint("SimpleDateFormat")
fun changeFormatDate(date: Date?): String? = with(date ?: Date()){
    SimpleDateFormat("EEEE, dd MMMM yyyy").format(this)
}
fun View.visible(){
    visibility = View.VISIBLE
}

fun View.invisible(){
    visibility = View.INVISIBLE
}

val Context.db: FavoriteDBHelper
    get() = FavoriteDBHelper.getInstance(applicationContext)
