package com.example.data

import com.example.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object SamajRepository {
    private val _transactions = MutableStateFlow(
        listOf(
            Transaction("1", "2026-11-01", "वार्षिक उत्सव चंदा सहयोग", "आय (Income)", 15000.0, "RCP-001", "नकद (Cash)", "राजेश कोस्टा"),
            Transaction("2", "2026-11-05", "सामुदायिक भवन हॉल किराया", "आय (Income)", 5100.0, "RCP-002", "UPI", "मनीष कोस्टा"),
            Transaction("3", "2026-11-10", "मंच टेंट एवं साउंड व्यवस्था भुगतान", "व्यय (Expense)", 8000.0, "EXP-001", "बैंक ट्रांसफर (Bank Transfer)", "कोषाध्यक्ष"),
            Transaction("4", "2026-11-15", "छात्र सम्मान समारोह जलपान", "व्यय (Expense)", 3500.0, "EXP-002", "नकद (Cash)", "मंत्री")
        )
    )
    val transactions: StateFlow<List<Transaction>> = _transactions.asStateFlow()

    private val _students = MutableStateFlow(
        listOf(
            Student("1", "राहुल कोस्टा", "श्री रमेश कोस्टा", "2005-06-12", "स्नातक (Graduate)", "B.Tech Computer Science", "Bundelkhand University Jhansi", "9876543210", "सिविल लाइन्स झांसी", "2026-11-02"),
            Student("2", "प्रिया कोस्टा", "श्री सुरेश कोस्टा", "2007-09-18", "स्कूल (School)", "Class 12th PCM", "KV Jhansi Cantt", "9123456789", "नवाबाद झांसी", "2026-11-12")
        )
    )
    val students: StateFlow<List<Student>> = _students.asStateFlow()

    private val _members = MutableStateFlow(
        listOf(
            Member("1", "श्री रामस्वरूप कोस्टा", "स्व. मखनलाल", "9415011111", "कश्यप", "सदर बाजार झांसी", "भुगतान किया (Paid)", "संरक्षक (Patron)"),
            Member("2", "श्री विनोद कुमार कोस्टा", "श्री हरिशंकर", "9415022222", "भारद्वाज", "माणिक चौक झांसी", "भुगतान किया (Paid)", "पदाधिकारी (Office Bearer)"),
            Member("3", "अमित कोस्टा", "श्री रामगोपाल", "9838333333", "कश्यप", "इमली चौराहा झांसी", "लंबित (Pending)", "सामान्य सदस्य (Member)")
        )
    )
    val members: StateFlow<List<Member>> = _members.asStateFlow()

    private val _matrimonyProfiles = MutableStateFlow(
        listOf(
            MatrimonyProfile("1", "वर (Groom)", "अंकित कोस्टा", "1998-03-15", "5' 10\"", "B.Tech, MBA", "Software Engineer (Bangalore)", "12 LPA", "कश्यप", "श्री मोहन लाल कोस्टा", "सिविल लाइन्स झांसी", "9876500001", "", true),
            MatrimonyProfile("2", "वधू (Bride)", "नेहा कोस्टा", "2001-07-22", "5' 4\"", "M.Sc. B.Ed", "Government Teacher", "6 LPA", "भारद्वाज", "श्री सुरेश चंद कोस्टा", "नवाबाद झांसी", "9876500002", "", true)
        )
    )
    val matrimonyProfiles: StateFlow<List<MatrimonyProfile>> = _matrimonyProfiles.asStateFlow()

    private val _notices = MutableStateFlow(
        listOf(
            Notice("1", "वार्षिक मिलन समारोह एवं वन-भोजन बैठक", "कोस्टा समाज की आगामी मासिक बैठक आगामी रविवार को अग्रसेन भवन झांसी में आयोजित की जाएगी। सभी स्वजातीय बंधुओं की उपस्थिति अनिवार्य है।", "2026-11-20", "महत्वपूर्ण (Important)"),
            Notice("2", "मेधावी छात्र सम्मान समारोह 2026", "हाईस्कूल एवं इंटरमीडिएट में 80% से अधिक अंक प्राप्त करने वाले छात्र-छात्राओं हेतु पंजीकरण प्रारंभ हो चुका है।", "2026-11-18", "तत्काल (Urgent)")
        )
    )
    val notices: StateFlow<List<Notice>> = _notices.asStateFlow()

    private val _committeeMembers = MutableStateFlow(
        listOf(
            CommitteeMember("1", "श्री विनोद कुमार कोस्टा", "अध्यक्ष (President)", "9415022222", "झांसी महानगर"),
            CommitteeMember("2", "श्री राजेश कोस्टा (इंजीनियर)", "महामंत्री (General Secretary)", "9415033333", "सिविल लाइन्स"),
            CommitteeMember("3", "श्री मनीष कोस्टा", "कोषाध्यक्ष (Treasurer)", "9415044444", "सदर बाजार"),
            CommitteeMember("4", "श्री सुनील कोस्टा", "युवा अध्यक्ष (Youth President)", "9415055555", "नवाबाद")
        )
    )
    val committeeMembers: StateFlow<List<CommitteeMember>> = _committeeMembers.asStateFlow()

    private val _customTabs = MutableStateFlow(
        listOf(
            CustomTabContent("1", "समाज संविधान एवं नियमावली", "संविधान (Constitution)", "नव युवक कोस्टा समाज समिति झांसी की आधिकारिक नियमावली, उद्देश्य एवं नियम।"),
            CustomTabContent("2", "समाज फोटो गैलरी 2026", "गैलरी (Gallery)", "वार्षिक सम्मेलन एवं होली मिलन समारोह के रंगारंग चित्र।"),
            CustomTabContent("3", "फॉर्म एवं प्रपत्र डाउनलोड", "डाउनलोड (Downloads)", "सदस्यता फॉर्म, छात्रवृत्ति आवेदन एवं विवाह परिचय पत्र फॉर्म PDF।")
        )
    )
    val customTabs: StateFlow<List<CustomTabContent>> = _customTabs.asStateFlow()

    fun addTransaction(t: Transaction) {
        _transactions.value = listOf(t) + _transactions.value
    }

    fun addStudent(s: Student) {
        _students.value = listOf(s) + _students.value
    }

    fun addMember(m: Member) {
        _members.value = listOf(m) + _members.value
    }

    fun addMatrimonyProfile(p: MatrimonyProfile) {
        _matrimonyProfiles.value = listOf(p) + _matrimonyProfiles.value
    }

    fun addNotice(n: Notice) {
        _notices.value = listOf(n) + _notices.value
    }

    fun addCommitteeMember(c: CommitteeMember) {
        _committeeMembers.value = _committeeMembers.value + c
    }
}
