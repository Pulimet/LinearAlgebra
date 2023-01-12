package net.alexandroid.linearalgebra

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import net.alexandroid.linearalgebra.databinding.FragmentHomeBinding

private const val TAG = "AlgebraResult"

class HomeFragment : Fragment(R.layout.fragment_home) {
    @Suppress("unused")
    private val binding by FragmentBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "1: ${Vector(-0.221, 7.437).magnitude(3)}")
        Log.d(TAG, "2: ${Vector(8.813, -1.331, -6.247).magnitude(3)}")
        Log.d(TAG, "3: ${Vector(5.581, -2.136).normalization(3)}")
        Log.d(TAG, "4: ${Vector(1.996, 3.108, -4.554).normalization(3)}")
        val angleInRadians = Vector(1, 2, -1).angle(Vector(3, 1, 0)).round(2)
        val angleInDegrees = angleInRadians.toDegrees().round(0)
        Log.d(TAG, "5: angleInRadians: $angleInRadians, angleInDegrees: $angleInDegrees")
        Log.d(TAG, "6: ${Vector(7.887, 4.138).dotProduct(Vector(-8.802, 6.776)).round(3)}")
        Log.d(TAG, "7: ${Vector(-5.955, -4.904, -1.874).dotProduct(Vector(-4.496, -8.755, 7.103).round(3)).round(3)}")
        Log.d(TAG, "8: ${Vector(3.183, -7.627).angle(Vector(-2.668, 5.319)).round(3)}")
        Log.d(TAG, "9: ${Vector(7.35, 0.221, 5.188).angle(Vector(2.751, 8.259, 3.985)).toDegrees().round(3)}")

        val vector1 = Vector(-7.579, -7.88)
        val vector2 = Vector(22.737, 23.64)
        val vector3 = Vector(-2.029, 9.97, 4.172)
        val vector4 = Vector(-9.231, 6.639, 7.245)
        val vector5 = Vector(-2.328, -7.284, -1.214)
        val vector6 = Vector(-1.821, 1.072, -2.94)
        val vector7 = Vector(2.118, 4.827)
        val vector8 = Vector(0, 0)
        Log.d(TAG, "10: isParallel?: ${vector1.isParallelTo(vector2)}")
        Log.d(TAG, "11: isOrthogonalTo?: ${vector1.isOrthogonalTo(vector2)}")
        Log.d(TAG, "12: isParallel?: ${vector3.isParallelTo(vector4)}")
        Log.d(TAG, "13: isOrthogonalTo?: ${vector3.isOrthogonalTo(vector4)}")
        Log.d(TAG, "14: isParallel?: ${vector5.isParallelTo(vector6)}")
        Log.d(TAG, "15: isOrthogonalTo?: ${vector5.isOrthogonalTo(vector6)}")
        Log.d(TAG, "16: isParallel?: ${vector7.isParallelTo(vector8)}")
        Log.d(TAG, "17: isOrthogonalTo?: ${vector7.isOrthogonalTo(vector8)}")

        Log.d(TAG, "18: ${Vector(3.039, 1.879).componentParallelTo(Vector(0.825, 2.036)).round(3)}")
        Log.d(TAG, "19: ${Vector(-9.88, -3.264, -8.159).componentOrthogonalTo(Vector(-2.155, -9.353, -9.473)).round(3)}")

        val v1 = Vector(3.009, -6.172, 3.692, -2.51)
        val v2 = Vector(6.404, -9.144, 2.759, 8.718)
        Log.d(TAG, "20: ${v1.componentParallelTo(v2).round(3)}")
        Log.d(TAG, "21: ${v1.componentOrthogonalTo(v2).round(3)}")

        val a = Vector(8.462, 7.893, -8.187)
        val b = Vector(6.984, -5.975, 4.778)
        val c = Vector(-8.987, -9.838, 5.031)
        val d = Vector(-4.268, -1.861, -8.866)
        val e = Vector(1.5, 9.547, 3.691)
        val f = Vector(-6.007, 0.124, 5.772)
        Log.d(TAG, "22: ${a.crossProduct(b).round(3)}")
        Log.d(TAG, "23: ${c.areaParallelogram(d).round(3)}")
        Log.d(TAG, "24: ${e.areaOfTriangle(f).round(3)}")
    }
}
