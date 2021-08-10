package com.tianhaoz95.lifestyletrackervoice_first.activities.report

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.tianhaoz95.lifestyletrackervoice_first.R
import com.tianhaoz95.lifestyletrackervoice_first.databinding.ActivityShowReportBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowReportActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityShowReportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShowReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController =
            findNavController(R.id.nav_host_fragment_content_show_report)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController =
            findNavController(R.id.nav_host_fragment_content_show_report)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}