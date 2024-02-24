package com.example.chamayetu.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NewsScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Third Wave Chama News",
            fontSize = 28.sp
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
                    .padding(10.dp)
            ) {
                Text(
                    text = "Title: Sample Title Mate",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "This is a sample description. This is a sample description. This is a sample description"
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Card(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(0.9f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                Text(
                    text = "Title: Sample 1 Title Mate",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "This is a sample description. This is a sample description. This is a sample description"
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Card(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(0.9f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                Text(
                    text = "Title: Sample 2 Title Mate",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "This is a sample description. This is a sample description. This is a sample description"
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Card(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(0.9f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                Text(
                    text = "Title: Sample 4 Title Mate",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "This is a sample description. This is a sample description. This is a sample description"
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NewsScreenPreview() {
    NewsScreen()
}