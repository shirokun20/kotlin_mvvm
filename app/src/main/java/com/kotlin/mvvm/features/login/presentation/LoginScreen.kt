package com.kotlin.mvvm.features.login.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kotlin.mvvm.features.login.viewModel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController, viewModel: LoginViewModel) {
    val focusRequester = remember { FocusRequester() }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = "Kotlin MVVM",
            modifier = Modifier.padding(vertical = 10.dp).fillMaxWidth(),
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
        TextField(
            value = viewModel.email,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            onValueChange = { viewModel.onSetEmail(it)},
            label = { Text("Enter Email") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusRequester.requestFocus()
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
            )
        )
        if (viewModel.showErrorEmail) {
            errorMessage(viewModel.errorEmailMessage)
        }
        TextField(
            value = viewModel.password,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .focusRequester(focusRequester),
            onValueChange = { viewModel.onSetPassword(it)},
            label = { Text("Enter password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
            )
        )
        if (viewModel.showErrorPassword) {
            errorMessage(viewModel.errorPasswordMessage)
        }
        Button(onClick = {
            viewModel.onPressButton()
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)) {
            Text(text = "Login")
        }
    }
}

@Composable
fun errorMessage(title: String) {
    Text(
        text = title,
        color = Color.Red,
        modifier = Modifier.padding(top = 4.dp)
    )
}