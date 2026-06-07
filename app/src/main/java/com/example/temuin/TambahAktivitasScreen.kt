package com.example.temuin

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TambahAktivitasScreen(
    onCloseClick: () -> Unit = {},
    onCreateActivityClick: () -> Unit = {}
) {
    val bg = Color(0xFFF5F8FE)
    val blue = Color(0xFF42A5F5)
    val darkBlue = Color(0xFF004D7A)
    val textDark = Color(0xFF20242C)
    val textSlate = Color(0xFF3F4653)
    val textMuted = Color(0xFF737C89)
    val border = Color(0xFFC0CAD8)

    var title by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var participantLimit by remember { mutableFloatStateOf(4f) }

    Scaffold(
        containerColor = bg,
        topBar = {
            TambahAktivitasTopBar(
                title = "Tambah Aktivitas",
                tint = Color(0xFF006AA6),
                textColor = textDark,
                onCloseClick = onCloseClick
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(bg)
                    .imePadding()
            ) {
                HorizontalDivider(color = Color(0xFFE1E7EF), thickness = DividerDefaults.Thickness)
                Button(
                    onClick = onCreateActivityClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 22.dp, vertical = 20.dp)
                        .height(60.dp),
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = blue,
                        contentColor = darkBlue
                    ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
                ) {
                    Text(
                        text = "Buat Aktivitas",
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.sp
                    )
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(bg),
            contentPadding = PaddingValues(horizontal = 22.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                ActivityFormField(
                    label = "Judul Aktivitas",
                    value = title,
                    onValueChange = { title = it },
                    placeholder = "Contoh: Ngopi Sore",
                    borderColor = border,
                    textColor = textDark,
                    placeholderColor = textMuted
                )
            }

            item {
                ActivityFormField(
                    label = "Kategori",
                    value = category,
                    onValueChange = { category = it },
                    placeholder = "Pilih Kategori",
                    borderColor = border,
                    textColor = textDark,
                    placeholderColor = textDark,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowDown,
                            contentDescription = null,
                            tint = textMuted,
                            modifier = Modifier.size(34.dp)
                        )
                    }
                )
            }

            item {
                Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                    ActivityFormField(
                        label = "Tanggal",
                        value = date,
                        onValueChange = { date = it },
                        placeholder = "mm/dd/yyyy",
                        borderColor = border,
                        textColor = textDark,
                        placeholderColor = textDark,
                        modifier = Modifier.weight(1f)
                    )
                    ActivityFormField(
                        label = "Waktu",
                        value = time,
                        onValueChange = { time = it },
                        placeholder = "--:-- --",
                        borderColor = border,
                        textColor = textDark,
                        placeholderColor = textDark,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                ActivityFormField(
                    label = "Lokasi",
                    value = location,
                    onValueChange = { location = it },
                    placeholder = "Cari tempat...",
                    borderColor = border,
                    textColor = textDark,
                    placeholderColor = textMuted,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.LocationOn,
                            contentDescription = null,
                            tint = textMuted,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                )
            }

            item {
                ActivityFormField(
                    label = "Deskripsi Singkat",
                    value = description,
                    onValueChange = { description = it },
                    placeholder = "Ceritakan sedikit tentang aktivitas ini...",
                    borderColor = border,
                    textColor = textDark,
                    placeholderColor = textMuted,
                    minHeight = 128.dp,
                    singleLine = false
                )
            }

            item {
                Column {
                    Text(
                        text = "Batas Peserta",
                        color = textSlate,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.sp
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(18.dp)
                    ) {
                        Slider(
                            value = participantLimit,
                            onValueChange = { participantLimit = it },
                            valueRange = 1f..12f,
                            steps = 10,
                            modifier = Modifier.weight(1f),
                            colors = SliderDefaults.colors(
                                thumbColor = blue,
                                activeTrackColor = blue,
                                inactiveTrackColor = Color(0xFFDDE3EC)
                            )
                        )
                        Text(
                            text = participantLimit.toInt().toString(),
                            color = Color(0xFF006AA6),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TambahAktivitasTopBar(
    title: String,
    tint: Color,
    textColor: Color,
    onCloseClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(78.dp)
            .background(Color(0xFFF6F8FC))
            .border(width = 1.dp, color = Color(0xFFE2E7EF)),
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            onClick = onCloseClick,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 18.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Tutup",
                tint = tint,
                modifier = Modifier.size(32.dp)
            )
        }
        Text(
            text = title,
            color = textColor,
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.sp
        )
    }
}

@Composable
private fun ActivityFormField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    borderColor: Color,
    textColor: Color,
    placeholderColor: Color,
    modifier: Modifier = Modifier,
    minHeight: androidx.compose.ui.unit.Dp = 58.dp,
    singleLine: Boolean = true,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            color = Color(0xFF3F4653),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(minHeight)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .border(1.dp, borderColor, RoundedCornerShape(8.dp))
                .padding(horizontal = 18.dp, vertical = if (singleLine) 0.dp else 16.dp),
            verticalAlignment = if (singleLine) Alignment.CenterVertically else Alignment.Top
        ) {
            if (leadingIcon != null) {
                leadingIcon()
                Spacer(modifier = Modifier.size(16.dp))
            }
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.weight(1f),
                singleLine = singleLine,
                cursorBrush = SolidColor(Color(0xFF006AA6)),
                textStyle = TextStyle(
                    color = textColor,
                    fontSize = 21.sp,
                    lineHeight = 28.sp,
                    letterSpacing = 0.sp
                ),
                decorationBox = { innerTextField ->
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = placeholderColor,
                            fontSize = 21.sp,
                            lineHeight = 28.sp,
                            letterSpacing = 0.sp
                        )
                    }
                    innerTextField()
                }
            )
            if (trailingIcon != null) {
                Spacer(modifier = Modifier.size(12.dp))
                trailingIcon()
            }
        }
    }
}
