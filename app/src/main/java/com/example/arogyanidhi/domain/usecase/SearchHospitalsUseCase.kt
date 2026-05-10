package com.example.arogyanidhi.domain.usecase

import com.example.arogyanidhi.domain.model.Hospital

class SearchHospitalsUseCase {
    operator fun invoke(
        hospitals: List<Hospital>,
        state: String?,
        district: String?
    ): List<Hospital> {
        return hospitals.filter { hospital ->
            val stateOk = state.isNullOrBlank() || hospital.state == state
            val districtOk = district.isNullOrBlank() || hospital.district == district
            stateOk && districtOk
        }
    }
}
