package shetty.people.slabstech.com.shetty.quotes

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home_screen.*
import kotlinx.android.synthetic.main.app_bar_home_screen.*

class HomeScreenActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home_screen, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.

        val duration = Toast.LENGTH_SHORT


        when (item.itemId) {
            R.id.nav_quotes -> {
                // Handle the camera action
                val text = "quotes"
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()

            }
            R.id.nav_missionmars -> {
                val text = "missionmars"
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()

            }
            R.id.nav_books -> {
                val text = "books"
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()

            }
            R.id.nav_mbga -> {
                val text = "mbga"
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()

            }
            R.id.nav_share -> {
                val text = "share"
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()

            }
            R.id.nav_send -> {
                val text = "send"
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}

class MyWebViewClient : WebViewClient() {
    //show the web page in webview but not in web browser
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        view.loadUrl(url)
        return true
    }
}
