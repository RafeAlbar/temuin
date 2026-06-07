package com.example.temuin

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.RowScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PopularActivityScreen(
    onBackClick: () -> Unit = {},
    onJoinActivityClick: (InvitationPlan) -> Unit = {},
    onHomeClick: () -> Unit = {},
    onFriendsClick: () -> Unit = {},
    onActivitiesClick: () -> Unit = {},
    onMessagesClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    val blue = Color(0xFF006AA6)
    val lightBlue = Color(0xFF3FA9F5)
    val bg = Color(0xFFF6F8FC)
    val textDark = Color(0xFF20232B)
    val textGray = Color(0xFF5F6673)

    Scaffold(
        containerColor = bg,
        topBar = {
            ActivityTopBar(
                blue = blue,
                textDark = textDark,
                onBackClick = onBackClick
            )
        },
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
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                FloatingActionButton(
                    onClick = {},
                    containerColor = blue,
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

                FloatingActionButton(
                    onClick = {},
                    containerColor = blue,
                    contentColor = Color.White,
                    shape = CircleShape,
                    modifier = Modifier.size(64.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Kalender",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(bg),
            contentPadding = PaddingValues(
                start = 24.dp,
                end = 24.dp,
                top = 24.dp,
                bottom = 24.dp
            )
        ) {
            item {
                Text(
                    text = "Aktivitas Populer di temu.in",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = textDark
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Temukan kegiatan seru untuk dilakukan hari ini.",
                    fontSize = 17.sp,
                    color = textGray
                )

                Spacer(modifier = Modifier.height(24.dp))

                CategoryChips()

                Spacer(modifier = Modifier.height(28.dp))

                ActivityCard(
                    category = "Ngopi",
                    categoryColor = blue,
                    title = "Kopi & Diskusi Santai",
                    location = "Kopi Kenangan, Sudirman",
                    distance = "1.2 km",
                    time = "Hari ini, 16:00 - 18:00 WIB",
                    avatarCountText = "+3",
                    blue = blue,
                    textDark = textDark,
                    textGray = textGray,
                    onJoinActivityClick = onJoinActivityClick
                )

                Spacer(modifier = Modifier.height(24.dp))

                ActivityCard(
                    category = "Belajar",
                    categoryColor = Color(0xFF1B8A4B),
                    title = "Study Session: UI/UX",
                    location = "Perpustakaan Nasional",
                    distance = "3.5 km",
                    time = "Hari ini, 19:00 - 21:00 WIB",
                    avatarCountText = "",
                    blue = blue,
                    textDark = textDark,
                    textGray = textGray,
                    onJoinActivityClick = onJoinActivityClick
                )

                Spacer(modifier = Modifier.height(24.dp))

                ActivityCard(
                    category = "Nongkrong",
                    categoryColor = Color(0xFF9A6A10),
                    title = "Main Boardgame Sore",
                    location = "Taman Suropati",
                    distance = "0.8 km",
                    time = "Hari ini, 16:30 - 18:30 WIB",
                    description = "Ada yang mau join main Catan atau Ticket to Ride sore ini? Tempatnya asik buat nongkrong.",
                    blue = blue,
                    textDark = textDark,
                    textGray = textGray,
                    onJoinActivityClick = onJoinActivityClick
                )

                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}

@Composable
fun ActivityTopBar(
    blue: Color,
    textDark: Color,
    onBackClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier.background(Color(0xFFF7F9FC))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(88.dp)
                .padding(horizontal = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = textDark,
                    modifier = Modifier.size(32.dp)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE1E7F0)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "AR",
                        color = textDark,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "Notification",
                tint = textDark,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(31.dp)
            )
        }

        HorizontalDivider(
            color = Color(0xFFE2E6ED),
            thickness = 1.dp
        )
    }
}

@Composable
fun CategoryChips() {
    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        CategoryChip(
            text = "Semua",
            selected = true
        )

        CategoryChip(
            text = "☕ Ngopi",
            selected = false
        )

        CategoryChip(
            text = "📚 Belajar Bareng",
            selected = false
        )

        CategoryChip(
            text = "🎮 Nongkrong",
            selected = false
        )
    }
}

@Composable
fun CategoryChip(
    text: String,
    selected: Boolean
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(28.dp))
            .background(
                if (selected) Color(0xFF3FA9F5) else Color(0xFFE9EDF2)
            )
            .padding(horizontal = 22.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            color = if (selected) Color(0xFF073F62) else Color(0xFF4F5662)
        )
    }
}

@Composable
fun ActivityCard(
    category: String,
    categoryColor: Color,
    title: String,
    location: String,
    distance: String,
    time: String,
    avatarCountText: String = "",
    description: String? = null,
    blue: Color,
    textDark: Color,
    textGray: Color,
    onJoinActivityClick: (InvitationPlan) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Column {
            Box {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .background(
                            Brush.linearGradient(
                                listOf(
                                    categoryColor.copy(alpha = 0.88f),
                                    Color(0xFFF7FAFF)
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        color = Color.White,
                        fontSize = 27.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)
                        .padding(horizontal = 14.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = when (category) {
                            "Ngopi" -> "☕ $category"
                            "Belajar" -> "▣ $category"
                            else -> "🎮 $category"
                        },
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = categoryColor
                    )
                }
            }

            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = textDark,
                        modifier = Modifier.weight(1f)
                    )

                    if (avatarCountText.isNotEmpty()) {
                        Text(
                            text = avatarCountText,
                            fontSize = 14.sp,
                            color = textGray,
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(Color(0xFFE8EAEE))
                                .padding(horizontal = 8.dp, vertical = 5.dp)
                        )
                    }
                }

                if (description != null) {
                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = description,
                        fontSize = 18.sp,
                        color = textGray,
                        lineHeight = 26.sp
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.LocationOn,
                        contentDescription = null,
                        tint = textGray,
                        modifier = Modifier.size(20.dp)
                    )

                    Text(
                        text = "$location  •  $distance",
                        fontSize = 17.sp,
                        color = textGray
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        onJoinActivityClick(
                            InvitationPlan(
                                title = title,
                                time = time,
                                place = location
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = blue
                    )
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Send,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(22.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "Ikut Aktivitas",
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun ActivityBottomBar(
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
        modifier = Modifier.height(92.dp)
    ) {
        ActivityBottomItem(
            title = "Beranda",
            icon = Icons.Outlined.Home,
            selected = selectedMenu == "Beranda",
            blue = blue,
            textGray = textGray,
            onClick = onHomeClick
        )

        ActivityBottomItem(
            title = "Teman",
            icon = Icons.Outlined.Person,
            selected = selectedMenu == "Teman",
            blue = blue,
            textGray = textGray,
            onClick = onFriendsClick
        )

        ActivityBottomItem(
            title = "Aktivitas",
            icon = Icons.Outlined.LocationOn,
            selected = selectedMenu == "Aktivitas",
            blue = blue,
            textGray = textGray,
            onClick = onActivitiesClick
        )

        ActivityBottomItem(
            title = "Pesan",
            icon = Icons.Outlined.Send,
            selected = selectedMenu == "Pesan",
            blue = blue,
            textGray = textGray,
            onClick = onMessagesClick
        )

        ActivityBottomItem(
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
fun RowScope.ActivityBottomItem(
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
                modifier = Modifier.size(28.dp)
            )
        },
        label = {
            Text(
                text = title,
                fontSize = 13.sp
            )
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Color.White,
            selectedTextColor = Color(0xFF0B6FAE),
            indicatorColor = blue,
            unselectedIconColor = textGray,
            unselectedTextColor = textGray
        )
    )
}
