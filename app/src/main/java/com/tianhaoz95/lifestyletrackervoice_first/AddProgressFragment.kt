package com.tianhaoz95.lifestyletrackervoice_first

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.tianhaoz95.lifestyletrackervoice_first.databinding.FragmentAddProgressBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AddProgressFragment : Fragment() {

    private var _binding: FragmentAddProgressBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddProgressBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        var createItemName = activity?.intent?.extras?.getString("name")
        binding.textviewFirst.text = createItemName
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}