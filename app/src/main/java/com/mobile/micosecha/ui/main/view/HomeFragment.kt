package com.mobile.micosecha.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.mobile.micosecha.R
import com.mobile.micosecha.databinding.ActivityMainBinding
import com.mobile.micosecha.databinding.FragmentHomeBinding
import com.mobile.micosecha.ui.main.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.content_home.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(
            requireActivity()).get(HomeViewModel::class.java)


        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        _binding?.homeViewModel = homeViewModel

        _binding?.lifecycleOwner = this

        val root: View = binding.root

        binding.homeFragment.fab.setOnClickListener { view ->
            Snackbar.make(view, "Iniciando chat", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            view.findNavController().navigate(R.id.action_nav_home_to_chatFragment)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}