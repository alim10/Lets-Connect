//package org.alimapps.letsconnect.data
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.RoomDatabase
//import androidx.room.TypeConverters
//import org.alimapps.letsconnect.core.data.encyrption.provideEncryptedRoomDatabase
//
//@Database(
//    entities = [
//        UserEntity::class,
//        CityEntity::class,
//        DistrictEntity::class,
//        AnnouncementEntity::class,
//        TetammanContactsEntity::class,
//        QuestionEntity::class,
//        TetammanDashboardEntity::class,
//        CountryEntity::class,
//        UiChatMessageEntity::class,
//        CachedDashboardSearch::class,
//        CachedNotifications::class,
//        CachedPrivateNotification::class,
//        CachedLatestNotifications::class,
//        DashboardBanner::class,
//        CachedHealthSummaryComponents::class,
//        CachedCustomLink::class,
//        CachedDashboardShortcutOrder::class,
//    ],
//    exportSchema = false,
//    version = BuildConfig.VERSION_CODE
//)
//
//@TypeConverters(
//    SummaryComponentsConverter::class,
//    UserConsentConverter::class,
//    NationalityConverter::class,
//    DurationConverter::class,
//    GenderConverter::class,
//    SurveyStatusConverter::class,
//    CityCenterConverter::class,
//    RegionConverter::class,
//    MaritalStatusConverter::class,
//    CampaignStatusConverter::class,
//    AppointmentStatusConverter::class,
//    AppointmentSourceConverter::class,
//    BookingTypeConverter::class,
//    ClinicAppointmentConverter::class,
//    MawidFacilityConverter::class,
//    MawidFacilityDetailsConverter::class,
//    MawidFacilityServiceDetailsConverter::class,
//    ClinicAppointmentsResponseConverter::class,
//    ClinicAppointmentsListConverter::class,
//    MawidFacilityTypeVOEConverter::class,
//    MawidFacilityDirectorateVOEConverter::class,
//    MawidFacilityServiceGroupConverter::class,
//    LocalDateTimeConverter::class,
//    MedicalProfileConverter::class,
//    CachedDependentConverter::class,
//    NotificationMainExtraConverter::class,
//    BundleConverter::class,
//    ExclusiveFeatureConverter::class,
//    UserRoleConverter::class,
//    ExternalAccountsConverter::class,
//    RelationManagerConverter::class,
//)
//abstract class AppDatabase : RoomDatabase() {
//
//    abstract fun userDao(): UserDao
//    abstract fun cityDao(): CityDao
//    abstract fun districtDao(): DistrictDao
//    abstract fun announcementDao(): AnnouncementDao
//    abstract fun tetammanSurveyDao(): TetammanSurveyDao
//    abstract fun tetammanContactsDao(): TetammanContactsDao
//    abstract fun tetammanDashboardDao(): TetammanDashboardDao
//    abstract fun tetammanCountryListDao(): TetammanCountryListDao
//    abstract fun chattingDao(): ChatDao
//    abstract fun dashboardShortcutOrderDao(): DashboardShortcutOrderDao
//
//    abstract fun dashboardDao(): DashboardDao
//    abstract fun notificationsDao(): NotificationsDao
//    abstract fun privateNotificationsDao(): PrivateNotificationsDao
//    abstract fun latestNotificationDao(): LatestNotificationDao
//    abstract fun healthSummaryComponentsDao(): HealthSummaryComponentDao
//    abstract fun customDeepLinkDao(): CustomDeepLinkDao
//    //TODO rest of dao classes here
//
//    companion object {
//        private val DB_NAME = "LetsConnect.db"
//
//        // Singleton prevents multiple instances of database opening at the
//        // same time.
//        @Volatile
//        private var appDatabase: AppDatabase? = null
//
//        fun getDatabase(context: Context): AppDatabase {
//            val tempInstance = appDatabase
//            if (tempInstance != null) {
//                return tempInstance
//            }
//            synchronized(this) {
//                val instance = provideEncryptedRoomDatabase(
//                    context.applicationContext,
//                    AppDatabase::class.java,
//                    DB_NAME
//                )
//                appDatabase = instance
//                return instance
//            }
//        }
//    }
//}
