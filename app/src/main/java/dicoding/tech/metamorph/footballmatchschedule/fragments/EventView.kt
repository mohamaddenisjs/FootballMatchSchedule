package dicoding.tech.metamorph.footballmatchschedule.fragments

import dicoding.tech.metamorph.footballmatchschedule.model.EventItem

interface EventView {
    fun showLoading()
    fun hideLoading()
    fun showList(data: List<EventItem>)

}