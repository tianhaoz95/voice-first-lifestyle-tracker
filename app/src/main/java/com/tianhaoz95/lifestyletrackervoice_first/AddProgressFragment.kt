package com.tianhaoz95.lifestyletrackervoice_first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.tianhaoz95.lifestyletrackervoice_first.databinding.FragmentAddProgressBinding
import com.tianhaoz95.lifestyletrackervoice_first.matcher.DescriptionToCategoryConverter
import com.tianhaoz95.lifestyletrackervoice_first.matcher.IntakeItemCategory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AddProgressFragment : Fragment() {

    private var _binding: FragmentAddProgressBinding? = null
    private var _converter: DescriptionToCategoryConverter =
        DescriptionToCategoryConverter()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentAddProgressBinding.inflate(
                inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var createItemName = activity?.intent?.extras?.getString("name")
        val category: IntakeItemCategory = _converter.convert(createItemName)
        binding.textviewAddItemTitle.text = createItemName
        binding.textviewAddItemDescription.text = category.toString()

        viewLifecycleOwner.lifecycleScope.launch {
            delay(3000)
            findNavController().navigate(
                R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}