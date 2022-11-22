package nl.hva.backend.domain.api

import nl.hva.backend.domain.CareProvider
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.value_objects.Specialism

interface CareProviderRepository {

    fun nextIdentity(): CareProviderId

    fun createAccount(careProvider: CareProvider)

    fun editAccount(
        careProviderId: CareProviderId,
        firstName: String,
        lastName: String,
        street: String,
        zip: String,
        city: String,
        country: String,
        phoneNumber: String,
        specialism: Enum<Specialism>,
    )

    fun deleteAccount(careProviderId: CareProviderId)

    fun getAccountById(careProviderId: CareProviderId): CareProvider

    fun getAllAccounts(): List<CareProvider>

}