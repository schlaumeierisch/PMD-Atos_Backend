package nl.hva.backend.domain.api

import nl.hva.backend.domain.Patient
import nl.hva.backend.domain.ids.GeneralPractitionerId
import nl.hva.backend.domain.ids.PatientId
import nl.hva.backend.domain.value_objects.Gender
import java.time.LocalDate

interface PatientRepository {

    fun nextIdentity(): PatientId

    fun createAccount(patient: Patient)

    fun editAccount(
        patientId: PatientId, firstName: String, lastName: String, street: String,
        zip: String, city: String, country: String, gender: Enum<Gender>, birthDate: LocalDate,
        phoneNumber: String, email: String, isUsingApp: Boolean, gpDomainId: GeneralPractitionerId
    )

    fun deleteAccount(patientId: PatientId)

    fun getAccountById(patientId: PatientId): Patient

    fun getAllAccounts(): List<Patient>

}