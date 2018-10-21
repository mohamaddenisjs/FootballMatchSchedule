package dicoding.tech.metamorph.footballmatchschedule.api

import android.net.Uri
import dicoding.tech.metamorph.footballmatchschedule.BuildConfig
import java.net.URL
import java.security.cert.CertPath

class TheSportDBApi(val id: String?){
    private fun urlBuild(path: String?) = Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath(path)
            .appendQueryParameter("id", id)
            .build().toString()

    fun getPrevSchdule() = urlBuild("eventspastleague.php")
    fun getNextSchdule() = urlBuild("eventsnextleague.php")
    fun getMatchDetail() = urlBuild("lookupevent.php")
    fun getTeamDetail() = urlBuild("lookupteam.php")
}
