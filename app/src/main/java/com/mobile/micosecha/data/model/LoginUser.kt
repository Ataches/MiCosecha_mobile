package com.mobile.micosecha.data.model

import android.util.Patterns


class LoginUser(EmailAddress: String?, Password: String?) {

    var email: String? = EmailAddress
        private set
    var password: String? = Password
        private set

    val isEmailValid: Boolean
        get() = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    val isPasswordLengthGreaterThan5: Boolean
        get() = password!!.length > 5


}