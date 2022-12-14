package nl.hva.backend.domain.many_to_many

import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.DiagnosisId
import java.time.LocalDate

open class DiagnosisCareProviderRelation {
    private val id: Long = 0

    private lateinit var cpDomainId: CareProviderId
    private lateinit var diagnosisId: DiagnosisId
    private lateinit var validDate: LocalDate


    // required by hibernate
    protected constructor()

    constructor(
        cpDomainId: CareProviderId,
        diagnosisId: DiagnosisId,
        validDate: LocalDate
    ) {
        this.cpDomainId = cpDomainId
        this.diagnosisId = diagnosisId
        this.validDate = validDate
    }

    fun id(): Long = this.id
    fun cpDomainId(): CareProviderId = this.cpDomainId
    fun diagnosisId(): DiagnosisId = this.diagnosisId
    fun validDate(): LocalDate = this.validDate


}