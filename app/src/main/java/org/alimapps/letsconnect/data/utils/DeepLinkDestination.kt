package org.alimapps.letsconnect.data.utils

import android.os.Parcelable

/**
 * You can use this method with navController.navigateToDeeplink([DeepLinkDestination]) extension when navigating between modules.
 * */
sealed class DeepLinkDestination(val address: String) {

    object CitizenProfileMobileUpdate :
        DeepLinkDestination("android-app://org.alimapps.letsconnect/update_mobile_number/citizen")
    
    data class VisitorProfileMobileUpdate(val phoneSuffix: String) :
        DeepLinkDestination("android-app://org.alimapps.letsconnect/update_mobile_number/visitor?phoneSuffix=$phoneSuffix")

    data class SehhatyWallet(val dependentId: String? = null) :
        DeepLinkDestination(
            "android-app://org.alimapps.letsconnect/sehhatyWallet?".putArgsOld(
                "dependentId",
                dependentId
            )
        )

    object RegisterSuccess :
        DeepLinkDestination("android-app://org.alimapps.letsconnect/registration/success")

    class CalendarAppointment(
        bookAppointmentUI: String ,
    ) : DeepLinkDestination(
        "android-app://org.alimapps.letsconnect/calendarAppointment"
            .putArgsOld("appointment_book_request", bookAppointmentUI)

    )


    object As3afnyAddReportFragment :
        DeepLinkDestination("android-app://org.alimapps.letsconnect/as3afnyAddReportFragment")
    
    class EntryVaccines(
        val userFilter: Parcelable? = null,
        val showOnlyVirusVaccine: Boolean = false
    ) : DeepLinkDestination("android-app://org.alimapps.letsconnect/healthSummaryVaccineFragment?showOnlyVirusVaccine=$showOnlyVirusVaccine")
    
    object EntryHealthSummary: DeepLinkDestination("android-app://org.alimapps.letsconnect/healthSummary")

}

fun <T> String.putArgsOld(key: String, value: T?) = this + value?.let { "?$key=$it" }.orEmpty()
fun <T> String.putArgs(key: String, value: T?) = this + value?.let { "$key=$it&" }.orEmpty()
