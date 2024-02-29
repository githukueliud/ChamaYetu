package com.example.chamayetu.presentation.home

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.chamayetu.data.local.user.User
import com.example.chamayetu.presentation.signup.SignupViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {


    val getData = viewModel.state.value



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Third Wave Chama",
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier =Modifier.height(5.dp))
        Text(
            text = "Every coin counts",
            fontSize = 18.sp,
            color = Color.Green
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Hi ${getData.username}!",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Your balance (Ksh)",
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 3.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Ksh 53,500",
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .height(60.dp)
                    .width(170.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White, containerColor = Color(44, 198, 123))
            ) {
                Text(
                    text = "Withdraw Money",
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .height(60.dp)
                    .width(140.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White, containerColor = Color(44, 198, 123))
            ) {
                Text(
                    text = "Save Money",
                    fontSize = 16.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Activities",
                fontSize = 20.sp
            )
            Text(
                text = "+ Add New",
                fontSize = 20.sp,
                color = Color.Blue
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Card(
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Projects",
                        fontSize = 24.sp
                    )
                }
            }
            Spacer(modifier = Modifier.width(20.dp))
            Card(
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Welfare",
                        fontSize = 24.sp
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Card(
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Loans",
                        fontSize = 24.sp
                    )
                }
            }
            Spacer(modifier = Modifier.width(20.dp))
            Card(
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Red
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Fines",
                        fontSize = 24.sp,
                        color = Color.White
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Transactions",
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Withdrawal")
            Text(text = "Ksh 7,000")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Deposit")
            Text(text = "Ksh 15,000")
        }
    }
}










@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}