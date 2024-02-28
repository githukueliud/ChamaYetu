package com.example.chamayetu.presentation.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.chamayetu.R
import com.example.chamayetu.presentation.navigation.Destinations
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest {event ->
            when(event) {
                is LoginUIEvents.ShowSnackBar -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(event.message)
                    }
                }
                LoginUIEvents.NavigateToHome -> {
                    navController.navigate(Destinations.AppNavigation.route) {
                        popUpTo(Destinations.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                }
                LoginUIEvents.NavigateToSignup -> {
                    navController.navigate(Destinations.SignupScreen.route) {
                        popUpTo(Destinations.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }

    LoginScreenComponent(
        state = state,
        onEvent = viewModel::onEvent,
        modifier = Modifier
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenComponent(
    modifier: Modifier = Modifier,
    state: LoginState,
    onEvent: (LoginEvents) -> Unit
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Third Wave Chama",
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Every coin matters",
            fontSize = 20.sp,
            color = Color.Green
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Login To Your Account",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = state.email,
            onValueChange = { onEvent(LoginEvents.OnPasswordChanged(it)) },
            leadingIcon = { Icon(
                imageVector = Icons.Outlined.Email,
                contentDescription = null
            ) },
            label = { Text(text = "Email or Phone Number")},
            modifier = Modifier.fillMaxWidth(0.9f)
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = state.password,
            onValueChange = { onEvent(LoginEvents.OnPasswordChanged(it)) },
            leadingIcon = { Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = null
            ) },
            label = { Text(text = "Password")},
            modifier = Modifier.fillMaxWidth(0.9f)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Or",
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Continue with",
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.height(65.dp),
                border = BorderStroke(1.dp, Color.LightGray),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icons8_facebook_48),
                    contentDescription = "Facebook signup",
                    Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "Facebook")
            }
            Spacer(modifier = Modifier.width(30.dp))
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.height(65.dp),
                border = BorderStroke(1.dp, Color.LightGray),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icons8_google_48),
                    contentDescription = "Google signup",
                    Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "Google")
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { onEvent(LoginEvents.OnLoginClicked) },
            modifier = Modifier
                .height(80.dp)
                .width(250.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White, containerColor = Color(44, 198, 123))
        ) {
            Text(
                text = "Login",
                fontSize = 22.sp
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row{
            Text(text = "Don't have an account?")
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Sign up",
                modifier = Modifier.clickable { onEvent(LoginEvents.OnSignupClicked) },
                color = Color.Blue
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row{
            Text(
                text = "Terms",
                color = Color.Blue
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "and")
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "conditions",
                color = Color.Blue
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "apply")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreenComponent(state = LoginState(), onEvent = {})
}