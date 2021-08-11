package com.tianhaoz95.lifestyletrackervoice_first.activities.record

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tianhaoz95.lifestyletrackervoice_first.databinding.ActivityAddBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}