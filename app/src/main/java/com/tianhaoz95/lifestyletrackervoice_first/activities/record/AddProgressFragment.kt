package com.tianhaoz95.lifestyletrackervoice_first.activities.record

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tianhaoz95.lifestyletrackervoice_first.R
import com.tianhaoz95.lifestyletrackervoice_first.databinding.FragmentAddProgressBinding
import com.tianhaoz95.lifestyletrackervoice_first.services.UserDataService
import com.tianhaoz95.lifestyletrackervoice_first.types.HydrationRecord
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AddProgressFragment : Fragment() {
    @Inject
    lateinit var userDataService: UserDataService
    private val db: FirebaseFirestore = Firebase.firestore
    private val user: FirebaseUser? = Firebase.auth.currentUser
    private var _binding: FragmentAddProgressBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentAddProgressBinding.inflate(
                inflater, container, false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            context?.let {
                userDataService.addHydrationRecord(
                    it,
                    HydrationRecord.fromExtras(activity?.intent?.extras),
                    onError = { code, msg ->

                    },
                    onSuccess = {}
                )
            }
            delay(3000)
            findNavController().navigate(
                R.id.action_ReportProgress_to_ReportResult
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}