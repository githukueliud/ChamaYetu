package com.example.chamayetu.presentation.signup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.chamayetu.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    signupViewModel: SignupViewModel = hiltViewModel(),
    onEvent: (SignupEvents) -> Unit
) {
    //val state = signupViewModel.state.
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Third Wave Chama",
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier =Modifier.height(20.dp))
        Text(
            text = "Every coin counts",
            fontSize = 22.sp,
            color = Color.Green
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Sign up here",
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = state.username,
            onValueChange = { onEvent(SignupEvents.OnUsernameChanged(it)) },
            label = { Text(text = "Name")},
            modifier = Modifier.fillMaxWidth(0.9f)
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            leadingIcon = { Icon(
                imageVector = Icons.Outlined.Email,
                contentDescription = null
            ) },
            label = { Text(text = "Email or Phone Number")},
            modifier = Modifier.fillMaxWidth(0.9f)
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            leadingIcon = { Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = null
            ) },
            label = { Text(text = "Password")},
            modifier = Modifier.fillMaxWidth(0.9f)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "or",
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Sign up with",
            fontSize = 25.sp
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
            onClick = { /*TODO*/ },
            modifier = Modifier
                .height(80.dp)
                .width(250.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White, containerColor = Color(44, 198, 123))
        ) {
            Text(
                text = "Create Account",
                fontSize = 22.sp
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row{
            Text(text = "Already have an account?")
            Spacer(modifier = Modifier.width(10.dp))
            ClickableText(
                text = AnnotatedString("Login", spanStyle = SpanStyle(color = Color.Blue, fontSize = 14.sp)),

                onClick = {}
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
fun SignupScreenPreview() {
    SignupScreen(
        state = SignupState(),
        onEvent = {}
    )
}