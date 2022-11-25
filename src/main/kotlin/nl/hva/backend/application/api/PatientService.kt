package nl.hva.backend.application.api

import nl.hva.backend.application.dto.PatientDTO
import nl.hva.backend.domain.ids.GeneralPractitionerId
import nl.hva.backend.domain.ids.PatientId
import nl.hva.backend.domain.value_objects.Gender
import java.time.LocalDate

interface PatientService {

    fun createAccount(
        firstName: String, lastName: String, street: String, zip: String,
        city: String, country: String, gender: Enum<Gender>, birthDate: LocalDate,
        phoneNumber: String, email: String, isUsingApp: Boolean
    )

    fun editAccount(
        patientId: PatientId, firstName: String, lastName: String, street: String,
        zip: String, city: String, country: String, gender: Enum<Gender>, birthDate: LocalDate,
        phoneNumber: String, email: String, isUsingApp: Boolean
    )

    fun deleteAccount(patientId: PatientId)

    fun getAccountById(patientId: PatientId): PatientDTO

    fun getAccountByGeneralPractitionerId(generalPractitionerId: GeneralPractitionerId): List<PatientDTO>

    fun getAllAccounts(): List<PatientDTO>

}