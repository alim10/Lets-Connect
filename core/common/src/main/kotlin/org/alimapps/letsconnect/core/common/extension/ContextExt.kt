package org.alimapps.letsconnect.core.common.extension

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.core.content.FileProvider
import okhttp3.ResponseBody
import org.alimapps.letsconnect.core.common.general.ErrorObject
import org.alimapps.letsconnect.core.common.general.Resource
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

/**Helper function to convert [ResponseBody] to file and return [Resource] of [Uri]*/
fun Context.saveFile(body: ResponseBody, nameId: String?): Resource<Uri> {
    val name = nameId ?: System.currentTimeMillis().toString()
    val fileName = "pdf-$name.pdf"
    val filePath = "${filesDir.absolutePath}/$fileName"
    var input: InputStream? = null
    return try {
        input = body.byteStream()
        val fos = FileOutputStream(filePath)
        fos.use { output ->
            val buffer = ByteArray(4 * 1024) // or other buffer size
            var read: Int
            while (input.read(buffer).also { read = it } != -1) {
                output.write(buffer, 0, read)
            }
            output.flush()
        }
        val authority = "${packageName}.provider"
        val uri = FileProvider.getUriForFile(this, authority, File(filePath))
        Resource.success(uri)
    } catch (e: Exception) {
        Log.d("SaveFile", "Failed to create pdf file because ${e.message}")
        Resource.error(ErrorObject.default(), null)
    } finally {
        input?.close()
    }
    
}