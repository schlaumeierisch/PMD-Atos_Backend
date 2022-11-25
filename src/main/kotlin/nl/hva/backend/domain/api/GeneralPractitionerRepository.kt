package nl.hva.backend.domain.api

import nl.hva.backend.domain.GeneralPractitioner
import nl.hva.backend.domain.ids.GeneralPractitionerId

interface GeneralPractitionerRepository {

    fun nextIdentity(): GeneralPractitionerId

    fun createAccount(generalPractitioner: GeneralPractitioner)

    fun editAccount(
        generalPractitionerId: GeneralPractitionerId, firstName: String, lastName: String,
        street: String, zip: String, city: String, country: String, phoneNumber: String
    )

    fun deleteAccount(generalPractitionerId: GeneralPractitionerId)

    fun getAccountById(generalPractitionerId: GeneralPractitionerId): GeneralPractitioner

    fun getAllAccounts(): List<GeneralPractitioner>

}