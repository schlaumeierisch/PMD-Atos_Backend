package nl.hva.backend.application

import nl.hva.backend.application.api.CareProviderService
import nl.hva.backend.application.dto.CareProviderDTO
import nl.hva.backend.application.dto.value_objects.AddressDTO
import nl.hva.backend.domain.CareProvider
import nl.hva.backend.domain.api.CareProviderRepository
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.value_objects.Address
import nl.hva.backend.domain.value_objects.Specialism
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CareProviderServiceImpl : CareProviderService {

    @Autowired
    private lateinit var careProviderRepository: CareProviderRepository

    @Transactional
    override fun createAccount(
        firstName: String,
        lastName: String,
        street: String,
        zip: String,
        city: String,
        country: String,
        phoneNumber: String,
        specialism: Enum<Specialism>
    ) {
        val careProviderId: CareProviderId = careProviderRepository.nextIdentity()

        // todo: change mutableSetOf() to real values
        val careProvider = CareProvider(
            careProviderId,
            firstName,
            lastName,
            Address(street, zip, city, country),
            phoneNumber,
            specialism,
            mutableSetOf()
        )

        this.careProviderRepository.createAccount(careProvider)
    }

    @Transactional
    override fun editAccount(
        careProviderId: CareProviderId,
        firstName: String,
        lastName: String,
        street: String,
        zip: String,
        city: String,
        country: String,
        phoneNumber: String,
        specialism: Enum<Specialism>
    ) {
        this.careProviderRepository.editAccount(
            careProviderId,
            firstName,
            lastName,
            street,
            zip,
            city,
            country,
            phoneNumber,
            specialism
        )
    }

    @Transactional
    override fun deleteAccount(careProviderId: CareProviderId) {
        this.careProviderRepository.deleteAccount(careProviderId)
    }

    @Transactional
    override fun getAccountById(careProviderId: CareProviderId): CareProviderDTO {
        val careProvider: CareProvider =
            this.careProviderRepository.getAccountById(careProviderId)

        // todo: change mutableSetOf() to real values
        return CareProviderDTO().builder()
            .withId(careProvider.domainId().id())
            .withFirstName(careProvider.firstName())
            .withLastName(careProvider.lastName())
            .withAddress(AddressDTO.fromAddress(careProvider.address()))
            .withPhoneNumber(careProvider.phoneNumber())
            .withSpecialism(careProvider.specialism())
            .withPatientDTOs(mutableSetOf())
            .build()
    }

    @Transactional
    override fun getAllAccounts(): List<CareProviderDTO> {
        val careProviders: List<CareProvider> = this.careProviderRepository.getAllAccounts()
        val careProviderDTOs: ArrayList<CareProviderDTO> = arrayListOf()

        // todo: change mutableSetOf() to real values
        for (careProvider in careProviders) {
            val careProviderDTO: CareProviderDTO = CareProviderDTO().builder()
                .withId(careProvider.domainId().id())
                .withFirstName(careProvider.firstName())
                .withLastName(careProvider.lastName())
                .withAddress(AddressDTO.fromAddress(careProvider.address()))
                .withPhoneNumber(careProvider.phoneNumber())
                .withSpecialism(careProvider.specialism())
                .withPatientDTOs(mutableSetOf())
                .build()

            careProviderDTOs.add(careProviderDTO)
        }

        return careProviderDTOs
    }

}