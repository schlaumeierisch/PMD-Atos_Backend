package nl.hva.backend.domain.many_to_many

import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.MedicationId
import java.time.LocalDate

open class MedicationCareProviderRelation {
    private val id: Long = 0

    private lateinit var cpDomainId: CareProviderId
    private lateinit var medicationId: MedicationId
    private lateinit var validDate: LocalDate


    // required by hibernate
    protected constructor()

    constructor(
        cpDomainId: CareProviderId,
        medicationId: MedicationId,
        validDate: LocalDate
    ) {
        this.cpDomainId = cpDomainId
        this.medicationId = medicationId
        this.validDate = validDate
    }

    fun id(): Long = this.id
    fun cpDomainId(): CareProviderId = this.cpDomainId
    fun medicationId(): MedicationId = this.medicationId
    fun validDate(): LocalDate = this.validDate


}