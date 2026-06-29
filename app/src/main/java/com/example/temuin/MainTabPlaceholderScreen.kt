package com.example.temuin

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Interests
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private data class ChatPreview(
    val name: String,
    val message: String,
    val time: String,
    val initials: String,
    val online: Boolean,
    val unreadCount: Int = 0,
    val colors: List<Color>
)

private data class OnlineFriend(
    val name: String,
    val initials: String,
    val colors: List<Color>
)

@Composable
fun MessagesPlaceholderScreen(
    onHomeClick: () -> Unit = {},
    onFriendsClick: () -> Unit = {},
    onActivitiesClick: () -> Unit = {},
    onMessagesClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onChatClick: () -> Unit = {}
) {
    val bg = Color(0xFFF6F8FC)
    val blue = Color(0xFF006AA6)
    val textDark = Color(0xFF20242C)
    val textGray = Color(0xFF707987)

    val onlineFriends = listOf(
        OnlineFriend("Bayu A", "BA", listOf(Color(0xFFBDE7FF), Color(0xFF0670A8))),
        OnlineFriend("Sekar P", "SP", listOf(Color(0xFFFFD9E1), Color(0xFFD94D73))),
        OnlineFriend("Seno", "SN", listOf(Color(0xFFE7EAF1), Color(0xFF697386)))
    )

    val chats = listOf(
        ChatPreview(
            name = "Bayu A",
            message = "Otw ya! Tunggu di lobi.",
            time = "14:20",
            initials = "BA",
            online = true,
            unreadCount = 2,
            colors = listOf(Color(0xFFBDE7FF), Color(0xFF0670A8))
        ),
        ChatPreview(
            name = "Sekar P",
            message = "Sampai jam berapa event nya?",
            time = "Kemarin",
            initials = "SP",
            online = true,
            colors = listOf(Color(0xFFFFD9E1), Color(0xFFD94D73))
        ),
        ChatPreview(
            name = "Seno",
            message = "Thanks info nya bro.",
            time = "Senin",
            initials = "SN",
            online = false,
            unreadCount = 1,
            colors = listOf(Color(0xFFE7EAF1), Color(0xFF697386))
        )
    )

    Scaffold(
        containerColor = bg,
        bottomBar = {
            TemuinBottomBar(
                selectedMenu = "Pesan",
                onHomeClick = onHomeClick,
                onFriendsClick = onFriendsClick,
                onActivitiesClick = onActivitiesClick,
                onMessagesClick = onMessagesClick,
                onProfileClick = onProfileClick
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = blue,
                contentColor = Color.White,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.size(64.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "Pesan baru",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(bg)
                .statusBarsPadding(),
            contentPadding = PaddingValues(horizontal = 22.dp, vertical = 18.dp),
            verticalArrangement = Arrangement.spacedBy(22.dp)
        ) {
            item {
                MainTabHeader(
                    title = "temu.in",
                    blue = blue,
                    textDark = textDark,
                    showNotificationDot = true
                )
            }

            item {
                SearchBox(textGray = textGray)
            }

            item {
                Column {
                    Text(
                        text = "Aktif Sekarang",
                        color = textDark,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.sp
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(22.dp)) {
                        items(onlineFriends.size) { index ->
                            OnlineFriendItem(friend = onlineFriends[index])
                        }
                        item {
                            SearchFriendItem(textGray = textGray)
                        }
                    }
                }
            }

            items(chats.size) { index ->
                ChatRow(
                    chat = chats[index],
                    textDark = textDark,
                    textGray = textGray,
                    blue = blue,
                    onClick = onChatClick
                )
            }

            item {
                Spacer(modifier = Modifier.height(180.dp))
            }
        }
    }
}

@Composable
fun ProfilePlaceholderScreen(
    onHomeClick: () -> Unit = {},
    onFriendsClick: () -> Unit = {},
    onActivitiesClick: () -> Unit = {},
    onMessagesClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onProfileDetailClick: () -> Unit = {},
    onEditProfileClick: () -> Unit = {},
    onInterestsClick: () -> Unit = {},
    onActivityHistoryClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onHelpCenterClick: () -> Unit = {},
    onRatingClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {},
    onNotificationClick: () -> Unit = {}
) {
    val bg = Color(0xFFF6F8FC)
    val blue = Color(0xFF006AA6)
    val textDark = Color(0xFF20242C)
    val textGray = Color(0xFF4F5866)
    Scaffold(
        containerColor = bg,
        bottomBar = {
            TemuinBottomBar(
                selectedMenu = "Profil",
                onHomeClick = onHomeClick,
                onFriendsClick = onFriendsClick,
                onActivitiesClick = onActivitiesClick,
                onMessagesClick = onMessagesClick,
                onProfileClick = onProfileClick
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(bg)
                .statusBarsPadding(),
            contentPadding = PaddingValues(horizontal = 22.dp, vertical = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                MainTabHeader(
                    title = "temu.in",
                    blue = blue,
                    textDark = textDark,
                    showNotificationDot = false,
                    onNotificationClick = onNotificationClick
                )
            }

            item {
                Spacer(modifier = Modifier.height(60.dp))
                Box(
                    modifier = Modifier.clickable(onClick = onProfileDetailClick),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    AvatarCircle(
                        initials = "AR",
                        colors = listOf(Color(0xFFFFD9B8), Color(0xFF18202A)),
                        modifier = Modifier.size(132.dp),
                        fontSize = 34.sp
                    )
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF5DBB68))
                            .border(4.dp, bg, CircleShape)
                    )
                }
                Spacer(modifier = Modifier.height(26.dp))
                Text(
                    text = "Adrian Rafe",
                    color = textDark,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = 0.sp,
                    modifier = Modifier.clickable(onClick = onProfileDetailClick)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Product Manager | Coffee Enthusiast",
                    color = textGray,
                    fontSize = 20.sp,
                    letterSpacing = 0.sp
                )
            }

            item {
                Spacer(modifier = Modifier.height(34.dp))
                ProfileStatsCard(
                    blue = blue,
                    textGray = textGray,
                    onFriendsClick = { onFriendsClick() },
                    onActivitiesClick = { onActivitiesClick() },
                    onRatingClick = onRatingClick
                )
            }

            item {
                Spacer(modifier = Modifier.height(28.dp))
                ProfileMenuCard(
                    blue = blue,
                    textDark = textDark,
                    onEditProfileClick = onEditProfileClick,
                    onInterestsClick = onInterestsClick,
                    onActivityHistoryClick = onActivityHistoryClick,
                    onSettingsClick = onSettingsClick,
                    onHelpCenterClick = onHelpCenterClick
                )
            }

            item {
                Spacer(modifier = Modifier.height(42.dp))
                Row(
                    modifier = Modifier.clickable(onClick = onLogoutClick),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Logout,
                        contentDescription = null,
                        tint = Color(0xFFB51F25),
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Logout",
                        color = Color(0xFFB51F25),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 0.sp
                    )
                }
                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    }
}

@Composable
fun ProfileMenuDetailScreen(
    title: String,
    subtitle: String,
    items: List<String>,
    actionText: String,
    onActionClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onFriendsClick: () -> Unit = {},
    onActivitiesClick: () -> Unit = {},
    onMessagesClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {

    val bg = Color(0xFFF6F8FC)
    val blue = Color(0xFF006AA6)
    val textDark = Color(0xFF20242C)
    val textGray = Color(0xFF4F5866)

    Scaffold(
        containerColor = bg,
        bottomBar = {
            TemuinBottomBar(
                selectedMenu = "Profil",
                onHomeClick = onHomeClick,
                onFriendsClick = onFriendsClick,
                onActivitiesClick = onActivitiesClick,
                onMessagesClick = onMessagesClick,
                onProfileClick = onProfileClick
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(bg)
                .statusBarsPadding(),
            contentPadding = PaddingValues(horizontal = 22.dp, vertical = 18.dp)
        ) {
            item {
                DetailProfileHeader(
                    title = title,
                    blue = blue,
                    textDark = textDark,
                    onBackClick = onBackClick
                )
            }

            item {
                Spacer(modifier = Modifier.height(22.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(22.dp)) {
                        Text(
                            text = subtitle,
                            color = textGray,
                            fontSize = 17.sp,
                            lineHeight = 25.sp,
                            letterSpacing = 0.sp
                        )
                        Spacer(modifier = Modifier.height(22.dp))
                        items.forEachIndexed { index, item ->
                            ProfileMenuDetailRow(
                                number = index + 1,
                                text = item,
                                blue = blue,
                                textDark = textDark
                            )
                            if (index != items.lastIndex) {
                                HorizontalDivider(color = Color(0xFFE5EAF2))
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(blue)
                        .clickable {
                            onActionClick()
                        },
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = actionText,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.sp
                    )
                }
                Spacer(modifier = Modifier.height(52.dp))
            }
        }
    }
}

@Composable
private fun ProfileMenuDetailRow(
    number: Int,
    text: String,
    blue: Color,
    textDark: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(Color(0xFFEAF4FF)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = number.toString(),
                color = blue,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.sp
            )
        }
        Spacer(modifier = Modifier.width(14.dp))
        Text(
            text = text,
            color = textDark,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.sp,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun MyProfileDetailScreen(
    onBackClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onFriendsClick: () -> Unit = {},
    onActivitiesClick: () -> Unit = {},
    onMessagesClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    val bg = Color(0xFFF6F8FC)
    val blue = Color(0xFF006AA6)
    val textDark = Color(0xFF20242C)
    val textGray = Color(0xFF4F5866)

    Scaffold(
        containerColor = bg,
        bottomBar = {
            TemuinBottomBar(
                selectedMenu = "Profil",
                onHomeClick = onHomeClick,
                onFriendsClick = onFriendsClick,
                onActivitiesClick = onActivitiesClick,
                onMessagesClick = onMessagesClick,
                onProfileClick = onProfileClick
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(bg)
                .statusBarsPadding(),
            contentPadding = PaddingValues(horizontal = 22.dp, vertical = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                DetailProfileHeader(
                    title = "Profil Saya",
                    blue = blue,
                    textDark = textDark,
                    onBackClick = onBackClick
                )
            }

            item {
                Spacer(modifier = Modifier.height(26.dp))
                AvatarCircle(
                    initials = "AR",
                    colors = listOf(Color(0xFFFFD9B8), Color(0xFF18202A)),
                    modifier = Modifier.size(144.dp),
                    fontSize = 38.sp
                )
                Spacer(modifier = Modifier.height(22.dp))
                Text(
                    text = "Adrian Rafe",
                    color = textDark,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Product Manager | Coffee Enthusiast",
                    color = textGray,
                    fontSize = 18.sp,
                    letterSpacing = 0.sp
                )
            }

            item {
                Spacer(modifier = Modifier.height(28.dp))
                ProfileInfoCard(blue = blue, textDark = textDark, textGray = textGray)
            }

            item {
                Spacer(modifier = Modifier.height(22.dp))
                ProfileAboutCard(textDark = textDark, textGray = textGray)
                Spacer(modifier = Modifier.height(52.dp))
            }
        }
    }
}

@Composable
private fun DetailProfileHeader(
    title: String,
    blue: Color,
    textDark: Color,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.ArrowBack,
            contentDescription = "Kembali",
            tint = textDark,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(34.dp)
                .clickable(onClick = onBackClick)
        )
        Text(
            text = title,
            color = blue,
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 0.sp
        )
    }
}

@Composable
private fun ProfileInfoCard(
    blue: Color,
    textDark: Color,
    textGray: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(vertical = 8.dp)) {
            ProfileInfoRow(Icons.Outlined.Email, "Email", "adrian.rafe@email.com", blue, textDark, textGray)
            HorizontalDivider(color = Color(0xFFE5EAF2))
            ProfileInfoRow(Icons.Outlined.LocationOn, "Lokasi", "Jakarta Selatan", blue, textDark, textGray)
            HorizontalDivider(color = Color(0xFFE5EAF2))
            ProfileInfoRow(Icons.Outlined.Badge, "Status", "Online dan siap bertemu", blue, textDark, textGray)
            HorizontalDivider(color = Color(0xFFE5EAF2))
            ProfileInfoRow(Icons.Outlined.CalendarMonth, "Bergabung", "Juni 2026", blue, textDark, textGray)
        }
    }
}

@Composable
private fun ProfileInfoRow(
    icon: ImageVector,
    label: String,
    value: String,
    blue: Color,
    textDark: Color,
    textGray: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 22.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(46.dp)
                .clip(CircleShape)
                .background(Color(0xFFEAF4FF)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = blue,
                modifier = Modifier.size(26.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                color = textGray,
                fontSize = 14.sp,
                letterSpacing = 0.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                color = textDark,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 0.sp
            )
        }
    }
}

@Composable
private fun ProfileAboutCard(
    textDark: Color,
    textGray: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(22.dp)) {
            Text(
                text = "Tentang Saya",
                color = textDark,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Suka ngobrol soal produk, kopi, dan komunitas kreatif. Terbuka untuk bertemu teman baru lewat aktivitas santai di sekitar Jakarta.",
                color = textGray,
                fontSize = 17.sp,
                lineHeight = 25.sp,
                letterSpacing = 0.sp
            )
        }
    }
}

@Composable
private fun MainTabHeader(
    title: String,
    blue: Color,
    textDark: Color,
    showNotificationDot: Boolean,
    onNotificationClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Menu",
            tint = textDark,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(34.dp)
        )
        Text(
            text = title,
            color = blue,
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 0.sp
        )
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(40.dp)
                .clickable(onClick = onNotificationClick),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "Notifikasi",
                tint = textDark,
                modifier = Modifier.size(32.dp)
            )
            if (showNotificationDot) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 4.dp, end = 4.dp)
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFBE1E2D))
                )
            }
        }
    }
}

@Composable
private fun SearchBox(textGray: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFEFF3F9))
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.Search,
            contentDescription = null,
            tint = textGray,
            modifier = Modifier.size(34.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "Cari pesan atau teman...",
            color = textGray,
            fontSize = 19.sp,
            letterSpacing = 0.sp
        )
    }
}

@Composable
private fun OnlineFriendItem(friend: OnlineFriend) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(contentAlignment = Alignment.BottomEnd) {
            AvatarCircle(
                initials = friend.initials,
                colors = friend.colors,
                modifier = Modifier.size(62.dp),
                fontSize = 18.sp
            )
            OnlineDot()
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = friend.name,
            color = Color(0xFF424B58),
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.sp
        )
    }
}

@Composable
private fun SearchFriendItem(textGray: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(62.dp)
                .clip(CircleShape)
                .background(Color(0xFFE1E6EF)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = "Cari",
                tint = textGray,
                modifier = Modifier.size(34.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Cari",
            color = Color(0xFF424B58),
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.sp
        )
    }
}

@Composable
private fun ChatRow(
    chat: ChatPreview,
    textDark: Color,
    textGray: Color,
    blue: Color,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(contentAlignment = Alignment.BottomEnd) {
            AvatarCircle(
                initials = chat.initials,
                colors = chat.colors,
                modifier = Modifier.size(64.dp),
                fontSize = 19.sp
            )
            if (chat.online) {
                OnlineDot()
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = chat.name,
                color = textDark,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = chat.message,
                color = if (chat.unreadCount > 0) textDark else textGray,
                fontSize = 16.sp,
                letterSpacing = 0.sp
            )
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = chat.time,
                color = if (chat.unreadCount > 0) blue else textGray,
                fontSize = 14.sp,
                letterSpacing = 0.sp
            )
            if (chat.unreadCount > 0) {
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(blue),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = chat.unreadCount.toString(),
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun AvatarCircle(
    initials: String,
    colors: List<Color>,
    modifier: Modifier,
    fontSize: androidx.compose.ui.unit.TextUnit
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(Brush.linearGradient(colors))
            .border(3.dp, Color.White, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initials,
            color = Color.White,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.sp
        )
    }
}

@Composable
private fun OnlineDot() {
    Box(
        modifier = Modifier
            .size(16.dp)
            .clip(CircleShape)
            .background(Color(0xFF0B8B3E))
            .border(3.dp, Color(0xFFF6F8FC), CircleShape)
    )
}

@Composable
private fun ProfileStatsCard(
    blue: Color,
    textGray: Color,
    onFriendsClick: () -> Unit = {},
    onActivitiesClick: () -> Unit = {},
    onRatingClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfileStat("128", "Friends", blue, textGray, Modifier.weight(1f), onFriendsClick)
            StatDivider()
            ProfileStat("42", "Activities", blue, textGray, Modifier.weight(1f), onActivitiesClick)
            StatDivider()
            ProfileStat("4.9 ?", "Rating", Color(0xFFE29A00), textGray, Modifier.weight(1f), onRatingClick)
        }
    }
}

@Composable
private fun ProfileStat(
    value: String,
    label: String,
    valueColor: Color,
    labelColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier.clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            color = valueColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            color = labelColor,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.sp
        )
    }
}

@Composable
private fun StatDivider() {
    Box(
        modifier = Modifier
            .width(1.dp)
            .height(48.dp)
            .background(Color(0xFFE0E5EE))
    )
}

@Composable
private fun ProfileMenuCard(
    blue: Color,
    textDark: Color,
    onEditProfileClick: () -> Unit = {},
    onInterestsClick: () -> Unit = {},
    onActivityHistoryClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onHelpCenterClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            ProfileMenuItem("Edit Profil", Icons.Outlined.Edit, blue, textDark, onClick = onEditProfileClick)
            HorizontalDivider(color = Color(0xFFE5EAF2))
            ProfileMenuItem("Minat & Hobi", Icons.Outlined.Interests, blue, textDark, onClick = onInterestsClick)
            HorizontalDivider(color = Color(0xFFE5EAF2))
            ProfileMenuItem("Riwayat Aktivitas", Icons.Outlined.History, blue, textDark, onClick = onActivityHistoryClick)
            HorizontalDivider(color = Color(0xFFE5EAF2))
            ProfileMenuItem("Pengaturan", Icons.Outlined.Settings, blue, textDark, onClick = onSettingsClick)
            HorizontalDivider(color = Color(0xFFE5EAF2))
            ProfileMenuItem("Pusat Bantuan", Icons.Outlined.HelpOutline, blue, textDark, onClick = onHelpCenterClick)
        }
    }
}

@Composable
private fun ProfileMenuItem(
    title: String,
    icon: ImageVector,
    blue: Color,
    textDark: Color,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(82.dp)
            .clickable(onClick = onClick)
            .padding(horizontal = 28.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color(0xFFEAF4FF)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = blue,
                modifier = Modifier.size(28.dp)
            )
        }
        Spacer(modifier = Modifier.width(18.dp))
        Text(
            text = title,
            color = textDark,
            fontSize = 20.sp,
            letterSpacing = 0.sp,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = Color(0xFF414A56),
            modifier = Modifier.size(30.dp)
        )
    }
}

@Composable
fun ProfilRatingScreen(
    onBackClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onFriendsClick: () -> Unit = {},
    onActivitiesClick: () -> Unit = {},
    onMessagesClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    val bg = Color(0xFFF6F8FC)
    val blue = Color(0xFF006AA6)
    val textDark = Color(0xFF20242C)
    val textGray = Color(0xFF4F5866)
    Scaffold(
        containerColor = bg,
        bottomBar = {
            TemuinBottomBar(
                selectedMenu = "Profil",
                onHomeClick = onHomeClick,
                onFriendsClick = onFriendsClick,
                onActivitiesClick = onActivitiesClick,
                onMessagesClick = onMessagesClick,
                onProfileClick = onProfileClick
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(paddingValues).background(bg).statusBarsPadding(),
            contentPadding = PaddingValues(horizontal = 22.dp, vertical = 18.dp)
        ) {
            item {
                DetailProfileHeader(title = "Rating Saya", blue = blue, textDark = textDark, onBackClick = onBackClick)
            }
            item {
                Spacer(Modifier.height(26.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(Modifier.fillMaxWidth().padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("4,9", color = Color(0xFFE29A00), fontSize = 56.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.sp)
                        Spacer(Modifier.height(6.dp))
                        Text("Rating Sempurna!", color = textGray, fontSize = 20.sp, letterSpacing = 0.sp)
                        Spacer(Modifier.height(20.dp))
                        Text("Dari 24 ulasan Ã¢â‚¬â€ kamu dinilai ramah, aktif, dan seru diajak ngobrol.", color = textGray, fontSize = 16.sp, lineHeight = 24.sp, letterSpacing = 0.sp)
                    }
                }
                Spacer(Modifier.height(22.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(Modifier.padding(22.dp)) {
                        Text("Ulasan Terbaru", color = textDark, fontSize = 18.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.sp)
                        Spacer(Modifier.height(16.dp))
                        Text("\"Seru banget ngobrol sama Adrian, recommended!\" Ã¢â‚¬â€ Bayu", color = textGray, fontSize = 16.sp, lineHeight = 24.sp, letterSpacing = 0.sp)
                        HorizontalDivider(Modifier.padding(vertical = 12.dp), color = Color(0xFFE5EAF2))
                        Text("\"Ramah dan aktif, cocok buat diskusi santai.\" Ã¢â‚¬â€ Sekar", color = textGray, fontSize = 16.sp, lineHeight = 24.sp, letterSpacing = 0.sp)
                        HorizontalDivider(Modifier.padding(vertical = 12.dp), color = Color(0xFFE5EAF2))
                        Text("\"Kopinya enak, obrolannya lebih enak lagi Ã°Å¸Ëœâ€ž\" Ã¢â‚¬â€ Seno", color = textGray, fontSize = 16.sp, lineHeight = 24.sp, letterSpacing = 0.sp)
                    }
                }
                Spacer(Modifier.height(52.dp))
            }
        }
    }
}

@Composable
fun LogoutConfirmScreen(
    onBackClick: () -> Unit = {},
    onLogoutConfirm: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onFriendsClick: () -> Unit = {},
    onActivitiesClick: () -> Unit = {},
    onMessagesClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    val bg = Color(0xFFF6F8FC)
    val blue = Color(0xFF006AA6)
    val textDark = Color(0xFF20242C)
    val textGray = Color(0xFF4F5866)
    Scaffold(
        containerColor = bg,
        bottomBar = {
            TemuinBottomBar(
                selectedMenu = "Profil",
                onHomeClick = onHomeClick,
                onFriendsClick = onFriendsClick,
                onActivitiesClick = onActivitiesClick,
                onMessagesClick = onMessagesClick,
                onProfileClick = onProfileClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).background(bg).statusBarsPadding().padding(horizontal = 22.dp, vertical = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DetailProfileHeader(title = "Keluar", blue = blue, textDark = textDark, onBackClick = onBackClick)
            Spacer(Modifier.height(80.dp))
            Box(Modifier.size(96.dp).clip(CircleShape).background(Color(0xFFFFE5E5)), contentAlignment = Alignment.Center) {
                Icon(Icons.Outlined.Logout, contentDescription = null, tint = Color(0xFFB51F25), modifier = Modifier.size(50.dp))
            }
            Spacer(Modifier.height(32.dp))
            Text("Yakin ingin keluar?", color = textDark, fontSize = 24.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.sp)
            Spacer(Modifier.height(12.dp))
            Text("Kamu akan kembali ke layar masuk dan perlu login ulang.", color = textGray, fontSize = 17.sp, lineHeight = 25.sp, letterSpacing = 0.sp)
            Spacer(Modifier.height(44.dp))
            Row(
                modifier = Modifier.fillMaxWidth().height(56.dp).clip(RoundedCornerShape(14.dp)).background(Color(0xFFB51F25)).clickable(onClick = onLogoutConfirm),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Outlined.Logout, contentDescription = null, tint = Color.White, modifier = Modifier.size(26.dp))
                Spacer(Modifier.width(10.dp))
                Text("Ya, Keluar", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.sp)
            }
            Spacer(Modifier.height(18.dp))
            Text("Batal", color = blue, fontSize = 18.sp, fontWeight = FontWeight.SemiBold, letterSpacing = 0.sp, modifier = Modifier.clickable(onClick = onBackClick))
        }
    }
}

@Composable
fun NotificationsScreen(
    onBackClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onFriendsClick: () -> Unit = {},
    onActivitiesClick: () -> Unit = {},
    onMessagesClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    val bg = Color(0xFFF6F8FC)
    val blue = Color(0xFF006AA6)
    val textDark = Color(0xFF20242C)
    val textGray = Color(0xFF4F5866)
    val notifications = listOf(
        "Bayu A mengirim ajakan" to "Kopi & Diskusi Santai Ã¢â‚¬Â¢ 2 jam lalu",
        "Aktivitas baru di dekatmu" to "Workshop UI/UX Ã¢â‚¬Â¢ Sabtu, 16:00",
        "Sekar P menerima ajakanmu" to "Jogging Bareng Ã¢â‚¬Â¢ Kemarin",
        "Rating baru untukmu" to "Ã¢Â­Â 5.0 dari Seno Ã¢â‚¬Â¢ 2 hari lalu"
    )
    Scaffold(
        containerColor = bg,
        bottomBar = {
            TemuinBottomBar(
                selectedMenu = "Profil",
                onHomeClick = onHomeClick,
                onFriendsClick = onFriendsClick,
                onActivitiesClick = onActivitiesClick,
                onMessagesClick = onMessagesClick,
                onProfileClick = onProfileClick
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(paddingValues).background(bg).statusBarsPadding(),
            contentPadding = PaddingValues(horizontal = 22.dp, vertical = 18.dp)
        ) {
            item {
                DetailProfileHeader(title = "Notifikasi", blue = blue, textDark = textDark, onBackClick = onBackClick)
            }
            item {
                Spacer(Modifier.height(16.dp))
                notifications.forEachIndexed { i, (title, desc) ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                    ) {
                        Row(Modifier.padding(horizontal = 20.dp, vertical = 18.dp), verticalAlignment = Alignment.CenterVertically) {
                            Box(Modifier.size(44.dp).clip(CircleShape).background(Color(0xFFEAF4FF)), contentAlignment = Alignment.Center) {
                                Icon(Icons.Outlined.Notifications, contentDescription = null, tint = blue, modifier = Modifier.size(26.dp))
                            }
                            Spacer(Modifier.width(14.dp))
                            Column(Modifier.weight(1f)) {
                                Text(title, color = textDark, fontSize = 17.sp, fontWeight = FontWeight.SemiBold, letterSpacing = 0.sp)
                                Spacer(Modifier.height(4.dp))
                                Text(desc, color = textGray, fontSize = 14.sp, letterSpacing = 0.sp)
                            }
                        }
                    }
                    if (i != notifications.lastIndex) { Spacer(Modifier.height(8.dp)) }
                }
                Spacer(Modifier.height(52.dp))
            }
        }
    }
}
