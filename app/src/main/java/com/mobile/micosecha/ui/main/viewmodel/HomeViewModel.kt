package com.mobile.micosecha.ui.main.viewmodel

import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.mobile.micosecha.R

class HomeViewModel : ViewModel() {

    fun onClick(view: View?, variety: String) {
        val bundle = bundleOf("variety" to variety)
        when (view?.id){
            R.id.F733Button -> view.findNavController().navigate(R.id.action_nav_home_to_nav_bright, bundle)
            R.id.lagunasButton -> view.findNavController().navigate(R.id.action_nav_home_to_nav_bright, bundle)
            R.id.F60Button -> view.findNavController().navigate(R.id.action_nav_home_to_nav_bright, bundle)
            R.id.F50Button -> view.findNavController().navigate(R.id.action_nav_home_to_nav_bright, bundle)
            R.id.F473Button -> view.findNavController().navigate(R.id.action_nav_home_to_nav_bright, bundle)
            R.id.cimarronButton -> view.findNavController().navigate(R.id.action_nav_home_to_nav_bright, bundle)
            R.id.F200Button -> view.findNavController().navigate(R.id.action_nav_home_to_nav_bright, bundle)
            R.id.onlyRiceButton -> view.findNavController().navigate(R.id.action_nav_home_to_nav_bright, bundle)
            R.id.colombiaButton -> view.findNavController().navigate(R.id.action_nav_home_to_nav_bright, bundle)
        }
    }
}