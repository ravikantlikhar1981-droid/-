package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.data.SamajRepository
import com.example.model.*
import kotlinx.coroutines.flow.*

class SamajViewModel : ViewModel() {
    // Current user session ('guest', 'member', 'admin')
    private val _currentUserRole = MutableStateFlow("guest")
    val currentUserRole: StateFlow<String> = _currentUserRole.asStateFlow()

    private val _currentScreen = MutableStateFlow("auth") // 'auth', 'dashboard', 'accounts', 'students', 'members', 'matrimony', 'notices', 'committee', 'custom_tabs', 'settings'
    val currentScreen: StateFlow<String> = _currentScreen.asStateFlow()

    private val _isDarkMode = MutableStateFlow(false)
    val isDarkMode: StateFlow<Boolean> = _isDarkMode.asStateFlow()

    // Data flows from repository
    val transactions = SamajRepository.transactions
    val students = SamajRepository.students
    val members = SamajRepository.members
    val matrimonyProfiles = SamajRepository.matrimonyProfiles
    val notices = SamajRepository.notices
    val committeeMembers = SamajRepository.committeeMembers
    val customTabs = SamajRepository.customTabs

    fun login(role: String) {
        _currentUserRole.value = role
        _currentScreen.value = "dashboard"
    }

    fun logout() {
        _currentUserRole.value = "guest"
        _currentScreen.value = "auth"
    }

    fun navigateTo(screen: String) {
        _currentScreen.value = screen
    }

    fun toggleDarkMode() {
        _isDarkMode.value = !_isDarkMode.value
    }

    fun addTransaction(t: Transaction) {
        SamajRepository.addTransaction(t)
    }

    fun addStudent(s: Student) {
        SamajRepository.addStudent(s)
    }

    fun addMember(m: Member) {
        SamajRepository.addMember(m)
    }

    fun addMatrimonyProfile(p: MatrimonyProfile) {
        SamajRepository.addMatrimonyProfile(p)
    }

    fun addNotice(n: Notice) {
        SamajRepository.addNotice(n)
    }
}
