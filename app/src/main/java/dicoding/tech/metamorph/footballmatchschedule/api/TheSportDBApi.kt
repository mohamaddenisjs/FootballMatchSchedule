package dicoding.tech.metamorph.footballmatchschedule.api

import dicoding.tech.metamorph.footballmatchschedule.BuildConfig

object TheSportDBApi{

    private fun urlBuild(path: String, id: String?) = BuildConfig.BASE_URL+"api/v1/json/"+BuildConfig.TSDB_API_KEY+"/"+path+"?id="+id

    fun getPrevSchedule(id: String?) = urlBuild("eventspastleague.php", id)
    fun getNextSchedule(id: String?) = urlBuild("eventsnextleague.php", id)
    fun getMatchDetail(id: String?) = urlBuild("lookupevent.php", id)
    fun getTeamDetail(id: String?) = urlBuild("lookupteam.php", id)
}