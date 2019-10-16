package com.slabstech.app.thehdtour.quotes

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
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

        val intent: Intent = Intent(this, Narration::class.java)
        intent.putExtra("pageType", "Partner")
        startActivity(intent)

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
            R.id.experience -> {

                val intent: Intent = Intent(this, Places::class.java)
                intent.putExtra("pageType", "Experience")
                startActivity(intent)

            }
            R.id.partners -> {

                val intent: Intent = Intent(this, Places::class.java)
                intent.putExtra("pageType", "Partner")
                startActivity(intent)

            }
            R.id.ticket -> {

                val intent: Intent = Intent(this, WebViewActivity::class.java)
                val webUrl: String = getString(R.string.ticketUrl)
                intent.putExtra("webUrl", webUrl)

                startActivity(intent)

            }
            R.id.gaganyatri -> {

                val intent: Intent = Intent(this, WebViewActivity::class.java)
                val webUrl: String = getString(R.string.gaganyatriUrl)
                intent.putExtra("webUrl", webUrl)

                startActivity(intent)

            }
            R.id.settings -> {

                val text = "Coming Soon"
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
/*
                val intent: Intent = Intent(this, Settings::class.java)
                startActivity(intent)

  */          }

            /*
            R.id.nav_share -> {
                val text = "Coming Soon"
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()

            }
            R.id.nav_send -> {
                val text = "Coming Soon"
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()

            }
            */
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
