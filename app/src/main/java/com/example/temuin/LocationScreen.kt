package com.example.temuin

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MeetLocationScreen(
    onBackClick: () -> Unit = {},
    onEndSessionClick: () -> Unit = {}
) {
    val primaryBlue = Color(0xFF006AA6)
    val lightBlue = Color(0xFF42A5F5)
    val background = Color(0xFFF6F8FC)
    val textDark = Color(0xFF1D222B)
    val textGray = Color(0xFF5A616D)

    Scaffold(
        containerColor = background,
        bottomBar = {
            CheckInButton(
                primaryBlue = primaryBlue,
                onEndSessionClick = onEndSessionClick
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(background)
                .verticalScroll(rememberScrollState())
        ) {
            TopMeetBar(
                primaryBlue = primaryBlue,
                textGray = textGray,
                onBackClick = onBackClick
            )

            MapHeroSection(
                primaryBlue = primaryBlue,
                lightBlue = lightBlue
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                LocationCard(
                    primaryBlue = primaryBlue,
                    lightBlue = lightBlue,
                    textDark = textDark,
                    textGray = textGray
                )

                Spacer(modifier = Modifier.height(26.dp))

                AttendanceSection(
                    primaryBlue = primaryBlue,
                    textDark = textDark,
                    textGray = textGray
                )

                Spacer(modifier = Modifier.height(24.dp))

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun TopMeetBar(
    primaryBlue: Color,
    textGray: Color,
    onBackClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .background(Color.White)
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = textGray,
            modifier = Modifier
                .size(30.dp)
                .clickable(onClick = onBackClick)
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "Menu",
            tint = textGray,
            modifier = Modifier.size(30.dp)
        )
    }
}

@Composable
fun MapHeroSection(
    primaryBlue: Color,
    lightBlue: Color
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF9DD9D8),
                        Color(0xFFD9F1F2),
                        Color(0xFFF6F8FC)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        MapMockup(
            primaryBlue = primaryBlue,
            lightBlue = lightBlue
        )
    }
}

@Composable
fun MapMockup(
    primaryBlue: Color,
    lightBlue: Color
) {
    Box(
        modifier = Modifier
            .width(250.dp)
            .height(360.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(Color(0xFFF1F3F4))
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = 0.8f),
                shape = RoundedCornerShape(22.dp)
            )
    ) {
        Box(
            modifier = Modifier
                .width(34.dp)
                .height(360.dp)
                .align(Alignment.Center)
                .background(Color(0xFFB9E4E8).copy(alpha = 0.85f))
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .align(Alignment.TopCenter)
                .padding(top = 70.dp)
                .background(Color(0xFFFFD764))
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .align(Alignment.BottomCenter)
                .padding(bottom = 95.dp)
                .background(Color(0xFFFFD764))
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            repeat(8) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(Color.White.copy(alpha = 0.7f))
                )
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 28.dp, top = 38.dp)
                .width(72.dp)
                .height(48.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFCFE7D3))
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 28.dp, bottom = 52.dp)
                .width(82.dp)
                .height(58.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(Color(0xFFD8E1EA))
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(62.dp)
                    .clip(CircleShape)
                    .background(primaryBlue),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(34.dp)
                )
            }

            Box(
                modifier = Modifier
                    .offset(y = (-6).dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
                    .padding(horizontal = 18.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "WorkBench Co-working",
                    color = Color(0xFF30343B),
                    fontSize = 14.sp
                )
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(22.dp)
                .size(32.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF435761)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
fun LocationCard(
    primaryBlue: Color,
    lightBlue: Color,
    textDark: Color,
    textGray: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-44).dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Row(
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFFEAF6FF))
                            .padding(horizontal = 12.dp, vertical = 5.dp)
                    ) {
                        Text(
                            text = "Tempat Ngopi",
                            color = primaryBlue,
                            fontSize = 14.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "WorkBench Co-working Jaksel",
                        color = textDark,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    InfoTextRow(
                        icon = Icons.Default.Info,
                        text = "Hari ini, 15:30 WIB",
                        color = textGray
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    InfoTextRow(
                        icon = Icons.Default.LocationOn,
                        text = "Jl. Sudirman No. 45, Jakarta Selatan",
                        color = textGray
                    )
                }

                Box(
                    modifier = Modifier
                        .size(58.dp)
                        .clip(CircleShape)
                        .background(lightBlue),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = Color(0xFF004B78),
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            Divider(color = Color(0xFFE2E6EE))

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = lightBlue
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = Color(0xFF004B78),
                        modifier = Modifier.size(22.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Buka Maps",
                        color = Color(0xFF004B78),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                OutlinedButton(
                    onClick = {},
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = null,
                        tint = textGray,
                        modifier = Modifier.size(22.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Bagikan",
                        color = textGray,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun InfoTextRow(
    icon: ImageVector,
    text: String,
    color: Color
) {
    Row(
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = text,
            color = color,
            fontSize = 16.sp,
            lineHeight = 23.sp
        )
    }
}

@Composable
fun AttendanceSection(
    primaryBlue: Color,
    textDark: Color,
    textGray: Color
) {
    Column(
        modifier = Modifier.offset(y = (-24).dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Status Kehadiran",
                color = textDark,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                modifier = Modifier.weight(1f)
            )

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color(0xFFEAF7EA))
                    .border(
                        width = 1.dp,
                        color = Color(0xFF7BD47B),
                        shape = RoundedCornerShape(18.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 7.dp)
            ) {
                Text(
                    text = "1 Teman Diundang",
                    color = Color(0xFF2E9B42),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        AttendanceItem(
            name = "Bayu A.",
            status = "Sudah Tiba",
            arrived = true,
            avatarText = "BA",
            primaryBlue = primaryBlue,
            textDark = textDark,
            textGray = textGray
        )
    }
}

@Composable
fun AttendanceItem(
    name: String,
    status: String,
    arrived: Boolean,
    avatarText: String,
    primaryBlue: Color,
    textDark: Color,
    textGray: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (arrived) Color.White else Color(0xFFF1F4F8)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (arrived) 2.dp else 0.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE4EAF2)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = avatarText,
                        color = primaryBlue,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                if (arrived) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF1F9D3A))
                            .border(2.dp, Color.White, CircleShape)
                            .align(Alignment.BottomEnd),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(10.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(18.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = name,
                    color = textDark,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = status,
                    color = if (arrived) Color(0xFF1B7D35) else textGray,
                    fontSize = 15.sp
                )
            }

            if (!arrived) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = Color(0xFF8A929D),
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}

@Composable
fun CheckInButton(
    primaryBlue: Color,
    onEndSessionClick: () -> Unit = {}
) {
    var checkedIn by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 20.dp, vertical = 14.dp)
    ) {
        Button(
            onClick = {
                if (checkedIn) {
                    onEndSessionClick()
                } else {
                    checkedIn = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryBlue
            )
        ) {
            Icon(
                imageVector = if (checkedIn) Icons.Default.Check else Icons.Default.Send,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = if (checkedIn) "Akhiri Sesi" else "Check-in Sekarang",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
        }
    }
}
