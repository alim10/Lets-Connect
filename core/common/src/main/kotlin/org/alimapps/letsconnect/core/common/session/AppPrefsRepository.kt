package org.alimapps.letsconnect.core.common.session

/**
 * Created by Ahmed Ibrahim on 22,March,2021
 *
 * Contract to make it easy to add FakeImpl to be used into Tests.
 */
interface AppPrefsRepository {
    var healthCareCenterId: Int?
    var locale: String

    //userProfile
    var isLoggedIn: Boolean
    var nationalID: String?
    var phoneNumber: String?
    var userDOB: String?
    var healthId: String?
    var userName: String?
    var firstNameAr: String?
    var firstNameEn: String?
    var secondNameAr: String?
    var secondNameEn: String?
    var lastNameAr: String?
    var lastNameEn: String?
    var userFullName: String?
    var languageCode: String
    var languageName: String
    var isVisitor: Boolean
    var isUser: Boolean
    var isUnderAge: Boolean
    var isVerified: Boolean
    var gender: Int
    var cityId: Long?
    var districtId: Long?
    var cityName: String?
    var districtName: String?
    var isConfirmedNationalAddress: Boolean
    var nationalityCode: String?
    var nationalityNameEn: String?
    var nationalityNameAr: String?
    var accessToken: String?
    var tokenExpiredDate: Long?
    var refreshToken: String?
    var isFcmTokenRegistered: Boolean
    var allowProfileUpdate: Boolean?    //  for update user data
    var profileUpdatedAt: String?

    //temp user session
    var tempIdentifier: String?
    var tempIdentifierForTwoOtp: String?
    var tempNationalId: String?
    var tempPassword: String?
    var tempUserKey: String?
    var userHash: String?
    var tempPassportNumber: String?
    var tempNationalityId: Int?
    var tempPhoneNumber: String?
    var iAMRedirectionUrl: String?
    var iAmSessionId: String?


    // determine if the user click skip in the "JoinEmshFragment" to not display it again
    var shouldShowEatizazPrivacy: Boolean
    var showHealthSummaryToolTip: Boolean
    var isUserSkippedEmshIntro: Boolean
    var isStepPermissionGranted: Boolean
    var forceUpdate: Boolean
    var normalUpdate: Boolean
    var bmiTimestamp: String? //TODO temporary until API returns bmi data
    var needToSubmitDailySurvey: Boolean
    var isActiveQuarantine: Boolean
    var showInAppHttpInterceptor: Boolean
    var virusStatusResponse: String
    var virusVaccineResponse: String
    var virusProfile: String
    var oldVirusTestCount: Int
    var virusVaccineStatesResponse: String
    var isOver40thWeeksPopUpIsShown: Boolean
    var dependentNationalID: String?
    var dependentDOB: String?

    /**
     * For super user only. Returns boolean of 3 cases:
     * [null]: By default, when user did not view summary feedback dialog yet.
     * [false]: Means user should NOT dialog "again", he already viewed once.
     * [true]: Means user SHOULD view feedback now.
     */
    var shouldViewHealthSummaryFeedbackDialog: Boolean?

    var dashboardBanner: String?
    var userLocator: String?
    var userClusterName : String?
    var userClusterId : String?
    var showNotificationCenterDialog: Boolean

    var healthSummeryToolTipVisitedId: String?
    var onboardingVisitedVersion: String?

    var themePreference: String?

    var appAppearancePreference: String?

    fun getCacheMeterTimestamp(key: String, default: Long): Long
    fun setCacheMeterTimestamp(key: String, value: Long)
    fun removeCacheMeterTimestamp(key: String)
    fun clear()
}
