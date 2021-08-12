package com.tianhaoz95.lifestyletrackervoice_first.activities.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.tianhaoz95.lifestyletrackervoice_first.R
import com.tianhaoz95.lifestyletrackervoice_first.databinding.FragmentReportProgressBinding
import com.tianhaoz95.lifestyletrackervoice_first.services.UserDataService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ReportProgressFragment : Fragment() {
    @Inject
    lateinit var userDataService: UserDataService
    private var _binding: FragmentReportProgressBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentReportProgressBinding.inflate(
                inflater, container, false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(
                R.id.action_ReportProgress_to_ReportResult
            )
        }
        viewLifecycleOwner.lifecycleScope.launch {
            context?.let {
                userDataService.getCurrentDayHydration(it,
                    onError = { code, msg ->
                        binding.fetchReportStatus.text = msg
                    },
                    onSuccess = {
                        binding.fetchReportStatus.text = userDataService.recordCount.toString()
                        findNavController().navigate(
                            R.id.action_ReportProgress_to_ReportResult
                        )
                    })
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}