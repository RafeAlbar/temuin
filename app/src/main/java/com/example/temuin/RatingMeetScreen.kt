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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MeetingFeedbackScreen(
    onCloseClick: () -> Unit = {}
) {
    var rating by remember { mutableStateOf(0) }
    var review by remember { mutableStateOf("") }
    var addFriend by remember { mutableStateOf<Boolean?>(null) }

    val blue = Color(0xFF0B6FAE)
    val lightBlue = Color(0xFF42A5F5)
    val green = Color(0xFF086B1F)
    val background = Color(0xFFF6F8FC)
    val textDark = Color(0xFF1D1F27)
    val textGray = Color(0xFF5F6673)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
    ) {
        HeaderBar(
            blue = blue,
            onCloseClick = onCloseClick
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(38.dp))

            Text(
                text = "Bagaimana Pertemuanmu?",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = textDark,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Bantu kami meningkatkan pengalaman untuk\naktivitas selanjutnya.",
                fontSize = 17.sp,
                color = textGray,
                textAlign = TextAlign.Center,
                lineHeight = 25.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            ProfileCard(
                blue = blue,
                textDark = textDark,
                textGray = textGray
            )

            Spacer(modifier = Modifier.height(24.dp))

            RatingCard(
                rating = rating,
                onRatingChange = { rating = it },
                review = review,
                onReviewChange = { review = it },
                textDark = textDark,
                textGray = textGray
            )

            Spacer(modifier = Modifier.height(24.dp))

            AddFriendCard(
                selected = addFriend,
                onSelect = { addFriend = it },
                blue = blue,
                green = green,
                textDark = textDark,
                textGray = textGray
            )

            Spacer(modifier = Modifier.height(28.dp))

            Button(
                onClick = {
                    // aksi ketika tombol Nongkrong lagi ditekan
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                shape = RoundedCornerShape(32.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = lightBlue
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "Nongkrong lagi?",
                    color = Color.White,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            TextButton(
                onClick = {
                    // aksi lewati
                }
            ) {
                Text(
                    text = "Lewati untuk sekarang",
                    fontSize = 20.sp,
                    color = textGray
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun HeaderBar(
    blue: Color,
    onCloseClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(92.dp)
            .background(Color(0xFFF7F9FC))
            .padding(horizontal = 22.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Close",
            tint = Color(0xFF222833),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(32.dp)
                .clickable(onClick = onCloseClick)
        )

        
    }

    HorizontalDivider(
        color = Color(0xFFE2E6ED),
        thickness = 1.dp
    )
}

@Composable
fun ProfileCard(
    blue: Color,
    textDark: Color,
    textGray: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(82.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE3E9F2)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "AR",
                    color = blue,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(18.dp))

            Column {
                Text(
                    text = "Adrian Rafe",
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Bold,
                    color = textDark
                )

                Text(
                    text = "Aktivitas: Lari Pagi di GBK",
                    fontSize = 17.sp,
                    color = textGray
                )

                Spacer(modifier = Modifier.height(8.dp))

                Surface(
                    shape = RoundedCornerShape(50),
                    color = Color(0xFFEAF4FF)
                ) {
                    Text(
                        text = "⚡ Energi Positif",
                        modifier = Modifier.padding(
                            horizontal = 12.dp,
                            vertical = 5.dp
                        ),
                        fontSize = 16.sp,
                        color = blue,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
fun RatingCard(
    rating: Int,
    onRatingChange: (Int) -> Unit,
    review: String,
    onReviewChange: (String) -> Unit,
    textDark: Color,
    textGray: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(22.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Beri Nilai Pertemuan",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                color = textDark
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                for (i in 1..5) {
                    IconButton(
                        onClick = {
                            onRatingChange(i)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Star,
                            contentDescription = "Star $i",
                            tint = if (i <= rating) Color(0xFFFFC107) else Color(0xFFC8CFD9),
                            modifier = Modifier.size(42.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Review Singkat (Opsional)",
                fontSize = 18.sp,
                color = textGray,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = review,
                onValueChange = onReviewChange,
                placeholder = {
                    Text(
                        text = "Tulis pengalaman menarikmu...",
                        color = Color(0xFF8A92A0)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFB8C2D1),
                    unfocusedBorderColor = Color(0xFFB8C2D1),
                    focusedContainerColor = Color(0xFFF7F9FC),
                    unfocusedContainerColor = Color(0xFFF7F9FC)
                )
            )
        }
    }
}

@Composable
fun AddFriendCard(
    selected: Boolean?,
    onSelect: (Boolean) -> Unit,
    blue: Color,
    green: Color,
    textDark: Color,
    textGray: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Row(
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(58.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF9CF29B)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color(0xFF157A2F),
                        modifier = Modifier.size(32.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = "Tambah sebagai teman?",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = textDark
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Kamu bisa melihat aktivitas Budi\nselanjutnya jika berteman.",
                        fontSize = 18.sp,
                        color = textGray,
                        lineHeight = 25.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(26.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                OutlinedButton(
                    onClick = {
                        onSelect(false)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = RoundedCornerShape(28.dp),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        width = 1.5.dp
                    ),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = blue,
                        containerColor = if (selected == false) Color(0xFFEAF4FF) else Color.White
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Tidak",
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = {
                        onSelect(true)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = RoundedCornerShape(28.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = green
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier.size(26.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Ya",
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
