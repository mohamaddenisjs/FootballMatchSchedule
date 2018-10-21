package dicoding.tech.metamorph.footballmatchschedule.activities

import dicoding.tech.metamorph.footballmatchschedule.model.EventItem

interface EventDetailView {
    fun showLoading()
    fun hideloading()
    fun showDetail(eventDetail: EventItem, hometeam: EventItem, awayTeam: EventItem)

}