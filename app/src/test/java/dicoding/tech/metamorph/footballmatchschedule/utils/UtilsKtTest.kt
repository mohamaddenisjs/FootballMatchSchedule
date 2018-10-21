package dicoding.tech.metamorph.footballmatchschedule.utils

import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat

class UtilsKtTest {

    @Test
    fun strToDate() {
        val date = SimpleDateFormat("dd/MM/yyy").parse("22/07/2018")

        assertEquals(date, strToDate("2018-07-22"))
    }

    @Test
    fun changeFormatDate() {
        val dateFormat = SimpleDateFormat("MM/dd/yyyy")
        val date = dateFormat.parse("02/28/2018")
        assertEquals("Wednesday, 28 February 2018", changeFormatDate(date))

    }
}