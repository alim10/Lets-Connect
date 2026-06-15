package org.alimapps.letsconnect.data
import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.alimapps.letsconnect.BuildConfig
import org.alimapps.letsconnect.core.common.encyrption.provideEncryptedRoomDatabase
import org.alimapps.letsconnect.core.database.dao.ChatDao
import org.alimapps.letsconnect.core.database.entity.ChatEntity
import org.alimapps.letsconnect.core.database.entity.MessageEntity
import org.alimapps.letsconnect.notification.local.dao.LatestNotificationDao
import org.alimapps.letsconnect.notification.local.dao.NotificationsDao
import org.alimapps.letsconnect.notification.local.dao.PrivateNotificationsDao
import org.alimapps.letsconnect.notification.local.model.CachedLatestNotifications
import org.alimapps.letsconnect.notification.local.model.CachedNotifications
import org.alimapps.letsconnect.notification.local.model.CachedPrivateNotification
import org.alimapps.letsconnect.notification.local.model.NotificationMainExtraConverter

@Database(
    entities = [
//        UserEntity::class,
        CachedNotifications::class,
        CachedPrivateNotification::class,
        CachedLatestNotifications::class,
        ChatEntity::class,
        MessageEntity::class
    ],
    exportSchema = false,
    version = BuildConfig.VERSION_CODE
)

@TypeConverters(
    NotificationMainExtraConverter::class,
)
abstract class AppDatabase : RoomDatabase() {

//    abstract fun userDao(): UserDao
//    abstract fun chattingDao(): ChatDao
    abstract fun notificationsDao(): NotificationsDao
    abstract fun privateNotificationsDao(): PrivateNotificationsDao
    abstract fun latestNotificationDao(): LatestNotificationDao
    abstract fun chatDao(): ChatDao
    //TODO rest of dao classes here

    companion object {
        private val DB_NAME = "LetsConnect.db"

        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var appDatabase: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = appDatabase
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = provideEncryptedRoomDatabase(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                )
                appDatabase = instance
                return instance
            }
        }
    }
}
