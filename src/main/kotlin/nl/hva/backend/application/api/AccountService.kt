package nl.hva.backend.application.api

import nl.hva.backend.application.dto.CareProviderDTO
import nl.hva.backend.application.dto.GeneralPractitionerDTO
import nl.hva.backend.application.dto.PatientDTO
import nl.hva.backend.application.dto.many_to_many.PatientCareProviderRelationDTO
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.GeneralPractitionerId
import nl.hva.backend.domain.ids.PatientId

interface AccountService {

    //*** GeneralPractitioner ******************************************************************************************
    fun getAllGeneralPractitioners(): List<GeneralPractitionerDTO>

    fun getGeneralPractitionerById(generalPractitionerId: GeneralPractitionerId): List<GeneralPractitionerDTO>

    fun getGeneralPractitionerByEmail(email: String): List<GeneralPractitionerDTO>

    fun getPatientsOfGeneralPractitionerById(generalPractitionerId: GeneralPractitionerId): List<PatientDTO>


    //*** Patient ******************************************************************************************************
    fun getAllPatients(): List<PatientDTO>

    fun getPatientById(patientId: PatientId): List<PatientDTO>

    fun getPatientByEmail(email: String): List<PatientDTO>

    fun getPatientCareProviderRelationsByPatientId(patientId: PatientId): List<PatientCareProviderRelationDTO>

    fun createPatientCareProviderRelation(patientId: PatientId, careProviderId: CareProviderId)


    //*** CareProvider *************************************************************************************************
    fun getAllCareProviders(): List<CareProviderDTO>

    fun getCareProviderById(careProviderId: CareProviderId): List<CareProviderDTO>

    fun getCareProviderByEmail(email: String): List<CareProviderDTO>

    fun getPatientCareProviderRelationsByCareProviderId(careProviderId: CareProviderId): List<PatientCareProviderRelationDTO>

    fun getPatientsOfCareProviderById(careProviderId: CareProviderId): List<PatientDTO>

}