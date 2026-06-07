package com.example.temuin

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OnboardingScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var hobbyInput by remember { mutableStateOf("") }
    val selectedInterests = remember { mutableStateListOf("Kopi", "Musik") }
    val hobbies = remember { mutableStateListOf("Fotografi Analog") }
    var selectedStatus by remember { mutableStateOf("Mahasiswa") }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = PageBackground
    ) {
        Box(Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .navigationBarsPadding()
                    .imePadding()
            ) {
                TopBar(onBackClick = onBackClick)
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 28.dp)
                        .padding(top = 38.dp, bottom = 116.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ProfilePromptIcon()
                    Spacer(Modifier.height(18.dp))
                    Text(
                        text = "Bantu kami mencarikan teman aktivitas yang cocok",
                        color = TextSlate,
                        fontSize = 15.sp,
                        letterSpacing = 0.sp
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "untukmu.",
                        color = TextSlate,
                        fontSize = 15.sp,
                        letterSpacing = 0.sp
                    )
                    Spacer(Modifier.height(34.dp))
                    SectionCard {
                        Text(
                            text = "Minat Utama",
                            color = TextDark,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 0.sp
                        )
                        Spacer(Modifier.height(22.dp))
                        Text(
                            text = "Pilih beberapa topik yang kamu sukai.",
                            color = TextSlate,
                            fontSize = 16.sp,
                            letterSpacing = 0.sp
                        )
                        Spacer(Modifier.height(18.dp))
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            InterestChip("Kopi", "c", selectedInterests)
                            InterestChip("Olahraga", "r", selectedInterests)
                            InterestChip("Musik", "n", selectedInterests)
                            InterestChip("Teknologi", "d", selectedInterests)
                            InterestChip("Seni", "p", selectedInterests)
                            InterestChip("Traveling", "a", selectedInterests)
                        }
                    }
                    Spacer(Modifier.height(26.dp))
                    SectionCard {
                        Text(
                            text = "Hobi Spesifik",
                            color = TextDark,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 0.sp
                        )
                        Spacer(Modifier.height(22.dp))
                        Text(
                            text = "Tambahkan hobi spesifikmu (misal: Fotografi",
                            color = TextSlate,
                            fontSize = 16.sp,
                            letterSpacing = 0.sp
                        )
                        Spacer(Modifier.height(2.dp))
                        Text(
                            text = "Analog, Lari Maraton).",
                            color = TextSlate,
                            fontSize = 16.sp,
                            letterSpacing = 0.sp
                        )
                        Spacer(Modifier.height(18.dp))
                        HobbyInput(
                            value = hobbyInput,
                            onValueChange = { hobbyInput = it }
                        )
                        Spacer(Modifier.height(14.dp))
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            hobbies.forEach { hobby ->
                                HobbyTag(
                                    text = hobby,
                                    onRemove = { hobbies.remove(hobby) }
                                )
                            }
                        }
                    }
                    Spacer(Modifier.height(26.dp))
                    SectionCard {
                        Text(
                            text = "Status Kesibukan",
                            color = TextDark,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 0.sp
                        )
                        Spacer(Modifier.height(22.dp))
                        StatusOption(
                            title = "Mahasiswa",
                            subtitle = "Jadwal fleksibel, sering di kampus.",
                            selected = selectedStatus == "Mahasiswa",
                            onClick = { selectedStatus = "Mahasiswa" }
                        )
                        Spacer(Modifier.height(12.dp))
                        StatusOption(
                            title = "Pekerja",
                            subtitle = "Jadwal 9-5, aktif di akhir pekan.",
                            selected = selectedStatus == "Pekerja",
                            onClick = { selectedStatus = "Pekerja" }
                        )
                        Spacer(Modifier.height(12.dp))
                        StatusOption(
                            title = "Freelance",
                            subtitle = "Waktu luang yang sangat dinamis.",
                            selected = selectedStatus == "Freelance",
                            onClick = { selectedStatus = "Freelance" }
                        )
                        Spacer(Modifier.height(12.dp))
                        StatusOption(
                            title = "Lainnya",
                            subtitle = null,
                            selected = selectedStatus == "Lainnya",
                            onClick = { selectedStatus = "Lainnya" }
                        )
                    }
                }
            }
            BottomActionBar(
                modifier = Modifier.align(Alignment.BottomCenter),
                onClick = {}
            )
        }
    }
}

@Composable
private fun TopBar(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(Color(0xFFF7FAFF))
            .border(1.dp, Color(0xFFE3E8F0))
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .clickable(onClick = onBackClick),
            contentAlignment = Alignment.Center
        ) {
            BackIcon(Modifier.size(24.dp), Color(0xFF171B22))
        }
    }
}

@Composable
private fun ProfilePromptIcon() {
    Box(
        modifier = Modifier
            .size(96.dp)
            .clip(CircleShape)
            .background(Color(0xFFE3E7EF)),
        contentAlignment = Alignment.Center
    ) {
        UserAddIcon(Modifier.size(44.dp), Color(0xFF737D8B))
    }
}

@Composable
private fun SectionCard(content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 26.dp),
            content = content
        )
    }
}

@Composable
private fun InterestChip(label: String, icon: String, selectedInterests: MutableList<String>) {
    val selected = label in selectedInterests
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(24.dp))
            .background(if (selected) ButtonBlue else Color(0xFFEFF7FF))
            .border(
                width = 1.dp,
                color = if (selected) ButtonBlue else Color(0xFFCDE6F8),
                shape = RoundedCornerShape(24.dp)
            )
            .clickable {
                if (selected) selectedInterests.remove(label) else selectedInterests.add(label)
            }
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = icon,
            color = if (selected) Color(0xFF074E78) else TemuinBlue,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.sp
        )
        Spacer(Modifier.width(9.dp))
        Text(
            text = label,
            color = if (selected) Color(0xFF074E78) else TemuinBlue,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.sp
        )
    }
}

@Composable
private fun HobbyInput(value: String, onValueChange: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(7.dp))
            .background(FieldFill)
            .border(1.1.dp, Color(0xFFBFC9D8), RoundedCornerShape(7.dp))
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TagIcon(Modifier.size(22.dp), Color(0xFF7A8491))
        Spacer(Modifier.width(14.dp))
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.weight(1f),
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(
                color = TextDark,
                fontSize = 16.sp,
                letterSpacing = 0.sp
            ),
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(
                        text = "Ketik hobi dan tekan Enter",
                        color = IconMuted,
                        fontSize = 16.sp,
                        letterSpacing = 0.sp
                    )
                }
                innerTextField()
            }
        )
    }
}

@Composable
private fun HobbyTag(text: String, onRemove: () -> Unit) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(Color(0xFFE8EDF5))
            .border(1.dp, Color(0xFFD3DAE5), RoundedCornerShape(6.dp))
            .padding(horizontal = 12.dp, vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            color = TextSlate,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.sp
        )
        Spacer(Modifier.width(6.dp))
        Text(
            text = "x",
            color = TextSlate,
            fontSize = 14.sp,
            modifier = Modifier.clickable(onClick = onRemove),
            letterSpacing = 0.sp
        )
    }
}

@Composable
private fun StatusOption(
    title: String,
    subtitle: String?,
    selected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(7.dp))
            .background(if (selected) Color(0xFFEAF5FF) else Color.White)
            .border(
                width = if (selected) 2.dp else 1.dp,
                color = if (selected) TemuinBlue else Color(0xFFDDE3EC),
                shape = RoundedCornerShape(7.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 18.dp, vertical = 18.dp)
    ) {
        Text(
            text = title,
            color = TextDark,
            fontSize = 22.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 0.sp
        )
        if (subtitle != null) {
            Spacer(Modifier.height(6.dp))
            Text(
                text = subtitle,
                color = TextSlate,
                fontSize = 15.sp,
                letterSpacing = 0.sp
            )
        }
    }
}

@Composable
private fun BottomActionBar(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Color.White,
        shadowElevation = 8.dp
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .navigationBarsPadding()
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .fillMaxWidth()
                .height(58.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
        ) {
            Text(
                text = "Simpan & Lanjutkan",
                color = Color(0xFF064D78),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.sp
            )
            Spacer(Modifier.width(10.dp))
            ArrowIcon(Modifier.size(26.dp), Color(0xFF064D78))
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
private fun ArrowIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        drawLine(color, Offset(size.width * 0.1f, size.height * 0.5f), Offset(size.width * 0.86f, size.height * 0.5f), 3.dp.toPx(), StrokeCap.Round)
        drawLine(color, Offset(size.width * 0.6f, size.height * 0.2f), Offset(size.width * 0.86f, size.height * 0.5f), 3.dp.toPx(), StrokeCap.Round)
        drawLine(color, Offset(size.width * 0.6f, size.height * 0.8f), Offset(size.width * 0.86f, size.height * 0.5f), 3.dp.toPx(), StrokeCap.Round)
    }
}

@Composable
private fun UserAddIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        drawCircle(
            color = color,
            radius = size.minDimension * 0.14f,
            center = Offset(size.width * 0.42f, size.height * 0.28f),
            style = Stroke(3.dp.toPx())
        )
        drawRoundRect(
            color = color,
            topLeft = Offset(size.width * 0.18f, size.height * 0.56f),
            size = Size(size.width * 0.48f, size.height * 0.22f),
            cornerRadius = CornerRadius(3.dp.toPx()),
            style = Stroke(3.dp.toPx())
        )
        drawLine(color, Offset(size.width * 0.74f, size.height * 0.24f), Offset(size.width * 0.74f, size.height * 0.48f), 3.dp.toPx(), StrokeCap.Round)
        drawLine(color, Offset(size.width * 0.62f, size.height * 0.36f), Offset(size.width * 0.86f, size.height * 0.36f), 3.dp.toPx(), StrokeCap.Round)
    }
}

@Composable
private fun TagIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(2.8.dp.toPx(), cap = StrokeCap.Round)
        drawRoundRect(
            color = color,
            topLeft = Offset(size.width * 0.12f, size.height * 0.18f),
            size = Size(size.width * 0.56f, size.height * 0.56f),
            cornerRadius = CornerRadius(2.dp.toPx()),
            style = stroke
        )
        drawLine(
            color = color,
            start = Offset(size.width * 0.68f, size.height * 0.74f),
            end = Offset(size.width * 0.9f, size.height * 0.52f),
            strokeWidth = 2.8.dp.toPx(),
            cap = StrokeCap.Round
        )
        drawCircle(
            color = color,
            radius = size.minDimension * 0.055f,
            center = Offset(size.width * 0.28f, size.height * 0.34f)
        )
    }
}

private val PageBackground = Color(0xFFF5F9FF)
private val TextDark = Color(0xFF20242C)
private val TextSlate = Color(0xFF424B5B)
private val TemuinBlue = Color(0xFF086DA5)
private val ButtonBlue = Color(0xFF47A9EF)
private val IconMuted = Color(0xFFB8C2D0)
private val FieldFill = Color(0xFFF1F4FA)
