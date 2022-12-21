package nl.hva.backend.application

import nl.hva.backend.application.api.AccountService
import nl.hva.backend.application.dto.CareProviderDTO
import nl.hva.backend.application.dto.GeneralPractitionerDTO
import nl.hva.backend.application.dto.PatientDTO
import nl.hva.backend.domain.CareProvider
import nl.hva.backend.domain.GeneralPractitioner
import nl.hva.backend.domain.Patient
import nl.hva.backend.domain.api.AccountRepository
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.GeneralPractitionerId
import nl.hva.backend.domain.ids.MedicalRecordId
import nl.hva.backend.domain.ids.PatientId
import nl.hva.backend.domain.value_objects.Address
import nl.hva.backend.domain.value_objects.Gender
import nl.hva.backend.domain.value_objects.Specialism
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.time.LocalDate

@SpringBootTest
class AccountServiceTests {

    @Autowired
    private lateinit var accountService: AccountService

    @MockBean
    private lateinit var accountRepository: AccountRepository

    // test data
    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var address: Address
    private lateinit var gender: Enum<Gender>
    private lateinit var birthDate: LocalDate
    private lateinit var phoneNumber: String
    private lateinit var email: String
    private var usingApp: Boolean = true
    private lateinit var specialism: Enum<Specialism>

    @BeforeEach
    fun init() {
        this.firstName = "Lotje"
        this.lastName = "Oldhof"
        this.address = Address("Johan Frisoplantsoen 184", "2751XR", "Moerkapelle", "Netherlands")
        this.gender = Gender.FEMALE
        this.birthDate = LocalDate.of(1978, 11, 28)
        this.phoneNumber = "0636038499"
        this.email = "odin88@packiu.com"
        this.usingApp = true
        this.specialism = Specialism.APOTHECARY
    }

    @Test
    fun given_emptyRepository_when_getAllGeneralPractitioners_then_returnsEmpty() {
        // when
        Mockito.`when`(this.accountRepository.getAllGeneralPractitioners()).thenReturn(emptyList())

        // then
        Assertions.assertNotEquals(null, this.accountService.getAllGeneralPractitioners())
        Assertions.assertEquals(0, this.accountService.getAllGeneralPractitioners().size)
    }

    @Test
    fun given_emptyRepository_when_getAllPatients_then_returnsEmpty() {
        // when
        Mockito.`when`(this.accountRepository.getAllPatients()).thenReturn(emptyList())

        // then
        Assertions.assertNotEquals(null, this.accountService.getAllPatients())
        Assertions.assertEquals(0, this.accountService.getAllPatients().size)
    }

    @Test
    fun given_emptyRepository_when_getAllCareProviders_then_returnsEmpty() {
        // when
        Mockito.`when`(this.accountRepository.getAllCareProviders()).thenReturn(emptyList())

        // then
        Assertions.assertNotEquals(null, this.accountService.getAllCareProviders())
        Assertions.assertEquals(0, this.accountService.getAllCareProviders().size)
    }

    @Test
    fun given_repository_when_getAllGeneralPractitioners_then_returnsAll() {
        // given
        val allGeneralPractitioners: List<GeneralPractitioner> = listOf(
            GeneralPractitioner(
                GeneralPractitionerId("dom-id-gp-001"),
                this.firstName,
                this.lastName,
                this.address,
                this.phoneNumber
            ),
            GeneralPractitioner(
                GeneralPractitionerId("dom-id-gp-002"),
                this.firstName,
                this.lastName,
                this.address,
                this.phoneNumber
            ),
            GeneralPractitioner(
                GeneralPractitionerId("dom-id-gp-003"),
                this.firstName,
                this.lastName,
                this.address,
                this.phoneNumber
            )
        )

        // when
        Mockito.`when`(this.accountRepository.getAllGeneralPractitioners()).thenReturn(allGeneralPractitioners)
        val actual: List<GeneralPractitionerDTO> = this.accountService.getAllGeneralPractitioners()
        val expected: List<GeneralPractitionerDTO> =
            GeneralPractitionerDTO.fromGeneralPractitioners(allGeneralPractitioners)

        // then
        Assertions.assertEquals(expected.size, actual.size)
        Assertions.assertEquals(expected[0].id(), actual[0].id())
        Assertions.assertEquals(expected[1].id(), actual[1].id())
        Assertions.assertEquals(expected[2].id(), actual[2].id())
    }

    @Test
    fun given_repository_when_getAllPatients_then_returnsAll() {
        // given
        val allPatients: List<Patient> = listOf(
            Patient(
                PatientId("dom-id-pa-001"),
                this.firstName,
                this.lastName,
                this.address,
                this.gender,
                this.birthDate,
                this.phoneNumber,
                this.email,
                this.usingApp,
                MedicalRecordId("dom-id-mr-001"),
                GeneralPractitionerId("dom-id-gp-001")
            ),
            Patient(
                PatientId("dom-id-pa-002"),
                this.firstName,
                this.lastName,
                this.address,
                this.gender,
                this.birthDate,
                this.phoneNumber,
                this.email,
                this.usingApp,
                MedicalRecordId("dom-id-mr-002"),
                GeneralPractitionerId("dom-id-gp-002")
            ),
            Patient(
                PatientId("dom-id-pa-003"),
                this.firstName,
                this.lastName,
                this.address,
                this.gender,
                this.birthDate,
                this.phoneNumber,
                this.email,
                this.usingApp,
                MedicalRecordId("dom-id-mr-003"),
                GeneralPractitionerId("dom-id-gp-003")
            )
        )

        // when
        Mockito.`when`(this.accountRepository.getAllPatients()).thenReturn(allPatients)
        val actual: List<PatientDTO> = this.accountService.getAllPatients()
        val expected: List<PatientDTO> = PatientDTO.fromPatients(allPatients)

        // then
        Assertions.assertEquals(expected.size, actual.size)
        Assertions.assertEquals(expected[0].id(), actual[0].id())
        Assertions.assertEquals(expected[1].id(), actual[1].id())
        Assertions.assertEquals(expected[2].id(), actual[2].id())
    }

    @Test
    fun given_repository_when_getAllCareProviders_then_returnsAll() {
        // given
        val allCareProviders: List<CareProvider> = listOf(
            CareProvider(
                CareProviderId("dom-id-cp-001"),
                this.firstName,
                this.lastName,
                this.address,
                this.phoneNumber,
                this.specialism
            ),
            CareProvider(
                CareProviderId("dom-id-cp-002"),
                this.firstName,
                this.lastName,
                this.address,
                this.phoneNumber,
                this.specialism
            ),
            CareProvider(
                CareProviderId("dom-id-cp-003"),
                this.firstName,
                this.lastName,
                this.address,
                this.phoneNumber,
                this.specialism
            )
        )

        // when
        Mockito.`when`(this.accountRepository.getAllCareProviders()).thenReturn(allCareProviders)
        val actual: List<CareProviderDTO> = this.accountService.getAllCareProviders()
        val expected: List<CareProviderDTO> = CareProviderDTO.fromCareProviders(allCareProviders)

        // then
        Assertions.assertEquals(expected.size, actual.size)
        Assertions.assertEquals(expected[0].id(), actual[0].id())
        Assertions.assertEquals(expected[1].id(), actual[1].id())
        Assertions.assertEquals(expected[2].id(), actual[2].id())
    }

}