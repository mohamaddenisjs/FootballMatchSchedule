package dicoding.tech.metamorph.footballmatchschedule.fragments

import com.google.gson.Gson
import dicoding.tech.metamorph.footballmatchschedule.api.ApiRepository
import dicoding.tech.metamorph.footballmatchschedule.model.EventResponse
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class EventPresenter(private val view: EventView,
                     private val api: String,
                     private val gson: Gson){

    fun getList(){
        view.showLoading()

        async(UI) {
            val data = bg {
                gson.fromJson(ApiRepository()
                        .doRequest(api), EventResponse::class.java)

            }
            view.hideLoading()
            view.showList(data.await().events)
        }
    }

}