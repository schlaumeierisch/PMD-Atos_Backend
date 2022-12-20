package nl.hva.backend.application.api

import nl.hva.backend.application.dto.PatientDTO
import nl.hva.backend.application.dto.many_to_many.PatientCareProviderDTO
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.PatientId
import nl.hva.backend.domain.value_objects.Gender
import java.time.LocalDate

interface PatientService {

    fun createAccount(
        firstName: String, lastName: String, street: String, zip: String, city: String,
        country: String, gender: Enum<Gender>, birthDate: LocalDate, phoneNumber: String,
        email: String, usingApp: Boolean, medicalRecordId: String, gpId: String
    )

    fun editAccount(
        patientId: PatientId, firstName: String, lastName: String, street: String,
        zip: String, city: String, country: String, gender: Enum<Gender>, birthDate: LocalDate,
        phoneNumber: String, email: String, usingApp: Boolean, gpId: String
    )

    fun deleteAccount(patientId: PatientId)

    fun getAccountById(patientId: PatientId): List<PatientDTO>

    fun getAllAccounts(): List<PatientDTO>

    fun getPatientCareProviderRelationsByPatientId(patientId: PatientId): List<PatientCareProviderDTO>

    fun createLinkPatientCareProvider(patientId: PatientId, careProviderId: CareProviderId)

}