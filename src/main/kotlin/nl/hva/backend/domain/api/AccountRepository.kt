package nl.hva.backend.domain.api

import nl.hva.backend.domain.CareProvider
import nl.hva.backend.domain.GeneralPractitioner
import nl.hva.backend.domain.Patient
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.GeneralPractitionerId
import nl.hva.backend.domain.ids.PatientId
import nl.hva.backend.domain.many_to_many.PatientCareProviderRelation

interface AccountRepository {

    //*** GeneralPractitioner ******************************************************************************************
    fun getAllGeneralPractitioners(): List<GeneralPractitioner>

    fun getGeneralPractitionerById(generalPractitionerId: GeneralPractitionerId): List<GeneralPractitioner>

    fun getGeneralPractitionerByEmail(email: String): List<GeneralPractitioner>

    fun getPatientsOfGeneralPractitionerById(generalPractitionerId: GeneralPractitionerId): List<Patient>


    //*** Patient ******************************************************************************************************
    fun getAllPatients(): List<Patient>

    fun getPatientById(patientId: PatientId): List<Patient>

    fun getPatientByEmail(email: String): List<Patient>

    fun getPatientCareProviderRelationsByPatientId(patientId: PatientId): List<PatientCareProviderRelation>

    fun createPatientCareProviderRelation(patientCareProviderRelation: PatientCareProviderRelation)


    //*** CareProvider *************************************************************************************************
    fun getAllCareProviders(): List<CareProvider>

    fun getCareProviderById(careProviderId: CareProviderId): List<CareProvider>

    fun getCareProviderByEmail(email: String): List<CareProvider>

    fun getPatientCareProviderRelationsByCareProviderId(careProviderId: CareProviderId): List<PatientCareProviderRelation>

}