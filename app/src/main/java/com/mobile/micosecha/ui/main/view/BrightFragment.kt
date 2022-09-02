package com.mobile.micosecha.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mobile.micosecha.databinding.FragmentBrightBinding

class BrightFragment : Fragment() {

    private var _binding: FragmentBrightBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        private val lineSetFirst = linkedMapOf(
            "ENE" to 5.05F,
            "FEB" to 4.8F,
            "MAR" to 4.7F
        )
        private val lineSetSecond = linkedMapOf(
            "MAY" to 3.2F,
            "ABR" to 4.5F,
            "JUN" to 5.9F
        )
        private val lineSetThird = linkedMapOf(
            "JUL" to 5.5F,
            "AGO" to 5.4F,
            "SEPT" to 5.3F
        )
        private val lineSetFourth = linkedMapOf(
            "OCT" to 4.5F,
            "NOV" to 4.7F,
            "DIC" to 3.3F
        )
        private const val animationDuration = 1000L
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBrightBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.lineChartFirst.animation.duration = animationDuration
        binding.lineChartSecond.animation.duration = animationDuration
        binding.lineChartThird.animation.duration = animationDuration

        binding.lineChartFirst.animate(lineSetFirst)
        binding.lineChartSecond.animate(lineSetSecond)
        binding.lineChartThird.animate(lineSetThird)
        binding.lineChartFourth.animate(lineSetFourth)

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}