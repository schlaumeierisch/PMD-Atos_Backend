package nl.hva.backend.domain.api

import nl.hva.backend.domain.GeneralPractitioner
import nl.hva.backend.domain.Patient
import nl.hva.backend.domain.ids.GeneralPractitionerId
import java.time.LocalTime

interface GeneralPractitionerRepository {

    fun nextIdentity(): GeneralPractitionerId

    fun createAccount(generalPractitioner: GeneralPractitioner)

    fun editAccount(
            generalPractitionerId: GeneralPractitionerId, firstName: String, lastName: String,
            street: String, zip: String, city: String, country: String, phoneNumber: String,
            startTimeShift: LocalTime, endTimeShift: LocalTime, breakTimes: String, breakDuration: Long, appointmentDuration: Long
    )

    fun deleteAccount(generalPractitionerId: GeneralPractitionerId)

    fun getAccountById(generalPractitionerId: GeneralPractitionerId): GeneralPractitioner

    fun getAllAccounts(): List<GeneralPractitioner>

    fun getPatientsOfGeneralPractitionerById(generalPractitionerId: GeneralPractitionerId): List<Patient>

}