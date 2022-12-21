package nl.hva.backend.rest

import nl.hva.backend.application.api.AccountService
import nl.hva.backend.application.dto.CareProviderDTO
import nl.hva.backend.application.dto.GeneralPractitionerDTO
import nl.hva.backend.application.dto.PatientDTO
import nl.hva.backend.application.dto.many_to_many.PatientCareProviderRelationDTO
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.GeneralPractitionerId
import nl.hva.backend.domain.ids.PatientId
import nl.hva.backend.rest.exceptions.NotExistingException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rest/accounts/")
class AccountRestController {

    @Autowired
    private lateinit var accountService: AccountService

    //*** GeneralPractitioner ******************************************************************************************
    @GetMapping("/generalPractitioners/getAll")
    @ResponseBody
    fun getAllGeneralPractitioners(): ResponseEntity<List<GeneralPractitionerDTO>> {
        val generalPractitionerDTOs: List<GeneralPractitionerDTO> = this.accountService.getAllGeneralPractitioners()

        return ResponseEntity.status(HttpStatus.OK).body(generalPractitionerDTOs)
    }

    @GetMapping("/generalPractitioners/getById/{id}")
    @ResponseBody
    fun getGeneralPractitionerById(
        @PathVariable("id") id: String
    ): ResponseEntity<GeneralPractitionerDTO> {
        val generalPractitionerDTO: List<GeneralPractitionerDTO> = this.accountService.getGeneralPractitionerById(
            GeneralPractitionerId(id)
        )

        if (generalPractitionerDTO.isNotEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(generalPractitionerDTO[0])
        } else {
            throw NotExistingException("General practitioner with id '$id' does not exist.")
        }
    }

    @GetMapping("/generalPractitioners/getPatientsOfGeneralPractitionerById/{id}")
    @ResponseBody
    fun getPatientsOfGeneralPractitionerById(
        @PathVariable("id") id: String
    ): ResponseEntity<List<PatientDTO>> {
        val generalPractitionerDTO: List<GeneralPractitionerDTO> = this.accountService.getGeneralPractitionerById(
            GeneralPractitionerId(id)
        )

        if (generalPractitionerDTO.isNotEmpty()) {
            val patientDTOs: List<PatientDTO> = this.accountService.getPatientsOfGeneralPractitionerById(
                GeneralPractitionerId(id)
            )

            return ResponseEntity.status(HttpStatus.OK).body(patientDTOs)
        } else {
            throw NotExistingException("General practitioner with id '$id' does not exist.")
        }
    }


    //*** Patient ******************************************************************************************************
    @GetMapping("/patients/getAll")
    fun getAllPatients(): ResponseEntity<List<PatientDTO>> {
        val patientDTOs: List<PatientDTO> = this.accountService.getAllPatients()

        return ResponseEntity.status(HttpStatus.OK).body(patientDTOs)
    }

    @GetMapping("/patients/getById/{id}")
    @ResponseBody
    fun getPatientById(
        @PathVariable("id") id: String
    ): ResponseEntity<PatientDTO> {
        val patientDTO: List<PatientDTO> = this.accountService.getPatientById(PatientId(id))

        if (patientDTO.isNotEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(patientDTO[0])
        } else {
            throw NotExistingException("Patient with id \'$id\' does not exist.")
        }
    }

    @GetMapping("/patients/getCareProvidersOfPatientById/{id}")
    @ResponseBody
    fun getCareProvidersOfPatientById(
        @PathVariable("id") id: String
    ): ResponseEntity<List<CareProviderDTO>> {
        val patientDTO: List<PatientDTO> = this.accountService.getPatientById(PatientId(id))

        if (patientDTO.isNotEmpty()) {
            val patientCareProviderRelationDTOS: List<PatientCareProviderRelationDTO> =
                this.accountService.getPatientCareProviderRelationsByPatientId(
                    PatientId(id)
                )

            val careProviderDTOs: ArrayList<CareProviderDTO> = arrayListOf()
            for (patientCareProviderDTO in patientCareProviderRelationDTOS) {
                val careProviderDTO: List<CareProviderDTO> =
                    this.accountService.getCareProviderById(CareProviderId(patientCareProviderDTO.cpId()))

                if (careProviderDTO.isNotEmpty()) {
                    careProviderDTOs.add(
                        this.accountService.getCareProviderById(CareProviderId(patientCareProviderDTO.cpId()))[0]
                    )
                } else {
                    throw NotExistingException("Linked care provider with id \'${patientCareProviderDTO.cpId()}\' does not exist.")
                }
            }

            return ResponseEntity.status(HttpStatus.OK).body(careProviderDTOs)
        } else {
            throw NotExistingException("Patient with id \'$id\' does not exist.")
        }
    }

    @GetMapping("/patients/createPatientCareProviderRelation")
    @ResponseBody
    fun createPatientCareProviderRelation(
        careProviderId: String,
        patientId: String
    ) {
        this.accountService.createPatientCareProviderRelation(PatientId(patientId), CareProviderId(careProviderId))
    }


    //*** CareProvider *************************************************************************************************
    @GetMapping("/careProviders/getAll")
    fun getAllCareProviders(): ResponseEntity<List<CareProviderDTO>> {
        val careProviderDTOs: List<CareProviderDTO> = this.accountService.getAllCareProviders()

        return ResponseEntity.status(HttpStatus.OK).body(careProviderDTOs)
    }

    @GetMapping("/careProviders/getById/{id}")
    @ResponseBody
    fun getCareProviderById(
        @PathVariable("id") id: String
    ): ResponseEntity<CareProviderDTO> {
        val careProviderDTO: List<CareProviderDTO> = this.accountService.getCareProviderById(CareProviderId(id))

        if (careProviderDTO.isNotEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(careProviderDTO[0])
        } else {
            throw NotExistingException("Care provider with id '$id' does not exist.")
        }
    }
}