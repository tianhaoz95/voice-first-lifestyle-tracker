package com.tianhaoz95.lifestyletrackervoice_first.activities.report

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.tianhaoz95.lifestyletrackervoice_first.R
import com.tianhaoz95.lifestyletrackervoice_first.databinding.ActivityShowReportBinding
import com.tianhaoz95.lifestyletrackervoice_first.services.UserDataService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ShowReportActivity : AppCompatActivity() {
    @Inject lateinit var userDataService: UserDataService
    private lateinit var binding: ActivityShowReportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowReportBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController =
            findNavController(R.id.nav_host_fragment_content_show_report)
    }

    override fun onStart() {
        super.onStart()
        userDataService.maybeNeedAuthentication(this)
    }
}