package com.testapp.mckinleytest.presentation.register

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.testapp.mckinleytest.common.UiEvents
import com.testapp.mckinleytest.destinations.LoginScreenDestination
import com.testapp.mckinleytest.presentation.AuthViewModel
import com.testapp.mckinleytest.ui.theme.PurpleBg
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.testapp.mckinleytest.ui.theme.Teal200
import kotlinx.coroutines.flow.collectLatest

@Destination
@Composable
fun RegisterScreen(
    navigator: DestinationsNavigator,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val emailState = viewModel.emailState.value
    val passwordState = viewModel.passwordState.value
    val firstNameState = viewModel.firstName.value
    val lastNameState = viewModel.lastName.value
    val state = viewModel.loginState.value
    val scaffoldState = rememberScaffoldState()
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvents.SnackbarEvent -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        duration = SnackbarDuration.Short
                    )
                }
                is UiEvents.NavigateEvent -> {
                    navigator.navigate(event.route)
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "Register Successful",
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
            Spacer(modifier = Modifier.height(64.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Welcome!",
                fontSize = 26.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Register an account with Us",
                fontSize = 19.sp,
                color = Color.Black,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = firstNameState.text,
                onValueChange = {
                    viewModel.setFirstName(it)
                },
                placeholder = {
                    Text(text = "First name")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                ),
                isError = emailState.error != null
            )
            if (emailState.error != "") {
                Text(
                    text = emailState.error ?: "",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = lastNameState.text,
                onValueChange = {
                    viewModel.setLastName(it)
                },
                placeholder = {
                    Text(text = "Last name")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                ),
                isError = emailState.error != null
            )
            if (emailState.error != "") {
                Text(
                    text = emailState.error ?: "",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = emailState.text,
                onValueChange = {
                    viewModel.setEmail(it)
                },
                placeholder = {
                    Text(text = "Enter Email")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                ),
                isError = emailState.error != null
            )
            if (emailState.error != "") {
                Text(
                    text = emailState.error ?: "",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = passwordState.text,
                onValueChange = {
                    viewModel.setPassword(it)
                },
                label = { Text("Password") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                isError = passwordState.error != null,
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff
                    // Localized description for accessibility services
                    val description = if (passwordVisible) "Hide password" else "Show password"

                    // Toggle button to hide or display password
                    IconButton(onClick = {passwordVisible = !passwordVisible}){
                        Icon(imageVector  = image, description)
                    }
                }
            )

            if (passwordState.error != "") {
                Text(
                    text = passwordState.error ?: "",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
            }


            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    viewModel.registerUser()
                },
                shape = RoundedCornerShape(16),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Teal200,
                    contentColor = Color.White
                )
            ) {

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = "Register",
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp
                )

            }
            TextButton(
                onClick = {
                    navigator.popBackStack()
                    navigator.navigate(LoginScreenDestination)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(color = Color.Black)
                        ) {
                            append("Already have an account?")
                        }
                        append(" ")
                        withStyle(
                            style = SpanStyle(color = Teal200, fontWeight = FontWeight.Bold)
                        ) {
                            append("Login")
                        }
                    },
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}