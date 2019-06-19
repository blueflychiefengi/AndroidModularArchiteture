package com.effective.android.base.util

import android.content.Context
import android.os.Environment
import android.os.Environment.*
import android.text.TextUtils
import java.io.File

/**
 * 目录管理，一般每个app都需要一些目录用于存放特定内容，比如缓存，data，下载内容，图片等
 * 需要新增哪些目录直接在这里添加就可以了。
 * 应用管理中存在两个按钮
 * CLEAR DATA 会清除 data/data 数据
 * CLEAR CACHE 会清除 data/data/projectName/cache 和 mnt/sdcard/Android/projectName/cache
 * Email yummyl.lau@gmail.com
 * Created by yummylau on 2019/06/16.
 */
object StorageUtils {

    enum class StorageType(var dirName: String = "temp") {
        // 放在私有内部，比如uuid文件等等
        IMPORTANT_FILE("important"),
        // 直接放在外部方便导入
        CRASH("crash"),
        // 放在私有内部
        CHANNEL("channel"),
    }

    private fun storage(context: Context, type: StorageType): String {
        var root = when (type) {
            StorageType.CRASH -> {

                getExternalFiles(context)
            }
            StorageType.CHANNEL -> {

                getCache(context)
            }
            else -> {
                getFiles(context)
            }
        }
        val dir = File(root + "/" + type.dirName)
        if (!dir.exists()) {
            dir.mkdirs()
        }
        return dir.absolutePath + "/"
    }

    fun getCrashDir(context: Context) = storage(context, StorageType.CRASH)

    fun getChannelDir(context: Context) = storage(context, StorageType.CHANNEL)

    fun getImportantDir(context: Context) = storage(context, StorageType.IMPORTANT_FILE)


    private fun getCache(context: Context): String {
        return if (MEDIA_MOUNTED == getExternalStorageState() || !isExternalStorageRemovable()) {
            getExternalCache(context)!!
        } else {
            getInternalCache(context)
        }
    }

    private fun getFiles(context: Context): String {
        return if (MEDIA_MOUNTED == getExternalStorageState() || !isExternalStorageRemovable()) {
            getExternalFiles(context)!!
        } else {
            getInternalFiles(context)
        }
    }

    /**
     * 获取 app 内部私有cache
     */
    fun getInternalCache(context: Context): String = context.cacheDir.absolutePath

    /**
     * 获取 app 外部私有cache
     */
    fun getExternalCache(context: Context): String? = context.externalCacheDir?.absolutePath

    /**
     * 获取 app 内部私有文件
     */
    fun getInternalFiles(context: Context): String = context.filesDir.absolutePath

    /**
     * 获取 app 外部私有文件
     */
    fun getExternalFiles(context: Context): String? = context.getExternalFilesDir(null)?.absolutePath

    /**
     * 外部存储
     * /storage/sdcard0/Alarms
     */
    fun getExternalAlarms(): String = Environment.getExternalStoragePublicDirectory(DIRECTORY_ALARMS).absolutePath

    /**
     * 外部存储
     * /storage/sdcard0/DCIM
     */
    fun getExternalDcim(): String = Environment.getExternalStoragePublicDirectory(DIRECTORY_DCIM).absolutePath

    /**
     * 外部存储
     * /storage/sdcard0/Download
     */
    fun getExternalDownload(): String = Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS).absolutePath

    /**
     * 外部存储
     * /storage/sdcard0/Movies
     */
    fun getExternalMovies(): String = Environment.getExternalStoragePublicDirectory(DIRECTORY_MOVIES).absolutePath

    /**
     * 外部存储
     * /storage/sdcard0/Music
     */
    fun getExternalMusic(): String = Environment.getExternalStoragePublicDirectory(DIRECTORY_MUSIC).absolutePath

    /**
     * 外部存储
     * /storage/sdcard0/Notifications
     */
    fun getExternalNotifications(): String = Environment.getExternalStoragePublicDirectory(DIRECTORY_NOTIFICATIONS).absolutePath

    /**
     * 外部存储
     * /storage/sdcard0/Pictures
     */
    fun getExternalPictures(): String = Environment.getExternalStoragePublicDirectory(DIRECTORY_PICTURES).absolutePath

    /**
     * 外部存储
     * /storage/sdcard0/Podcasts
     */
    fun getExternalPodcasts(): String = Environment.getExternalStoragePublicDirectory(DIRECTORY_PODCASTS).absolutePath

    /**
     * 外部存储
     * /storage/sdcard0/Ringtones
     */
    fun getExternalRingtones(): String = Environment.getExternalStoragePublicDirectory(DIRECTORY_RINGTONES).absolutePath

}