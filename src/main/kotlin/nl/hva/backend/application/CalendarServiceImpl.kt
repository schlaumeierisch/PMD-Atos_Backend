package nl.hva.backend.application

import nl.hva.backend.application.api.CalendarService
import nl.hva.backend.application.dto.AppointmentDTO
import nl.hva.backend.domain.Appointment
import nl.hva.backend.domain.TimeSlot
import nl.hva.backend.domain.api.CalendarRepository
import nl.hva.backend.domain.api.GeneralPractitionerRepository
import nl.hva.backend.domain.ids.AppointmentId
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.GeneralPractitionerId
import nl.hva.backend.domain.ids.PatientId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.Month


@Service
class CalendarServiceImpl : CalendarService {

    @Autowired
    private lateinit var calendarRepository: CalendarRepository

    @Autowired
    private lateinit var generalPractitionerRepository: GeneralPractitionerRepository

    @Transactional
    override fun createAppointment(
        dateTime: LocalDateTime,
        reason: String,
        patientId: String,
        gpId: String?,
        cpId: String?
    ) {
        val appointmentId: AppointmentId = this.calendarRepository.nextIdentity()
        var generalPractitionerId = GeneralPractitionerId("")
        var careProviderId = CareProviderId("")

        if (gpId != null && cpId == null) {
            generalPractitionerId = GeneralPractitionerId(gpId)
            careProviderId = CareProviderId("")
        } else if (gpId == null && cpId != null) {
            generalPractitionerId = GeneralPractitionerId("")
            careProviderId = CareProviderId(cpId)
        }

        val appointment = Appointment(
            appointmentId, dateTime, reason, PatientId(patientId), generalPractitionerId, careProviderId
        )

        this.calendarRepository.createAppointment(appointment)
    }

    @Transactional
    override fun cancelAppointment(appointmentId: AppointmentId) {
        this.calendarRepository.cancelAppointment(appointmentId)
    }

    @Transactional
    override fun getAllAppointmentsByPatientId(patientId: PatientId): List<AppointmentDTO> {
        val appointments: List<Appointment> = this.calendarRepository.getAllAppointmentsByPatientId(patientId)

        return AppointmentDTO.fromAppointments(appointments)
    }

    @Transactional
    override fun getAllAppointmentsByGeneralPractitionerId(generalPractitionerId: GeneralPractitionerId): List<AppointmentDTO> {
        val appointments: List<Appointment> =
            this.calendarRepository.getAllAppointmentsByGeneralPractitionerId(generalPractitionerId)

        return AppointmentDTO.fromAppointments(appointments)
    }

    @Transactional
    override fun getAllAppointmentsByCareProviderId(careProviderId: CareProviderId): List<AppointmentDTO> {
        val appointments: List<Appointment> = this.calendarRepository.getAllAppointmentsByCareProviderId(careProviderId)

        return AppointmentDTO.fromAppointments(appointments)
    }

    @Transactional
    override fun getNotAvailableDaysInThisMonth(yearNumber: Int, monthNumber: Int, gpId: String): List<LocalDate> {
        val gp = this.generalPractitionerRepository.getAccountById(GeneralPractitionerId(gpId))

        val defaultAvailableTimeSlots = generateTimeSlots(gp.startTimeShift(), gp.endTimeShift(), gp.appointmentDuration(),
            listOf(TimeSlot(LocalTime.parse(gp.breakTimes()), LocalTime.parse(gp.breakTimes()).plusMinutes(gp.breakDuration()))))

        val notAvailableDays: ArrayList<LocalDate> = ArrayList()

        val month = Month.of(monthNumber)
        for (monthDay in 1..month.length(false)) {
            // we go day by day, so first of december for example.
            val dayOfMonth = LocalDate.of(yearNumber, month, monthDay)

            // Of the chosen day in the calendar view in the app, get the timeslots from the appointments the gp has.
            // Created by getting the starttime that is stored. End time calculated by adding the duration of the chosen gp/cp
            val timeSlotsOfCurrentDay = this.calendarRepository.getAppointmentsOfParticularDay(yearNumber, monthNumber, monthDay, GeneralPractitionerId(gpId))
                .sortedBy { it.dateTime() }.map {
                    TimeSlot(it.dateTime().toLocalTime(), it.dateTime().toLocalTime().plusMinutes(gp.appointmentDuration()))
            }

            // getAvailableTimeSlots will compare all the timeslots of the current day with the default timeslots that would normally be available.
            val availableTimeSlots = getAvailableTimeslots(defaultAvailableTimeSlots, timeSlotsOfCurrentDay)

            println("\n\n\n\n\n SPOT ME!!" +
                    "timeSlotsOfCurrentDay: ${timeSlotsOfCurrentDay.toString()} \n" +
                    "defaultAvailableTimeSlots: ${defaultAvailableTimeSlots.toString()} \n" +
                    "availableTimeSlots: ${availableTimeSlots.toString()}" +
                    " \n\n\n\n\n")

            // If the list of available timeslots is empty that means there is no space anymore.
            // Meaning the currently looped day will be added to a list. The front-end will use this to disable fully booked days.
            if (availableTimeSlots.isEmpty()) { notAvailableDays.add(dayOfMonth) }
        }

        return notAvailableDays
    }

    @Transactional
    override fun getAvailableTimeSlotsOfParticularDay(yearNumber: Int, monthNumber: Int, monthDay: Int, gpId: String): List<TimeSlot> {
        val gp = this.generalPractitionerRepository.getAccountById(GeneralPractitionerId(gpId))

        val defaultAvailableTimeSlots = generateTimeSlots(gp.startTimeShift(), gp.endTimeShift(), gp.appointmentDuration(),
            listOf(TimeSlot(LocalTime.parse(gp.breakTimes()), LocalTime.parse(gp.breakTimes()).plusMinutes(gp.breakDuration()))))

        // Of the chosen day in the calendar view in the app, get the timeslots from the appointments the gp has.
        // Created by getting the starttime that is stored. End time calculated by adding the duration of the chosen gp/cp
        val timeSlotsOfCurrentDay = this.calendarRepository.getAppointmentsOfParticularDay(yearNumber, monthNumber, monthDay, GeneralPractitionerId(gpId))
            .sortedBy { it.dateTime() }.map {
                TimeSlot(it.dateTime().toLocalTime(), it.dateTime().toLocalTime().plusMinutes(gp.appointmentDuration()))
        }

        // getAvailableTimeSlots will compare all the timeslots of the current day with the default timeslots that would normally be available.
        return getAvailableTimeslots(defaultAvailableTimeSlots, timeSlotsOfCurrentDay)
    }

    /**
     * @author Nassim Mengat
     * @param byDefaultAvailableTimeSlots: a complete list of [TimeSlot]s that would be available if day was empty of appointments.
     * @param filledTimeSlots: actually booked [TimeSlot]s of a certain cp/gp.
     * @return a [List] of [TimeSlot]s that are available to book for the user that particular day.
     */
    fun getAvailableTimeslots(
        byDefaultAvailableTimeSlots: List<TimeSlot>,
        filledTimeSlots: List<TimeSlot>
    ): List<TimeSlot> {
        val notFilledTimeSlots = mutableListOf<TimeSlot>()

        // Loops through timeslots that by default would be available.
        for (timeSlot in byDefaultAvailableTimeSlots) {
            /* .any returns true if at least one condition is true that are given.
            *  the lambda in the .any method basically says return true if either one of these statements are NOT (!) true, do this...
            *  and what do this is... is the current TimeSlot that doesnt exist in filledTimeSlots. which means its available. */
            if (!filledTimeSlots.any { it.startTime == timeSlot.startTime && it.endTime == timeSlot.endTime }) {
                notFilledTimeSlots.add(timeSlot)
            }
        }

        return notFilledTimeSlots
    }

    /**
     * @author Nassim Mengat
     * @param startTime: The start time of the shift of a cp/gp.
     * @param endTime: The end time of the shift of a cp/gp.
     * @param duration: The duration of an appointment with a cp/gp. An appointment is longer at a Physio than a GP for example.
     * @param avoid: A list of timeslots (timeframe) to avoid making timeslots/appointments in. It's a list because a cp/gp can have multiple breaks for example.
     * @return a [List] of [TimeSlot]s that are not in a particular timeFrame with a custom duration of each appointment depending on cp/gp.
     */
    fun generateTimeSlots(
        startTime: LocalTime,
        endTime: LocalTime,
        duration: Long,
        avoid: List<TimeSlot>
    ): List<TimeSlot> {
        val timeSlots = mutableListOf<TimeSlot>()
        // For example: startTime is 09:00 and endTime is 17:00, avoid is listOf(TimeSlot(12:00, 13:00)
        var currentTime = startTime

        // While the currently stored time (which was startTime) hasn't reached endTime yet... loop...
        while (currentTime < endTime) {
            // The next startTime will be the current stored startTime plus the amount of minutes an appointment takes.
            val nextTime = currentTime.plusMinutes(duration)
            // save the current timeslot which is in the first loop, TimeSlot(09:00, 09:15) (nextTime already have been added the minutes to for the endTime)
            val timeSlot = TimeSlot(currentTime, nextTime)

            /* .none will return true if no elements match the given predicate.
            *  the lambda in the .none method basically says:
            *  if the startTime of the currently looped timeSlot is in the timeFrame (it.startTime..it.endTime) given by @param avoid, return true
            *  So for the first loop, TimeSlot(09:00, 09:15) is not in the (example) avoid value, 12:00-13:00.
            *  that is checked by the .. (inRange) operator: creates a range from one value to another value which are COMPARABLE with each other!
            *  1 minute has been subtracted of the avoid timeframe because otherwise the next timeslot couldn't be generated at the endTime of @avoid. */
            if (avoid.none { timeSlot.startTime in it.startTime..it.endTime.minusMinutes(1) }) {

                // add the timeslot if the currently looped TimeSlot is not in the timeframe.
                timeSlots.add(timeSlot)
            }

            // update the currentTime with the endTime of the current TimeSlot, later on nextTime will be added minutes of duration again.
            currentTime = nextTime
        }

        // return the timeslots when the loop is finished adding timeslots till the endtime avoiding the timeframe.
        return timeSlots
    }
}