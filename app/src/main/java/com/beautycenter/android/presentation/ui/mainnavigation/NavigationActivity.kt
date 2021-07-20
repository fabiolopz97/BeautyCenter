package com.beautycenter.android.presentation.ui.mainnavigation

import android.os.Bundle
import com.beautycenter.android.R
import com.beautycenter.android.presentation.adapters.SectionsPagerAdapter
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_navigation.*


class NavigationActivity : AppCompatActivity() {
    //var click = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        showActionBar()
        configurationTab()

        /**
         * Only test
         */
        /*val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
              //  .setAction("Action", null).show()
            click = !click
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                val interpolador = AnimationUtils.loadInterpolator(
                    baseContext,
                    android.R.interpolator.fast_out_slow_in
                )
                view.animate()
                    .rotation(if (click) 360f else 0f)
                    .setInterpolator(interpolador)
                    .start()
            }
        }*/
    }

    private fun showActionBar() {
        setSupportActionBar(toolbar as Toolbar)
        supportActionBar?.title = getString(R.string.title_action_bar)
    }

    private fun configurationTab(){
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = tabs
        tabs.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}