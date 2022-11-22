package nl.hva.backend.application.api

import nl.hva.backend.application.dto.CareProviderDTO
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.value_objects.Specialism

interface CareProviderService {

    fun createAccount(
        firstName: String,
        lastName: String,
        street: String,
        zip: String,
        city: String,
        country: String,
        phoneNumber: String,
        specialism: Enum<Specialism>
    )

    fun editAccount(
        careProviderId: CareProviderId,
        firstName: String,
        lastName: String,
        street: String,
        zip: String,
        city: String,
        country: String,
        phoneNumber: String,
        specialism: Enum<Specialism>
    )

    fun deleteAccount(careProviderId: CareProviderId)

    fun getAccountById(careProviderId: CareProviderId): CareProviderDTO

    fun getAllAccounts(): List<CareProviderDTO>

}