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
import com.example.model.Transaction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountsScreen(
    transactions: List<Transaction>,
    userRole: String,
    onAddTransaction: (Transaction) -> Unit,
    onBack: () -> Unit
) {
    var showAddDialog by remember { mutableStateOf(false) }
    var desc by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("आय (Income)") }
    var paymentMode by remember { mutableStateOf("नकद (Cash)") }
    var selectedTab by remember { mutableIntStateOf(0) } // 0: All, 1: Income, 2: Expense

    val filteredList = when (selectedTab) {
        1 -> transactions.filter { it.category.contains("आय") }
        2 -> transactions.filter { it.category.contains("व्यय") }
        else -> transactions
    }

    val totalIncome = transactions.filter { it.category.contains("आय") }.sumOf { it.amount }
    val totalExpense = transactions.filter { it.category.contains("व्यय") }.sumOf { it.amount }
    val netBalance = totalIncome - totalExpense

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("खाता बही (Accounts Ledger)", color = Color.White, fontWeight = FontWeight.Bold) },
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
                    onClick = { showAddDialog = true },
                    containerColor = Color(0xFF047857),
                    contentColor = Color.White,
                    modifier = Modifier.testTag("btn_add_transaction")
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Entry")
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Summary Banner
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "कोष सारांश (Financial Summary)", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color(0xFF1E293B))
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        SummaryPill(label = "कुल आय", amount = "₹ $totalIncome", color = Color(0xFF047857))
                        SummaryPill(label = "कुल व्यय", amount = "₹ $totalExpense", color = Color(0xFF9F1239))
                        SummaryPill(label = "शेष बैलेंस", amount = "₹ $netBalance", color = Color(0xFF1D4ED8))
                    }
                }
            }

            // Filter Tabs
            TabRow(selectedTabIndex = selectedTab, containerColor = Color.White, contentColor = Color(0xFF1E3A8A)) {
                Tab(selected = selectedTab == 0, onClick = { selectedTab = 0 }, text = { Text("सभी प्रविष्टियाँ") })
                Tab(selected = selectedTab == 1, onClick = { selectedTab = 1 }, text = { Text("आय (Income)") })
                Tab(selected = selectedTab == 2, onClick = { selectedTab = 2 }, text = { Text("व्यय (Expense)") })
            }

            // Transaction List
            LazyColumn(
                modifier = Modifier.fillMaxWidth().weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(filteredList) { t ->
                    TransactionItemCard(transaction = t)
                }
            }
        }
    }

    // Add Transaction Dialog
    if (showAddDialog) {
        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            title = { Text("नया लेन-देन दर्ज करें", fontWeight = FontWeight.Bold) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = desc,
                        onValueChange = { desc = it },
                        label = { Text("विवरण (Description)") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = amount,
                        onValueChange = { amount = it },
                        label = { Text("राशि (Amount in ₹)") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = category,
                        onValueChange = { category = it },
                        label = { Text("श्रेणी (आय / व्यय)") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = paymentMode,
                        onValueChange = { paymentMode = it },
                        label = { Text("भुगतान माध्यम (नकद / UPI)") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val amtVal = amount.toDoubleOrNull() ?: 0.0
                        if (desc.isNotBlank() && amtVal > 0) {
                            val newT = Transaction(
                                id = (transactions.size + 1).toString(),
                                date = "2026-11-25",
                                description = desc,
                                category = category,
                                amount = amtVal,
                                receiptNo = "RCP-00${transactions.size + 1}",
                                paymentMode = paymentMode,
                                addedBy = "एडमिन"
                            )
                            onAddTransaction(newT)
                            showAddDialog = false
                            desc = ""
                            amount = ""
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF047857))
                ) {
                    Text("सुरक्षित करें")
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddDialog = false }) { Text("रद्द करें") }
            }
        )
    }
}

@Composable
fun SummaryPill(label: String, amount: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, fontSize = 11.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = amount, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = color)
    }
}

@Composable
fun TransactionItemCard(transaction: Transaction) {
    val isIncome = transaction.category.contains("आय")
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(if (isIncome) Color(0xFFD1FAE5) else Color(0xFFFEE2E2), RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isIncome) Icons.Default.ArrowDownward else Icons.Default.ArrowUpward,
                        contentDescription = null,
                        tint = if (isIncome) Color(0xFF047857) else Color(0xFF9F1239)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = transaction.description, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color(0xFF1E293B))
                    Text(text = "${transaction.date} • ${transaction.receiptNo} • ${transaction.paymentMode}", fontSize = 11.sp, color = Color.Gray)
                }
            }
            Text(
                text = "${if (isIncome) "+" else "-"} ₹ ${transaction.amount}",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = if (isIncome) Color(0xFF047857) else Color(0xFF9F1239)
            )
        }
    }
}
