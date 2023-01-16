package nl.hva.backend.application.api

import nl.hva.backend.application.dto.GeneralPractitionerDTO
import nl.hva.backend.application.dto.PatientDTO
import nl.hva.backend.domain.ids.GeneralPractitionerId
import java.time.LocalTime

interface GeneralPractitionerService {

    fun createAccount(
        firstName: String, lastName: String, street: String,zip: String,
        city: String, country: String, phoneNumber: String,
        startTimeShift: LocalTime, endTimeShift: LocalTime, breakTimes: String, breakDuration: Long, appointmentDuration: Long
    )

    fun editAccount(
        generalPractitionerId: GeneralPractitionerId, firstName: String, lastName: String,
        street: String, zip: String, city: String, country: String, phoneNumber: String,
        startTimeShift: LocalTime, endTimeShift: LocalTime, breakTimes: String, breakDuration: Long, appointmentDuration: Long
    )

    fun deleteAccount(generalPractitionerId: GeneralPractitionerId)

    fun getAccountById(generalPractitionerId: GeneralPractitionerId): GeneralPractitionerDTO

    fun getAllAccounts(): List<GeneralPractitionerDTO>

    fun getPatientsOfGeneralPractitionerById(generalPractitionerId: GeneralPractitionerId): List<PatientDTO>

}