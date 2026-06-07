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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onRecommendationClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = Color(0xFFF5F9FF)
    ) {
        Box(Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .navigationBarsPadding()
            ) {
                HomeTopBar()
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 24.dp)
                        .padding(top = 56.dp, bottom = 112.dp)
                ) {
                    Text(
                        text = "Mau ngapain hari ini?",
                        color = HomeTextDark,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 0.sp
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "Pilih jalur eksplorasimu untuk menemukan koneksi\ndan aktivitas baru di sekitarmu.",
                        color = HomeTextSlate,
                        fontSize = 18.sp,
                        lineHeight = 26.sp,
                        letterSpacing = 0.sp
                    )
                    Spacer(Modifier.height(34.dp))
                    ExploreCard(
                        title = "Rekomendasi Teman\nOtomatis",
                        description = "Biarkan AI kami mencocokkan\nprofilmu dengan teman baru yang\nmemiliki minat serupa secara instan.",
                        circleColor = Color(0xFF42A9F4),
                        gradientStart = Color(0xFFF4F8FF),
                        gradientEnd = Color(0xFFE4F0FF),
                        icon = { BotIcon(Modifier.size(34.dp), Color(0xFF064D78)) },
                        onClick = onRecommendationClick
                    )
                    Spacer(Modifier.height(26.dp))
                    ExploreCard(
                        title = "Filter Minat & Aktivitas",
                        description = "Cari koneksi spesifik berdasarkan\nhobi, gaya hidup, atau jadwal\naktivitas yang kamu inginkan.",
                        circleColor = Color(0xFF91F29D),
                        gradientStart = Color(0xFFF5F8FF),
                        gradientEnd = Color(0xFFE0F7E5),
                        icon = { SlidersIcon(Modifier.size(34.dp), Color(0xFF0F7A36)) }
                    )
                    Spacer(Modifier.height(26.dp))
                    ExploreCard(
                        title = "Aktivitas Populer Sekitar",
                        description = "Lihat acara, tempat nongkrong, atau\nkegiatan fisik yang sedang ramai\ndiminati di lokasimu saat ini.",
                        circleColor = Color(0xFFFFD2CE),
                        gradientStart = Color(0xFFF7F9FF),
                        gradientEnd = Color(0xFFFFEFF0),
                        icon = { FlameIcon(Modifier.size(34.dp), Color(0xFFB00018)) }
                    )
                }
            }
            HomeBottomNav(Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
private fun HomeTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(86.dp)
            .background(Color(0xFFF7FAFF))
            .border(1.dp, Color(0xFFE3E8F0))
            .padding(horizontal = 32.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(Color(0xFFE1E5ED)),
            contentAlignment = Alignment.Center
        ) {
            HomeUserIcon(Modifier.size(34.dp), Color(0xFF414B59))
        }
        BellIcon(Modifier.size(32.dp), HomeBlue)
    }
}

@Composable
private fun ExploreCard(
    title: String,
    description: String,
    circleColor: Color,
    gradientStart: Color,
    gradientEnd: Color,
    icon: @Composable () -> Unit,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Brush.horizontalGradient(listOf(gradientStart, gradientEnd)))
            .border(1.2.dp, Color(0xFFDDE4EE), RoundedCornerShape(18.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 24.dp, vertical = 26.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .background(circleColor),
            contentAlignment = Alignment.Center
        ) {
            icon()
        }
        Spacer(Modifier.width(28.dp))
        Column(Modifier.weight(1f)) {
            Text(
                text = title,
                color = HomeTextDark,
                fontSize = 24.sp,
                lineHeight = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 0.sp
            )
            Spacer(Modifier.height(14.dp))
            Text(
                text = description,
                color = HomeTextSlate,
                fontSize = 18.sp,
                lineHeight = 26.sp,
                letterSpacing = 0.sp
            )
        }
    }
}

@Composable
private fun HomeBottomNav(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Color.White,
        shape = RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp),
        shadowElevation = 10.dp
    ) {
        Row(
            modifier = Modifier
                .navigationBarsPadding()
                .padding(horizontal = 24.dp, vertical = 14.dp)
                .fillMaxWidth()
                .height(62.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SelectedNavItem("Beranda", Modifier.weight(1f))
            NavItem("Teman", Modifier.weight(1f)) { HomePeopleIcon(Modifier.size(30.dp), Color(0xFF434B58)) }
            NavItem("Aktivitas", Modifier.weight(1f)) { CompassIcon(Modifier.size(30.dp), Color(0xFF434B58)) }
            NavItem("Pesan", Modifier.weight(1f)) { ChatIcon(Modifier.size(30.dp), Color(0xFF434B58)) }
            NavItem("Profil", Modifier.weight(1f)) { HomeUserIcon(Modifier.size(30.dp), Color(0xFF434B58)) }
        }
    }
}

@Composable
private fun SelectedNavItem(label: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .height(56.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(HomeButtonBlue)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        HomeNavIcon(Modifier.size(28.dp), Color(0xFF064D78))
        Spacer(Modifier.width(8.dp))
        Text(label, color = Color(0xFF064D78), fontSize = 14.sp, letterSpacing = 0.sp)
    }
}

@Composable
private fun NavItem(label: String, modifier: Modifier = Modifier, icon: @Composable () -> Unit) {
    Column(
        modifier = modifier.height(56.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        icon()
        Spacer(Modifier.height(3.dp))
        Text(label, color = Color(0xFF434B58), fontSize = 14.sp, letterSpacing = 0.sp)
    }
}

@Composable
private fun HomeUserIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        drawCircle(
            color = color,
            radius = size.minDimension * 0.15f,
            center = Offset(size.width * 0.5f, size.height * 0.28f),
            style = Stroke(3.dp.toPx())
        )
        drawRoundRect(
            color = color,
            topLeft = Offset(size.width * 0.2f, size.height * 0.58f),
            size = Size(size.width * 0.6f, size.height * 0.28f),
            cornerRadius = CornerRadius(2.dp.toPx()),
            style = Stroke(3.dp.toPx())
        )
    }
}

@Composable
private fun HomePeopleIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        drawCircle(color, radius = size.minDimension * 0.13f, center = Offset(size.width * 0.5f, size.height * 0.28f))
        drawCircle(color, radius = size.minDimension * 0.1f, center = Offset(size.width * 0.23f, size.height * 0.38f))
        drawCircle(color, radius = size.minDimension * 0.1f, center = Offset(size.width * 0.77f, size.height * 0.38f))
        drawRoundRect(color, topLeft = Offset(size.width * 0.32f, size.height * 0.48f), size = Size(size.width * 0.36f, size.height * 0.28f), cornerRadius = CornerRadius(7.dp.toPx(), 7.dp.toPx()))
        drawRoundRect(color, topLeft = Offset(size.width * 0.06f, size.height * 0.57f), size = Size(size.width * 0.26f, size.height * 0.2f), cornerRadius = CornerRadius(6.dp.toPx(), 6.dp.toPx()))
        drawRoundRect(color, topLeft = Offset(size.width * 0.68f, size.height * 0.57f), size = Size(size.width * 0.26f, size.height * 0.2f), cornerRadius = CornerRadius(6.dp.toPx(), 6.dp.toPx()))
    }
}

@Composable
private fun HomeNavIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        val roof = Path().apply {
            moveTo(size.width * 0.14f, size.height * 0.46f)
            lineTo(size.width * 0.5f, size.height * 0.14f)
            lineTo(size.width * 0.86f, size.height * 0.46f)
            lineTo(size.width * 0.78f, size.height * 0.46f)
            lineTo(size.width * 0.78f, size.height * 0.86f)
            lineTo(size.width * 0.22f, size.height * 0.86f)
            lineTo(size.width * 0.22f, size.height * 0.46f)
            close()
        }
        drawPath(roof, color)
        drawRoundRect(
            color = HomeButtonBlue,
            topLeft = Offset(size.width * 0.45f, size.height * 0.58f),
            size = Size(size.width * 0.1f, size.height * 0.28f),
            cornerRadius = CornerRadius(1.dp.toPx())
        )
    }
}

@Composable
private fun BellIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        drawLine(color, Offset(size.width * 0.18f, size.height * 0.78f), Offset(size.width * 0.82f, size.height * 0.78f), 3.dp.toPx(), StrokeCap.Round)
        drawArc(color, 180f, 180f, false, Offset(size.width * 0.26f, size.height * 0.22f), Size(size.width * 0.48f, size.height * 0.7f), style = Stroke(3.dp.toPx(), cap = StrokeCap.Round))
        drawLine(color, Offset(size.width * 0.26f, size.height * 0.56f), Offset(size.width * 0.26f, size.height * 0.78f), 3.dp.toPx(), StrokeCap.Round)
        drawLine(color, Offset(size.width * 0.74f, size.height * 0.56f), Offset(size.width * 0.74f, size.height * 0.78f), 3.dp.toPx(), StrokeCap.Round)
        drawLine(color, Offset(size.width * 0.5f, size.height * 0.16f), Offset(size.width * 0.5f, size.height * 0.08f), 3.dp.toPx(), StrokeCap.Round)
        drawCircle(color, radius = size.minDimension * 0.06f, center = Offset(size.width * 0.5f, size.height * 0.9f))
    }
}

@Composable
private fun BotIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        drawRoundRect(color, Offset(size.width * 0.18f, size.height * 0.28f), Size(size.width * 0.64f, size.height * 0.52f), CornerRadius(4.dp.toPx()), style = Stroke(3.dp.toPx()))
        drawLine(color, Offset(size.width * 0.5f, size.height * 0.28f), Offset(size.width * 0.5f, size.height * 0.14f), 3.dp.toPx(), StrokeCap.Round)
        drawCircle(color, radius = size.minDimension * 0.05f, center = Offset(size.width * 0.5f, size.height * 0.1f))
        drawLine(color, Offset(size.width * 0.06f, size.height * 0.52f), Offset(size.width * 0.18f, size.height * 0.52f), 3.dp.toPx(), StrokeCap.Round)
        drawLine(color, Offset(size.width * 0.82f, size.height * 0.52f), Offset(size.width * 0.94f, size.height * 0.52f), 3.dp.toPx(), StrokeCap.Round)
        drawCircle(color, radius = size.minDimension * 0.06f, center = Offset(size.width * 0.38f, size.height * 0.52f))
        drawCircle(color, radius = size.minDimension * 0.06f, center = Offset(size.width * 0.62f, size.height * 0.52f))
        drawLine(color, Offset(size.width * 0.38f, size.height * 0.66f), Offset(size.width * 0.62f, size.height * 0.66f), 3.dp.toPx(), StrokeCap.Round)
    }
}

@Composable
private fun SlidersIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        val strokeWidth = 3.dp.toPx()
        listOf(0.28f, 0.5f, 0.72f).forEach { y ->
            drawLine(color, Offset(size.width * 0.16f, size.height * y), Offset(size.width * 0.84f, size.height * y), strokeWidth, StrokeCap.Round)
        }
        drawLine(color, Offset(size.width * 0.34f, size.height * 0.18f), Offset(size.width * 0.34f, size.height * 0.38f), strokeWidth, StrokeCap.Round)
        drawLine(color, Offset(size.width * 0.64f, size.height * 0.4f), Offset(size.width * 0.64f, size.height * 0.6f), strokeWidth, StrokeCap.Round)
        drawLine(color, Offset(size.width * 0.46f, size.height * 0.62f), Offset(size.width * 0.46f, size.height * 0.82f), strokeWidth, StrokeCap.Round)
    }
}

@Composable
private fun FlameIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        val flame = Path().apply {
            moveTo(size.width * 0.52f, size.height * 0.9f)
            cubicTo(size.width * 0.18f, size.height * 0.82f, size.width * 0.2f, size.height * 0.46f, size.width * 0.46f, size.height * 0.28f)
            cubicTo(size.width * 0.48f, size.height * 0.46f, size.width * 0.62f, size.height * 0.5f, size.width * 0.7f, size.height * 0.18f)
            cubicTo(size.width * 0.92f, size.height * 0.48f, size.width * 0.84f, size.height * 0.84f, size.width * 0.52f, size.height * 0.9f)
            close()
        }
        drawPath(flame, color, style = Stroke(3.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round))
        drawCircle(color, radius = size.minDimension * 0.11f, center = Offset(size.width * 0.52f, size.height * 0.7f), style = Stroke(3.dp.toPx()))
    }
}

@Composable
private fun CompassIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        drawCircle(color, radius = size.minDimension * 0.38f, center = Offset(size.width * 0.5f, size.height * 0.5f), style = Stroke(3.dp.toPx()))
        val needle = Path().apply {
            moveTo(size.width * 0.62f, size.height * 0.28f)
            lineTo(size.width * 0.52f, size.height * 0.58f)
            lineTo(size.width * 0.32f, size.height * 0.72f)
            lineTo(size.width * 0.42f, size.height * 0.42f)
            close()
        }
        drawPath(needle, color, style = Stroke(3.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round))
    }
}

@Composable
private fun ChatIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        drawRoundRect(color, Offset(size.width * 0.14f, size.height * 0.2f), Size(size.width * 0.72f, size.height * 0.5f), CornerRadius(2.dp.toPx()), style = Stroke(3.dp.toPx()))
        drawLine(color, Offset(size.width * 0.34f, size.height * 0.7f), Offset(size.width * 0.22f, size.height * 0.84f), 3.dp.toPx(), StrokeCap.Round)
    }
}

private val HomeBlue = Color(0xFF086DA5)
private val HomeButtonBlue = Color(0xFF47A9EF)
private val HomeTextDark = Color(0xFF20242C)
private val HomeTextSlate = Color(0xFF424B5B)
