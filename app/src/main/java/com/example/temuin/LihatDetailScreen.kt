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
fun LihatDetailScreen(
    profile: RecommendationProfile,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onInviteClick: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = DetailBackground,
        bottomBar = {
            Button(
                onClick = onInviteClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 20.dp)
                    .height(58.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = DetailButtonBlue)
            ) {
                SendIcon(Modifier.size(26.dp), Color(0xFF004B78))
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Kirim Ajakan",
                    color = Color(0xFF004B78),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.sp
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            DetailTopBar(onBackClick = onBackClick)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 24.dp)
            ) {
                ProfileCard(profile = profile)

                Spacer(modifier = Modifier.height(28.dp))

                InterestSection(profile = profile)

                Spacer(modifier = Modifier.height(26.dp))

                ActivityCard(profile = profile)
            }
        }
    }
}

@Composable
private fun DetailTopBar(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .clickable(onClick = onBackClick),
            contentAlignment = Alignment.Center
        ) {
            BackIcon(Modifier.size(28.dp), DetailTextGray)
        }

        MoreIcon(Modifier.size(28.dp), DetailTextGray)
    }
}

@Composable
private fun ProfileCard(profile: RecommendationProfile) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 26.dp, vertical = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box {
                Box(
                    modifier = Modifier
                        .size(130.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE6EDF5)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = profile.avatarText,
                        color = DetailPrimaryBlue,
                        fontSize = 46.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.sp
                    )
                }

                if (profile.online) {
                    Box(
                        modifier = Modifier
                            .size(26.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF0B7D2B))
                            .border(4.dp, Color.White, CircleShape)
                            .align(Alignment.BottomEnd)
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = profile.name,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = DetailTextDark,
                textAlign = TextAlign.Center,
                letterSpacing = 0.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${profile.job} - ${profile.distance}",
                color = DetailTextGray,
                fontSize = 16.sp,
                letterSpacing = 0.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .background(if (profile.online) Color(0xFFE6F5E8) else Color(0xFFEDEFF4))
                    .padding(horizontal = 14.dp, vertical = 7.dp)
            ) {
                Text(
                    text = if (profile.online) "Available Sore ini" else "Balas saat senggang",
                    color = if (profile.online) Color(0xFF157A2E) else DetailTextGray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 0.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = profileBio(profile),
                color = DetailTextGray,
                fontSize = 17.sp,
                lineHeight = 28.sp,
                textAlign = TextAlign.Center,
                letterSpacing = 0.sp
            )
        }
    }
}

@Composable
private fun InterestSection(profile: RecommendationProfile) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            PeopleIcon(Modifier.size(34.dp), DetailPrimaryBlue)
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Minat yang Sama",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = DetailTextDark,
                letterSpacing = 0.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            profile.tags.take(2).forEach { tag ->
                InterestChipLarge(
                    text = tag,
                    active = true,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        if (profile.tags.size > 2) {
            Spacer(modifier = Modifier.height(14.dp))
            InterestChipLarge(
                text = profile.tags[2],
                active = false,
                modifier = Modifier.width(190.dp)
            )
        }
    }
}

@Composable
private fun InterestChipLarge(
    text: String,
    active: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(46.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(if (active) Color(0xFFF4FAFF) else Color(0xFFEDEFF4))
            .border(
                width = 1.dp,
                color = if (active) Color(0xFF90CAF9) else Color(0xFFDDE2EA),
                shape = RoundedCornerShape(24.dp)
            )
            .padding(horizontal = 14.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (active) DetailPrimaryBlue else DetailTextGray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.sp
        )
    }
}

@Composable
private fun ActivityCard(profile: RecommendationProfile) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Text(
                text = "Aktivitas Terakhir",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = DetailTextDark,
                letterSpacing = 0.sp
            )

            Spacer(modifier = Modifier.height(18.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFFD9A3)),
                    contentAlignment = Alignment.Center
                ) {
                    LocationIcon(Modifier.size(32.dp), DetailTextDark)
                }

                Spacer(modifier = Modifier.width(18.dp))

                Column {
                    Text(
                        text = latestActivity(profile),
                        color = DetailTextDark,
                        fontSize = 17.sp,
                        lineHeight = 26.sp,
                        letterSpacing = 0.sp
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "2 Jam yang lalu",
                        color = DetailTextGray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 0.sp
                    )
                }
            }
        }
    }
}

private fun profileBio(profile: RecommendationProfile): String {
    val mainInterest = profile.tags.firstOrNull() ?: "aktivitas baru"
    return "\"Suka ngobrol soal $mainInterest dan terbuka untuk kenalan baru di sekitar kota.\""
}

private fun latestActivity(profile: RecommendationProfile): String {
    val activity = when (profile.tags.firstOrNull()) {
        "UI/UX" -> "Baru saja menyimpan referensi desain untuk weekend project."
        "Fotografi" -> "Baru saja menambahkan spot foto baru ke daftar keinginannya."
        "Coding" -> "Baru saja mencari teman untuk sesi coding santai."
        else -> "Baru saja memperbarui daftar aktivitas favoritnya."
    }
    return activity
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

@Composable
private fun PeopleIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        drawCircle(color, radius = size.minDimension * 0.13f, center = Offset(size.width * 0.5f, size.height * 0.28f))
        drawCircle(color, radius = size.minDimension * 0.1f, center = Offset(size.width * 0.23f, size.height * 0.38f))
        drawCircle(color, radius = size.minDimension * 0.1f, center = Offset(size.width * 0.77f, size.height * 0.38f))
        drawRoundRect(color, Offset(size.width * 0.32f, size.height * 0.48f), Size(size.width * 0.36f, size.height * 0.28f), CornerRadius(7.dp.toPx()))
        drawRoundRect(color, Offset(size.width * 0.06f, size.height * 0.57f), Size(size.width * 0.26f, size.height * 0.2f), CornerRadius(6.dp.toPx()))
        drawRoundRect(color, Offset(size.width * 0.68f, size.height * 0.57f), Size(size.width * 0.26f, size.height * 0.2f), CornerRadius(6.dp.toPx()))
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

private val DetailPrimaryBlue = Color(0xFF006AA6)
private val DetailButtonBlue = Color(0xFF42A5F5)
private val DetailBackground = Color(0xFFF6F8FC)
private val DetailTextDark = Color(0xFF1D222B)
private val DetailTextGray = Color(0xFF4E5560)
