package nl.hva.backend.application

import nl.hva.backend.application.api.GeneralPractitionerService
import nl.hva.backend.application.dto.GeneralPractitionerDTO
import nl.hva.backend.application.dto.value_objects.AddressDTO
import nl.hva.backend.domain.GeneralPractitioner
import nl.hva.backend.domain.api.GeneralPractitionerRepository
import nl.hva.backend.domain.ids.GeneralPractitionerId
import nl.hva.backend.domain.value_objects.Address
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class GeneralPractitionerServiceImpl : GeneralPractitionerService {

    @Autowired
    private lateinit var generalPractitionerRepository: GeneralPractitionerRepository

    @Transactional
    override fun createAccount(
        firstName: String,
        lastName: String,
        street: String,
        zip: String,
        city: String,
        country: String,
        phoneNumber: String
    ) {
        val generalPractitionerId: GeneralPractitionerId = generalPractitionerRepository.nextIdentity()

        // todo: change mutableSetOf() to real values
        val generalPractitioner = GeneralPractitioner(
            generalPractitionerId,
            firstName,
            lastName,
            Address(street, zip, city, country),
            phoneNumber,
            mutableSetOf()
        )

        this.generalPractitionerRepository.createAccount(generalPractitioner)
    }

    @Transactional
    override fun editAccount(
        generalPractitionerId: GeneralPractitionerId,
        firstName: String,
        lastName: String,
        street: String,
        zip: String,
        city: String,
        country: String,
        phoneNumber: String
    ) {
        this.generalPractitionerRepository.editAccount(
            generalPractitionerId,
            firstName,
            lastName,
            street,
            zip,
            city,
            country,
            phoneNumber
        )
    }

    @Transactional
    override fun deleteAccount(generalPractitionerId: GeneralPractitionerId) {
        this.generalPractitionerRepository.deleteAccount(generalPractitionerId)
    }

    @Transactional
    override fun getAccountById(generalPractitionerId: GeneralPractitionerId): GeneralPractitionerDTO {
        val generalPractitioner: GeneralPractitioner =
            this.generalPractitionerRepository.getAccountById(generalPractitionerId)

        // todo: change mutableSetOf() to real values
        return GeneralPractitionerDTO().builder()
            .withId(generalPractitioner.domainId().id())
            .withFirstName(generalPractitioner.firstName())
            .withLastName(generalPractitioner.lastName())
            .withAddress(AddressDTO.fromAddress(generalPractitioner.address()))
            .withPhoneNumber(generalPractitioner.phoneNumber())
            .withPatientDTOs(mutableSetOf())
            .build()
    }

    @Transactional
    override fun getAllAccounts(): List<GeneralPractitionerDTO> {
        val generalPractitioners: List<GeneralPractitioner> = this.generalPractitionerRepository.getAllAccounts()
        val generalPractitionerDTOs: ArrayList<GeneralPractitionerDTO> = arrayListOf()

        // todo: change mutableSetOf() to real values
        for (generalPractitioner in generalPractitioners) {
            val generalPractitionerDTO: GeneralPractitionerDTO = GeneralPractitionerDTO().builder()
                .withId(generalPractitioner.domainId().id())
                .withFirstName(generalPractitioner.firstName())
                .withLastName(generalPractitioner.lastName())
                .withAddress(AddressDTO.fromAddress(generalPractitioner.address()))
                .withPhoneNumber(generalPractitioner.phoneNumber())
                .withPatientDTOs(mutableSetOf())
                .build()

            generalPractitionerDTOs.add(generalPractitionerDTO)
        }

        return generalPractitionerDTOs
    }

}