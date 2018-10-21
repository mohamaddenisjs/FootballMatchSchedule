package dicoding.tech.metamorph.footballmatchschedule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import dicoding.tech.metamorph.footballmatchschedule.R.id.*
import dicoding.tech.metamorph.footballmatchschedule.fragments.EventFragment
import dicoding.tech.metamorph.footballmatchschedule.fragments.favorite.FavoriteFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private  var leagueId = "4328" //EPL
    private var fixture = 1
    private var savedInstanceState: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_button.setOnNavigationItemSelectedListener {
            item -> when(item.itemId){
            navigation_prev -> {
                fixture = 1
                openFragment(EventFragment.newInstance(fixture, leagueId))
                return@setOnNavigationItemSelectedListener true
            }
            navigation_next -> {
                fixture = 2
                openFragment(EventFragment.newInstance(fixture, leagueId))
                return@setOnNavigationItemSelectedListener true
            }
            navigation_favorite -> {
                openFragment(FavoriteFragment())
                return@setOnNavigationItemSelectedListener true
            }


        }
            false
        }
        nav_button.selectedItemId = navigation_prev
    }

    private fun openFragment(fragment: Fragment){
        if(savedInstanceState == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, fragment, fragment.javaClass.simpleName)
                    .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.league, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            menu_english -> {
                leagueId = "4328"
            }
            menu_german -> {
                leagueId ="4331"
            }
            menu_italian -> {
                leagueId="4332"
            }
            menu_freanch -> {
                leagueId="4334"
            }
            menu_spanyol -> {
                leagueId = "4335"
            }
        }
        if (nav_button.selectedItemId == navigation_favorite) nav_button.selectedItemId = navigation_prev
        openFragment(EventFragment.newInstance(fixture, leagueId))
        return super.onOptionsItemSelected(item)
    }
}
