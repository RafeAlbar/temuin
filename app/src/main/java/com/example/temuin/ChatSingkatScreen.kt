package com.example.temuin

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChatSingkatScreen(
    profile: RecommendationProfile,
    invitation: InvitationPlan,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onMoveToInviteePovClick: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = ChatBackground,
        topBar = {
            ChatTopBar(
                profile = profile,
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            ChatInputBar()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(ChatBackground)
                .verticalScroll(rememberScrollState())
        ) {
            InvitationHeader(invitation = invitation)

            Spacer(modifier = Modifier.height(30.dp))

            IceBreakerSection(profile = profile, invitation = invitation)

            Spacer(modifier = Modifier.height(42.dp))

            ChatArea(
                profile = profile,
                onMoveToInviteePovClick = onMoveToInviteePovClick
            )
        }
    }
}

@Composable
private fun ChatTopBar(
    profile: RecommendationProfile,
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(70.dp)
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
            BackIcon(Modifier.size(30.dp), ChatTextGray)
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .size(46.dp)
                .clip(CircleShape)
                .background(Color(0xFFE8ECF2)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = profile.avatarText,
                color = ChatPrimaryBlue,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.sp
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = profile.name,
            color = ChatTextDark,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .background(ChatPrimaryBlue),
            contentAlignment = Alignment.Center
        ) {
            Text("i", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.sp)
        }
    }
}

@Composable
private fun InvitationHeader(invitation: InvitationPlan) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp, start = 28.dp, end = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "AJAKAN TERKIRIM",
            color = ChatTextGray,
            fontSize = 14.sp,
            letterSpacing = 3.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = invitation.title,
            color = ChatTextDark,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            letterSpacing = 0.sp
        )

        Spacer(modifier = Modifier.height(18.dp))

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFFEDEFF4))
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                LocationIcon(Modifier.size(20.dp), ChatTextGray)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "${invitation.place} - ${invitation.time}",
                    color = ChatTextGray,
                    fontSize = 16.sp,
                    letterSpacing = 0.sp
                )
            }
        }
    }
}

@Composable
private fun IceBreakerSection(
    profile: RecommendationProfile,
    invitation: InvitationPlan
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "REKOMENDASI ICEBREAKER",
                color = ChatTextGray,
                fontSize = 15.sp,
                letterSpacing = 1.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Acak",
                color = ChatPrimaryBlue,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.sp
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(start = 40.dp, end = 40.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            IceBreakerCard(
                message = "\"Eh ${profile.name.substringBefore(" ")}, biasanya paling suka bahas apa soal ${profile.tags.firstOrNull() ?: "hobi"}?\"",
                tag = "Santai"
            )

            IceBreakerCard(
                message = "\"Seru nih ajakannya: ${invitation.title}. Lagi ada referensi tempat lain juga?\"",
                tag = "Topik Minat"
            )
        }
    }
}

@Composable
private fun IceBreakerCard(
    message: String,
    tag: String
) {
    Card(
        modifier = Modifier
            .width(255.dp)
            .height(150.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row {
                SparkIcon(Modifier.size(28.dp), ChatPrimaryBlue)
                Spacer(modifier = Modifier.width(14.dp))
                Text(
                    text = message,
                    color = ChatTextDark,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    letterSpacing = 0.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .padding(start = 42.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFEAF6FF))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    text = tag,
                    color = ChatPrimaryBlue,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 0.sp
                )
            }
        }
    }
}

@Composable
private fun ChatArea(
    profile: RecommendationProfile,
    onMoveToInviteePovClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(520.dp),
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 74.dp, start = 24.dp, end = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFFEDEFF4))
                    .padding(horizontal = 22.dp, vertical = 8.dp)
            ) {
                Text(text = "Hari Ini", color = ChatTextGray, fontSize = 15.sp, letterSpacing = 0.sp)
            }

            Spacer(modifier = Modifier.height(32.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFF7FCF8))
                    .border(1.dp, Color(0xFFC8E6C9), RoundedCornerShape(12.dp))
                    .padding(horizontal = 18.dp, vertical = 18.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    PersonAddIcon(Modifier.size(24.dp), Color(0xFF1B7D35))
                    Spacer(modifier = Modifier.width(18.dp))
                    Text(
                        text = "Ajakan nongkrong terkirim.\nMenunggu konfirmasi ${profile.name.substringBefore(" ")}.",
                        color = Color(0xFF1B7D35),
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        letterSpacing = 0.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onMoveToInviteePovClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ChatSendBlue)
            ) {
                Text(
                    text = "Move to ${profile.name.substringBefore(" ")} POV",
                    color = Color(0xFF004B78),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.sp
                )
            }
        }
    }
}

@Composable
private fun ChatInputBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(ChatBackground)
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("+", color = Color(0xFF68717D), fontSize = 34.sp, fontWeight = FontWeight.Medium, letterSpacing = 0.sp)

            Spacer(modifier = Modifier.width(16.dp))

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(58.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(ChatBackground)
                    .border(1.dp, Color(0xFFD5DBE5), RoundedCornerShape(18.dp))
                    .padding(horizontal = 18.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Pilih template di atas atau\nketik pesan...",
                    color = Color(0xFF7A838F),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    letterSpacing = 0.sp
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Box(
                modifier = Modifier
                    .size(58.dp)
                    .clip(CircleShape)
                    .background(ChatSendBlue),
                contentAlignment = Alignment.Center
            ) {
                SendIcon(Modifier.size(28.dp), Color.White)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Kirim pesan untuk memecah keheningan",
            color = ChatTextGray,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 70.dp),
            letterSpacing = 0.sp
        )
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
private fun LocationIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        drawCircle(color, radius = size.minDimension * 0.32f, center = Offset(size.width * 0.5f, size.height * 0.38f), style = Stroke(2.5.dp.toPx()))
        drawLine(color, Offset(size.width * 0.5f, size.height * 0.7f), Offset(size.width * 0.5f, size.height * 0.92f), 2.5.dp.toPx(), StrokeCap.Round)
        drawCircle(color, radius = size.minDimension * 0.08f, center = Offset(size.width * 0.5f, size.height * 0.38f))
    }
}

@Composable
private fun SparkIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        drawLine(color, Offset(size.width * 0.5f, size.height * 0.08f), Offset(size.width * 0.5f, size.height * 0.92f), 3.dp.toPx(), StrokeCap.Round)
        drawLine(color, Offset(size.width * 0.08f, size.height * 0.5f), Offset(size.width * 0.92f, size.height * 0.5f), 3.dp.toPx(), StrokeCap.Round)
        drawLine(color, Offset(size.width * 0.22f, size.height * 0.22f), Offset(size.width * 0.78f, size.height * 0.78f), 2.5.dp.toPx(), StrokeCap.Round)
        drawLine(color, Offset(size.width * 0.78f, size.height * 0.22f), Offset(size.width * 0.22f, size.height * 0.78f), 2.5.dp.toPx(), StrokeCap.Round)
    }
}

@Composable
private fun PersonAddIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        drawCircle(color, radius = size.minDimension * 0.14f, center = Offset(size.width * 0.38f, size.height * 0.3f), style = Stroke(3.dp.toPx()))
        drawRoundRect(color, Offset(size.width * 0.14f, size.height * 0.58f), Size(size.width * 0.46f, size.height * 0.22f), CornerRadius(3.dp.toPx()), style = Stroke(3.dp.toPx()))
        drawLine(color, Offset(size.width * 0.78f, size.height * 0.28f), Offset(size.width * 0.78f, size.height * 0.56f), 3.dp.toPx(), StrokeCap.Round)
        drawLine(color, Offset(size.width * 0.64f, size.height * 0.42f), Offset(size.width * 0.92f, size.height * 0.42f), 3.dp.toPx(), StrokeCap.Round)
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

private val ChatBackground = Color(0xFFF6F8FC)
private val ChatPrimaryBlue = Color(0xFF006AA6)
private val ChatSendBlue = Color(0xFF42A5F5)
private val ChatTextDark = Color(0xFF1D222B)
private val ChatTextGray = Color(0xFF4E5560)
