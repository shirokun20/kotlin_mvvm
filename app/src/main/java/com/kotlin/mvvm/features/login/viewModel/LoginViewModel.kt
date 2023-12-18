package com.kotlin.mvvm.features.login.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.kotlin.mvvm.components.utils.LocalStorageUtils

class LoginViewModel(private val ls: LocalStorageUtils, private val navHostController: NavHostController): ViewModel() {
    //
    var password by mutableStateOf("")
    var email by mutableStateOf("")
    //
    var showErrorPassword by mutableStateOf(false)
    var showErrorEmail by mutableStateOf(false)
    //
    var errorPasswordMessage = ""
    var errorEmailMessage = ""
    //
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
    //

    fun onSetPassword(text: String) {
        password = text
    }

    fun onSetEmail(text: String) {
        email = text
    }

    fun onPressButton() {
        showErrorPassword = false
        showErrorEmail = false

        errorEmailMessage = ""
        errorPasswordMessage = ""
        //
        if (password.isEmpty()) {
            showErrorPassword = true
            errorPasswordMessage = "Password cannot be empty"
        }
        if (email.isEmpty()) {
            showErrorEmail = true
            errorEmailMessage = "Email cannot be empty"
        } else if (!isValidEmail(email)) {
            showErrorEmail = true
            errorEmailMessage = "Email format was wrong"
        }

        if (!showErrorPassword && !showErrorEmail) {
            ls.isLoggedIn = true
            navHostController.navigate("movies") {
                popUpTo(0)
            }
        }
    }

    fun isValidEmail(email: String): Boolean {
        return email.matches(emailRegex.toRegex())
    }
}
