package nl.hva.backend.application

import nl.hva.backend.application.api.CareProviderService
import nl.hva.backend.application.dto.CareProviderDTO
import nl.hva.backend.domain.CareProvider
import nl.hva.backend.domain.api.CareProviderRepository
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.value_objects.Address
import nl.hva.backend.domain.value_objects.Specialism
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
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

        val careProvider = CareProvider(
            careProviderId,
            firstName,
            lastName,
            Address(street, zip, city, country),
            phoneNumber,
            specialism
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
    override fun getAccountById(careProviderId: CareProviderId): List<CareProviderDTO> {
        val careProvider: List<CareProvider> = this.careProviderRepository.getAccountById(careProviderId)

        return if (careProvider.isNotEmpty()) {
            listOf(CareProviderDTO.fromCareProvider(careProvider[0]))
        } else {
            emptyList()
        }
    }

    @Transactional
    override fun getAllAccounts(): List<CareProviderDTO> {
        val careProviders: List<CareProvider> = this.careProviderRepository.getAllAccounts()

        return CareProviderDTO.fromCareProviders(careProviders)
    }

}