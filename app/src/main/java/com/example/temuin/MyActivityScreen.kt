package com.example.temuin

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class MyActivityItem(
    val title: String,
    val status: String,
    val statusType: String,
    val date: String,
    val location: String,
    val icon: ImageVector,
    val iconColor: Color,
    val friendsText: String? = null
)

@Composable
fun MyActivitiesScreen(
    onHomeClick: () -> Unit = {},
    onFriendsClick: () -> Unit = {},
    onActivitiesClick: () -> Unit = {},
    onMessagesClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onAddActivityClick: () -> Unit = {}
) {
    val bg = Color(0xFFF6F8FC)
    val blue = Color(0xFF006AA6)
    val lightBlue = Color(0xFF42A5F5)
    val textDark = Color(0xFF20232B)
    val textGray = Color(0xFF5F6673)

    var selectedTab by remember { mutableStateOf("Berjalan") }

    val activities = listOf(
        MyActivityItem(
            title = "Lari Sore di GBK",
            status = "Confirmed",
            statusType = "confirmed",
            date = "Hari ini, 16:00 WIB",
            location = "Gelora Bung Karno, Pintu 5",
            icon = Icons.Default.DirectionsRun,
            iconColor = lightBlue,
            friendsText = "+3 Teman"
        ),
        MyActivityItem(
            title = "Ngopi & Diskusi Tech",
            status = "Pending Approval",
            statusType = "pending",
            date = "Besok, 19:00 WIB",
            location = "Filosofi Kopi, Melawai",
            icon = Icons.Default.Storefront,
            iconColor = Color(0xFF9A6A10)
        )
    )

    Scaffold(
        containerColor = bg,
        bottomBar = {
            TemuinBottomBar(
                selectedMenu = "Aktivitas",
                onHomeClick = onHomeClick,
                onFriendsClick = onFriendsClick,
                onActivitiesClick = onActivitiesClick,
                onMessagesClick = onMessagesClick,
                onProfileClick = onProfileClick
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddActivityClick,
                containerColor = lightBlue,
                contentColor = Color.White,
                shape = CircleShape,
                modifier = Modifier.size(64.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah",
                    modifier = Modifier.size(34.dp)
                )
            }
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(bg),
            contentPadding = PaddingValues(
                start = 20.dp,
                top = 20.dp,
                end = 20.dp,
                bottom = 112.dp
            ),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            item {
                MyActivityHeader(blue = blue)
            }

            item {
                Column {
                    Text(
                        text = "Aktivitas Saya",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = textDark,
                        letterSpacing = 0.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Pantau aktivitas yang sedang berjalan dan riwayat pertemuanmu.",
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        color = textGray,
                        letterSpacing = 0.sp
                    )
                }
            }

            item {
                ActivityTabSelector(
                    selectedTab = selectedTab,
                    onTabChange = { selectedTab = it },
                    blue = blue,
                    textGray = textGray
                )
            }

            items(activities.size) { index ->
                MyActivityCard(
                    item = activities[index],
                    textDark = textDark,
                    textGray = textGray
                )
            }
        }
    }
}

@Composable
fun MyActivityHeader(
    blue: Color
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Menu",
            tint = blue,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(30.dp)
        )

        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(48.dp)
                .clip(CircleShape)
                .background(Color(0xFFE3E9F2))
                .border(
                    width = 4.dp,
                    color = Color(0xFFD6DBE4),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "AR",
                color = blue,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ActivityTabSelector(
    selectedTab: String,
    onTabChange: (String) -> Unit,
    blue: Color,
    textGray: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(Color(0xFFF0F3F8))
            .padding(5.dp)
    ) {
        TabButton(
            text = "Berjalan",
            selected = selectedTab == "Berjalan",
            onClick = { onTabChange("Berjalan") },
            blue = blue,
            textGray = textGray,
            modifier = Modifier.weight(1f)
        )

        TabButton(
            text = "Riwayat",
            selected = selectedTab == "Riwayat",
            onClick = { onTabChange("Riwayat") },
            blue = blue,
            textGray = textGray,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun TabButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    blue: Color,
    textGray: Color,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxHeight(),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) Color.White else Color.Transparent,
            contentColor = if (selected) blue else textGray
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = if (selected) 1.dp else 0.dp
        )
    ) {
        Text(
            text = text,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun MyActivityCard(
    item: MyActivityItem,
    textDark: Color,
    textGray: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(78.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFF0F7FD)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null,
                        tint = item.iconColor,
                        modifier = Modifier.size(32.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = item.title,
                        fontSize = 20.sp,
                        lineHeight = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = textDark,
                        letterSpacing = 0.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    StatusBadge(
                        text = item.status,
                        type = item.statusType
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = null,
                    tint = textGray,
                    modifier = Modifier.size(22.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = item.date,
                    fontSize = 15.sp,
                    color = textGray,
                    letterSpacing = 0.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = null,
                    tint = textGray,
                    modifier = Modifier.size(22.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = item.location,
                    fontSize = 15.sp,
                    color = textGray,
                    letterSpacing = 0.sp
                )
            }

            if (item.friendsText != null) {
                Spacer(modifier = Modifier.height(18.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OverlapFriendAvatars()

                    Spacer(modifier = Modifier.width(14.dp))

                    Text(
                        text = item.friendsText,
                        fontSize = 14.sp,
                        color = Color(0xFF7A828F),
                        letterSpacing = 0.sp
                    )
                }
            }
        }
    }
}

@Composable
fun StatusBadge(
    text: String,
    type: String
) {
    val bg = if (type == "confirmed") Color(0xFFE5F6E9) else Color(0xFFE8EAEE)
    val textColor = if (type == "confirmed") Color(0xFF187A35) else Color(0xFF4F5662)

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .background(bg)
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            color = textColor
        )
    }
}

@Composable
fun OverlapFriendAvatars() {
    Box(
        modifier = Modifier.width(110.dp)
    ) {
        FriendInitialAvatar(
            text = "B",
            modifier = Modifier
                .align(Alignment.CenterStart)
        )

        FriendInitialAvatar(
            text = "S",
            modifier = Modifier
                .padding(start = 24.dp)
                .align(Alignment.CenterStart)
        )

        FriendInitialAvatar(
            text = "R",
            modifier = Modifier
                .padding(start = 48.dp)
                .align(Alignment.CenterStart)
        )
    }
}

@Composable
fun MyActivityBottomBar(
    selectedMenu: String,
    blue: Color,
    textGray: Color,
    onHomeClick: () -> Unit = {},
    onFriendsClick: () -> Unit = {},
    onActivitiesClick: () -> Unit = {},
    onMessagesClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp,
        modifier = Modifier.height(96.dp)
    ) {
        MyActivityBottomItem(
            title = "Beranda",
            icon = Icons.Outlined.Home,
            selected = selectedMenu == "Beranda",
            blue = blue,
            textGray = textGray,
            onClick = onHomeClick
        )

        MyActivityBottomItem(
            title = "Teman",
            icon = Icons.Default.Groups,
            selected = selectedMenu == "Teman",
            blue = blue,
            textGray = textGray,
            onClick = onFriendsClick
        )

        MyActivityBottomItem(
            title = "Aktivitas",
            icon = Icons.Outlined.Explore,
            selected = selectedMenu == "Aktivitas",
            blue = blue,
            textGray = textGray,
            onClick = onActivitiesClick
        )

        MyActivityBottomItem(
            title = "Pesan",
            icon = Icons.Outlined.Email,
            selected = selectedMenu == "Pesan",
            blue = blue,
            textGray = textGray,
            onClick = onMessagesClick
        )

        MyActivityBottomItem(
            title = "Profil",
            icon = Icons.Outlined.Person,
            selected = selectedMenu == "Profil",
            blue = blue,
            textGray = textGray,
            onClick = onProfileClick
        )
    }
}

@Composable
fun RowScope.MyActivityBottomItem(
    title: String,
    icon: ImageVector,
    selected: Boolean,
    blue: Color,
    textGray: Color,
    onClick: () -> Unit = {}
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(30.dp)
            )
        },
        label = {
            Text(
                text = title,
                fontSize = 16.sp
            )
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Color.White,
            selectedTextColor = Color.White,
            indicatorColor = blue,
            unselectedIconColor = textGray,
            unselectedTextColor = textGray
        )
    )
}

@Composable
private fun FriendInitialAvatar(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(38.dp)
            .clip(CircleShape)
            .background(Color(0xFFE3E9F2))
            .border(2.dp, Color.White, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color(0xFF006AA6),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
