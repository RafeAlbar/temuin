package com.example.temuin

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class InvitationPlan(
    val title: String,
    val time: String,
    val place: String
)

@Composable
fun BuatAjakanScreen(
    profile: RecommendationProfile,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onCreateClick: (InvitationPlan) -> Unit = {}
) {
    var title by remember { mutableStateOf(defaultInvitationTitle(profile)) }
    var time by remember { mutableStateOf("Sabtu, 16.00") }
    var place by remember { mutableStateOf(defaultPlace(profile)) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = InviteBackground,
        bottomBar = {
            Button(
                onClick = {
                    onCreateClick(
                        InvitationPlan(
                            title = title.ifBlank { defaultInvitationTitle(profile) },
                            time = time.ifBlank { "Waktu fleksibel" },
                            place = place.ifBlank { "Tempat ditentukan nanti" }
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 20.dp)
                    .height(58.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = InviteButtonBlue)
            ) {
                Text(
                    text = "Buat Ajakan",
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
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 18.dp)
        ) {
            InviteTopBar(onBackClick = onBackClick)
            Spacer(Modifier.height(28.dp))

            Text(
                text = "Atur ajakan untuk ${profile.name}",
                color = InviteTextDark,
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                lineHeight = 34.sp,
                letterSpacing = 0.sp
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = "Tentukan topik, waktu, dan tempat sebelum membuka chat singkat.",
                color = InviteTextGray,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.sp
            )

            Spacer(Modifier.height(26.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(18.dp)
                ) {
                    InviteProfileRow(profile)

                    InviteField(
                        label = "Topik Ajakan",
                        value = title,
                        onValueChange = { title = it },
                        placeholder = "Contoh: Kopi dan ngobrol UI/UX"
                    )

                    InviteField(
                        label = "Waktu",
                        value = time,
                        onValueChange = { time = it },
                        placeholder = "Contoh: Sabtu, 16.00"
                    )

                    InviteField(
                        label = "Tempat",
                        value = place,
                        onValueChange = { place = it },
                        placeholder = "Contoh: WorkBench Co-working"
                    )
                }
            }
        }
    }
}

@Composable
private fun InviteTopBar(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .clickable(onClick = onBackClick),
            contentAlignment = Alignment.Center
        ) {
            BackIcon(Modifier.size(28.dp), InviteTextGray)
        }
    }
}

@Composable
private fun InviteProfileRow(profile: RecommendationProfile) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(58.dp)
                .clip(CircleShape)
                .background(Color(0xFFE3E9F2)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = profile.avatarText,
                color = InvitePrimaryBlue,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.sp
            )
        }

        Spacer(Modifier.width(14.dp))

        Column {
            Text(
                text = profile.name,
                color = InviteTextDark,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.sp
            )
            Text(
                text = "${profile.job} - ${profile.distance}",
                color = InviteTextGray,
                fontSize = 14.sp,
                letterSpacing = 0.sp
            )
        }
    }
}

@Composable
private fun InviteField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    Column {
        Text(
            text = label,
            color = InviteTextDark,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.sp
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            placeholder = {
                Text(placeholder, color = Color(0xFF8B94A1), letterSpacing = 0.sp)
            },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = InvitePrimaryBlue,
                unfocusedBorderColor = Color(0xFFD5DBE5),
                focusedContainerColor = Color(0xFFF9FBFF),
                unfocusedContainerColor = Color(0xFFF9FBFF)
            )
        )
    }
}

private fun defaultInvitationTitle(profile: RecommendationProfile): String {
    return when (profile.tags.firstOrNull()) {
        "UI/UX" -> "Kopi & ngobrol UI/UX"
        "Fotografi" -> "Foto hunting santai"
        "Coding" -> "Coding bareng"
        else -> "Nongkrong santai"
    }
}

private fun defaultPlace(profile: RecommendationProfile): String {
    return when (profile.tags.firstOrNull()) {
        "UI/UX" -> "WorkBench Co-working, Jaksel"
        "Fotografi" -> "Taman kota terdekat"
        "Coding" -> "Cafe dengan Wi-Fi kencang"
        else -> "Cafe sekitar lokasi"
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

private val InvitePrimaryBlue = Color(0xFF006AA6)
private val InviteButtonBlue = Color(0xFF42A5F5)
private val InviteBackground = Color(0xFFF6F8FC)
private val InviteTextDark = Color(0xFF1D222B)
private val InviteTextGray = Color(0xFF4E5560)
