package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    userRole: String,
    onNavigate: (String) -> Unit,
    onLogout: () -> Unit,
    onToggleTheme: () -> Unit,
    isDarkMode: Boolean
) {
    val primaryBlue = Color(0xFF1E3A8A)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("कोस्टा समाज झांसी - डैशबोर्ड", color = Color.White, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = primaryBlue),
                actions = {
                    IconButton(onClick = onToggleTheme, modifier = Modifier.testTag("btn_theme_toggle")) {
                        Icon(
                            imageVector = if (isDarkMode) Icons.Default.LightMode else Icons.Default.DarkMode,
                            contentDescription = "Theme Toggle",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = onLogout, modifier = Modifier.testTag("btn_logout")) {
                        Icon(imageVector = Icons.Default.Logout, contentDescription = "Logout", tint = Color.White)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF3F5FA))
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Welcome Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(primaryBlue.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(imageVector = Icons.Default.VerifiedUser, contentDescription = null, tint = primaryBlue)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = if (userRole == "admin") "स्वागत है, मास्टर एडमिन" else "स्वागत है, सम्मानित सदस्य",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1E293B)
                        )
                        Text(
                            text = "भूमिका: ${if (userRole == "admin") "संचालक / एडमिन" else "सक्रिय सदस्य"}",
                            fontSize = 13.sp,
                            color = Color.Gray
                        )
                    }
                }
            }

            // Quick Overview Summary Cards
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SummaryCard(
                    title = "कुल कोष बैलेंस",
                    value = "₹ 24,600",
                    icon = Icons.Default.AccountBalanceWallet,
                    color = Color(0xFF047857),
                    modifier = Modifier.weight(1f)
                )
                SummaryCard(
                    title = "सक्रिय सदस्य",
                    value = "142",
                    icon = Icons.Default.People,
                    color = Color(0xFF1D4ED8),
                    modifier = Modifier.weight(1f)
                )
            }

            Text(
                text = "सभी प्रबंधकीय एवं सामाजिक मॉड्यूल",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E293B)
            )

            // Module Grid
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    ModuleButton(
                        title = "खाता बही (Accounts)",
                        subtitle = "आय-व्यय लेज़र एवं रसीद",
                        icon = Icons.Default.ReceiptLong,
                        color = Color(0xFF047857),
                        modifier = Modifier.weight(1f),
                        testTag = "nav_accounts",
                        onClick = { onNavigate("accounts") }
                    )
                    ModuleButton(
                        title = "छात्र पंजीकरण",
                        subtitle = "मेधावी छात्र सूची व स्लिप",
                        icon = Icons.Default.School,
                        color = Color(0xFF1D4ED8),
                        modifier = Modifier.weight(1f),
                        testTag = "nav_students",
                        onClick = { onNavigate("students") }
                    )
                }

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    ModuleButton(
                        title = "सदस्य डायरेक्टरी",
                        subtitle = "सदस्यता एवं शुल्क स्थिति",
                        icon = Icons.Default.Badge,
                        color = Color(0xFFB45309),
                        modifier = Modifier.weight(1f),
                        testTag = "nav_members",
                        onClick = { onNavigate("members") }
                    )
                    ModuleButton(
                        title = "विवाह परिचय पोर्टल",
                        subtitle = "युवक-युवती परिचय बायोडाटा",
                        icon = Icons.Default.Favorite,
                        color = Color(0xFF9F1239),
                        modifier = Modifier.weight(1f),
                        testTag = "nav_matrimony",
                        onClick = { onNavigate("matrimony") }
                    )
                }

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    ModuleButton(
                        title = "सूचना पट (Notices)",
                        subtitle = "आगामी बैठकें व कार्यक्रम",
                        icon = Icons.Default.Campaign,
                        color = Color(0xFF6D28D9),
                        modifier = Modifier.weight(1f),
                        testTag = "nav_notices",
                        onClick = { onNavigate("notices") }
                    )
                    ModuleButton(
                        title = "कार्यकारिणी समिति",
                        subtitle = "पदाधिकारी परिचय",
                        icon = Icons.Default.Group,
                        color = Color(0xFF0F766E),
                        modifier = Modifier.weight(1f),
                        testTag = "nav_committee",
                        onClick = { onNavigate("committee") }
                    )
                }

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    ModuleButton(
                        title = "संविधान व गैलरी",
                        subtitle = "नियमावली एवं चित्र",
                        icon = Icons.Default.MenuBook,
                        color = Color(0xFFC2410C),
                        modifier = Modifier.weight(1f),
                        testTag = "nav_custom_tabs",
                        onClick = { onNavigate("custom_tabs") }
                    )
                    ModuleButton(
                        title = "सेटिंग्स व बैकअप",
                        subtitle = "डेटा प्रबंधन",
                        icon = Icons.Default.Settings,
                        color = Color(0xFF475569),
                        modifier = Modifier.weight(1f),
                        testTag = "nav_settings",
                        onClick = { onNavigate("settings") }
                    )
                }
            }
        }
    }
}

@Composable
fun SummaryCard(
    title: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = null, tint = color, modifier = Modifier.size(24.dp))
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = title, fontSize = 12.sp, color = Color.Gray)
                Text(text = value, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
            }
        }
    }
}

@Composable
fun ModuleButton(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color,
    modifier: Modifier = Modifier,
    testTag: String,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .height(110.dp)
            .testTag(testTag),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(color.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = null, tint = color, modifier = Modifier.size(20.dp))
            }
            Column {
                Text(
                    text = title,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E293B),
                    maxLines = 1
                )
                Text(
                    text = subtitle,
                    fontSize = 11.sp,
                    color = Color.Gray,
                    maxLines = 1
                )
            }
        }
    }
}
