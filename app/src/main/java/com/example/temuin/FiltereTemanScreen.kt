package com.example.temuin

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FriendFilterScreen(
    onInviteClick: (RecommendationProfile) -> Unit = {},
    onHomeClick: () -> Unit = {},
    onFriendsClick: () -> Unit = {},
    onActivitiesClick: () -> Unit = {},
    onMessagesClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    val blue = Color(0xFF0669A8)
    val lightBlue = Color(0xFF42A5F5)
    val bg = Color(0xFFF6F8FC)
    val textDark = Color(0xFF1D1F27)
    val textGray = Color(0xFF626A76)

    var radius by remember { mutableStateOf(15f) }
    var selectedActivity by remember { mutableStateOf("Nongkrong Santai") }

    Scaffold(
        bottomBar = {
            TemuinBottomBar(
                selectedMenu = "Teman",
                onHomeClick = onHomeClick,
                onFriendsClick = onFriendsClick,
                onActivitiesClick = onActivitiesClick,
                onMessagesClick = onMessagesClick,
                onProfileClick = onProfileClick
            )
        },
        containerColor = bg
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(bg),
            contentPadding = PaddingValues(horizontal = 28.dp, vertical = 24.dp)
        ) {
            item {
                TopBar(
                    blue = blue,
                    textDark = textDark
                )

                Spacer(modifier = Modifier.height(24.dp))

                FilterCard(
                    radius = radius,
                    onRadiusChange = { radius = it },
                    selectedActivity = selectedActivity,
                    onActivityChange = { selectedActivity = it },
                    blue = blue,
                    lightBlue = lightBlue,
                    textDark = textDark,
                    textGray = textGray
                )

                Spacer(modifier = Modifier.height(34.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Hasil Pencarian",
                        fontSize = 27.sp,
                        fontWeight = FontWeight.Bold,
                        color = textDark,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = "3 Ditemukan",
                        fontSize = 17.sp,
                        color = textGray
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                SearchResultCard(
                    name = "Adrian Rafe",
                    info = "2 km - Aktif 5 mnt lalu",
                    tags = listOf("UI/UX", "KOPI"),
                    isOnline = true,
                    blue = blue,
                    textDark = textDark,
                    textGray = textGray,
                    onInviteClick = {
                        onInviteClick(
                            RecommendationProfile(
                                name = "Adrian Rafe",
                                job = "Web Developer",
                                distance = "2 km",
                                match = "95% Cocok",
                                tags = listOf("UI/UX", "Kopi", "Museum"),
                                avatarText = "AR",
                                online = true
                            )
                        )
                    }
                )

                Spacer(modifier = Modifier.height(18.dp))

                SearchResultCard(
                    name = "Bayu A.",
                    info = "5 km - Online",
                    tags = listOf("FOTOGRAFI", "KOPI"),
                    isOnline = false,
                    blue = blue,
                    textDark = textDark,
                    textGray = textGray,
                    onInviteClick = {
                        onInviteClick(
                            RecommendationProfile(
                                name = "Bayu A.",
                                job = "UI/UX Designer",
                                distance = "5 km",
                                match = "88% Cocok",
                                tags = listOf("Fotografi", "Kopi", "Bersepeda"),
                                avatarText = "BA",
                                online = false
                            )
                        )
                    }
                )

                Spacer(modifier = Modifier.height(18.dp))

                SearchResultCard(
                    name = "Sekar P",
                    info = "1 km - Online",
                    tags = listOf("CODING", "GAMING"),
                    isOnline = true,
                    blue = blue,
                    textDark = textDark,
                    textGray = textGray,
                    onInviteClick = {
                        onInviteClick(
                            RecommendationProfile(
                                name = "Sekar P",
                                job = "Software Engineer",
                                distance = "1 km",
                                match = "75% Cocok",
                                tags = listOf("Coding", "Gaming", "Anime"),
                                avatarText = "SP",
                                online = true
                            )
                        )
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun TopBar(
    blue: Color,
    textDark: Color
) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = textDark,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(32.dp)
            )

            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "Notification",
                tint = textDark,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(31.dp)
            )
        }

        Text(
            text = "Filter Teman",
            color = textDark,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(14.dp))

        HorizontalDivider(
            color = Color(0xFFE2E6ED),
            thickness = 1.dp
        )
    }
}

@Composable
fun FilterCard(
    radius: Float,
    onRadiusChange: (Float) -> Unit,
    selectedActivity: String,
    onActivityChange: (String) -> Unit,
    blue: Color,
    lightBlue: Color,
    textDark: Color,
    textGray: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier.padding(26.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    tint = blue,
                    modifier = Modifier.size(31.dp)
                )

                Spacer(modifier = Modifier.width(14.dp))

                Text(
                    text = "Filter Spesifik",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = textDark
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Radius Jarak",
                    fontSize = 18.sp,
                    color = textGray,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = "${radius.toInt()} km",
                    fontSize = 18.sp,
                    color = blue
                )
            }

            Slider(
                value = radius,
                onValueChange = onRadiusChange,
                valueRange = 1f..50f,
                colors = SliderDefaults.colors(
                    thumbColor = blue,
                    activeTrackColor = Color(0xFFDCE2EA),
                    inactiveTrackColor = Color(0xFFDCE2EA)
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "1 m",
                    fontSize = 16.sp,
                    color = textGray,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = "50 km",
                    fontSize = 16.sp,
                    color = textGray
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Kategori Minat",
                fontSize = 18.sp,
                color = textGray
            )

            Spacer(modifier = Modifier.height(14.dp))

            InterestChips(
                lightBlue = lightBlue,
                blue = blue
            )

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Tipe Aktivitas",
                fontSize = 18.sp,
                color = textGray
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                ActivityOption(
                    title = "Nongkrong\nSantai",
                    selected = selectedActivity == "Nongkrong Santai",
                    onClick = { onActivityChange("Nongkrong Santai") },
                    blue = blue,
                    modifier = Modifier.weight(1f)
                )

                ActivityOption(
                    title = "Belajar Bareng",
                    selected = selectedActivity == "Belajar Bareng",
                    onClick = { onActivityChange("Belajar Bareng") },
                    blue = blue,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(22.dp))

            Button(
                onClick = {
                    // aksi terapkan filter
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
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "Terapkan Filter",
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun InterestChips(
    lightBlue: Color,
    blue: Color
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            FilterChipItem("Olahraga", true, lightBlue, blue)
            FilterChipItem("Musik", false, lightBlue, blue)
            FilterChipItem("Kopi", true, lightBlue, blue)
            FilterChipItem("Tech / Coding", false, lightBlue, blue)
            FilterChipItem("Fotografi", false, lightBlue, blue)
        }
    }
}

@Composable
fun FilterChipItem(
    text: String,
    selected: Boolean,
    lightBlue: Color,
    blue: Color
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(28.dp))
            .background(if (selected) lightBlue else Color(0xFFF8FAFD))
            .border(
                width = 1.dp,
                color = if (selected) lightBlue else Color(0xFFE1E6EE),
                shape = RoundedCornerShape(28.dp)
            )
            .padding(horizontal = 18.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            color = if (selected) Color(0xFF174E73) else Color(0xFF535B67)
        )
    }
}

@Composable
fun ActivityOption(
    title: String,
    selected: Boolean,
    onClick: () -> Unit,
    blue: Color,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(76.dp),
        shape = RoundedCornerShape(8.dp),
        border = ButtonDefaults.outlinedButtonBorder.copy(
            width = 1.dp
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (selected) Color(0xFFF1F8FD) else Color.White,
            contentColor = Color(0xFF333740)
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (selected) Icons.Default.Check else Icons.Default.Info,
                contentDescription = null,
                tint = if (selected) blue else Color(0xFF7A818C),
                modifier = Modifier.size(28.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = title,
                fontSize = 15.sp,
                lineHeight = 20.sp
            )
        }
    }
}

@Composable
fun SearchResultCard(
    name: String,
    info: String,
    tags: List<String>,
    isOnline: Boolean,
    blue: Color,
    textDark: Color,
    textGray: Color,
    onInviteClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box {
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFE3E9F2)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = name.split(" ").mapNotNull { it.firstOrNull()?.toString() }.take(2).joinToString(""),
                            color = blue,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    if (isOnline) {
                        Box(
                            modifier = Modifier
                                .size(14.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF129B31))
                                .align(Alignment.BottomEnd)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(18.dp))

                Column {
                    Text(
                        text = name,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold,
                        color = textDark
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.LocationOn,
                            contentDescription = null,
                            tint = textGray,
                            modifier = Modifier.size(18.dp)
                        )

                        Text(
                            text = info,
                            fontSize = 16.sp,
                            color = textGray
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                tags.forEachIndexed { index, tag ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(
                                if (index == 0) Color(0xFFEAF4FF) else Color(0xFFE8EAEE)
                            )
                            .padding(horizontal = 14.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = tag,
                            fontSize = 14.sp,
                            color = if (index == 0) blue else Color(0xFF5D6570),
                            letterSpacing = 1.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(22.dp))

            OutlinedButton(
                onClick = onInviteClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(8.dp),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    width = 1.3.dp
                ),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = blue
                )
            ) {
                Text(
                    text = "Kirim Ajakan",
                    fontSize = 17.sp
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
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
        BottomNavItem(
            title = "Beranda",
            icon = Icons.Outlined.Home,
            selected = selectedMenu == "Beranda",
            blue = blue,
            textGray = textGray,
            onClick = onHomeClick
        )

        BottomNavItem(
            title = "Teman",
            icon = Icons.Default.Person,
            selected = selectedMenu == "Teman",
            blue = blue,
            textGray = textGray,
            onClick = onFriendsClick
        )

        BottomNavItem(
            title = "Aktivitas",
            icon = Icons.Outlined.Home,
            selected = selectedMenu == "Aktivitas",
            blue = blue,
            textGray = textGray,
            onClick = onActivitiesClick
        )

        BottomNavItem(
            title = "Pesan",
            icon = Icons.Outlined.Email,
            selected = selectedMenu == "Pesan",
            blue = blue,
            textGray = textGray,
            onClick = onMessagesClick
        )

        BottomNavItem(
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
fun RowScope.BottomNavItem(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
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
            selectedIconColor = Color(0xFF134D72),
            selectedTextColor = Color(0xFF134D72),
            indicatorColor = blue,
            unselectedIconColor = textGray,
            unselectedTextColor = textGray
        )
    )
}


