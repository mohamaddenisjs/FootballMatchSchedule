package dicoding.tech.metamorph.footballmatchschedule.fragments

import com.google.gson.Gson
import dicoding.tech.metamorph.footballmatchschedule.api.ApiRepository
import dicoding.tech.metamorph.footballmatchschedule.api.TheSportDBApi
import dicoding.tech.metamorph.footballmatchschedule.model.EventItem
import dicoding.tech.metamorph.footballmatchschedule.model.EventResponse
import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class EventPresenterTest {

    @Mock private lateinit var view: EventView
    @Mock private lateinit var gson: Gson
    @Mock private lateinit var api: String

    private lateinit var presenter: EventPresenter


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = EventPresenter(view, api, gson)
    }

    @Test
    fun getList() {
        val teams: MutableList<EventItem> = mutableListOf()
        val response = EventResponse(teams)


        `when`{
            gson.fromJson(ApiRepository().doRequest(api), EventResponse::class.java)
        }.thenReturn { response }

//        presenter.getList("English Premiere League")

        verify(view).showLoading()
        verify(view).showList(teams)
        verify(view).hideLoading()

    }
}