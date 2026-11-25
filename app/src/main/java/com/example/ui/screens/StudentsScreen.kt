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
import com.example.model.Student

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentsScreen(
    students: List<Student>,
    userRole: String,
    onAddStudent: (Student) -> Unit,
    onBack: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var father by remember { mutableStateOf("") }
    var education by remember { mutableStateOf("स्नातक (Graduate)") }
    var course by remember { mutableStateOf("") }
    var institution by remember { mutableStateOf("") }
    var contact by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("छात्र पंजीकरण एवं सम्मान पोर्टल", color = Color.White, fontWeight = FontWeight.Bold) },
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
                containerColor = Color(0xFF1D4ED8),
                contentColor = Color.White,
                modifier = Modifier.testTag("btn_add_student")
            ) {
                Icon(imageVector = Icons.Default.PersonAdd, contentDescription = "Register Student")
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
            Text(text = "पंजीकृत मेधावी छात्र-छात्राएँ (${students.size})", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color(0xFF1E293B))

            LazyColumn(
                modifier = Modifier.fillMaxWidth().weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(students) { student ->
                    StudentCard(student = student)
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("नया छात्र पंजीकरण", fontWeight = FontWeight.Bold) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("विद्यार्थी का नाम") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = father, onValueChange = { father = it }, label = { Text("पिता का नाम") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = course, onValueChange = { course = it }, label = { Text("कक्षा / कोर्स (जैसे B.Tech, 12th)") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = institution, onValueChange = { institution = it }, label = { Text("विद्यालय / कॉलेज का नाम") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = contact, onValueChange = { contact = it }, label = { Text("मोबाइल नंबर") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = address, onValueChange = { address = it }, label = { Text("पता (Address)") }, modifier = Modifier.fillMaxWidth())
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (name.isNotBlank() && course.isNotBlank()) {
                            val newS = Student(
                                id = (students.size + 1).toString(),
                                fullName = name,
                                fatherName = father,
                                dob = "2006-01-01",
                                educationLevel = education,
                                courseName = course,
                                institution = institution,
                                contactNo = contact,
                                address = address,
                                registrationDate = "2026-11-25"
                            )
                            onAddStudent(newS)
                            showDialog = false
                            name = ""
                            course = ""
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1D4ED8))
                ) {
                    Text("पंजीकृत करें")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) { Text("रद्द करें") }
            }
        )
    }
}

@Composable
fun StudentCard(student: Student) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = student.fullName, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF1E293B))
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = Color(0xFFDBEAFE)
                ) {
                    Text(
                        text = student.educationLevel,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        fontSize = 11.sp,
                        color = Color(0xFF1D4ED8),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "पिता: ${student.fatherName}", fontSize = 13.sp, color = Color.Gray)
            Text(text = "कोर्स: ${student.courseName} | संस्थान: ${student.institution}", fontSize = 13.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(6.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "संपर्क: ${student.contactNo}", fontSize = 12.sp, color = Color(0xFF047857), fontWeight = FontWeight.Bold)
                Text(text = "दिनांक: ${student.registrationDate}", fontSize = 12.sp, color = Color.Gray)
            }
        }
    }
}
