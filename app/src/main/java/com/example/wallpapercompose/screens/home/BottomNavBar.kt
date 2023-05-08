package com.example.wallpapercompose.screens.home

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun BottomNavigationApp(onChangePage: (BottomNavItem) -> Unit, bottomCurrentPage: BottomNavItem) {
      NavigationBar(
        contentColor = Color.Black,
        modifier = Modifier.height(75.dp)
    ) {

        BottomNavItem.values().map {item->
            NavigationBarItem(
                icon = { Icon(item.icon(), contentDescription = item.name) },
                label = { Text(text = item.name,
                    fontSize = 9.sp) },
                alwaysShowLabel = true,
                selected = bottomCurrentPage == item,
                onClick = {
                    onChangePage(item)
                }
            )
        }
    }

}


enum class BottomNavItem{
    Overview,
    Favorite,
    Profile;

    fun icon() : ImageVector {
       return when (this){
            Overview -> Icons.Rounded.Home
            Favorite -> Icons.Rounded.Favorite
            Profile -> Icons.Rounded.Person
        }
    }





}