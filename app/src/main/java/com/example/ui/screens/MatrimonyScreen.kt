package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.MatrimonyProfile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatrimonyScreen(
    profiles: List<MatrimonyProfile>,
    userRole: String,
    onAddProfile: (MatrimonyProfile) -> Unit,
    onBack: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var type by remember { mutableStateOf("वर (Groom)") }
    var name by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var education by remember { mutableStateOf("") }
    var occupation by remember { mutableStateOf("") }
    var gotra by remember { mutableStateOf("") }
    var contact by remember { mutableStateOf("") }
    var selectedTab by remember { mutableIntStateOf(0) } // 0: All, 1: Groom, 2: Bride

    val filteredList = when (selectedTab) {
        1 -> profiles.filter { it.profileType.contains("वर") }
        2 -> profiles.filter { it.profileType.contains("वधू") }
        else -> profiles
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("विवाह योग्य युवक-युवती परिचय", color = Color.White, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("btn_back")) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1E3A8A))
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                containerColor = Color(0xFF9F1239),
                contentColor = Color.White,
                modifier = Modifier.testTag("btn_add_matrimony")
            ) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Add Biodata")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF3F5FA))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TabRow(selectedTabIndex = selectedTab, containerColor = Color.White, contentColor = Color(0xFF9F1239)) {
                Tab(selected = selectedTab == 0, onClick = { selectedTab = 0 }, text = { Text("सभी बायोडाटा") })
                Tab(selected = selectedTab == 1, onClick = { selectedTab = 1 }, text = { Text("वर (Groom)") })
                Tab(selected = selectedTab == 2, onClick = { selectedTab = 2 }, text = { Text("वधू (Bride)") })
            }

            LazyColumn(
                modifier = Modifier.fillMaxWidth().weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(filteredList) { profile ->
                    MatrimonyCard(profile = profile)
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("नया विवाह बायोडाटा दर्ज करें", fontWeight = FontWeight.Bold) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("पूरा नाम") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = education, onValueChange = { education = it }, label = { Text("शिक्षा (Education)") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = occupation, onValueChange = { occupation = it }, label = { Text("व्यवसाय / नौकरी") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = gotra, onValueChange = { gotra = it }, label = { Text("गोत्र") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = contact, onValueChange = { contact = it }, label = { Text("संपर्क मोबाइल नंबर") }, modifier = Modifier.fillMaxWidth())
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (name.isNotBlank() && contact.isNotBlank()) {
                            val newP = MatrimonyProfile(
                                id = (profiles.size + 1).toString(),
                                profileType = type,
                                fullName = name,
                                dob = "2000-01-01",
                                height = "5' 8\"",
                                education = education,
                                occupation = occupation,
                                income = "5 LPA",
                                gotra = gotra,
                                fatherName = "श्री...",
                                address = "झांसी",
                                contactNo = contact,
                                verified = true
                            )
                            onAddProfile(newP)
                            showDialog = false
                            name = ""
                            contact = ""
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9F1239))
                ) {
                    Text("सुरक्षित करें")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) { Text("रद्द करें") }
            }
        )
    }
}

@Composable
fun MatrimonyCard(profile: MatrimonyProfile) {
    val isGroom = profile.profileType.contains("वर")
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = profile.fullName, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF1E293B))
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = if (isGroom) Color(0xFFDBEAFE) else Color(0xFFFCE7F3)
                ) {
                    Text(
                        text = profile.profileType,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        fontSize = 11.sp,
                        color = if (isGroom) Color(0xFF1D4ED8) else Color(0xFF9F1239),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "शिक्षा: ${profile.education} | व्यवसाय: ${profile.occupation}", fontSize = 13.sp, color = Color.Gray)
            Text(text = "गोत्र: ${profile.gotra} | आय: ${profile.income}", fontSize = 13.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(6.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "मोबाइल: ${profile.contactNo}", fontSize = 12.sp, color = Color(0xFF047857), fontWeight = FontWeight.Bold)
                Text(text = "पता: ${profile.address}", fontSize = 12.sp, color = Color.Gray)
            }
        }
    }
}
