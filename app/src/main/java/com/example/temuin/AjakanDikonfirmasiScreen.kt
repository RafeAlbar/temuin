package com.example.temuin

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AjakanDikonfirmasiScreen(
    profile: RecommendationProfile,
    invitation: InvitationPlan,
    showLocation: Boolean,
    modifier: Modifier = Modifier,
    chatName: String = profile.name,
    chatAvatarText: String = profile.avatarText,
    chatOnlineText: String = if (profile.online) "Online" else "Offline",
    onBackClick: () -> Unit = {},
    onHeaderClick: () -> Unit = {},
    onLocationClick: () -> Unit = {}
) {
    var locationVisible by remember(showLocation) { mutableStateOf(showLocation) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = ConfirmedBackground,
        topBar = {
            ConfirmedChatHeader(
                chatName = chatName,
                chatAvatarText = chatAvatarText,
                chatOnlineText = chatOnlineText,
                online = profile.online,
                onBackClick = onBackClick,
                onHeaderClick = onHeaderClick
            )
        },
        bottomBar = {
            ConfirmedMessageInputBar()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(ConfirmedBackground)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(26.dp))

            ConfirmedBanner(confirmed = locationVisible)

            Spacer(modifier = Modifier.height(28.dp))

            ConfirmedEventCard(
                invitation = invitation,
                showLocation = locationVisible,
                onConfirmClick = { locationVisible = true },
                onLocationClick = onLocationClick
            )

            Spacer(modifier = Modifier.height(44.dp))

            IncomingMessageBubble(
                avatar = chatAvatarText,
                text = if (locationVisible) {
                    "Sip, aku terima ya. Sampai ketemu di ${invitation.place}."
                } else {
                    "Aku chat dulu ya sebelum fix lokasi. Waktunya oke buatku."
                },
                time = "14:15"
            )

            Spacer(modifier = Modifier.height(18.dp))

            OutgoingMessageBubble(
                text = if (locationVisible) {
                    "Mantap! Aku siap, nanti kabari kalau sudah dekat lokasi."
                } else {
                    "Oke, kita ngobrol dulu. Lokasinya bisa kita tentukan setelah cocok."
                },
                time = "14:20"
            )

            Spacer(modifier = Modifier.height(160.dp))
        }
    }
}

@Composable
private fun ConfirmedChatHeader(
    chatName: String,
    chatAvatarText: String,
    chatOnlineText: String,
    online: Boolean,
    onBackClick: () -> Unit,
    onHeaderClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(70.dp)
            .background(ConfirmedBackground)
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .clickable(onClick = onBackClick),
            contentAlignment = Alignment.Center
        ) {
            BackIcon(Modifier.size(30.dp), ConfirmedTextGray)
        }

        Spacer(modifier = Modifier.width(20.dp))

        Box(
            modifier = Modifier.clickable(onClick = onHeaderClick)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE2E7EF)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = chatAvatarText,
                    color = ConfirmedPrimaryBlue,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.sp
                )
            }

            if (online) {
                Box(
                    modifier = Modifier
                        .size(13.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF0B7D2B))
                        .border(2.dp, Color.White, CircleShape)
                        .align(Alignment.BottomEnd)
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.clickable(onClick = onHeaderClick)
        ) {
            Text(
                text = chatName,
                color = ConfirmedTextDark,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.sp
            )

            Text(
                text = chatOnlineText,
                color = ConfirmedTextGray,
                fontSize = 14.sp,
                letterSpacing = 0.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        MoreIcon(Modifier.size(30.dp), ConfirmedTextGray)
    }
}

@Composable
private fun ConfirmedBanner(confirmed: Boolean) {
    val bannerColor = if (confirmed) Color(0xFFEAF7EA) else Color(0xFFFFF7E6)
    val borderColor = if (confirmed) Color(0xFFB7DDBB) else Color(0xFFFFDEA1)
    val iconColor = if (confirmed) Color(0xFF137A2A) else Color(0xFF8A5A00)
    val text = if (confirmed) "Ajakan dikonfirmasi" else "Ajakan belum dikonfirmasi"

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(bannerColor)
            .border(1.dp, borderColor, RoundedCornerShape(12.dp))
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(iconColor),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (confirmed) "✓" else "?",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.sp
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Text(
                text = text,
                color = iconColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.sp
            )
        }
    }
}

@Composable
private fun ConfirmedEventCard(
    invitation: InvitationPlan,
    showLocation: Boolean,
    onConfirmClick: () -> Unit,
    onLocationClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            Column(modifier = Modifier.padding(18.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = invitation.title,
                        color = ConfirmedPrimaryBlue,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f),
                        letterSpacing = 0.sp
                    )

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(22.dp))
                            .background(Color(0xFF42A5F5))
                            .padding(horizontal = 18.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = if (showLocation) "Confirmed" else "Chat First",
                            color = Color(0xFF004B78),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            letterSpacing = 0.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    ClockIcon(Modifier.size(22.dp), ConfirmedTextGray)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = invitation.time,
                        color = ConfirmedTextGray,
                        fontSize = 17.sp,
                        letterSpacing = 0.sp
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(0xFFDDE3EC))
            )

            if (showLocation) {
                LocationSection(invitation = invitation)

                Button(
                    onClick = onLocationClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp)
                        .height(52.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE5EAF1))
                ) {
                    MapIcon(Modifier.size(24.dp), ConfirmedPrimaryBlue)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Lihat Lokasi",
                        color = ConfirmedPrimaryBlue,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.sp
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp, vertical = 18.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFFFF7E6))
                        .border(1.dp, Color(0xFFFFDEA1), RoundedCornerShape(12.dp))
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Lokasi disembunyikan sampai kalian lanjut chat dan menyepakati tempat.",
                        color = Color(0xFF8A5A00),
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        letterSpacing = 0.sp
                    )
                }

                Button(
                    onClick = onConfirmClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp)
                        .height(52.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF42A5F5))
                ) {
                    Text(
                        text = "Konfirmasi Ajakan",
                        color = Color(0xFF004B78),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.sp
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))
            }
        }
    }
}

@Composable
private fun LocationSection(invitation: InvitationPlan) {
    Row(
        modifier = Modifier.padding(18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(78.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFFEDEFF4)),
            contentAlignment = Alignment.Center
        ) {
            LocationIcon(Modifier.size(34.dp), ConfirmedPrimaryBlue)
        }

        Spacer(modifier = Modifier.width(18.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = invitation.place,
                color = ConfirmedTextDark,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Detail lokasi tersedia setelah ajakan diterima.",
                color = ConfirmedTextGray,
                fontSize = 16.sp,
                letterSpacing = 0.sp
            )
        }
    }
}

@Composable
private fun IncomingMessageBubble(
    avatar: String,
    text: String,
    time: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom
    ) {
        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .background(Color(0xFFE2E7EF)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = avatar,
                color = ConfirmedPrimaryBlue,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                letterSpacing = 0.sp
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        MessageBubble(
            text = text,
            time = time,
            incoming = true
        )
    }
}

@Composable
private fun OutgoingMessageBubble(
    text: String,
    time: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        MessageBubble(
            text = text,
            time = time,
            incoming = false
        )
    }
}

@Composable
private fun MessageBubble(
    text: String,
    time: String,
    incoming: Boolean
) {
    Box(
        modifier = Modifier
            .widthIn(max = 560.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 18.dp,
                    topEnd = 18.dp,
                    bottomStart = if (incoming) 0.dp else 18.dp,
                    bottomEnd = if (incoming) 18.dp else 0.dp
                )
            )
            .background(if (incoming) Color(0xFFEDEFF4) else ConfirmedPrimaryBlue)
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = text,
                color = if (incoming) ConfirmedTextDark else Color.White,
                fontSize = 17.sp,
                lineHeight = 26.sp,
                letterSpacing = 0.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = time,
                color = if (incoming) ConfirmedTextGray else Color(0xFF9ED4F8),
                fontSize = 13.sp,
                modifier = Modifier.align(Alignment.End),
                letterSpacing = 0.sp
            )
        }
    }
}

@Composable
private fun ConfirmedMessageInputBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(ConfirmedBackground)
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("+", color = ConfirmedTextGray, fontSize = 34.sp, fontWeight = FontWeight.Medium, letterSpacing = 0.sp)

        Spacer(modifier = Modifier.width(14.dp))

        Box(
            modifier = Modifier
                .weight(1f)
                .height(56.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(ConfirmedBackground)
                .border(1.dp, Color(0xFFD6DCE6), RoundedCornerShape(18.dp))
                .padding(horizontal = 18.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "Ketik pesan...",
                color = Color(0xFF7A838F),
                fontSize = 18.sp,
                letterSpacing = 0.sp
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Box(
            modifier = Modifier
                .size(58.dp)
                .clip(CircleShape)
                .background(ConfirmedPrimaryBlue),
            contentAlignment = Alignment.Center
        ) {
            SendIcon(Modifier.size(28.dp), Color.White)
        }
    }
}

@Composable
private fun BackIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        drawLine(color, Offset(size.width * 0.82f, size.height * 0.5f), Offset(size.width * 0.2f, size.height * 0.5f), 3.dp.toPx(), StrokeCap.Round)
        drawLine(color, Offset(size.width * 0.2f, size.height * 0.5f), Offset(size.width * 0.46f, size.height * 0.22f), 3.dp.toPx(), StrokeCap.Round)
        drawLine(color, Offset(size.width * 0.2f, size.height * 0.5f), Offset(size.width * 0.46f, size.height * 0.78f), 3.dp.toPx(), StrokeCap.Round)
    }
}

@Composable
private fun MoreIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        drawCircle(color, radius = size.minDimension * 0.07f, center = Offset(size.width * 0.5f, size.height * 0.24f))
        drawCircle(color, radius = size.minDimension * 0.07f, center = Offset(size.width * 0.5f, size.height * 0.5f))
        drawCircle(color, radius = size.minDimension * 0.07f, center = Offset(size.width * 0.5f, size.height * 0.76f))
    }
}

@Composable
private fun ClockIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        drawCircle(color, radius = size.minDimension * 0.42f, center = Offset(size.width * 0.5f, size.height * 0.5f), style = Stroke(2.5.dp.toPx()))
        drawLine(color, Offset(size.width * 0.5f, size.height * 0.5f), Offset(size.width * 0.5f, size.height * 0.24f), 2.5.dp.toPx(), StrokeCap.Round)
        drawLine(color, Offset(size.width * 0.5f, size.height * 0.5f), Offset(size.width * 0.7f, size.height * 0.62f), 2.5.dp.toPx(), StrokeCap.Round)
    }
}

@Composable
private fun LocationIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        val marker = Path().apply {
            moveTo(size.width * 0.5f, size.height * 0.9f)
            cubicTo(size.width * 0.22f, size.height * 0.58f, size.width * 0.22f, size.height * 0.24f, size.width * 0.5f, size.height * 0.16f)
            cubicTo(size.width * 0.78f, size.height * 0.24f, size.width * 0.78f, size.height * 0.58f, size.width * 0.5f, size.height * 0.9f)
            close()
        }
        drawPath(marker, color, style = Stroke(3.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round))
        drawCircle(color, radius = size.minDimension * 0.1f, center = Offset(size.width * 0.5f, size.height * 0.42f), style = Stroke(3.dp.toPx()))
    }
}

@Composable
private fun MapIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        drawRoundRect(color, Offset(size.width * 0.14f, size.height * 0.18f), Size(size.width * 0.72f, size.height * 0.64f), CornerRadius(3.dp.toPx()), style = Stroke(2.5.dp.toPx()))
        drawLine(color, Offset(size.width * 0.38f, size.height * 0.18f), Offset(size.width * 0.38f, size.height * 0.82f), 2.5.dp.toPx(), StrokeCap.Round)
        drawLine(color, Offset(size.width * 0.62f, size.height * 0.18f), Offset(size.width * 0.62f, size.height * 0.82f), 2.5.dp.toPx(), StrokeCap.Round)
    }
}

@Composable
private fun SendIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        val path = Path().apply {
            moveTo(size.width * 0.1f, size.height * 0.16f)
            lineTo(size.width * 0.88f, size.height * 0.5f)
            lineTo(size.width * 0.1f, size.height * 0.84f)
            lineTo(size.width * 0.28f, size.height * 0.5f)
            close()
        }
        drawPath(path, color, style = Stroke(3.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round))
        drawLine(color, Offset(size.width * 0.28f, size.height * 0.5f), Offset(size.width * 0.88f, size.height * 0.5f), 3.dp.toPx(), StrokeCap.Round)
    }
}

private val ConfirmedBackground = Color(0xFFF6F8FC)
private val ConfirmedPrimaryBlue = Color(0xFF006AA6)
private val ConfirmedTextDark = Color(0xFF1D222B)
private val ConfirmedTextGray = Color(0xFF4E5560)
