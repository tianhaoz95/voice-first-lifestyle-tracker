package com.tianhaoz95.lifestyletrackervoice_first.activities.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tianhaoz95.lifestyletrackervoice_first.R
import com.tianhaoz95.lifestyletrackervoice_first.activities.authentication.AuthenticationActivity
import com.tianhaoz95.lifestyletrackervoice_first.activities.report.ShowReportActivity
import com.tianhaoz95.lifestyletrackervoice_first.databinding.FragmentHomeBinding
import com.tianhaoz95.lifestyletrackervoice_first.services.UserDataService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    @Inject lateinit var userDataService: UserDataService

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(
            inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSignOut.setOnClickListener {
            context?.let { it1 -> userDataService.signOut(it1) }
        }

        binding.reportBtn.setOnClickListener {
            context?.startActivity(
                Intent(context, ShowReportActivity::class.java))
        }

        binding.buttonSettings.setOnClickListener {
            findNavController().navigate(
                R.id.action_HomeFragment_to_SettingsFragment)
        }

        binding.buttonAdd.setOnClickListener {
            findNavController().navigate(
                R.id.action_HomeFragment_to_SelectFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}