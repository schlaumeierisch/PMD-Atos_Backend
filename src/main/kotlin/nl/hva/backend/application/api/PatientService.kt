package nl.hva.backend.application.api

import nl.hva.backend.application.dto.PatientDTO
import nl.hva.backend.domain.GeneralPractitioner
import nl.hva.backend.domain.ids.PatientId
import java.time.LocalDate

interface PatientService {

    fun createAccount(
        firstName: String,
        lastName: String,
        street: String,
        zip: String,
        city: String,
        country: String,
        gender: String,
        birthDate: LocalDate,
        phoneNumber: String,
        email: String,
        isUsingApp: Boolean
    )

    fun editAccount(
        patientId: PatientId,
        firstName: String,
        lastName: String,
        street: String,
        zip: String,
        city: String,
        country: String,
        gender: String,
        birthDate: LocalDate,
        phoneNumber: String,
        email: String,
        isUsingApp: Boolean
    )

    fun deleteAccount(patientId: PatientId)

    fun getAccountById(patientId: PatientId): PatientDTO

    fun getAllAccounts(): List<PatientDTO>

}