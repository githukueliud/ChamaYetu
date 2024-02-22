package com.example.chamayetu.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoansScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Third Wave Chama",
            fontSize = 32.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "There when in need",
            fontSize = 20.sp,
            color = Color(44, 198, 123)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Loan interest is 10%",
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            leadingIcon = {
                Text(
                    text = "$",
                    fontSize = 24.sp
                )
            },
            label = { Text(text = "Enter amount") }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth(0.8f),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(44, 198, 123)
            )
        ) {
            Text(
                text = "Get a loan",
                fontSize = 24.sp
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Loan history",
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Card(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(0.9f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "Date: 12/2/2024  Amount: Ksh 20,000",
                    fontSize = 16.sp
                )
                Text(
                    text = "Due: 20/2/2024  Amount: Ksh 22,000",
                    fontSize = 16.sp
                )
                Text(
                    text = "Paid: 18/2/2024  Amount: Ksh 22,000",
                    fontSize = 16.sp
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoansScreenPreview() {
    LoansScreen()
}