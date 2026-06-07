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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
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
fun KonfirmasiAjakanScreen(
    profile: RecommendationProfile,
    invitation: InvitationPlan,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onAcceptClick: () -> Unit = {},
    onChatFirstClick: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = ConfirmBackground
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(ConfirmBackground)
                .statusBarsPadding()
        ) {
            ConfirmTopHeader(
                profile = profile,
                onBackClick = onBackClick
            )

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Ajakan Nongkrong Baru!",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = ConfirmTextDark,
                letterSpacing = 0.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "POV ${profile.name}: ada yang ngajak kamu ketemuan.",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                color = ConfirmTextGray,
                letterSpacing = 0.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            InvitationCard(
                profile = profile,
                invitation = invitation,
                onAcceptClick = onAcceptClick,
                onChatFirstClick = onChatFirstClick
            )
        }
    }
}

@Composable
private fun ConfirmTopHeader(
    profile: RecommendationProfile,
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .clickable(onClick = onBackClick),
            contentAlignment = Alignment.Center
        ) {
            BackIcon(Modifier.size(28.dp), ConfirmTextGray)
        }

        Box(
            modifier = Modifier
                .size(46.dp)
                .clip(CircleShape)
                .background(Color(0xFFE2E7EF)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = profile.avatarText,
                color = ConfirmDarkBlue,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.sp
            )
        }
    }
}

@Composable
private fun InvitationCard(
    profile: RecommendationProfile,
    invitation: InvitationPlan,
    onAcceptClick: () -> Unit,
    onChatFirstClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box {
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFE6EDF5)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "A",
                            color = ConfirmDarkBlue,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.sp
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(18.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF0B7D2B))
                            .border(3.dp, Color.White, CircleShape)
                            .align(Alignment.BottomEnd)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = "Adrian Rafe",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = ConfirmTextDark,
                        letterSpacing = 0.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color(0xFFEAF6FF))
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                    ) {
                        Text(
                            text = profile.match,
                            color = ConfirmDarkBlue,
                            fontSize = 14.sp,
                            letterSpacing = 0.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            ActivityInfoBox(invitation = invitation)

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Pesan dari Adrian:",
                color = ConfirmTextGray,
                fontSize = 14.sp,
                letterSpacing = 0.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFFF4FAFF))
                    .border(1.dp, Color(0xFFBBDDFB), RoundedCornerShape(14.dp))
                    .padding(16.dp)
            ) {
                Text(
                    text = "\"Seru nih kalau kita ${invitation.title.lowercase()} di ${invitation.place}. Kamu available ${invitation.time}?\"",
                    color = Color(0xFF004B78),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    letterSpacing = 0.sp
                )
            }

            Spacer(modifier = Modifier.height(26.dp))

            ActionButtons(
                onAcceptClick = onAcceptClick,
                onChatFirstClick = onChatFirstClick
            )
        }
    }
}

@Composable
private fun ActivityInfoBox(invitation: InvitationPlan) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Color(0xFFF1F5FB))
            .border(1.dp, Color(0xFFD8DEE8), RoundedCornerShape(18.dp))
            .padding(16.dp)
    ) {
        Row {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .background(ConfirmPrimaryBlue),
                contentAlignment = Alignment.Center
            ) {
                CoffeeIcon(Modifier.size(30.dp), ConfirmDarkBlue)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = invitation.title,
                    color = ConfirmTextDark,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "ACTIVITY",
                    color = ConfirmDarkBlue,
                    fontSize = 13.sp,
                    letterSpacing = 2.sp
                )

                Spacer(modifier = Modifier.height(18.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color(0xFFDDE3EC))
                )

                Spacer(modifier = Modifier.height(16.dp))

                InfoRow(text = invitation.place)

                Spacer(modifier = Modifier.height(12.dp))

                InfoRow(text = invitation.time)
            }
        }
    }
}

@Composable
private fun InfoRow(text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        DotIcon(Modifier.size(18.dp), ConfirmTextGray)
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            color = ConfirmTextGray,
            fontSize = 15.sp,
            letterSpacing = 0.sp
        )
    }
}

@Composable
private fun ActionButtons(
    onAcceptClick: () -> Unit = {},
    onChatFirstClick: () -> Unit = {}
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Button(
                onClick = onAcceptClick,
                modifier = Modifier
                    .weight(1f)
                    .height(54.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ConfirmPrimaryBlue)
            ) {
                Text(
                    text = "Terima Ajakan",
                    color = Color(0xFF004B78),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 0.sp
                )
            }

            OutlinedButton(
                onClick = onChatFirstClick,
                modifier = Modifier
                    .weight(1f)
                    .height(54.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = ConfirmDarkBlue)
            ) {
                Text(
                    text = "Chat Dulu",
                    color = ConfirmDarkBlue,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 0.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            OutlinedButton(
                onClick = {},
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Nanti Dulu",
                    color = ConfirmTextGray,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 0.sp
                )
            }

            Button(
                onClick = {},
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD6D2))
            ) {
                Text(
                    text = "Tolak",
                    color = Color(0xFFA00000),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 0.sp
                )
            }
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
private fun CoffeeIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        drawRoundRect(color, Offset(size.width * 0.18f, size.height * 0.32f), Size(size.width * 0.5f, size.height * 0.42f), CornerRadius(4.dp.toPx()), style = Stroke(3.dp.toPx()))
        drawArc(color, -90f, 180f, false, Offset(size.width * 0.58f, size.height * 0.38f), Size(size.width * 0.24f, size.height * 0.28f), style = Stroke(3.dp.toPx(), cap = StrokeCap.Round))
        drawLine(color, Offset(size.width * 0.22f, size.height * 0.84f), Offset(size.width * 0.74f, size.height * 0.84f), 3.dp.toPx(), StrokeCap.Round)
        drawLine(color, Offset(size.width * 0.32f, size.height * 0.14f), Offset(size.width * 0.32f, size.height * 0.24f), 2.5.dp.toPx(), StrokeCap.Round)
        drawLine(color, Offset(size.width * 0.5f, size.height * 0.1f), Offset(size.width * 0.5f, size.height * 0.24f), 2.5.dp.toPx(), StrokeCap.Round)
    }
}

@Composable
private fun DotIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        drawCircle(color, radius = size.minDimension * 0.24f, center = Offset(size.width * 0.5f, size.height * 0.5f), style = Stroke(2.5.dp.toPx()))
    }
}

private val ConfirmBackground = Color(0xFFF6F8FC)
private val ConfirmPrimaryBlue = Color(0xFF42A5F5)
private val ConfirmDarkBlue = Color(0xFF006AA6)
private val ConfirmTextDark = Color(0xFF1D222B)
private val ConfirmTextGray = Color(0xFF4E5560)
