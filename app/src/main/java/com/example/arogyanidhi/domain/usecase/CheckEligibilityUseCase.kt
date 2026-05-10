package com.example.arogyanidhi.domain.usecase

import com.example.arogyanidhi.domain.model.EligibilityResult
import com.example.arogyanidhi.domain.model.Scheme
import com.example.arogyanidhi.domain.model.UserProfile

class CheckEligibilityUseCase {
    operator fun invoke(user: UserProfile, schemes: List<Scheme>): List<EligibilityResult> {
        return schemes.map { scheme ->
            val incomeOk = scheme.maxIncome?.let { user.annualIncome <= it } ?: true
            val bplOk = scheme.requiresBpl?.let { required ->
                if (required) user.hasBpl == true else true
            } ?: true
            val occupationOk = scheme.validOccupations?.let { user.occupation in it } ?: true
            val stateOk = scheme.validStates.isEmpty() || user.state in scheme.validStates
            EligibilityResult(
                scheme = scheme,
                isEligible = incomeOk && bplOk && occupationOk && stateOk,
                failureReasons = buildList {
                    if (!incomeOk && scheme.maxIncome != null) add("Income exceeds Rs ${scheme.maxIncome}")
                    if (!bplOk) add("Requires BPL card")
                    if (!occupationOk) add("Occupation not covered")
                    if (!stateOk) add("Not available in ${user.state}")
                }
            )
        }
    }
}
