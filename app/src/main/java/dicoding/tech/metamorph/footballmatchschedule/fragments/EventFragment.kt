package dicoding.tech.metamorph.footballmatchschedule.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import dicoding.tech.metamorph.footballmatchschedule.R
import dicoding.tech.metamorph.footballmatchschedule.activities.EventDetailActivity
import dicoding.tech.metamorph.footballmatchschedule.adapters.EventAdapter
import dicoding.tech.metamorph.footballmatchschedule.api.ApiRepository
import dicoding.tech.metamorph.footballmatchschedule.api.TheSportDBApi
import dicoding.tech.metamorph.footballmatchschedule.model.EventItem
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class EventFragment: Fragment(), AnkoComponent<Context>, EventView {
    private var match: MutableList<EventItem> = mutableListOf()
    private lateinit var presenter: EventPresenter
    private lateinit var review: RecyclerView
    private lateinit var swipe: SwipeRefreshLayout
    private lateinit var adapter: EventAdapter
    private var fixture= 1
    private var leagueId = "4328"

    companion object {
        fun newInstance(fixture: Int, leagueId: String): EventFragment {
            val fragment = EventFragment()
            fragment.fixture = fixture
            fragment.leagueId = leagueId
            return fragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()
        presenter = EventPresenter(this, request, gson, fixture)
        adapter = EventAdapter(match){
            ctx.startActivity<EventDetailActivity>("EVENT" to it)
        }
        review.adapter = adapter

        swipe.onRefresh {
            presenter.getList(leagueId)
        }

        presenter.getList(leagueId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }


    override fun showLoading() {
        swipe.isRefreshing = true
    }

    override fun hideLoading() {
        swipe.isRefreshing = false
    }

    override fun showList(data: List<EventItem>) {
        hideLoading()
        match.clear()
        match.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun createView(ui: AnkoContext<Context>) = with(ui){
        verticalLayout {
            lparams(width = matchParent, height = wrapContent)
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipe = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light
                )

                review = recyclerView {
                    id = R.id.recycler_event
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }
            }

        }
    }
}