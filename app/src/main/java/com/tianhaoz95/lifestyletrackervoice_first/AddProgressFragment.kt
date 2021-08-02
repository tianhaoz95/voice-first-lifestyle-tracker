package com.tianhaoz95.lifestyletrackervoice_first

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tianhaoz95.lifestyletrackervoice_first.databinding.FragmentAddProgressBinding
import com.tianhaoz95.lifestyletrackervoice_first.matcher.DescriptionToCategoryConverter
import com.tianhaoz95.lifestyletrackervoice_first.matcher.IntakeItemCategory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AddProgressFragment : Fragment() {

    private var _binding: FragmentAddProgressBinding? = null
    private var _converter: DescriptionToCategoryConverter =
        DescriptionToCategoryConverter()
    private val db: FirebaseFirestore = Firebase.firestore
    private val user: FirebaseUser? = Firebase.auth.currentUser

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

        if (user == null) {
            val intent = Intent(context, MainActivity::class.java)
            context?.startActivity(intent)
        }

        var createItemName = activity?.intent?.extras?.getString("name")
        val category: IntakeItemCategory = _converter.convert(createItemName)
        binding.textviewAddItemTitle.text = category.toString()
        binding.textviewAddItemDescription.text = createItemName

        viewLifecycleOwner.lifecycleScope.launch {
            var uid: String = "not_initialized"
            if (user == null) {
                val intent = Intent(context, MainActivity::class.java)
                context?.startActivity(intent)
            } else {
                uid = user?.uid
            }
            val currentTime: Date = java.util.Calendar.getInstance().time
            val sdf = SimpleDateFormat(
                "yyyy-MM-dd", Locale.ENGLISH)
            val strDate = sdf.format(currentTime)
            binding.textviewAddItemDescription.text = "user_data/$uid"

//            val dataRef = db
//                .collection("user_data/$uid")
//                .document(strDate)
//            dataRef
//                .update(category.toString(), FieldValue.increment(1))
//                .addOnSuccessListener {
//                    binding.textviewAddItemDescription.text = "DocumentSnapshot successfully written!" }
//                .addOnFailureListener {
//                    binding.textviewAddItemDescription.text = "Error writing document" }

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