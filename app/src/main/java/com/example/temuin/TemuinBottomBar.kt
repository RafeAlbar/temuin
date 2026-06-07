package com.example.temuin

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val TemuinNavBlue = Color(0xFF42A5F5)
private val TemuinNavSelectedContent = Color(0xFF134D72)
private val TemuinNavUnselectedContent = Color(0xFF5F6673)

@Composable
fun TemuinBottomBar(
    selectedMenu: String,
    modifier: Modifier = Modifier,
    onHomeClick: () -> Unit = {},
    onFriendsClick: () -> Unit = {},
    onActivitiesClick: () -> Unit = {},
    onMessagesClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp,
        modifier = modifier
            .navigationBarsPadding()
            .height(92.dp)
    ) {
        TemuinBottomItem(
            title = "Beranda",
            icon = Icons.Outlined.Home,
            selected = selectedMenu == "Beranda",
            onClick = onHomeClick
        )
        TemuinBottomItem(
            title = "Teman",
            icon = Icons.Default.Groups,
            selected = selectedMenu == "Teman",
            onClick = onFriendsClick
        )
        TemuinBottomItem(
            title = "Aktivitas",
            icon = Icons.Outlined.Explore,
            selected = selectedMenu == "Aktivitas",
            onClick = onActivitiesClick
        )
        TemuinBottomItem(
            title = "Pesan",
            icon = Icons.Outlined.Email,
            selected = selectedMenu == "Pesan",
            onClick = onMessagesClick
        )
        TemuinBottomItem(
            title = "Profil",
            icon = Icons.Outlined.Person,
            selected = selectedMenu == "Profil",
            onClick = onProfileClick
        )
    }
}

@Composable
private fun RowScope.TemuinBottomItem(
    title: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(28.dp)
            )
        },
        label = {
            Text(
                text = title,
                fontSize = 13.sp,
                fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
                letterSpacing = 0.sp
            )
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = TemuinNavSelectedContent,
            selectedTextColor = TemuinNavSelectedContent,
            indicatorColor = TemuinNavBlue,
            unselectedIconColor = TemuinNavUnselectedContent,
            unselectedTextColor = TemuinNavUnselectedContent
        )
    )
}
