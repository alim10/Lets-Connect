package org.alimapps.letsconnect.core.network.util

import com.google.gson.Gson
import org.alimapps.letsconnect.core.network.retrofit.RemoteConfigFeaturesConstants
import org.alimapps.letsconnect.core.network.retrofit.RemoteConfigFeaturesConstants.MyHealth.MY_HEALTH_NEPHIS_PARTNER_COUNT
import org.alimapps.letsconnect.core.network.retrofit.utils.FeatureConfigurations

fun main() {
    // Please add any new object class name here
    val classes = listOf(
        RemoteConfigFeaturesConstants.Dashboard.javaClass.declaredFields,
        RemoteConfigFeaturesConstants.Notification.javaClass.declaredFields,
        RemoteConfigFeaturesConstants.SideMenu.javaClass.declaredFields,
        RemoteConfigFeaturesConstants.Appointment.javaClass.declaredFields,
        RemoteConfigFeaturesConstants.Appointment.Upcoming.javaClass.declaredFields,
        RemoteConfigFeaturesConstants.Appointment.Past.javaClass.declaredFields,
        RemoteConfigFeaturesConstants.Appointment.AddNewAppointment.javaClass.declaredFields,
        RemoteConfigFeaturesConstants.MyHealth.javaClass.declaredFields,
        RemoteConfigFeaturesConstants.MyHealth.MedicalReports.javaClass.declaredFields,
        RemoteConfigFeaturesConstants.MyHealth.Prescriptions.javaClass.declaredFields,
        RemoteConfigFeaturesConstants.MyHealth.WomanHealth.javaClass.declaredFields,
        RemoteConfigFeaturesConstants.MyHealth.Medication.javaClass.declaredFields,
        RemoteConfigFeaturesConstants.MyHealth.HealthRecord.javaClass.declaredFields,
        RemoteConfigFeaturesConstants.MyHealth.WalletList.javaClass.declaredFields,
        RemoteConfigFeaturesConstants.MyHealth.PersonalProfile.javaClass.declaredFields,
        RemoteConfigFeaturesConstants.MyHealth.Visits.javaClass.declaredFields,
        RemoteConfigFeaturesConstants.MyHealth.Lab.javaClass.declaredFields,
        RemoteConfigFeaturesConstants.MyHealth.MyDoctor.javaClass.declaredFields,
        RemoteConfigFeaturesConstants.MyHealth.Procedures.javaClass.declaredFields,
        RemoteConfigFeaturesConstants.MyHealth.Vaccines.javaClass.declaredFields,
        RemoteConfigFeaturesConstants.MyHealth.InsuranceApproval.javaClass.declaredFields,
        RemoteConfigFeaturesConstants.MyHealth.SoftLaunch.javaClass.declaredFields,
        RemoteConfigFeaturesConstants.As3afny.javaClass.declaredFields,
        RemoteConfigFeaturesConstants.DailyActivity.javaClass.declaredFields,
        RemoteConfigFeaturesConstants.MyFamily.javaClass.declaredFields,
        RemoteConfigFeaturesConstants.MyFamily.AddNewMember.javaClass.declaredFields,
        RemoteConfigFeaturesConstants.MyFamily.MembersRequest.javaClass.declaredFields,
        RemoteConfigFeaturesConstants.MyFamily.MemberHealthProfile.javaClass.declaredFields,
        RemoteConfigFeaturesConstants.WellBeing.javaClass.declaredFields,
        RemoteConfigFeaturesConstants.Landing.javaClass.declaredFields,
    )
    val keysList: MutableList<String> = mutableListOf()
    classes.forEach {
        val properties = it
        properties.forEach {
            val value = it[0]
            if (value is String
                && !keysList.contains(value) // NO_DUPLICATION
                && value != "INSTANCE"       // NOT THE CLASS INSTANCE
                && !value.startsWith("com.lean.sehhaty.remoteconfig")       // NOT THE INSTANCE VALUE
            ) {
                keysList.add(value)
            }
        }
    }
    print("keys size= ${keysList.size}")
    println()
    
    val configs: MutableList<FeatureConfigurations> = mutableListOf()
    keysList.forEach {
        configs.add(
            FeatureConfigurations(
                featureKey = it,
                isEnabled = true,
                data = if (it == MY_HEALTH_NEPHIS_PARTNER_COUNT) 40.0 else null
            )
        )
    }
    print(
        Gson()
            .newBuilder()
            .setPrettyPrinting()
            .create()
            .toJson(configs)
    )
}