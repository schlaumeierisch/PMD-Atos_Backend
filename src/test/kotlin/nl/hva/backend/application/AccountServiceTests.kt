package nl.hva.backend.application

import nl.hva.backend.application.api.AccountService
import nl.hva.backend.application.dto.CareProviderDTO
import nl.hva.backend.application.dto.GeneralPractitionerDTO
import nl.hva.backend.application.dto.PatientDTO
import nl.hva.backend.application.dto.many_to_many.PatientCareProviderRelationDTO
import nl.hva.backend.domain.CareProvider
import nl.hva.backend.domain.GeneralPractitioner
import nl.hva.backend.domain.Patient
import nl.hva.backend.domain.api.AccountRepository
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.GeneralPractitionerId
import nl.hva.backend.domain.ids.MedicalRecordId
import nl.hva.backend.domain.ids.PatientId
import nl.hva.backend.domain.many_to_many.PatientCareProviderRelation
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
import java.time.LocalTime

@SpringBootTest
class AccountServiceTests {

    @Autowired
    private lateinit var accountService: AccountService

    @MockBean
    private lateinit var accountRepository: AccountRepository

    // test data
    private lateinit var generalPractitionerId1: GeneralPractitionerId
    private lateinit var generalPractitionerId2: GeneralPractitionerId
    private lateinit var generalPractitionerId3: GeneralPractitionerId
    private lateinit var patientId1: PatientId
    private lateinit var patientId2: PatientId
    private lateinit var patientId3: PatientId
    private lateinit var careProviderId1: CareProviderId
    private lateinit var careProviderId2: CareProviderId
    private lateinit var careProviderId3: CareProviderId
    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var address: Address
    private lateinit var gender: Enum<Gender>
    private lateinit var birthDate: LocalDate
    private lateinit var phoneNumber: String
    private lateinit var email: String
    private var usingApp: Boolean = true
    private lateinit var specialism: Enum<Specialism>
    private lateinit var startTimeShift: LocalTime
    private lateinit var endTimeShift: LocalTime
    private lateinit var breakTimes: String
    private var breakDuration: Long = 0L
    private var appointmentDuration: Long = 0L

    @BeforeEach
    fun init() {
        // ids
        this.generalPractitionerId1 = GeneralPractitionerId("dom-id-gp-001")
        this.generalPractitionerId2 = GeneralPractitionerId("dom-id-gp-002")
        this.generalPractitionerId3 = GeneralPractitionerId("dom-id-gp-003")
        this.patientId1 = PatientId("dom-id-pa-001")
        this.patientId2 = PatientId("dom-id-pa-002")
        this.patientId3 = PatientId("dom-id-pa-003")
        this.careProviderId1 = CareProviderId("dom-id-cp-001")
        this.careProviderId2 = CareProviderId("dom-id-cp-002")
        this.careProviderId3 = CareProviderId("dom-id-cp-003")

        // attributes
        this.firstName = "Lotje"
        this.lastName = "Oldhof"
        this.address = Address("Johan Frisoplantsoen 184", "2751XR", "Moerkapelle", "Netherlands")
        this.gender = Gender.FEMALE
        this.birthDate = LocalDate.of(1978, 11, 28)
        this.phoneNumber = "0636038499"
        this.email = "odin88@packiu.com"
        this.usingApp = true
        this.specialism = Specialism.APOTHECARY
        this.startTimeShift = LocalTime.now()
        this.endTimeShift = LocalTime.now()
        this.breakTimes = "These are the break times"
        this.breakDuration = 30
        this.appointmentDuration = 15
    }

    @Test
    fun given_emptyRepository_when_getAllGeneralPractitioners_then_returnsEmpty() {
        // when
        Mockito.`when`(this.accountRepository.getAllGeneralPractitioners()).thenReturn(emptyList())

        // then
        Assertions.assertEquals(0, this.accountService.getAllGeneralPractitioners().size)
    }

    @Test
    fun given_emptyRepository_when_getAllPatients_then_returnsEmpty() {
        // when
        Mockito.`when`(this.accountRepository.getAllPatients()).thenReturn(emptyList())

        // then
        Assertions.assertEquals(0, this.accountService.getAllPatients().size)
    }

    @Test
    fun given_emptyRepository_when_getAllCareProviders_then_returnsEmpty() {
        // when
        Mockito.`when`(this.accountRepository.getAllCareProviders()).thenReturn(emptyList())

        // then
        Assertions.assertEquals(0, this.accountService.getAllCareProviders().size)
    }

    @Test
    fun given_repository_when_getAllGeneralPractitioners_then_returnsAll() {
        // given
        val allGeneralPractitioners: List<GeneralPractitioner> = listOf(
            GeneralPractitioner(
                this.generalPractitionerId1,
                this.firstName,
                this.lastName,
                this.address,
                this.phoneNumber,
                this.email,
                this.startTimeShift,
                this.endTimeShift,
                this.breakTimes,
                this.breakDuration,
                this.appointmentDuration
            ),
            GeneralPractitioner(
                this.generalPractitionerId2,
                this.firstName,
                this.lastName,
                this.address,
                this.phoneNumber,
                this.email,
                this.startTimeShift,
                this.endTimeShift,
                this.breakTimes,
                this.breakDuration,
                this.appointmentDuration
            ),
            GeneralPractitioner(
                this.generalPractitionerId3,
                this.firstName,
                this.lastName,
                this.address,
                this.phoneNumber,
                this.email,
                this.startTimeShift,
                this.endTimeShift,
                this.breakTimes,
                this.breakDuration,
                this.appointmentDuration
            )
        )
        val expected: List<GeneralPractitionerDTO> =
            GeneralPractitionerDTO.fromGeneralPractitioners(allGeneralPractitioners)

        // when
        Mockito.`when`(this.accountRepository.getAllGeneralPractitioners()).thenReturn(allGeneralPractitioners)
        val actual: List<GeneralPractitionerDTO> = this.accountService.getAllGeneralPractitioners()

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
                this.patientId1,
                this.firstName,
                this.lastName,
                this.address,
                this.gender,
                this.birthDate,
                this.phoneNumber,
                this.email,
                this.usingApp,
                MedicalRecordId("dom-id-mr-001"),
                this.generalPractitionerId1
            ),
            Patient(
                this.patientId2,
                this.firstName,
                this.lastName,
                this.address,
                this.gender,
                this.birthDate,
                this.phoneNumber,
                this.email,
                this.usingApp,
                MedicalRecordId("dom-id-mr-002"),
                this.generalPractitionerId2
            ),
            Patient(
                this.patientId3,
                this.firstName,
                this.lastName,
                this.address,
                this.gender,
                this.birthDate,
                this.phoneNumber,
                this.email,
                this.usingApp,
                MedicalRecordId("dom-id-mr-003"),
                this.generalPractitionerId3
            )
        )
        val expected: List<PatientDTO> = PatientDTO.fromPatients(allPatients)

        // when
        Mockito.`when`(this.accountRepository.getAllPatients()).thenReturn(allPatients)
        val actual: List<PatientDTO> = this.accountService.getAllPatients()

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
                this.careProviderId1,
                this.firstName,
                this.lastName,
                this.address,
                this.phoneNumber,
                this.email,
                this.specialism
            ),
            CareProvider(
                this.careProviderId2,
                this.firstName,
                this.lastName,
                this.address,
                this.phoneNumber,
                this.email,
                this.specialism
            ),
            CareProvider(
                this.careProviderId3,
                this.firstName,
                this.lastName,
                this.address,
                this.phoneNumber,
                this.email,
                this.specialism
            )
        )
        val expected: List<CareProviderDTO> = CareProviderDTO.fromCareProviders(allCareProviders)

        // when
        Mockito.`when`(this.accountRepository.getAllCareProviders()).thenReturn(allCareProviders)
        val actual: List<CareProviderDTO> = this.accountService.getAllCareProviders()

        // then
        Assertions.assertEquals(expected.size, actual.size)
        Assertions.assertEquals(expected[0].id(), actual[0].id())
        Assertions.assertEquals(expected[1].id(), actual[1].id())
        Assertions.assertEquals(expected[2].id(), actual[2].id())
    }

    @Test
    fun given_emptyRepository_when_getGeneralPractitionerById_then_returnsEmpty() {
        // when
        Mockito.`when`(this.accountRepository.getGeneralPractitionerById(GeneralPractitionerId("")))
            .thenReturn(emptyList())

        // then
        Assertions.assertEquals(0, this.accountService.getGeneralPractitionerById(GeneralPractitionerId("")).size)
    }

    @Test
    fun given_emptyRepository_when_getPatientById_then_returnsEmpty() {
        // when
        Mockito.`when`(this.accountRepository.getPatientById(PatientId(""))).thenReturn(emptyList())

        // then
        Assertions.assertEquals(0, this.accountService.getPatientById(PatientId("")).size)
    }

    @Test
    fun given_emptyRepository_when_getCareProviderById_then_returnsEmpty() {
        // when
        Mockito.`when`(this.accountRepository.getCareProviderById(CareProviderId(""))).thenReturn(emptyList())

        // then
        Assertions.assertEquals(0, this.accountService.getCareProviderById(CareProviderId("")).size)
    }

    @Test
    fun given_repository_when_getGeneralPractitionerById_then_returnsGeneralPractitioner() {
        // given
        val generalPractitioner: List<GeneralPractitioner> = listOf(
            GeneralPractitioner(
                this.generalPractitionerId1,
                this.firstName,
                this.lastName,
                this.address,
                this.phoneNumber,
                this.email,
                this.startTimeShift,
                this.endTimeShift,
                this.breakTimes,
                this.breakDuration,
                this.appointmentDuration
            )
        )
        val expected: List<GeneralPractitionerDTO> =
            listOf(GeneralPractitionerDTO.fromGeneralPractitioner(generalPractitioner[0]))

        // when
        Mockito.`when`(this.accountRepository.getGeneralPractitionerById(this.generalPractitionerId1))
            .thenReturn(generalPractitioner)
        val actual: List<GeneralPractitionerDTO> =
            this.accountService.getGeneralPractitionerById(this.generalPractitionerId1)

        // then
        Assertions.assertEquals(expected.size, actual.size)
        Assertions.assertEquals(expected[0].id(), actual[0].id())
    }

    @Test
    fun given_repository_when_getPatientById_then_returnsPatient() {
        // given
        val patient: List<Patient> = listOf(
            Patient(
                this.patientId1,
                this.firstName,
                this.lastName,
                this.address,
                this.gender,
                this.birthDate,
                this.phoneNumber,
                this.email,
                this.usingApp,
                MedicalRecordId("dom-id-mr-001"),
                this.generalPractitionerId1
            )
        )
        val expected: List<PatientDTO> = listOf(PatientDTO.fromPatient(patient[0]))

        // when
        Mockito.`when`(this.accountRepository.getPatientById(this.patientId1)).thenReturn(patient)
        val actual: List<PatientDTO> = this.accountService.getPatientById(this.patientId1)

        // then
        Assertions.assertEquals(expected.size, actual.size)
        Assertions.assertEquals(expected[0].id(), actual[0].id())
    }

    @Test
    fun given_repository_when_getCareProviderById_then_returnsCareProvider() {
        // given
        val careProvider: List<CareProvider> = listOf(
            CareProvider(
                this.careProviderId1,
                this.firstName,
                this.lastName,
                this.address,
                this.phoneNumber,
                this.email,
                this.specialism
            )
        )
        val expected: List<CareProviderDTO> = listOf(CareProviderDTO.fromCareProvider(careProvider[0]))

        // when
        Mockito.`when`(this.accountRepository.getCareProviderById(this.careProviderId1))
            .thenReturn(careProvider)
        val actual: List<CareProviderDTO> = this.accountService.getCareProviderById(this.careProviderId1)

        // then
        Assertions.assertEquals(expected.size, actual.size)
        Assertions.assertEquals(expected[0].id(), actual[0].id())
    }

    @Test
    fun given_emptyRepository_when_getPatientsOfGeneralPractitionerById_then_returnsEmpty() {
        // when
        Mockito.`when`(this.accountRepository.getPatientsOfGeneralPractitionerById(GeneralPractitionerId("")))
            .thenReturn(emptyList())

        // then
        Assertions.assertEquals(
            0,
            this.accountService.getPatientsOfGeneralPractitionerById(GeneralPractitionerId("")).size
        )
    }

    @Test
    fun given_repository_when_getPatientsOfGeneralPractitionerById_then_returnsAll() {
        // given
        val allPatients: List<Patient> = listOf(
            Patient(
                this.patientId1,
                this.firstName,
                this.lastName,
                this.address,
                this.gender,
                this.birthDate,
                this.phoneNumber,
                this.email,
                this.usingApp,
                MedicalRecordId("dom-id-mr-001"),
                this.generalPractitionerId1
            ),
            Patient(
                this.patientId2,
                this.firstName,
                this.lastName,
                this.address,
                this.gender,
                this.birthDate,
                this.phoneNumber,
                this.email,
                this.usingApp,
                MedicalRecordId("dom-id-mr-002"),
                this.generalPractitionerId2
            ),
            Patient(
                this.patientId3,
                this.firstName,
                this.lastName,
                this.address,
                this.gender,
                this.birthDate,
                this.phoneNumber,
                this.email,
                this.usingApp,
                MedicalRecordId("dom-id-mr-003"),
                this.generalPractitionerId3
            )
        )
        val expected: List<PatientDTO> = PatientDTO.fromPatients(allPatients)

        // when
        Mockito.`when`(this.accountRepository.getPatientsOfGeneralPractitionerById(GeneralPractitionerId("")))
            .thenReturn(allPatients)
        val actual: List<PatientDTO> =
            this.accountService.getPatientsOfGeneralPractitionerById(GeneralPractitionerId(""))

        // then
        Assertions.assertEquals(expected.size, actual.size)
        Assertions.assertEquals(expected[0].id(), actual[0].id())
        Assertions.assertEquals(expected[1].id(), actual[1].id())
        Assertions.assertEquals(expected[2].id(), actual[2].id())
    }

    @Test
    fun given_emptyRepository_when_getPatientCareProviderRelationsByPatientId_then_returnsEmpty() {
        // when
        Mockito.`when`(this.accountRepository.getPatientCareProviderRelationsByPatientId(PatientId("")))
            .thenReturn(emptyList())

        // then
        Assertions.assertEquals(0, this.accountService.getPatientCareProviderRelationsByPatientId(PatientId("")).size)
    }

    @Test
    fun given_repository_when_getPatientCareProviderRelationsByPatientId_then_returnsAll() {
        // given
        val allPatientCareProviderRelations: List<PatientCareProviderRelation> = listOf(
            PatientCareProviderRelation(
                this.patientId1,
                this.careProviderId1
            ),
            PatientCareProviderRelation(
                this.patientId2,
                this.careProviderId2
            ),
            PatientCareProviderRelation(
                this.patientId3,
                this.careProviderId3
            )
        )
        val expected: List<PatientCareProviderRelationDTO> =
            PatientCareProviderRelationDTO.fromPatientCareProviderRelations(allPatientCareProviderRelations)

        // when
        Mockito.`when`(this.accountRepository.getPatientCareProviderRelationsByPatientId(PatientId("")))
            .thenReturn(allPatientCareProviderRelations)
        val actual: List<PatientCareProviderRelationDTO> =
            this.accountService.getPatientCareProviderRelationsByPatientId(PatientId(""))

        // then
        Assertions.assertEquals(expected.size, actual.size)
        Assertions.assertEquals(expected[0].patientId(), actual[0].patientId())
        Assertions.assertEquals(expected[1].patientId(), actual[1].patientId())
        Assertions.assertEquals(expected[2].patientId(), actual[2].patientId())
        Assertions.assertEquals(expected[0].cpId(), actual[0].cpId())
        Assertions.assertEquals(expected[1].cpId(), actual[1].cpId())
        Assertions.assertEquals(expected[2].cpId(), actual[2].cpId())
    }

}