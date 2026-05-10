package com.example.arogyanidhi.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.arogyanidhi.data.repository.LocalDataRepository
import com.example.arogyanidhi.domain.model.EligibilityResult
import com.example.arogyanidhi.domain.model.Scheme
import com.example.arogyanidhi.domain.model.UserProfile
import com.example.arogyanidhi.domain.usecase.CheckEligibilityUseCase
import com.example.arogyanidhi.domain.usecase.SearchHospitalsUseCase

class AppViewModel : ViewModel() {
    private val repository = LocalDataRepository()
    private val checkEligibility = CheckEligibilityUseCase()
    private val searchHospitals = SearchHospitalsUseCase()

    val schemes = repository.getSchemes()
    private val allHospitals = repository.getHospitals()
    val statesWithDistricts = repository.getStatesWithDistricts()

    var language by mutableStateOf("English")
        private set
    var onboardingDone by mutableStateOf(false)
        private set
    var currentQuizStep by mutableStateOf(1)
        private set
    var annualIncome by mutableStateOf(100000)
        private set
    var hasBpl by mutableStateOf<Boolean?>(null)
        private set
    var bplAnswered by mutableStateOf(false)
        private set
    var occupation by mutableStateOf("")
        private set
    var familySize by mutableStateOf(1)
        private set
    var selectedState by mutableStateOf("")
        private set
    var selectedDistrict by mutableStateOf("")
        private set

    var eligibilityResults by mutableStateOf<List<EligibilityResult>>(emptyList())
        private set

    val documentChecks = mutableStateListOf<String>()

    fun updateLanguage(value: String) {
        language = value
    }

    fun finishOnboarding() {
        onboardingDone = true
    }

    fun updateAnnualIncome(value: Int) {
        annualIncome = value
    }

    fun updateHasBpl(value: Boolean?) {
        hasBpl = value
        bplAnswered = true
    }

    fun updateOccupation(value: String) {
        occupation = value
    }

    fun updateFamilySize(value: Int) {
        familySize = value.coerceIn(1, 12)
    }

    fun setState(value: String) {
        selectedState = value
        val districts = statesWithDistricts[value].orEmpty()
        selectedDistrict = districts.firstOrNull().orEmpty()
    }

    fun setDistrict(value: String) {
        selectedDistrict = value
    }

    fun canProceedCurrentStep(): Boolean {
        return when (currentQuizStep) {
            1 -> annualIncome > 0
            2 -> bplAnswered
            3 -> occupation.isNotBlank()
            4 -> familySize >= 1
            5 -> selectedState.isNotBlank() && selectedDistrict.isNotBlank()
            else -> false
        }
    }

    fun nextQuizStep() {
        if (currentQuizStep < 5) currentQuizStep += 1
    }

    fun previousQuizStep() {
        if (currentQuizStep > 1) currentQuizStep -= 1
    }

    fun submitQuiz() {
        val profile = UserProfile(
            annualIncome = annualIncome,
            hasBpl = hasBpl,
            occupation = occupation,
            familySize = familySize,
            state = selectedState,
            district = selectedDistrict
        )
        eligibilityResults = checkEligibility(profile, schemes)
    }

    fun hospitalsForCurrentSelection() = searchHospitals(
        hospitals = allHospitals,
        state = selectedState.ifBlank { null },
        district = selectedDistrict.ifBlank { null }
    )

    fun toggleDocument(document: String) {
        if (documentChecks.contains(document)) {
            documentChecks.remove(document)
        } else {
            documentChecks.add(document)
        }
    }

    fun clearChecklist() {
        documentChecks.clear()
    }

    fun getSchemeById(id: String): Scheme? = schemes.firstOrNull { it.id == id }
}
