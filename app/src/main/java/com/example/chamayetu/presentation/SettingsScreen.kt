package com.example.chamayetu.presentation

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Account Settings",
            fontSize = 28.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = "",
            leadingIcon = { Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = ""
            ) },
            label = { Text(text = "Edit Name") },
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = "",
            leadingIcon = { Icon(
                imageVector = Icons.Outlined.Email,
                contentDescription = ""
            ) },
            label = { Text(text = "Edit Email") },
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = "",
            leadingIcon = { Icon(
                imageVector = Icons.Outlined.Phone,
                contentDescription = ""
            ) },
            label = { Text(text = "Edit Phone Number") },
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "To change your password, input the old password first",
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = "",
            leadingIcon = { Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = ""
            ) },
            label = { Text(text = "Enter old password") },
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = "",
            leadingIcon = { Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = ""
            ) },
            label = { Text(text = "Enter new password") },
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .height(60.dp)
                    .width(150.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                )
            ) {
                Text(
                    text = "Edit Profile",
                    fontSize = 18.sp
                )
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .height(60.dp)
                    .width(150.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(44, 198, 123)
                )
            ) {
                Text(
                    text = "Cancel Edit",
                    fontSize = 18.sp
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}