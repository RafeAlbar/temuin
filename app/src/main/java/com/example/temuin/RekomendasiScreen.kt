package com.example.temuin

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class RecommendationProfile(
    val name: String,
    val job: String,
    val distance: String,
    val match: String,
    val tags: List<String>,
    val avatarText: String,
    val online: Boolean
)

@Composable
fun RekomendasiScreen(
    modifier: Modifier = Modifier,
    onHomeClick: () -> Unit = {},
    onFriendsClick: () -> Unit = {},
    onActivitiesClick: () -> Unit = {},
    onMessagesClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onDetailClick: (RecommendationProfile) -> Unit = {}
) {
    val friends = listOf(
        RecommendationProfile(
            name = "Adrian Rafe",
            job = "Web Developer",
            distance = "2 km",
            match = "95% Cocok",
            tags = listOf("UI/UX", "Kopi", "Museum"),
            avatarText = "AR",
            online = true
        ),
        RecommendationProfile(
            name = "Bayu A.",
            job = "UI/UX Designer",
            distance = "5 km",
            match = "88% Cocok",
            tags = listOf("Fotografi", "Kopi", "Bersepeda"),
            avatarText = "BA",
            online = false
        ),
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

    Scaffold(
        modifier = modifier.fillMaxSize(),
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
        containerColor = RecommendationBackground
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(RecommendationBackground)
                .statusBarsPadding(),
            contentPadding = PaddingValues(
                start = 20.dp,
                end = 20.dp,
                top = 24.dp,
                bottom = 24.dp
            ),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            item {
                RecommendationHeader()
            }

            item {
                Text(
                    text = "Rekomendasi Untukmu",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = RecommendationTextDark,
                    letterSpacing = 0.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Temukan teman baru dengan minat yang sama.",
                    fontSize = 15.sp,
                    color = RecommendationTextGray,
                    letterSpacing = 0.sp
                )
            }

            item {
                RecommendationFilterChips()
            }

            items(friends.size) { index ->
                FriendCard(
                    friend = friends[index],
                    onDetailClick = { onDetailClick(friends[index]) }
                )
            }
        }
    }
}

@Composable
private fun RecommendationHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color(0xFFDDE3EA)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "A",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = RecommendationTextDark,
                letterSpacing = 0.sp
            )
        }

        Text(
            text = "!",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = RecommendationBlue,
            letterSpacing = 0.sp
        )
    }
}

@Composable
private fun RecommendationFilterChips() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        FilterChipItem(text = "Semua", selected = true)
        FilterChipItem(text = "Mahasiswa", selected = false)
        FilterChipItem(text = "Pekerja", selected = false)
    }
}

@Composable
private fun FilterChipItem(
    text: String,
    selected: Boolean
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(28.dp))
            .background(if (selected) RecommendationBlue else Color.Transparent)
            .border(
                width = 1.dp,
                color = if (selected) RecommendationBlue else Color(0xFFD8DEE8),
                shape = RoundedCornerShape(28.dp)
            )
            .padding(horizontal = 22.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (selected) Color.White else RecommendationTextGray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.sp
        )
    }
}

@Composable
private fun FriendCard(
    friend: RecommendationProfile,
    onDetailClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Avatar(friend)

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = friend.name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = RecommendationTextDark,
                        letterSpacing = 0.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "${friend.job} - ${friend.distance}",
                        fontSize = 15.sp,
                        color = RecommendationTextGray,
                        letterSpacing = 0.sp
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(RecommendationLightBlue)
                        .padding(horizontal = 12.dp, vertical = 7.dp)
                ) {
                    Text(
                        text = friend.match,
                        color = Color(0xFF3B9BEF),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 0.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                friend.tags.forEachIndexed { index, tag ->
                    InterestChip(text = tag, active = index < 2)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = onDetailClick,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = RecommendationBlue),
                    border = BorderStroke(1.5.dp, RecommendationBlue)
                ) {
                    Text(
                        text = "Lihat Detail",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 0.sp
                    )
                }

                Button(
                    onClick = {},
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = RecommendationBlue)
                ) {
                    Text(
                        text = "Sapa!",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        letterSpacing = 0.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun Avatar(friend: RecommendationProfile) {
    Box {
        Box(
            modifier = Modifier
                .size(66.dp)
                .clip(CircleShape)
                .background(Color(0xFFE3E9F2)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = friend.avatarText,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = RecommendationBlue,
                letterSpacing = 0.sp
            )
        }

        if (friend.online) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF0B7D2B))
                    .border(2.dp, Color.White, CircleShape)
                    .align(Alignment.BottomEnd)
            )
        }
    }
}

@Composable
private fun InterestChip(
    text: String,
    active: Boolean
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (active) Color(0xFFF3FAFF) else Color(0xFFE7EBF1))
            .border(
                width = if (active) 1.dp else 0.dp,
                color = if (active) Color(0xFFD0E8FA) else Color.Transparent,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 13.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            color = if (active) RecommendationBlue else RecommendationTextGray,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.sp
        )
    }
}

@Composable
private fun RecommendationBottomNavigationBar(
    onHomeClick: () -> Unit,
    onFriendsClick: () -> Unit,
    onActivitiesClick: () -> Unit,
    onMessagesClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        BottomNavItem(
            icon = "H",
            label = "Beranda",
            selected = false,
            onClick = onHomeClick
        )

        BottomNavItem(
            icon = "T",
            label = "Teman",
            selected = true,
            onClick = onFriendsClick
        )

        BottomNavItem(
            icon = "A",
            label = "Aktivitas",
            selected = false,
            onClick = onActivitiesClick
        )

        BottomNavItem(
            icon = "P",
            label = "Pesan",
            selected = false,
            onClick = onMessagesClick
        )

        BottomNavItem(
            icon = "U",
            label = "Profil",
            selected = false,
            onClick = onProfileClick
        )
    }
}

@Composable
private fun RowScope.BottomNavItem(
    icon: String,
    label: String,
    selected: Boolean,
    onClick: () -> Unit = {}
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = {
            Text(
                text = icon,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.sp
            )
        },
        label = {
            Text(
                text = label,
                fontSize = 12.sp,
                letterSpacing = 0.sp
            )
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = RecommendationTextDark,
            selectedTextColor = RecommendationTextDark,
            indicatorColor = Color(0xFF42A5F5),
            unselectedIconColor = RecommendationTextGray,
            unselectedTextColor = RecommendationTextGray
        )
    )
}

private val RecommendationBlue = Color(0xFF006AA6)
private val RecommendationLightBlue = Color(0xFFEAF6FF)
private val RecommendationBackground = Color(0xFFF6F8FC)
private val RecommendationTextDark = Color(0xFF1D222B)
private val RecommendationTextGray = Color(0xFF4E5560)
