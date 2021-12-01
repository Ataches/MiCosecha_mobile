package com.mobile.micosecha.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mobile.micosecha.databinding.FragmentGalleryBinding
import com.mobile.micosecha.ui.main.viewmodel.GalleryViewModel

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel
    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        private val lineSet = linkedMapOf(
            "JAN" to 110F,
            "FEB" to 2F,
            "MAR" to 5F,
            "MAY" to 2F,
            "APR" to 35F,
            "JUN" to 45F,
            "JUL" to 55F,
            "" to 10F
        )
        private const val animationDuration = 1000L
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGallery
        galleryViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        binding.lineChart.animation.duration = animationDuration
        binding.lineChart.animate(lineSet)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}