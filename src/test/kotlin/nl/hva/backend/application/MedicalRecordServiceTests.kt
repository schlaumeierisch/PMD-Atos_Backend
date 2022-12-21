package nl.hva.backend.application

import nl.hva.backend.application.api.MedicalRecordService
import nl.hva.backend.application.dto.*
import nl.hva.backend.domain.*
import nl.hva.backend.domain.api.MedicalRecordRepository
import nl.hva.backend.domain.ids.*
import nl.hva.backend.domain.value_objects.DiagnosisType
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.time.LocalDate

@SpringBootTest
class MedicalRecordServiceTests {

    @Autowired
    private lateinit var medicalRecordService: MedicalRecordService

    @MockBean
    private lateinit var medicalRecordRepository: MedicalRecordRepository

    // test data
    private lateinit var medicalRecordId: MedicalRecordId
    private lateinit var noteId1: NoteId
    private lateinit var noteId2: NoteId
    private lateinit var noteId3: NoteId
    private lateinit var medicationId1: MedicationId
    private lateinit var medicationId2: MedicationId
    private lateinit var medicationId3: MedicationId
    private lateinit var diagnosisId1: DiagnosisId
    private lateinit var diagnosisId2: DiagnosisId
    private lateinit var diagnosisId3: DiagnosisId
    private lateinit var exerciseId1: ExerciseId
    private lateinit var exerciseId2: ExerciseId
    private lateinit var exerciseId3: ExerciseId
    private lateinit var title: String
    private lateinit var description: String
    private lateinit var date: LocalDate
    private lateinit var diagnosisType: Enum<DiagnosisType>
    private lateinit var cause: String
    private lateinit var treatment: String
    private lateinit var advice: String

    @BeforeEach
    fun init() {
        // ids
        this.medicalRecordId = MedicalRecordId("dom-id-mr-001")
        this.noteId1 = NoteId("dom-id-no-001")
        this.noteId2 = NoteId("dom-id-no-002")
        this.noteId3 = NoteId("dom-id-no-003")
        this.medicationId1 = MedicationId("dom-id-med-001")
        this.medicationId2 = MedicationId("dom-id-med-002")
        this.medicationId3 = MedicationId("dom-id-med-003")
        this.diagnosisId1 = DiagnosisId("dom-id-dia-001")
        this.diagnosisId2 = DiagnosisId("dom-id-dia-002")
        this.diagnosisId3 = DiagnosisId("dom-id-dia-003")
        this.exerciseId1 = ExerciseId("dom-id-exerc-001")
        this.exerciseId2 = ExerciseId("dom-id-exerc-002")
        this.exerciseId3 = ExerciseId("dom-id-exerc-003")

        // attributes
        this.title = "Title"
        this.description = "Description"
        this.date = LocalDate.now()
        this.diagnosisType = DiagnosisType.BLOOD
        this.cause = "Cause"
        this.treatment = "Treatment"
        this.advice = "Advice"
    }

    @Test
    fun given_emptyRepository_when_getMedicalRecord_then_returnsEmpty() {
        // when
        Mockito.`when`(this.medicalRecordRepository.getMedicalRecord(MedicalRecordId(""))).thenReturn(emptyList())

        // then
        Assertions.assertEquals(0, this.medicalRecordService.getMedicalRecord(MedicalRecordId("")).size)
    }

    @Test
    fun given_repository_when_getMedicalRecord_then_returnsMedicalRecord() {
        // given
        val medicalRecord: List<MedicalRecord> = listOf(
            MedicalRecord(this.medicalRecordId)
        )
        val expected: List<MedicalRecordDTO> = listOf(
            MedicalRecordDTO.fromMedicalRecord(medicalRecord[0])
        )

        // when
        Mockito.`when`(this.medicalRecordRepository.getMedicalRecord(this.medicalRecordId)).thenReturn(medicalRecord)
        val actual: List<MedicalRecordDTO> = this.medicalRecordService.getMedicalRecord(this.medicalRecordId)

        // then
        Assertions.assertEquals(expected.size, actual.size)
        Assertions.assertEquals(expected[0].id(), actual[0].id())
    }

    @Test
    fun given_emptyRepository_when_getAllNotes_then_returnsEmpty() {
        // when
        Mockito.`when`(this.medicalRecordService.getAllNotes(MedicalRecordId(""))).thenReturn(emptyList())

        // then
        Assertions.assertEquals(0, this.medicalRecordService.getAllNotes(MedicalRecordId("")).size)
    }

    @Test
    fun given_emptyRepository_when_getAllMedication_then_returnsEmpty() {
        // when
        Mockito.`when`(this.medicalRecordService.getAllMedication(MedicalRecordId(""))).thenReturn(emptyList())

        // then
        Assertions.assertEquals(0, this.medicalRecordService.getAllMedication(MedicalRecordId("")).size)
    }

    @Test
    fun given_emptyRepository_when_getAllDiagnoses_then_returnsEmpty() {
        // when
        Mockito.`when`(this.medicalRecordService.getAllDiagnoses(MedicalRecordId(""))).thenReturn(emptyList())

        // then
        Assertions.assertEquals(0, this.medicalRecordService.getAllDiagnoses(MedicalRecordId("")).size)
    }

    @Test
    fun given_emptyRepository_when_getAllExercises_then_returnsEmpty() {
        // when
        Mockito.`when`(this.medicalRecordService.getAllExercises(MedicalRecordId(""))).thenReturn(emptyList())

        // then
        Assertions.assertEquals(0, this.medicalRecordService.getAllExercises(MedicalRecordId("")).size)
    }

    @Test
    fun given_repository_when_getAllNotes_then_returnsAll() {
        // given
        val allNotes: List<Note> = listOf(
            Note(
                this.noteId1,
                this.title,
                this.description,
                this.date,
                this.medicalRecordId
            ),
            Note(
                this.noteId2,
                this.title,
                this.description,
                this.date,
                this.medicalRecordId
            ),
            Note(
                this.noteId3,
                this.title,
                this.description,
                this.date,
                this.medicalRecordId
            )
        )
        val expected: List<NoteDTO> = NoteDTO.fromNotes(allNotes)

        // when
        Mockito.`when`(this.medicalRecordRepository.getAllNotes(medicalRecordId)).thenReturn(allNotes)
        val actual: List<NoteDTO> = this.medicalRecordService.getAllNotes(medicalRecordId)

        // then
        Assertions.assertEquals(expected.size, actual.size)
        Assertions.assertEquals(expected[0].id(), actual[0].id())
        Assertions.assertEquals(expected[1].id(), actual[1].id())
        Assertions.assertEquals(expected[2].id(), actual[2].id())
    }

    @Test
    fun given_repository_when_getAllMedication_then_returnsAll() {
        // given
        val allMedication: List<Medication> = listOf(
            Medication(
                this.medicationId1,
                this.title,
                this.description,
                this.date,
                this.date,
                this.medicalRecordId
            ),
            Medication(
                this.medicationId2,
                this.title,
                this.description,
                this.date,
                this.date,
                this.medicalRecordId
            ),
            Medication(
                this.medicationId3,
                this.title,
                this.description,
                this.date,
                this.date,
                this.medicalRecordId
            )
        )
        val expected: List<MedicationDTO> = MedicationDTO.fromMedication(allMedication)

        // when
        Mockito.`when`(this.medicalRecordRepository.getAllMedication(medicalRecordId)).thenReturn(allMedication)
        val actual: List<MedicationDTO> = this.medicalRecordService.getAllMedication(medicalRecordId)

        // then
        Assertions.assertEquals(expected.size, actual.size)
        Assertions.assertEquals(expected[0].id(), actual[0].id())
        Assertions.assertEquals(expected[1].id(), actual[1].id())
        Assertions.assertEquals(expected[2].id(), actual[2].id())
    }

    @Test
    fun given_repository_when_getAllDiagnoses_then_returnsAll() {
        // given
        val allDiagnoses: List<Diagnosis> = listOf(
            Diagnosis(
                this.diagnosisId1,
                this.title,
                this.diagnosisType,
                this.date,
                this.cause,
                this.treatment,
                this.advice,
                this.medicalRecordId
            ),
            Diagnosis(
                this.diagnosisId2,
                this.title,
                this.diagnosisType,
                this.date,
                this.cause,
                this.treatment,
                this.advice,
                this.medicalRecordId
            ),
            Diagnosis(
                this.diagnosisId3,
                this.title,
                this.diagnosisType,
                this.date,
                this.cause,
                this.treatment,
                this.advice,
                this.medicalRecordId
            )
        )
        val expected: List<DiagnosisDTO> = DiagnosisDTO.fromDiagnoses(allDiagnoses)

        // when
        Mockito.`when`(this.medicalRecordRepository.getAllDiagnoses(medicalRecordId)).thenReturn(allDiagnoses)
        val actual: List<DiagnosisDTO> = this.medicalRecordService.getAllDiagnoses(medicalRecordId)

        // then
        Assertions.assertEquals(expected.size, actual.size)
        Assertions.assertEquals(expected[0].id(), actual[0].id())
        Assertions.assertEquals(expected[1].id(), actual[1].id())
        Assertions.assertEquals(expected[2].id(), actual[2].id())
    }

    @Test
    fun given_repository_when_getAllExercises_then_returnsAll() {
        // given
        val allExercises: List<Exercise> = listOf(
            Exercise(
                this.exerciseId1,
                this.title,
                this.description,
                this.date,
                this.date,
                this.medicalRecordId
            ),
            Exercise(
                this.exerciseId2,
                this.title,
                this.description,
                this.date,
                this.date,
                this.medicalRecordId
            ),
            Exercise(
                this.exerciseId3,
                this.title,
                this.description,
                this.date,
                this.date,
                this.medicalRecordId
            )
        )
        val expected: List<ExerciseDTO> = ExerciseDTO.fromExercises(allExercises)

        // when
        Mockito.`when`(this.medicalRecordRepository.getAllExercises(medicalRecordId)).thenReturn(allExercises)
        val actual: List<ExerciseDTO> = this.medicalRecordService.getAllExercises(medicalRecordId)

        // then
        Assertions.assertEquals(expected.size, actual.size)
        Assertions.assertEquals(expected[0].id(), actual[0].id())
        Assertions.assertEquals(expected[1].id(), actual[1].id())
        Assertions.assertEquals(expected[2].id(), actual[2].id())
    }

//    @Test
//    fun given_emptyRepository_when_getNoteByIdAndMr_then_returnsEmpty() {
//        // when
//        Mockito.`when`(this.medicalRecordRepository.getNoteByIdAndMr(NoteId(""), MedicalRecordId(""))).thenReturn(emptyList())
//
//        // then
//        Assertions.assertEquals(0, this.accountService.getGeneralPractitionerById(GeneralPractitionerId("")).size)
//    }

}