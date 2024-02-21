package com.example.chamayetu.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chamayetu.R

@Composable
fun WelfareScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Third Wave Welfare",
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Your friend in deed",
            fontSize = 25.sp,
            color = Color(44, 198, 123)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .height(100.dp)
                .width(260.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(44, 198, 123)
            )
        ) {
            Text(
                text = "Pay Ksh 300",
                fontSize = 28.sp
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Each month paid will get a check, failure leads to a cross.",
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Card(
            modifier = Modifier
                .height(280.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                Text(
                    text = "Year: 2024",
                    fontSize = 30.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Row {
                        Text(
                            text = "January",
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Image(
                            painter = painterResource(id = R.drawable.icons8_check_48),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                    Row {
                        Text(
                            text = "February",
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Image(
                            painter = painterResource(id = R.drawable.icons8_check_48),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Row {
                        Text(
                            text = "March",
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Image(
                            painter = painterResource(id = R.drawable.icons8_check_48),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                    Row {
                        Text(
                            text = "April",
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Image(
                            painter = painterResource(id = R.drawable.icons8_check_48),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Row {
                        Text(
                            text = "May",
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Image(
                            painter = painterResource(id = R.drawable.icons8_check_48),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                    Row {
                        Text(
                            text = "June",
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Image(
                            painter = painterResource(id = R.drawable.icons8_check_48),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Row {
                        Text(
                            text = "July",
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Image(
                            painter = painterResource(id = R.drawable.icons8_check_48),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                    Row {
                        Text(
                            text = "August",
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Image(
                            painter = painterResource(id = R.drawable.icons8_check_48),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Row {
                        Text(
                            text = "September",
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Image(
                            painter = painterResource(id = R.drawable.icons8_check_48),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                    Row {
                        Text(
                            text = "October",
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Image(
                            painter = painterResource(id = R.drawable.icons8_check_48),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Row {
                        Text(
                            text = "November",
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Image(
                            painter = painterResource(id = R.drawable.icons8_check_48),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                    Row {
                        Text(
                            text = "December",
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Image(
                            painter = painterResource(id = R.drawable.icons8_check_48),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Our welfare deeds:",
            fontSize = 28.sp
        )
        Card(
            modifier = Modifier.height(100.dp).fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(10.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = "Deed title")
                Text(text = "Deed description Deed description v Deed description v v Deed descriptionDeed descriptionDeed description")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun WelfareScreenPreview() {
    WelfareScreen()
}