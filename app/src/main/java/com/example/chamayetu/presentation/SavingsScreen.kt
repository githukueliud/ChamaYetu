package com.example.chamayetu.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SavingsScreen(modifier: Modifier = Modifier) {
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
            text = "The future is built now!",
            color = Color(44, 198, 123),
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Contribute Ksh 500 every week. Click the button to send the money directly to the chamas bank account.",
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth(0.9f),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(44, 198, 123)
            )
        ) {
            Text(
                text = "Pay ksh. 500 for this week",
                fontSize = 21.sp
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Any unpaid week appears here",
            fontSize = 20.sp
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SavingsScreenPreview() {
    SavingsScreen()
}