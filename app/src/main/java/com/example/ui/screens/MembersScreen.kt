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
import com.example.model.Member

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MembersScreen(
    members: List<Member>,
    userRole: String,
    onAddMember: (Member) -> Unit,
    onBack: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var father by remember { mutableStateOf("") }
    var mobile by remember { mutableStateOf("") }
    var gotra by remember { mutableStateOf("कश्यप") }
    var address by remember { mutableStateOf("") }
    var role by remember { mutableStateOf("सामान्य सदस्य (Member)") }
    var searchQuery by remember { mutableStateOf("") }

    val filteredMembers = members.filter {
        it.name.contains(searchQuery, true) || it.mobile.contains(searchQuery) || it.address.contains(searchQuery, true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("सदस्य डायरेक्टरी एवं शुल्क स्थिति", color = Color.White, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("btn_back")) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1E3A8A))
            )
        },
        floatingActionButton = {
            if (userRole == "admin") {
                FloatingActionButton(
                    onClick = { showDialog = true },
                    containerColor = Color(0xFFB45309),
                    contentColor = Color.White,
                    modifier = Modifier.testTag("btn_add_member")
                ) {
                    Icon(imageVector = Icons.Default.PersonAdd, contentDescription = "Add Member")
                }
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
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("सदस्य खोजें (नाम, मोबाइल या पता)") },
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("input_search_member"),
                singleLine = true
            )

            Text(text = "कुल सदस्य (${filteredMembers.size})", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color(0xFF1E293B))

            LazyColumn(
                modifier = Modifier.fillMaxWidth().weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(filteredMembers) { m ->
                    MemberCard(member = m)
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("नया सदस्य जोड़ें", fontWeight = FontWeight.Bold) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("सदस्य का पूरा नाम") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = father, onValueChange = { father = it }, label = { Text("पिता का नाम") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = mobile, onValueChange = { mobile = it }, label = { Text("मोबाइल नंबर") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = gotra, onValueChange = { gotra = it }, label = { Text("गोत्र") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = address, onValueChange = { address = it }, label = { Text("पता (Address)") }, modifier = Modifier.fillMaxWidth())
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (name.isNotBlank() && mobile.isNotBlank()) {
                            val newM = Member(
                                id = (members.size + 1).toString(),
                                name = name,
                                fatherName = father,
                                mobile = mobile,
                                gotra = gotra,
                                address = address,
                                feeStatus = "भुगतान किया (Paid)",
                                role = role
                            )
                            onAddMember(newM)
                            showDialog = false
                            name = ""
                            mobile = ""
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB45309))
                ) {
                    Text("जोड़ें")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) { Text("रद्द करें") }
            }
        )
    }
}

@Composable
fun MemberCard(member: Member) {
    val isPaid = member.feeStatus.contains("भुगतान किया")
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = member.name, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF1E293B))
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = if (isPaid) Color(0xFFD1FAE5) else Color(0xFFFEE2E2)
                ) {
                    Text(
                        text = member.feeStatus,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        fontSize = 11.sp,
                        color = if (isPaid) Color(0xFF047857) else Color(0xFF9F1239),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "पिता: ${member.fatherName} | गोत्र: ${member.gotra}", fontSize = 13.sp, color = Color.Gray)
            Text(text = "पता: ${member.address}", fontSize = 13.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(6.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "मोबाइल: ${member.mobile}", fontSize = 12.sp, color = Color(0xFF1D4ED8), fontWeight = FontWeight.Bold)
                Text(text = member.role, fontSize = 12.sp, color = Color(0xFFB45309), fontWeight = FontWeight.Bold)
            }
        }
    }
}
