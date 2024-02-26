package com.example.chamayetu.presentation.bottomBar.bottomBarComponent

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chamayetu.R
import com.example.chamayetu.ui.theme.ChamaYetuTheme


@Composable
fun BottomNavigation(
    items: List<BottomNavigationItem>,
    selected: Int,
    onItemClicked: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, item -> 
            NavigationBarItem(
                selected = index == selected,
                onClick = { onItemClicked(index) },
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = item.text,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = colorResource(id = R.color.black),
                    unselectedTextColor = colorResource(id = R.color.black),
                    indicatorColor = MaterialTheme.colorScheme.background
                )
            )
        }
    }
}



data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    val text: String
)


@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview() {
    ChamaYetuTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            BottomNavigation(
                items = listOf(
                    BottomNavigationItem(icon = R.drawable.ic_home, "Home"),
                    BottomNavigationItem(icon = R.drawable.ic_home, "Home"),
                    BottomNavigationItem(icon = R.drawable.ic_home, "Home"),
                    BottomNavigationItem(icon = R.drawable.ic_home, "Home")
                ),
                selected = 1,
                onItemClicked = {}
            )
        }
    }
}