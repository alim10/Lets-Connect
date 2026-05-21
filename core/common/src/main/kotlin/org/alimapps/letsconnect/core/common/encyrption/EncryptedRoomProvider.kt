package org.alimapps.letsconnect.core.common.encyrption

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import net.zetetic.database.sqlcipher.SupportOpenHelperFactory
import org.alimapps.letsconnect.core.common.BuildConfig
import org.alimapps.letsconnect.core.common.nativeLib.Secrets
import java.io.File
import java.io.FileInputStream

fun <T : RoomDatabase> provideEncryptedRoomDatabase(
    context: Context,
    dbClass: Class<T>,
    dbName: String
): T {
    val passphraseChars = Secrets.databaseEncryptionKey()
    val passphraseBytes = passphraseChars.toByteArray(Charsets.UTF_8)
    val factory = SupportOpenHelperFactory(passphraseBytes)

    val databaseFile = context.getDatabasePath(dbName)

    return if (!BuildConfig.DEBUG) {
        if (!databaseFile.exists() || isDatabaseEncrypted(databaseFile)) {
            Room.databaseBuilder(context, dbClass, dbName)
                .openHelperFactory(factory)
                .fallbackToDestructiveMigration()
                .build()
        } else {
            context.deleteDatabase(dbName)
            Room.databaseBuilder(context, dbClass, dbName)
                .openHelperFactory(factory)
                .fallbackToDestructiveMigration()
                .build()
        }
    } else {
        Room.databaseBuilder(context, dbClass, dbName)
            .fallbackToDestructiveMigration()
            .build()
    }
}

/**
 * Plaintext SQLite files start with "SQLite format 3\0".
 * SQLCipher-encrypted files look like random bytes, so header won't match.
 */
fun isDatabaseEncrypted(file: File): Boolean {
    val header = ByteArray(16)
    FileInputStream(file).use { it.read(header) }
    val headerString = String(header)
    return headerString != "SQLite format 3\u0000"
}