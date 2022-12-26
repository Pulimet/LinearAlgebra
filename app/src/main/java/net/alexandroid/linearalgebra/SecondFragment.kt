package net.alexandroid.linearalgebra

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import net.alexandroid.linearalgebra.databinding.FragmentSecondBinding

class SecondFragment : Fragment(R.layout.fragment_second) {
    private val binding by FragmentBinding(FragmentSecondBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_homeFragment)
        }
    }
}