package com.example.arogyanidhi.domain.model

data class Scheme(
    val id: String,
    val name: String,
    val description: String,
    val benefits: String,
    val maxIncome: Int?,
    val requiresBpl: Boolean?,
    val validOccupations: List<String>?,
    val validStates: List<String>,
    val documents: List<String>
)

data class UserProfile(
    val annualIncome: Int,
    val hasBpl: Boolean?,
    val occupation: String,
    val familySize: Int,
    val state: String,
    val district: String
)

data class EligibilityResult(
    val scheme: Scheme,
    val isEligible: Boolean,
    val failureReasons: List<String>
)

data class Hospital(
    val id: String,
    val name: String,
    val address: String,
    val district: String,
    val state: String,
    val phone: String?,
    val schemesAccepted: List<String>
)
