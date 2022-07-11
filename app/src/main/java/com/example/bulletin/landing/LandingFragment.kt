package com.example.bulletin.landing

import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bulletin.R
import com.example.bulletin.databinding.FragmentLandingBinding


/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class LandingFragment : Fragment() {

    private var _binding: FragmentLandingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLandingBinding.inflate(inflater, container, false)
        binding.staticBtn.setOnClickListener {
            //Toast.makeText(getActivity(),"Static Button", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_homeFragment_to_staticFragment)
        }

        binding.dynamicBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_dynamicFragment)
        }

        val linkTextView = binding.sourceCodeLink as TextView
        linkTextView.movementMethod = LinkMovementMethod.getInstance();
        linkTextView.setLinkTextColor(Color.BLUE)
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}