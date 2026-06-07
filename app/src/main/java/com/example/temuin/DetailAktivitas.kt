package com.example.temuin

import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ActivityDetailScreen(
    invitation: InvitationPlan = defaultActivityInvitation(),
    onBackClick: () -> Unit = {},
    onJoinActivityClick: () -> Unit = {}
) {
    val blue = Color(0xFF006AA6)
    val lightBlue = Color(0xFF42A5F5)
    val bg = Color(0xFFF6F8FC)
    val textDark = Color(0xFF20232B)
    val textGray = Color(0xFF5F6673)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.linearGradient(
                                listOf(
                                    Color(0xFF006AA6),
                                    Color(0xFF42A5F5),
                                    Color(0xFFEAF6FF)
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = invitation.title,
                        color = Color.White,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .padding(start = 28.dp, top = 30.dp)
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFEFF3F8))
                        .align(Alignment.TopStart)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = textDark,
                        modifier = Modifier.size(32.dp)
                    )
                }

                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .padding(end = 28.dp, top = 30.dp)
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFEFF3F8))
                        .align(Alignment.TopEnd)
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share",
                        tint = textDark,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 320.dp)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(
                topStart = 36.dp,
                topEnd = 36.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = bg
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 32.dp, vertical = 32.dp)
            ) {
                CategoryLabel(
                    text = "☕ Ngopi",
                    blue = blue
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = invitation.title,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    color = textDark
                )

                Spacer(modifier = Modifier.height(14.dp))

                Row {
                    Text(
                        text = "Bersama ",
                        fontSize = 21.sp,
                        color = textGray
                    )

                    Text(
                        text = "Andi Pratama",
                        fontSize = 21.sp,
                        color = blue
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                InfoBox(
                    icon = Icons.Default.Info,
                    iconColor = blue,
                    title = "Hari ini",
                    subtitle = invitation.time
                )

                Spacer(modifier = Modifier.height(18.dp))

                InfoBox(
                    icon = Icons.Default.LocationOn,
                    iconColor = Color(0xFFFF6847),
                    title = invitation.place,
                    subtitle = "Jl. Jend. Sudirman No.Kav 21"
                )

                Spacer(modifier = Modifier.height(34.dp))

                Text(
                    text = "Tentang Aktivitas",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = textDark
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Ngobrol santai seputar tech, startup, dan hobi. " +
                        "Terbuka untuk siapa saja yang ingin menambah networking " +
                        "di kawasan SCBD. Kita bisa saling share pengalaman dan " +
                        "mungkin menemukan partner project baru. Diharapkan datang " +
                        "tepat waktu agar diskusinya lebih seru!",
                    fontSize = 21.sp,
                    color = textGray,
                    lineHeight = 32.sp
                )

                Spacer(modifier = Modifier.height(38.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Partisipan (5/10)",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = textDark,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = "Lihat Semua",
                        fontSize = 18.sp,
                        color = blue,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                ParticipantRow(
                    textGray = textGray
                )

                Spacer(modifier = Modifier.height(46.dp))

                Button(
                    onClick = onJoinActivityClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(68.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = lightBlue
                    )
                ) {
                    Text(
                        text = "Ikut Aktivitas",
                        fontSize = 22.sp,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }
}

private fun defaultActivityInvitation(): InvitationPlan {
    return InvitationPlan(
        title = "Kopi & Diskusi Santai",
        time = "16:00 - 18:00 WIB",
        place = "Kopi Kenangan, Sudirman"
    )
}

@Composable
fun CategoryLabel(
    text: String,
    blue: Color
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(28.dp))
            .background(Color(0xFFEAF4FF))
            .padding(horizontal = 18.dp, vertical = 9.dp)
    ) {
        Text(
            text = text,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            color = blue
        )
    }
}

@Composable
fun InfoBox(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconColor: Color,
    title: String,
    subtitle: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF8FAFD)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(22.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(30.dp)
                )
            }

            Spacer(modifier = Modifier.width(18.dp))

            Column {
                Text(
                    text = title,
                    fontSize = 21.sp,
                    color = Color(0xFF2A2E36)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = subtitle,
                    fontSize = 19.sp,
                    color = Color(0xFF5F6673)
                )
            }
        }
    }
}

@Composable
fun ParticipantRow(
    textGray: Color
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.width(210.dp)
        ) {
            ParticipantAvatar(
                text = "B",
                modifier = Modifier.align(Alignment.CenterStart)
            )

            ParticipantAvatar(
                text = "S",
                modifier = Modifier
                    .padding(start = 42.dp)
                    .align(Alignment.CenterStart)
            )

            ParticipantAvatar(
                text = "A",
                modifier = Modifier
                    .padding(start = 84.dp)
                    .align(Alignment.CenterStart)
            )

            Box(
                modifier = Modifier
                    .padding(start = 126.dp)
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE2E6ED))
                    .align(Alignment.CenterStart),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "+2",
                    fontSize = 19.sp,
                    color = Color(0xFF4E5661)
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = "Budi, Siti dan 3 lainnya akan\nhadir.",
            fontSize = 20.sp,
            color = textGray,
            lineHeight = 28.sp
        )
    }
}

@Composable
fun ParticipantAvatar(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(56.dp)
            .clip(CircleShape)
            .background(Color(0xFFDCEAF7)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color(0xFF006AA6),
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
