package dicoding.tech.metamorph.footballmatchschedule.adapters

import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dicoding.tech.metamorph.footballmatchschedule.R
import dicoding.tech.metamorph.footballmatchschedule.model.db.FavoriteDBParam
import dicoding.tech.metamorph.footballmatchschedule.utils.changeFormatDate
import dicoding.tech.metamorph.footballmatchschedule.utils.strToDate
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.sdk25.coroutines.onClick

class FavoriteAdapter(private val event: List<FavoriteDBParam>, private val listener: (FavoriteDBParam) -> Unit): RecyclerView.Adapter<FavoriteAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MatchUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount() = event.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(event[position], listener)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val matchDate: TextView = itemView.find(R.id.date)
        private val homeTeam: TextView = itemView.find(R.id.homeTeam)
        private val homeScore: TextView = itemView.find(R.id.homeScore)
        private val awayTeam: TextView = itemView.find(R.id.awayTeam)
        private val awayScore: TextView = itemView.find(R.id.awayscore)

        fun bindItem(schedule: FavoriteDBParam, listener: (FavoriteDBParam) -> Unit){
            val date = strToDate(schedule.eventDate)
            matchDate.text = changeFormatDate(date)

            homeTeam.text = schedule.homeTeam
            homeScore.text = schedule.homeScore

            awayTeam.text = schedule.awayTeam
            awayScore.text = schedule.awayScore

            itemView.onClick {
                listener(schedule)
            }
        }
    }

    class MatchUI: AnkoComponent<ViewGroup>{
        override fun createView(ui: AnkoContext<ViewGroup>) = with(ui){
            cardView {
                lparams(width = matchParent, height = wrapContent){
                    gravity = Gravity.CENTER
                    topMargin = dip(8)
                    leftMargin = dip(16)
                    rightMargin = dip(16)
                    bottomMargin = dip(8)
                    radius = dip(4).toFloat()
                }

                verticalLayout {

                    textView(context.getString(R.string.tv_date)){
                        id = R.id.date
                    }.lparams(width = wrapContent, height = matchParent){
                        gravity = Gravity.CENTER
                        margin = dip(8)
                    }

                    relativeLayout {

                        textView(context.getString(R.string.tv_home_club)){
                            id = R.id.homeTeam
                            textSize = 16f
                            textColor = R.color.black_de
                            setTypeface(null, Typeface.BOLD)
                            gravity = Gravity.RIGHT
                        }.lparams(width = wrapContent, height = wrapContent){
                            leftOf(R.id.homeScore)
                            rightMargin = dip(10)
                        }

                        textView(context.getString(R.string.tv_home_score)){
                            id = R.id.homeScore
                            textSize = 16f
                            setTypeface(null, Typeface.BOLD)
                            gravity = Gravity.CENTER
                            textColor = Color.BLACK
                        }.lparams(width = wrapContent, height = wrapContent){
                            leftOf(R.id.vs)
                        }

                        textView("VS"){
                            id = R.id.vs
                            textSize = 16f
                        }.lparams(width = wrapContent, height = wrapContent){
                            centerInParent()
                            leftMargin = dip(6)
                            rightMargin = dip(6)
                        }

                        textView(context.getString(R.string.tv_away_score)){
                            id = R.id.awayscore
                            textSize = 16f
                            gravity = Gravity.CENTER
                            setTypeface(null, Typeface.BOLD)
                            textColor = Color.BLACK
                        }.lparams(width = wrapContent, height = wrapContent){
                            rightOf(R.id.vs)
                        }

                        textView(context.getString(R.string.tv_away_club)){
                            id = R.id.awayTeam
                            textSize = 16f
                            textColor =  R.color.black_de
                            setTypeface(null, Typeface.BOLD)
                            gravity = Gravity.LEFT
                        }.lparams(width = wrapContent, height = wrapContent){
                            rightOf(R.id.awayscore)
                            leftMargin = dip(10)
                        }

                    }.lparams(width = matchParent, height = wrapContent)

                }.lparams(width = matchParent, height = wrapContent){
                    gravity = Gravity.CENTER
                    bottomMargin = dip(8)
                }
            }
        }

    }
}