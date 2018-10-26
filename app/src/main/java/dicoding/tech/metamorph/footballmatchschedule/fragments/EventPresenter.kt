package dicoding.tech.metamorph.footballmatchschedule.fragments

import com.google.gson.Gson
import dicoding.tech.metamorph.footballmatchschedule.api.ApiRepository
import dicoding.tech.metamorph.footballmatchschedule.api.TheSportDBApi
import dicoding.tech.metamorph.footballmatchschedule.model.EventResponse
import dicoding.tech.metamorph.footballmatchschedule.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class EventPresenter(private val view: EventView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val fixture: Int = 1,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()){

    fun getList(id: String?){
        view.showLoading()

        val api = if (fixture == 1) TheSportDBApi.getPrevSchedule(id) else TheSportDBApi.getNextSchedule(id)

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(api), EventResponse::class.java)

            }
            view.hideLoading()
            view.showList(data.await().events)
        }
    }

}