package com.tianhaoz95.lifestyletrackervoice_first.activities.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tianhaoz95.lifestyletrackervoice_first.activities.record.AddActivity
import com.tianhaoz95.lifestyletrackervoice_first.databinding.FragmentSelectBinding

class SelectFragment : Fragment() {
    private var _binding: FragmentSelectBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSelectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonConfirmAdd.setOnClickListener {
            val intent = Intent(context, AddActivity::class.java)
            intent.putExtra("name", "coffee")
            context?.startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}