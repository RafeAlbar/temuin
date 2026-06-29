package com.example.temuin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.temuin.ui.theme.TemuinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TemuinTheme {
                var destination by remember { mutableStateOf(AppDestination.Auth) }
                var selectedProfile by remember { mutableStateOf<RecommendationProfile?>(null) }
                var invitationPlan by remember { mutableStateOf<InvitationPlan?>(null) }
                var selectedActivityInvitation by remember { mutableStateOf(defaultActivityInvitationPlan()) }
                var showConfirmedLocation by remember { mutableStateOf(true) }
                var chatBackDestination by remember { mutableStateOf(AppDestination.CreateInvitation) }
                var locationBackDestination by remember { mutableStateOf(AppDestination.InvitationConfirmed) }
                var participantsBackDestination by remember { mutableStateOf(AppDestination.ActivityConfirmed) }

                when (destination) {
                    AppDestination.Auth -> TemuinLoginScreen(
                        onAuthenticated = { destination = AppDestination.Onboarding }
                    )

                    AppDestination.Onboarding -> OnboardingScreen(
                        onBackClick = { destination = AppDestination.Auth },
                        onContinueClick = { destination = AppDestination.Home }
                    )

                    AppDestination.Home -> HomeScreen(
                        onRecommendationClick = { destination = AppDestination.Recommendations },
                        onFilterClick = { destination = AppDestination.FilterFriends },
                        onPopularActivityClick = { destination = AppDestination.PopularActivities },
                        onHomeClick = { destination = AppDestination.Home },
                        onFriendsClick = { destination = AppDestination.FilterFriends },
                        onActivitiesClick = { destination = AppDestination.MyActivities },
                        onMessagesClick = { destination = AppDestination.Messages },
                        onProfileClick = { destination = AppDestination.Profile }
                    )

                    AppDestination.FilterFriends -> FriendFilterScreen(
                        onInviteClick = { profile ->
                            selectedProfile = profile
                            destination = AppDestination.ProfileDetail
                        },
                        onHomeClick = { destination = AppDestination.Home },
                        onFriendsClick = { destination = AppDestination.FilterFriends },
                        onActivitiesClick = { destination = AppDestination.MyActivities },
                        onMessagesClick = { destination = AppDestination.Messages },
                        onProfileClick = { destination = AppDestination.Profile }
                    )

                    AppDestination.Recommendations -> RekomendasiScreen(
                        onHomeClick = { destination = AppDestination.Home },
                        onFriendsClick = { destination = AppDestination.FilterFriends },
                        onActivitiesClick = { destination = AppDestination.MyActivities },
                        onMessagesClick = { destination = AppDestination.Messages },
                        onProfileClick = { destination = AppDestination.Profile },
                        onDetailClick = { profile ->
                            selectedProfile = profile
                            destination = AppDestination.ProfileDetail
                        }
                    )

                    AppDestination.ProfileDetail -> selectedProfile?.let { profile ->
                        LihatDetailScreen(
                            profile = profile,
                            onBackClick = { destination = AppDestination.Recommendations },
                            onInviteClick = { destination = AppDestination.CreateInvitation }
                        )
                    } ?: RekomendasiScreen(
                        onHomeClick = { destination = AppDestination.Home },
                        onFriendsClick = { destination = AppDestination.FilterFriends },
                        onActivitiesClick = { destination = AppDestination.MyActivities },
                        onMessagesClick = { destination = AppDestination.Messages },
                        onProfileClick = { destination = AppDestination.Profile },
                        onDetailClick = { profile ->
                            selectedProfile = profile
                            destination = AppDestination.ProfileDetail
                        }
                    )

                    AppDestination.CreateInvitation -> selectedProfile?.let { profile ->
                        BuatAjakanScreen(
                            profile = profile,
                            onBackClick = { destination = AppDestination.ProfileDetail },
                            onCreateClick = { plan ->
                                invitationPlan = plan
                                chatBackDestination = AppDestination.CreateInvitation
                                destination = AppDestination.ShortChat
                            }
                        )
                    } ?: RekomendasiScreen(
                        onHomeClick = { destination = AppDestination.Home },
                        onFriendsClick = { destination = AppDestination.FilterFriends },
                        onActivitiesClick = { destination = AppDestination.MyActivities },
                        onMessagesClick = { destination = AppDestination.Messages },
                        onProfileClick = { destination = AppDestination.Profile },
                        onDetailClick = { profile ->
                            selectedProfile = profile
                            destination = AppDestination.ProfileDetail
                        }
                    )

                    AppDestination.ShortChat -> {
                        val profile = selectedProfile
                        val plan = invitationPlan
                        if (profile != null && plan != null) {
                            ChatSingkatScreen(
                                profile = profile,
                                invitation = plan,
                                onBackClick = { destination = chatBackDestination },
                                onMoveToInviteePovClick = { destination = AppDestination.InvitationConfirmation }
                            )
                        } else {
                            destination = AppDestination.Recommendations
                        }
                    }

                    AppDestination.InvitationConfirmation -> {
                        val profile = selectedProfile
                        val plan = invitationPlan
                        if (profile != null && plan != null) {
                            KonfirmasiAjakanScreen(
                                profile = profile,
                                invitation = plan,
                                onBackClick = { destination = AppDestination.ShortChat },
                                onAcceptClick = {
                                    showConfirmedLocation = true
                                    destination = AppDestination.InvitationConfirmed
                                },
                                onChatFirstClick = {
                                    showConfirmedLocation = false
                                    destination = AppDestination.InvitationConfirmed
                                }
                            )
                        } else {
                            destination = AppDestination.Recommendations
                        }
                    }

                    AppDestination.InvitationConfirmed -> {
                        val profile = selectedProfile
                        val plan = invitationPlan
                        if (profile != null && plan != null) {
                            AjakanDikonfirmasiScreen(
                                profile = profile,
                                invitation = plan,
                                showLocation = showConfirmedLocation,
                                onBackClick = { destination = AppDestination.InvitationConfirmation },
                                onHeaderClick = {
                                    participantsBackDestination = AppDestination.InvitationConfirmed
                                    destination = AppDestination.Participants
                                },
                                onLocationClick = {
                                    locationBackDestination = AppDestination.InvitationConfirmed
                                    destination = AppDestination.Location
                                }
                            )
                        } else {
                            destination = AppDestination.Recommendations
                        }
                    }

                    AppDestination.Location -> MeetLocationScreen(
                        onBackClick = { destination = locationBackDestination },
                        onEndSessionClick = { destination = AppDestination.RatingMeet }
                    )

                    AppDestination.Participants -> ParticipantsScreen(
                        onBackClick = { destination = participantsBackDestination }
                    )

                    AppDestination.RatingMeet -> MeetingFeedbackScreen(
                        onCloseClick = { destination = AppDestination.Home }
                    )

                    AppDestination.PopularActivities -> PopularActivityScreen(
                        onBackClick = { destination = AppDestination.Home },
                        onJoinActivityClick = { activity ->
                            selectedActivityInvitation = activity
                            destination = AppDestination.ActivityDetail
                        },
                        onHomeClick = { destination = AppDestination.Home },
                        onFriendsClick = { destination = AppDestination.FilterFriends },
                        onActivitiesClick = { destination = AppDestination.MyActivities },
                        onMessagesClick = { destination = AppDestination.Messages },
                        onProfileClick = { destination = AppDestination.Profile }
                    )

                    AppDestination.MyActivities -> MyActivitiesScreen(
                        onHomeClick = { destination = AppDestination.Home },
                        onFriendsClick = { destination = AppDestination.FilterFriends },
                        onActivitiesClick = { destination = AppDestination.MyActivities },
                        onMessagesClick = { destination = AppDestination.Messages },
                        onProfileClick = { destination = AppDestination.Profile },
                        onAddActivityClick = { destination = AppDestination.AddActivity }
                    )

                    AppDestination.AddActivity -> TambahAktivitasScreen(
                        onCloseClick = { destination = AppDestination.MyActivities },
                        onCreateActivityClick = { destination = AppDestination.MyActivities }
                    )

                    AppDestination.Messages -> MessagesPlaceholderScreen(
                        onHomeClick = { destination = AppDestination.Home },
                        onFriendsClick = { destination = AppDestination.FilterFriends },
                        onActivitiesClick = { destination = AppDestination.MyActivities },
                        onMessagesClick = { destination = AppDestination.Messages },
                        onProfileClick = { destination = AppDestination.Profile },
                        onChatClick = {
                            selectedProfile = messageChatProfile
                            invitationPlan = messageChatInvitationPlan()
                            chatBackDestination = AppDestination.Messages
                            destination = AppDestination.ShortChat
                        }
                    )

                    AppDestination.Profile -> ProfilePlaceholderScreen(
                        onHomeClick = { destination = AppDestination.Home },
                        onFriendsClick = { destination = AppDestination.FilterFriends },
                        onActivitiesClick = { destination = AppDestination.MyActivities },
                        onMessagesClick = { destination = AppDestination.Messages },
                        onProfileClick = { destination = AppDestination.Profile }
                    )

                    AppDestination.ActivityDetail -> ActivityDetailScreen(
                        invitation = selectedActivityInvitation,
                        onBackClick = { destination = AppDestination.PopularActivities },
                        onJoinActivityClick = { destination = AppDestination.ActivityConfirmed }
                    )

                    AppDestination.ActivityConfirmed -> AjakanDikonfirmasiScreen(
                        profile = activityChatProfile,
                        invitation = selectedActivityInvitation,
                        showLocation = true,
                        chatName = selectedActivityInvitation.title,
                        chatAvatarText = activityAvatarText(selectedActivityInvitation.title),
                        chatOnlineText = "Aktivitas dikonfirmasi",
                        onBackClick = { destination = AppDestination.ActivityDetail },
                        onHeaderClick = {
                            participantsBackDestination = AppDestination.ActivityConfirmed
                            destination = AppDestination.Participants
                        },
                        onLocationClick = {
                            locationBackDestination = AppDestination.ActivityConfirmed
                            destination = AppDestination.Location
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TemuinLoginScreen(
    modifier: Modifier = Modifier,
    onAuthenticated: () -> Unit = {}
) {
    var selectedTab by remember { mutableStateOf(AuthTab.Login) }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = Color(0xFFF5F9FF)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (selectedTab == AuthTab.Login) {
                Spacer(Modifier.height(38.dp))
                AppMark()
                Spacer(Modifier.height(18.dp))
                BrandTitle()
                Spacer(Modifier.height(32.dp))
                LoginCard(
                    selectedTab = selectedTab,
                    onTabSelected = { selectedTab = it },
                    onLoginClick = onAuthenticated
                )
                Spacer(Modifier.height(20.dp))
                TipCard()
            } else {
                Spacer(Modifier.height(56.dp))
                RegisterCard(
                    onRegisterClick = onAuthenticated,
                    onLoginClick = { selectedTab = AuthTab.Login }
                )
            }
        }
    }
}

@Composable
private fun BrandTitle() {
    Text(
        text = "temu.in",
        color = TemuinBlue,
        fontSize = 32.sp,
        fontWeight = FontWeight.ExtraBold,
        letterSpacing = 0.sp
    )
    Spacer(Modifier.height(8.dp))
    Text(
        text = "Temukan teman & aktivitas seru di sekitarmu.",
        color = TextSlate,
        fontSize = 17.sp,
        letterSpacing = 0.sp
    )
}

@Composable
private fun AppMark() {
    Box(
        modifier = Modifier
            .size(74.dp)
            .rotate(3f)
            .clip(RoundedCornerShape(18.dp))
            .background(TemuinBlue),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            PaperPlaneIcon(
                modifier = Modifier
                    .size(25.dp)
                    .rotate(-3f),
                color = Color.White,
                strokeWidth = 2.6.dp
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = "Temu.in",
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 13.sp,
                letterSpacing = 0.sp
            )
            Text(
                text = "FIND YOUR PEOPLE",
                color = Color(0xFFB9E3FF),
                fontSize = 6.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.sp
            )
        }
    }
}

@Composable
private fun LoginCard(
    selectedTab: AuthTab,
    onTabSelected: (AuthTab) -> Unit,
    onLoginClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 28.dp)
        ) {
            AuthTabs(
                selectedTab = selectedTab,
                onTabSelected = onTabSelected
            )
            Spacer(Modifier.height(28.dp))
            FieldLabel("Email")
            Spacer(Modifier.height(8.dp))
            AuthTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = "nama@email.com",
                leadingIcon = { MailIcon(Modifier.size(20.dp), IconMuted) }
            )
            Spacer(Modifier.height(22.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                FieldLabel("Password")
                Text(
                    text = "Lupa?",
                    color = TemuinBlue,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.sp
                )
            }
            Spacer(Modifier.height(8.dp))
            AuthTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = "••••••••",
                leadingIcon = { LockIcon(Modifier.size(20.dp), IconMuted) },
                trailingIcon = { EyeOffIcon(Modifier.size(22.dp), IconMuted) },
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(Modifier.height(28.dp))
            Button(
                onClick = onLoginClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
            ) {
                Text(
                    text = if (selectedTab == AuthTab.Login) "Masuk" else "Daftar",
                    color = Color(0xFF064D78),
                    fontSize = 20.sp,
                    letterSpacing = 0.sp
                )
                Spacer(Modifier.width(10.dp))
                ArrowRightIcon(Modifier.size(26.dp), Color(0xFF064D78))
            }
            Spacer(Modifier.height(48.dp))
            DividerText()
            Spacer(Modifier.height(32.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                SocialButton(
                    text = "Google",
                    modifier = Modifier.weight(1f),
                    icon = { GoogleMark(Modifier.size(24.dp)) }
                )
                SocialButton(
                    text = "Facebook",
                    modifier = Modifier.weight(1f),
                    icon = { FacebookMark(Modifier.size(24.dp)) }
                )
            }
        }
    }
}

@Composable
private fun RegisterCard(onRegisterClick: () -> Unit, onLoginClick: () -> Unit) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var agreed by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 34.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppMark()
            Spacer(Modifier.height(18.dp))
            BrandTitle()
            Spacer(Modifier.height(42.dp))
            Text(
                text = "Buat Akun Baru",
                color = Color(0xFF171B22),
                fontSize = 29.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 0.sp
            )
            Spacer(Modifier.height(14.dp))
            Text(
                text = "Bergabunglah untuk mulai merencanakan",
                color = TextSlate,
                fontSize = 17.sp,
                letterSpacing = 0.sp
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "aktivitas seru bersama teman-teman.",
                color = TextSlate,
                fontSize = 17.sp,
                letterSpacing = 0.sp
            )
            Spacer(Modifier.height(30.dp))
            RegisterField(
                label = "Nama Lengkap",
                value = fullName,
                onValueChange = { fullName = it },
                placeholder = "Masukkan nama lengkap Anda",
                leadingIcon = { UserIcon(Modifier.size(20.dp), IconMuted) }
            )
            Spacer(Modifier.height(18.dp))
            RegisterField(
                label = "Email",
                value = email,
                onValueChange = { email = it },
                placeholder = "contoh@email.com",
                leadingIcon = { MailIcon(Modifier.size(20.dp), IconMuted) }
            )
            Spacer(Modifier.height(18.dp))
            RegisterField(
                label = "Password",
                value = password,
                onValueChange = { password = it },
                placeholder = "Minimal 8 karakter",
                leadingIcon = { LockIcon(Modifier.size(20.dp), IconMuted) },
                trailingIcon = { EyeOffIcon(Modifier.size(22.dp), IconMuted) },
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(Modifier.height(18.dp))
            RegisterField(
                label = "Konfirmasi Password",
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                placeholder = "Ulangi password Anda",
                leadingIcon = { LockIcon(Modifier.size(20.dp), IconMuted) },
                trailingIcon = { EyeOffIcon(Modifier.size(22.dp), IconMuted) },
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(Modifier.height(18.dp))
            TermsRow(
                checked = agreed,
                onCheckedChange = { agreed = it }
            )
            Spacer(Modifier.height(24.dp))
            Button(
                onClick = onRegisterClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
            ) {
                Text(
                    text = "Daftar Sekarang",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.sp
                )
                Spacer(Modifier.width(10.dp))
                ArrowRightIcon(Modifier.size(26.dp), Color.White)
            }
            Spacer(Modifier.height(42.dp))
            DividerText(text = "atau daftar dengan")
            Spacer(Modifier.height(28.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                RegisterSocialButton("Google", Modifier.weight(1f))
                RegisterSocialButton("Apple", Modifier.weight(1f))
            }
            Spacer(Modifier.height(32.dp))
            Text(
                text = "Sudah punya akun?",
                color = TextSlate,
                fontSize = 17.sp,
                letterSpacing = 0.sp
            )
            Spacer(Modifier.height(10.dp))
            Button(
                onClick = onLoginClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.2.dp, Color(0xFFD8DEE9)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF9FBFF),
                    contentColor = TemuinBlue
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 10.dp)
            ) {
                Text(
                    text = "Masuk ke Akun",
                    color = TemuinBlue,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.sp
                )
            }
        }
    }
}

@Composable
private fun RegisterField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: @Composable () -> Unit,
    trailingIcon: (@Composable () -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        FieldLabel(label)
        Spacer(Modifier.height(8.dp))
        AuthTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            visualTransformation = visualTransformation
        )
    }
}

@Composable
private fun TermsRow(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.size(28.dp),
            colors = CheckboxDefaults.colors(
                checkedColor = TemuinBlue,
                uncheckedColor = IconMuted,
                checkmarkColor = Color.White
            )
        )
        Spacer(Modifier.width(14.dp))
        Column {
            Row {
                Text("Saya setuju dengan ", color = TextSlate, fontSize = 17.sp, letterSpacing = 0.sp)
                Text("Syarat Ketentuan", color = TemuinBlue, fontSize = 17.sp, letterSpacing = 0.sp)
            }
            Row {
                Text("dan ", color = TextSlate, fontSize = 17.sp, letterSpacing = 0.sp)
                Text("Kebijakan Privasi.", color = TemuinBlue, fontSize = 17.sp, letterSpacing = 0.sp)
            }
        }
    }
}

@Composable
private fun AuthTabs(selectedTab: AuthTab, onTabSelected: (AuthTab) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFFE3E7EF))
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        AuthTabButton(
            text = "Masuk",
            selected = selectedTab == AuthTab.Login,
            onClick = { onTabSelected(AuthTab.Login) },
            modifier = Modifier.weight(1f)
        )
        AuthTabButton(
            text = "Daftar Baru",
            selected = selectedTab == AuthTab.Register,
            onClick = { onTabSelected(AuthTab.Register) },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun AuthTabButton(text: String, selected: Boolean, onClick: () -> Unit, modifier: Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxSize(),
        shape = RoundedCornerShape(11.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) Color(0xFFF7F9FD) else Color.Transparent,
            contentColor = if (selected) TemuinBlue else TextSlate
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = if (selected) 2.dp else 0.dp),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)
    ) {
        Text(text = text, fontSize = 16.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.sp)
    }
}

@Composable
private fun FieldLabel(text: String) {
    Text(
        text = text,
        color = TextSlate,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.sp
    )
}

@Composable
private fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    trailingIcon: (@Composable () -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(FieldFill)
            .border(1.2.dp, Color(0xFFD7DEE9), RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        leadingIcon()
        Spacer(Modifier.width(16.dp))
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.weight(1f),
            singleLine = true,
            visualTransformation = visualTransformation,
            textStyle = LocalTextStyle.current.copy(
                color = Color(0xFF303847),
                fontSize = 17.sp,
                letterSpacing = 0.sp
            ),
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = IconMuted,
                        fontSize = 17.sp,
                        letterSpacing = 0.sp
                    )
                }
                innerTextField()
            }
        )
        if (trailingIcon != null) {
            Spacer(Modifier.width(12.dp))
            trailingIcon()
        }
    }
}

@Composable
private fun DividerText(text: String = "atau lanjutkan dengan") {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .weight(1f)
                .height(1.5.dp)
                .background(Color(0xFFDDE3EC))
        )
        Text(
            text = text,
            color = IconMuted,
            modifier = Modifier.padding(horizontal = 18.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.sp
        )
        Box(
            Modifier
                .weight(1f)
                .height(1.5.dp)
                .background(Color(0xFFDDE3EC))
        )
    }
}

@Composable
private fun RegisterSocialButton(text: String, modifier: Modifier = Modifier) {
    Button(
        onClick = {},
        modifier = modifier.height(48.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.2.dp, Color(0xFFD8DEE9)),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFF9FBFF),
            contentColor = Color(0xFF20242C)
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 10.dp)
    ) {
        Text(text = text, fontSize = 15.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.sp)
    }
}

@Composable
private fun SocialButton(
    text: String,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit
) {
    Button(
        onClick = {},
        modifier = modifier.height(48.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.2.dp, Color(0xFFD8DEE9)),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFF9FBFF),
            contentColor = Color(0xFF20242C)
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 10.dp)
    ) {
        icon()
        Spacer(Modifier.width(10.dp))
        Text(text = text, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, letterSpacing = 0.sp)
    }
}

@Composable
private fun TipCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 122.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFEFF7FF))
            .border(1.2.dp, Color(0xFFC7E5FF), RoundedCornerShape(12.dp))
            .padding(18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(Color(0xFFC9E6FF)),
            contentAlignment = Alignment.Center
        ) {
            PeopleIcon(Modifier.size(30.dp), TemuinBlue)
        }
        Spacer(Modifier.width(18.dp))
        Column {
            Text(
                text = "Siap Nongkrong?",
                color = Color(0xFF20242C),
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.sp
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Lengkapi profil minat & hobi setelah\nmendaftar untuk rekomendasi teman\nterbaik.",
                color = TextSlate,
                fontSize = 17.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.sp
            )
        }
    }
}

@Composable
private fun PaperPlaneIcon(modifier: Modifier = Modifier, color: Color, strokeWidth: Dp) {
    Canvas(modifier = modifier) {
        val path = Path().apply {
            moveTo(size.width * 0.1f, size.height * 0.35f)
            lineTo(size.width * 0.88f, size.height * 0.12f)
            lineTo(size.width * 0.55f, size.height * 0.9f)
            lineTo(size.width * 0.42f, size.height * 0.54f)
            close()
        }
        drawPath(path, color, style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round))
        drawLine(color, Offset(size.width * 0.42f, size.height * 0.54f), Offset(size.width * 0.88f, size.height * 0.12f), strokeWidth.toPx(), StrokeCap.Round)
    }
}

@Composable
private fun UserIcon(modifier: Modifier = Modifier, color: Color) {
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
private fun MailIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        drawRoundRect(color, style = Stroke(3.dp.toPx()), cornerRadius = CornerRadius(2.dp.toPx()))
        drawLine(color, Offset(0f, size.height * 0.18f), Offset(size.width * 0.5f, size.height * 0.55f), 3.dp.toPx(), StrokeCap.Round)
        drawLine(color, Offset(size.width, size.height * 0.18f), Offset(size.width * 0.5f, size.height * 0.55f), 3.dp.toPx(), StrokeCap.Round)
    }
}

@Composable
private fun LockIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        drawRoundRect(
            color = color,
            topLeft = Offset(size.width * 0.15f, size.height * 0.42f),
            size = Size(size.width * 0.7f, size.height * 0.5f),
            cornerRadius = CornerRadius(3.dp.toPx()),
            style = Stroke(3.dp.toPx())
        )
        drawArc(
            color = color,
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = false,
            topLeft = Offset(size.width * 0.28f, size.height * 0.08f),
            size = Size(size.width * 0.44f, size.height * 0.58f),
            style = Stroke(3.dp.toPx(), cap = StrokeCap.Round)
        )
    }
}

@Composable
private fun EyeOffIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        drawArc(
            color = color,
            startAngle = 25f,
            sweepAngle = 130f,
            useCenter = false,
            topLeft = Offset(size.width * 0.08f, size.height * 0.18f),
            size = Size(size.width * 0.84f, size.height * 0.62f),
            style = Stroke(3.dp.toPx(), cap = StrokeCap.Round)
        )
        drawArc(
            color = color,
            startAngle = 205f,
            sweepAngle = 130f,
            useCenter = false,
            topLeft = Offset(size.width * 0.08f, size.height * 0.18f),
            size = Size(size.width * 0.84f, size.height * 0.62f),
            style = Stroke(3.dp.toPx(), cap = StrokeCap.Round)
        )
        drawCircle(color, radius = size.minDimension * 0.12f, center = Offset(size.width * 0.5f, size.height * 0.5f), style = Stroke(3.dp.toPx()))
        drawLine(color, Offset(size.width * 0.12f, size.height * 0.08f), Offset(size.width * 0.88f, size.height * 0.92f), 3.dp.toPx(), StrokeCap.Round)
    }
}

@Composable
private fun ArrowRightIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        drawLine(color, Offset(size.width * 0.1f, size.height * 0.5f), Offset(size.width * 0.86f, size.height * 0.5f), 3.dp.toPx(), StrokeCap.Round)
        drawLine(color, Offset(size.width * 0.6f, size.height * 0.2f), Offset(size.width * 0.86f, size.height * 0.5f), 3.dp.toPx(), StrokeCap.Round)
        drawLine(color, Offset(size.width * 0.6f, size.height * 0.8f), Offset(size.width * 0.86f, size.height * 0.5f), 3.dp.toPx(), StrokeCap.Round)
    }
}

@Composable
private fun GoogleMark(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(1.dp))
            .background(Color(0xFFE8ECF4)),
        contentAlignment = Alignment.Center
    ) {
        Text("G", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.sp)
    }
}

@Composable
private fun FacebookMark(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(Color(0xFF20242C)),
        contentAlignment = Alignment.Center
    ) {
        Text("f", color = Color.White, fontSize = 27.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.sp)
    }
}

@Composable
private fun PeopleIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        drawCircle(color, radius = size.minDimension * 0.13f, center = Offset(size.width * 0.5f, size.height * 0.28f))
        drawCircle(color, radius = size.minDimension * 0.1f, center = Offset(size.width * 0.23f, size.height * 0.38f))
        drawCircle(color, radius = size.minDimension * 0.1f, center = Offset(size.width * 0.77f, size.height * 0.38f))
        drawRoundRect(
            color = color,
            topLeft = Offset(size.width * 0.32f, size.height * 0.48f),
            size = Size(size.width * 0.36f, size.height * 0.28f),
            cornerRadius = CornerRadius(7.dp.toPx(), 7.dp.toPx())
        )
        drawRoundRect(
            color = color,
            topLeft = Offset(size.width * 0.06f, size.height * 0.57f),
            size = Size(size.width * 0.26f, size.height * 0.2f),
            cornerRadius = CornerRadius(6.dp.toPx(), 6.dp.toPx())
        )
        drawRoundRect(
            color = color,
            topLeft = Offset(size.width * 0.68f, size.height * 0.57f),
            size = Size(size.width * 0.26f, size.height * 0.2f),
            cornerRadius = CornerRadius(6.dp.toPx(), 6.dp.toPx())
        )
    }
}

private enum class AuthTab {
    Login,
    Register
}

private enum class AppDestination {
    Auth,
    Onboarding,
    Home,
    FilterFriends,
    Recommendations,
    ProfileDetail,
    CreateInvitation,
    ShortChat,
    InvitationConfirmation,
    InvitationConfirmed,
    Location,
    Participants,
    RatingMeet,
    PopularActivities,
    MyActivities,
    AddActivity,
    Messages,
    Profile,
    ActivityDetail,
    ActivityConfirmed
}

private fun defaultActivityInvitationPlan(): InvitationPlan {
    return InvitationPlan(
        title = "Kopi & Diskusi Santai",
        time = "Hari ini, 16:00 - 18:00 WIB",
        place = "Kopi Kenangan, Sudirman"
    )
}

private fun messageChatInvitationPlan(): InvitationPlan {
    return InvitationPlan(
        title = "Ngobrol Santai",
        time = "Hari ini, 14:20 WIB",
        place = "Chat temu.in"
    )
}

private fun activityAvatarText(title: String): String {
    return title
        .split(" ")
        .mapNotNull { it.firstOrNull()?.toString() }
        .take(2)
        .joinToString("")
        .ifBlank { "AK" }
}

private val activityChatProfile = RecommendationProfile(
    name = "Aktivitas",
    job = "Temu.in Activity",
    distance = "Sekitar kamu",
    match = "Dikonfirmasi",
    tags = listOf("Aktivitas"),
    avatarText = "AK",
    online = true
)

private val messageChatProfile = RecommendationProfile(
    name = "Bayu A",
    job = "Teman temu.in",
    distance = "Online",
    match = "Chat aktif",
    tags = listOf("Kopi", "Diskusi"),
    avatarText = "BA",
    online = true
)

@Preview(showBackground = true)
@Composable
fun TemuinLoginScreenPreview() {
    TemuinTheme {
        TemuinLoginScreen()
    }
}

private val TemuinBlue = Color(0xFF086DA5)
private val ButtonBlue = Color(0xFF47A9EF)
private val TextSlate = Color(0xFF424B5B)
private val IconMuted = Color(0xFFB8C2D0)
private val FieldFill = Color(0xFFF1F4FA)
