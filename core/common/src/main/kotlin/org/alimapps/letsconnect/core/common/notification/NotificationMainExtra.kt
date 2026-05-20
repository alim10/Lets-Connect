package org.alimapps.letsconnect.core.common.notification
import com.google.errorprone.annotations.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Keep
data class NotificationMainExtra(
    @SerializedName("AppointmentId")
    val appointmentId: String? = null,
    @SerializedName("organization_logo")
    val organizationLogo: String? = null,
    @SerializedName("organization_name")
    val organizationName: String? = null,
    @SerializedName("orderId")
    val orderId: String? = null,
    @SerializedName("PractionerNameAr")
    val practitionerNameAr: String? = null, // Used in "SHY-TBC-CHAT-C"
    @SerializedName("PractionerNameEn")
    val practitionerNameEn: String? = null, // Used in "SHY-TBC-CHAT-C"
    @SerializedName("ChatCloserId")
    val chatCloserId: Int? = null, // Used in "SHY-TBC-CHAT-C"
    @SerializedName("SessionId")
    val sessionId: String? = null,
    @SerializedName("TempBookingId")
    val tempBookingId: String? = null,
    @SerializedName("surveyUrl")
    val surveyUrl: String? = null,
    @SerializedName("modSurveyUrl")     // fms survey url
    val modSurveyUrl: String? = null,
    @SerializedName("PractionerNID")
    val practitionerNID: String? = null,
    @SerializedName("SlotTimeMinutes")
    val slotTimeMinutes: String? = null,
    @SerializedName("Appointment")
    val appointment: AppointmentNotificationExtra? = null,

    @SerializedName("TelehealthAppointment")
    val telehealthAppointment: TelehealthAppointment? = null,
    @SerializedName("TelehealthConfiguration")
    val telehealthConfiguration: TelehealthConfiguration? = null,
    @SerializedName("EvaluationConfig")
    val evaluationConfig: EvaluationConfig? = null,

    //TODO -> TeamCare chat message -> SHY-TBC-CHAT-C
    @SerializedName("PractitionerNID")
    val teamCareDoctorNID: String?,
    @SerializedName("Team_id")
    val teamId: String?,
    @SerializedName("SessionID")
    val teamCareSessionId: String?,
    @SerializedName("visit_id")
    val visitId: String?,
    @SerializedName("dependent_nid")
    val dependentNationalId: String?,
    //TODO -> WebView digital twin -> SHY-DIGITALTWIN-SURVEY-A
    @SerializedName("urlPath", alternate = ["url"])
    val url: String?,
    //TODO -> Steps last saved date -> SHY-STP-Bg-Push-U
    @SerializedName("lastSavedDate")
    val lastSavedDate: String?,
    @SerializedName("isRetry")
    val shouldRetryPushSteps: Boolean? = false,
    //TODO -> Medical report  -> SHY-REPORT-SCHOOL-SCREENING-A
    @SerializedName("medical_reports_typeId")
    val medicalReportTypeId: String?,
    @SerializedName("medical_report_name_En")
    val medicalReportNameEn: String?,
    @SerializedName("medical_report_name_Ar")
    val medicalReportNameAr: String?,

    //TODO -> New Challenge details
    @SerializedName("challengeId")
    val challengeId: String? = null,
    @SerializedName("challengeStatusId")
    val challengeStatusId: String? = null,
    @SerializedName("challengeCategoryID")
    val challengeCategoryId: String? = null,

    @SerializedName("requestId")
    val requestId: String? = null, // for blood donation request
): Serializable {
    @Keep
    data class AppointmentNotificationExtra(
        @SerializedName("AppointmentId")
        val appointmentId: String? = null,
        @SerializedName("CASAppointmentCode")
        val cASAppointmentCode: String? = null,
        @SerializedName("CallId")
        val callId: String? = null,
        @SerializedName("CanJoinCall")
        val canJoinCall: Boolean? = null,
        @SerializedName("CareId")
        val careId: String? = null,
        @SerializedName("ClinicId")
        val clinicId: String? = null,
        @SerializedName("ClinicName")
        val clinicName: String? = null,
        @SerializedName("ClinicNameArabic")
        val clinicNameArabic: String? = null,
        @SerializedName("CompanionFullNameAr")
        val companionFullNameAr: String? = null,
        @SerializedName("CompanionFullNameEn")
        val companionFullNameEn: String? = null,
        @SerializedName("CompanionNationalId")
        val companionNationalId: String? = null,
        @SerializedName("CompanionDecision")
        val companionDecision: String? = null,
        @SerializedName("CompanionRelation")
        val companionRelation: Int? = null,
        @SerializedName("EndDateTime")
        val endDateTime: String? = null,
        @SerializedName("HospitalId")
        val hospitalId: String? = null,
        @SerializedName("HospitalName")
        val hospitalName: String? = null,
        @SerializedName("HospitalNameArabic")
        val hospitalNameArabic: String? = null,
        @SerializedName("IsCompanion")
        val isCompanion: Boolean? = null,
        @SerializedName("PatientId")
        val patientId: String? = null,
        @SerializedName("PatientName")
        val patientName: String? = null,
        @SerializedName("PatientNameArabic")
        val patientNameArabic: String? = null,
        @SerializedName("PhysicianID")
        val physicianID: String? = null,
        @SerializedName("PhysicianName")
        val physicianName: String? = null,
        @SerializedName("PhysicianNameArabic")
        val physicianNameArabic: String? = null,
        @SerializedName("SlotTimeMinutes")
        val slotTimeMinutes: String? = null,
        @SerializedName("Source")
        val source: String? = null,
        @SerializedName("StartDateTime")
        val startDateTime: String? = null,
        @SerializedName("Status")
        val status: String? = null
    ): Serializable {
        /*companion object {
            fun AppointmentNotificationExtra.toNewAppointment() = NewAppointmentItem(
                type = AppointmentType.ALL,
                virtualAppointmentItem = VirtualAppointmentItem(
                    appointmentId = appointmentId ?: "",
                    patientId = patientId,
                    CASAppointmentCode = cASAppointmentCode,
                    startDateTime = startDateTime,
                    patientName = patientName,
                    endDateTime = endDateTime,
                    slotTimeMinutes = slotTimeMinutes?.toInt() ?: 0,
                    physicianName = physicianName,
                    physicianID = physicianID,
                    Status = AppointmentStatus.BOOKED,
                    clinicName = clinicName,
                    ClinicId = clinicId,
                    hospitalName = hospitalName,
                    HospitalId = hospitalId,
                    canJoinCall = canJoinCall,
                    appointmentSource = AppointmentSource.VC,
                    bookingType = BookingType.ADVANCE,
                    chatAvailable = true,
                    isUpcoming = true,
                    callId = callId ?: "",
                    day = 1,
                    joinButtonVisibility = false,
                    canModify = true,
                    companion = SessionSettingImpl.CompanionImpl(
                        companionNationalId = companionNationalId,
                        companionFullNameAr = companionFullNameAr,
                        companionFullNameEn = companionFullNameEn,
                        companionRelation = companionRelation,
                        isCompanion = isCompanion,
                        companionDecision = ""
                    ),
                    canStart = false,
                    date = "",
                    month = "",
                    tempBookingId = appointmentId ?: "",
                    comment = "",
                    endTime = "",
                    startTime = ""
                ),
                clinicName = clinicName,
                clinicId = clinicId,
                patientName = patientName,
            )
        }*/
    }
}

data class TelehealthAppointment(
    @SerializedName("AppointmentId")
    val appointmentId: String?,
    @SerializedName("CASAppointmentCode")
    val cASAppointmentCode: String?,
    @SerializedName("CallId")
    val callId: String?,
    @SerializedName("CanJoinCall")
    val canJoinCall: Boolean?,
    @SerializedName("canStart")
    val canStart: Boolean?,
    @SerializedName("CaseDescription")
    val caseDescription: String?,
    @SerializedName("ClinicId")
    val clinicId: String?,
    @SerializedName("ClinicName")
    val clinicName: String?,
    @SerializedName("ClinicNameArabic")
    val clinicNameArabic: String?,
    @SerializedName("CompanionDecision")
    val companionDecision: String?,
    @SerializedName("CompanionFullNameAr")
    val companionFullNameAr: String?,
    @SerializedName("CompanionFullNameEn")
    val companionFullNameEn: String?,
    @SerializedName("CompanionNationalId")
    val companionNationalId: String?,
    @SerializedName("CompanionRelation")
    val companionRelation: Int?,
    @SerializedName("EndDateTime")
    val endDateTime: String?,
    @SerializedName("HospitalId")
    val hospitalId: String?,
    @SerializedName("HospitalName")
    val hospitalName: String?,
    @SerializedName("HospitalNameArabic")
    val hospitalNameArabic: String?,
    @SerializedName("IsCompanion")
    val isCompanion: Boolean?,
    @SerializedName("PatientId")
    val patientId: String?,
    @SerializedName("PatientName")
    val patientName: String?,
    @SerializedName("PatientNameArabic")
    val patientNameArabic: String?,
    @SerializedName("PhysicianID")
    val physicianID: String?,
    @SerializedName("PhysicianName")
    val physicianName: String?,
    @SerializedName("PhysicianNameArabic")
    val physicianNameArabic: String?,
    @SerializedName("sessionTimeConfiguration")
    val sessionTimeConfiguration: SessionTimeConfiguration?,
    @SerializedName("SlotTimeMinutes")
    val slotTimeMinutes: String?,
    @SerializedName("Source")
    val source: String?,
    @SerializedName("StartDateTime")
    val startDateTime: String?,
    @SerializedName("Status")
    val status: String?
): Serializable

data class TelehealthConfiguration(
    @SerializedName("CallExtensionMinutes")
    val callExtensionMinutes: String?,
    @SerializedName("CallJoinInTimeMinutes")
    val callJoinInTimeMinutes: String?,
    @SerializedName("CallUpdateStatusAfterMinutes")
    val callUpdateStatusAfterMinutes: String?,
    @SerializedName("ScheduleDaysRange")
    val scheduleDaysRange: String?
): Serializable

data class SessionTimeConfiguration(
    @SerializedName("bufferAfter")
    val bufferAfter: Int?,
    @SerializedName("bufferBefore")
    val bufferBefore: Int?,
    @SerializedName("duration")
    val duration: Int?,
    @SerializedName("isVideoEnabled")
    val isVideoEnabled: Boolean?,
    @SerializedName("platform")
    val platform: String?
): Serializable

data class EvaluationConfig(
    @SerializedName("isQuestionsRequired")
    val isQuestionsRequired: Boolean?,
    @SerializedName("sessionItemID")
    val sessionItemID: String?,
    @SerializedName("successMessage")
    val successMessage: String?
): Serializable
