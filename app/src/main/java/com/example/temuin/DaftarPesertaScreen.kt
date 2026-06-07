package com.example.temuin

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Participant(
    val name: String,
    val tags: List<String>,
    val isHost: Boolean = false,
    val isOnline: Boolean = false
)

@Composable
fun ParticipantsScreen(
    onBackClick: () -> Unit = {}
) {
    val bg = Color(0xFFF6F8FC)
    val blue = Color(0xFF006AA6)
    val textDark = Color(0xFF20232B)
    val textGray = Color(0xFF5F6673)

    val participants = listOf(
        Participant(
            name = "Budi Santoso",
            tags = listOf("UI Design", "Coffee"),
            isHost = true,
            isOnline = true
        ),
        Participant(
            name = "Siti Aminah",
            tags = listOf("Photography")
        ),
        Participant(
            name = "Reza Rahardian",
            tags = listOf("Web Dev", "Gaming"),
            isOnline = true
        ),
        Participant(
            name = "Dewi Lestari",
            tags = listOf("UX Research")
        ),
        Participant(
            name = "Andi Pratama",
            tags = listOf("Product Mgt", "Running")
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)
    ) {
        ParticipantsTopBar(
            textDark = textDark,
            onBackClick = onBackClick
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 20.dp,
                end = 20.dp,
                top = 26.dp,
                bottom = 26.dp
            ),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            items(participants.size) { index ->
                ParticipantCard(
                    participant = participants[index],
                    blue = blue,
                    textDark = textDark,
                    textGray = textGray
                )
            }
        }
    }
}

@Composable
fun ParticipantsTopBar(
    textDark: Color,
    onBackClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier.background(Color(0xFFF7F9FC))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(92.dp)
                .padding(horizontal = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color(0xFF3D424B),
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clickable(onClick = onBackClick)
                    .size(32.dp)
            )

            Text(
                text = "Peserta (5)",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = textDark
            )

            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color(0xFF3D424B),
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(34.dp)
            )
        }

        HorizontalDivider(
            color = Color(0xFFE2E6ED),
            thickness = 1.dp
        )
    }
}

@Composable
fun ParticipantCard(
    participant: Participant,
    blue: Color,
    textDark: Color,
    textGray: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 32.dp,
                    end = 28.dp,
                    top = 28.dp,
                    bottom = 28.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box {
                Box(
                    modifier = Modifier
                        .size(88.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE3E9F2)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = participantInitials(participant.name),
                        color = blue,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                if (participant.isOnline) {
                    Box(
                        modifier = Modifier
                            .size(22.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF0B7A25))
                            .border(
                                width = 3.dp,
                                color = Color.White,
                                shape = CircleShape
                            )
                            .align(Alignment.BottomEnd)
                    )
                }
            }

            Spacer(modifier = Modifier.width(28.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = participant.name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = textDark
                    )

                    if (participant.isHost) {
                        Spacer(modifier = Modifier.width(18.dp))

                        HostBadge()
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    participant.tags.forEachIndexed { index, tag ->
                        SkillTag(
                            text = tag,
                            isPrimary = index == 0,
                            blue = blue,
                            textGray = textGray
                        )
                    }
                }
            }

            Icon(
                imageVector = Icons.Outlined.Send,
                contentDescription = "Chat",
                tint = blue,
                modifier = Modifier.size(38.dp)
            )
        }
    }
}

private fun participantInitials(name: String): String {
    return name
        .split(" ")
        .mapNotNull { it.firstOrNull()?.toString() }
        .take(2)
        .joinToString("")
}

@Composable
fun HostBadge() {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .background(Color(0xFFFFF7E8))
            .border(
                width = 1.dp,
                color = Color(0xFFF3C979),
                shape = RoundedCornerShape(18.dp)
            )
            .padding(horizontal = 16.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Host",
            fontSize = 20.sp,
            color = Color(0xFF5B451A)
        )
    }
}

@Composable
fun SkillTag(
    text: String,
    isPrimary: Boolean,
    blue: Color,
    textGray: Color
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .background(
                if (isPrimary) Color(0xFFEAF4FF) else Color(0xFFEDEFF4)
            )
            .padding(horizontal = 14.dp, vertical = 7.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            color = if (isPrimary) blue else textGray
        )
    }
}
