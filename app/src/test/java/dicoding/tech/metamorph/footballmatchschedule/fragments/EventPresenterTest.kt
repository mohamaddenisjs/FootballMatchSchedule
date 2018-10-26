package dicoding.tech.metamorph.footballmatchschedule.fragments

import com.google.gson.Gson
import dicoding.tech.metamorph.footballmatchschedule.api.ApiRepository
import dicoding.tech.metamorph.footballmatchschedule.api.TheSportDBApi
import dicoding.tech.metamorph.footballmatchschedule.model.EventItem
import dicoding.tech.metamorph.footballmatchschedule.model.EventResponse
import dicoding.tech.metamorph.footballmatchschedule.utils.TestContextProvider
import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class EventPresenterTest {

    @Mock
    private lateinit var view: EventView
    @Mock
    private lateinit var gson: Gson
    @Mock
    private lateinit var apiRepository: ApiRepository


    private lateinit var presenter: EventPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = EventPresenter(view, apiRepository, gson, 1, TestContextProvider())

    }

    @Test
    fun getList() {
        val teams: MutableList<EventItem> = mutableListOf()
        val events: MutableList<EventItem> = mutableListOf()
        val response = EventResponse(events, teams)

        val league = "4329"
        Mockito.`when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getPrevSchedule(league)),
                EventResponse::class.java))
                .thenReturn(response)

        presenter.getList(league)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showList(teams)
        Mockito.verify(view).hideLoading()
    }
}