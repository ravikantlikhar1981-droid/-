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
import com.example.model.Notice

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoticesScreen(
    notices: List<Notice>,
    userRole: String,
    onAddNotice: (Notice) -> Unit,
    onBack: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf("महत्वपूर्ण (Important)") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("सूचना पट (Notice Board)", color = Color.White, fontWeight = FontWeight.Bold) },
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
                    containerColor = Color(0xFF6D28D9),
                    contentColor = Color.White,
                    modifier = Modifier.testTag("btn_add_notice")
                ) {
                    Icon(imageVector = Icons.Default.AddComment, contentDescription = "Add Notice")
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
            Text(text = "आगामी बैठकें एवं सामाजिक घोषणाएँ", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color(0xFF1E293B))

            LazyColumn(
                modifier = Modifier.fillMaxWidth().weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(notices) { notice ->
                    NoticeCard(notice = notice)
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("नई सूचना जारी करें", fontWeight = FontWeight.Bold) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("शीर्षक (Title)") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = content, onValueChange = { content = it }, label = { Text("विस्तृत विवरण (Content)") }, modifier = Modifier.fillMaxWidth())
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (title.isNotBlank() && content.isNotBlank()) {
                            val newN = Notice(
                                id = (notices.size + 1).toString(),
                                title = title,
                                content = content,
                                date = "2026-11-25",
                                priority = priority
                            )
                            onAddNotice(newN)
                            showDialog = false
                            title = ""
                            content = ""
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6D28D9))
                ) {
                    Text("जारी करें")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) { Text("रद्द करें") }
            }
        )
    }
}

@Composable
fun NoticeCard(notice: Notice) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = notice.title, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color(0xFF1E293B))
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = Color(0xFFEDE9FE)
                ) {
                    Text(
                        text = notice.priority,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        fontSize = 11.sp,
                        color = Color(0xFF6D28D9),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = notice.content, fontSize = 13.sp, color = Color.DarkGray)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "दिनांक: ${notice.date}", fontSize = 11.sp, color = Color.Gray)
        }
    }
}
