package com.mobile.micosecha.ui.main.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobile.micosecha.data.api.VariablesData

class HomeViewModel : ViewModel() {
    var varietiesList = MutableLiveData<String>()
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

    fun onClick(view: View?) {
        print(view?.id)
    }
}