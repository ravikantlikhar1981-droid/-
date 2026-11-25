package com.example.model

data class Transaction(
    val id: String,
    val date: String,
    val description: String,
    val category: String, // 'आय (Income)' or 'व्यय (Expense)'
    val amount: Double,
    val receiptNo: String,
    val paymentMode: String, // 'नकद (Cash)', 'UPI', 'बैंक ट्रांसफर (Bank Transfer)'
    val addedBy: String
)

data class Student(
    val id: String,
    val fullName: String,
    val fatherName: String,
    val dob: String,
    val educationLevel: String, // 'स्कूल (School)', 'स्नातक (Graduate)', 'परास्नातक (Post Graduate)', 'तकनीकी (Technical)'
    val courseName: String,
    val institution: String,
    val contactNo: String,
    val address: String,
    val registrationDate: String
)

data class Member(
    val id: String,
    val name: String,
    val fatherName: String,
    val mobile: String,
    val gotra: String,
    val address: String,
    val feeStatus: String, // 'भुगतान किया (Paid)', 'लंबित (Pending)'
    val role: String // 'सामान्य सदस्य (Member)', 'पदाधिकारी (Office Bearer)', 'संरक्षक (Patron)'
)

data class MatrimonyProfile(
    val id: String,
    val profileType: String, // 'वर (Groom)', 'वधू (Bride)'
    val fullName: String,
    val dob: String,
    val height: String,
    val education: String,
    val occupation: String,
    val income: String,
    val gotra: String,
    val fatherName: String,
    val address: String,
    val contactNo: String,
    val photoUrl: String = "",
    val verified: Boolean = true
)

data class Notice(
    val id: String,
    val title: String,
    val content: String,
    val date: String,
    val priority: String // 'सामान्य (Normal)', 'महत्वपूर्ण (Important)', 'तत्काल (Urgent)'
)

data class CommitteeMember(
    val id: String,
    val name: String,
    val designation: String, // 'अध्यक्ष (President)', 'महामंत्री (General Secretary)', 'कोषाध्यक्ष (Treasurer)', etc.
    val mobile: String,
    val area: String,
    val photoRes: String = ""
)

data class CustomTabContent(
    val id: String,
    val title: String,
    val category: String, // 'संविधान (Constitution)', 'गैलरी (Gallery)', 'डाउनलोड (Downloads)'
    val description: String,
    val fileUrl: String = ""
)
