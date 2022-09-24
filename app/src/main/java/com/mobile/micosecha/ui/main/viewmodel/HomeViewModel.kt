package com.mobile.micosecha.ui.main.viewmodel

import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.mobile.micosecha.R
import com.mobile.micosecha.data.api.VariablesData

class HomeViewModel : ViewModel() {
    private var userMutableLiveData: MutableLiveData<VariablesData>? = null
    val user: MutableLiveData<VariablesData>
        get() {
            if (userMutableLiveData == null) {
                userMutableLiveData = MutableLiveData()
            }
            return userMutableLiveData as MutableLiveData<VariablesData>
        }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

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