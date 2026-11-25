package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
    onLogin: (String) -> Unit,
    onNavigate: (String) -> Unit
) {
    var showLoginDialog by remember { mutableStateOf(false) }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginError by remember { mutableStateOf(false) }

    val primaryBlue = Color(0xFF1E3A8A)
    val indigoDark = Color(0xFF3730A3)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFEEF2FF), Color(0xFFDCE4F5), Color(0xFFCFD9EE))
                )
            )
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Header Banner
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("header_card"),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = primaryBlue),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Logo Icon Box
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .background(Color(0xFFFFFDE8))
                        .border(2.dp, Color(0xFFFDE047), RoundedCornerShape(18.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Groups,
                        contentDescription = "Logo",
                        tint = primaryBlue,
                        modifier = Modifier.size(40.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "नव युवक कोस्टा समाज समिति",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "झांसी (उत्तर प्रदेश) - डिजिटल पोर्टल",
                    color = Color(0xFF93C5FD),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Public Services Section Title
        Text(
            text = "सार्वजनिक सेवाएँ एवं पोर्टल मॉड्यूल",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1E293B),
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Service Action Tiles Grid
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ServiceTile(
                title = "खाता बही (Accounts)",
                icon = Icons.Default.AccountBalance,
                color = Color(0xFF047857),
                modifier = Modifier.weight(1f),
                testTag = "btn_accounts_public",
                onClick = { onNavigate("accounts") }
            )
            ServiceTile(
                title = "छात्र पंजीकरण",
                icon = Icons.Default.School,
                color = Color(0xFF1D4ED8),
                modifier = Modifier.weight(1f),
                testTag = "btn_students_public",
                onClick = { onNavigate("students") }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ServiceTile(
                title = "सदस्य डायरेक्टरी",
                icon = Icons.Default.People,
                color = Color(0xFFB45309),
                modifier = Modifier.weight(1f),
                testTag = "btn_members_public",
                onClick = { onNavigate("members") }
            )
            ServiceTile(
                title = "विवाह परिचय (Matrimony)",
                icon = Icons.Default.Favorite,
                color = Color(0xFF9F1239),
                modifier = Modifier.weight(1f),
                testTag = "btn_matrimony_public",
                onClick = { onNavigate("matrimony") }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ServiceTile(
                title = "सूचना पट (Notices)",
                icon = Icons.Default.Campaign,
                color = Color(0xFF6D28D9),
                modifier = Modifier.weight(1f),
                testTag = "btn_notices_public",
                onClick = { onNavigate("notices") }
            )
            ServiceTile(
                title = "कार्यकारिणी समिति",
                icon = Icons.Default.Badge,
                color = Color(0xFF0F766E),
                modifier = Modifier.weight(1f),
                testTag = "btn_committee_public",
                onClick = { onNavigate("committee") }
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Login & Member Action Button
        Button(
            onClick = { showLoginDialog = true },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .testTag("btn_open_login"),
            colors = ButtonDefaults.buttonColors(containerColor = primaryBlue),
            shape = RoundedCornerShape(14.dp)
        ) {
            Icon(imageVector = Icons.Default.Lock, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "पदाधिकारी / सदस्य लॉगिन (Login)", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))
    }

    // Login Dialog
    if (showLoginDialog) {
        AlertDialog(
            onDismissRequest = { showLoginDialog = false },
            title = { Text(text = "सदस्य / एडमिन लॉगिन", fontWeight = FontWeight.Bold) },
            text = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "कृपया अपनी यूजर आईडी और पासवर्ड दर्ज करें।", fontSize = 13.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it },
                        label = { Text("यूजर आईडी (User ID)") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_username"),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("पासवर्ड (Password)") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_password"),
                        singleLine = true
                    )
                    if (loginError) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "अमान्य यूजर आईडी या पासवर्ड!", color = Color.Red, fontSize = 12.sp)
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (username.isNotBlank() && password.isNotBlank()) {
                            val role = if (username.contains("admin", true)) "admin" else "member"
                            onLogin(role)
                            showLoginDialog = false
                        } else {
                            // Default quick login for demo/testing convenience
                            onLogin("admin")
                            showLoginDialog = false
                        }
                    },
                    modifier = Modifier.testTag("btn_confirm_login"),
                    colors = ButtonDefaults.buttonColors(containerColor = primaryBlue)
                ) {
                    Text("लॉगिन करें")
                }
            },
            dismissButton = {
                TextButton(onClick = { showLoginDialog = false }) {
                    Text("रद्द करें")
                }
            }
        )
    }
}

@Composable
fun ServiceTile(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color,
    modifier: Modifier = Modifier,
    testTag: String,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .height(100.dp)
            .testTag(testTag),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(color.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = null, tint = color, modifier = Modifier.size(22.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E293B),
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }
}
