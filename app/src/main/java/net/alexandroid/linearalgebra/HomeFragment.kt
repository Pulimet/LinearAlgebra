package net.alexandroid.linearalgebra

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import net.alexandroid.linearalgebra.databinding.FragmentHomeBinding

private const val TAG = "AlgebraResult"

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by FragmentBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "1: ${Vector(-0.221, 7.437).magnitude(3)}")
        Log.d(TAG, "2: ${Vector(8.813, -1.331, -6.247).magnitude(3)}")
        Log.d(TAG, "3: ${Vector(5.581, -2.136).normalization(3)}")
        Log.d(TAG, "4: ${Vector(1.996, 3.108, -4.554).normalization(3)}")
        val angleInRadians = Vector(1, 2, -1).angle(Vector(3, 1, 0)).round(2)
        val angleInDegrees = Math.toDegrees(angleInRadians).round(0)
        Log.d(TAG, "5: angleInRadians: $angleInRadians, angleInDegrees: $angleInDegrees")
    }
}

