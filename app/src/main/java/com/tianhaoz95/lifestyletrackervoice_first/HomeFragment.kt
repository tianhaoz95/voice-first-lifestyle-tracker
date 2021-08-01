package com.tianhaoz95.lifestyletrackervoice_first

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tianhaoz95.lifestyletrackervoice_first.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            Firebase.auth.signOut()
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.buttonSettings.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_settingsFragment)
        }

        binding.buttonAdd.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_selectFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}