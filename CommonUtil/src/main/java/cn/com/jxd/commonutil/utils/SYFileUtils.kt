package cn.com.jxd.commonutil.utils

import android.content.Context
import android.text.TextUtils
import java.io.*
import java.nio.channels.FileChannel
import java.util.*

/**
 * @author xiangdong.jia
 * @description:
 * @date :2021/5/31 下午4:16
 */
class SYFileUtils {

    private fun SYFileUtils() {
        throw UnsupportedOperationException("u can't instantiate me...")
    }

    /**
     * 获取文件
     *
     * @param filePath
     */
    fun getFileByPath(filePath: String?): File? {
        return if (TextUtils.isEmpty(filePath)) null else File(filePath)
    }

    /**
     * 判断文件是否存在
     *
     * @param filePath
     */
    fun isFileExists(filePath: String?): Boolean {
        return isFileExists(getFileByPath(filePath))
    }

    /**
     * 判断文件是否存在
     *
     * @param file
     */
    fun isFileExists(file: File?): Boolean {
        return file != null && file.exists()
    }

    /**
     * 重命名文件
     *
     * @param filePath
     * @param newName
     */
    fun rename(filePath: String?, newName: String): Boolean {
        return rename(getFileByPath(filePath), newName)
    }

    /**
     * 重命名文件
     *
     * @param file
     * @param newName
     */
    fun rename(file: File?, newName: String): Boolean {
        // file is null then return false
        if (file == null) return false
        // file doesn't exist then return false
        if (!file.exists()) return false
        // the new name is space then return false
        if (TextUtils.isEmpty(newName)) return false
        // the new name equals old name then return true
        if (newName == file.name) return true
        val newFile = File(file.parent + File.separator + newName)
        // the new name of file exists then return false
        return (!newFile.exists()
                && file.renameTo(newFile))
    }

    /**
     * 判断是否是目录
     *
     * @param dirPath
     */
    fun isDir(dirPath: String?): Boolean {
        return isDir(getFileByPath(dirPath))
    }

    /**
     * 判断是否是目录
     *
     * @param file
     */
    fun isDir(file: File?): Boolean {
        return file != null && file.exists() && file.isDirectory
    }

    /**
     * 判断是否是文件
     *
     * @param filePath
     */
    fun isFile(filePath: String?): Boolean {
        return isFile(getFileByPath(filePath))
    }

    /**
     * 判断是否是文件
     *
     * @param file
     */
    fun isFile(file: File?): Boolean {
        return file != null && file.exists() && file.isFile
    }

    /**
     * 判断目录是否存在，不存在则判断是否创建成功
     *
     * @param dirPath
     */
    fun createOrExistsDir(dirPath: String?): Boolean {
        return createOrExistsDir(getFileByPath(dirPath))
    }

    /**
     * 判断目录是否存在，不存在则判断是否创建成功
     *
     * @param file
     */
    fun createOrExistsDir(file: File?): Boolean {
        return file != null && if (file.exists()) file.isDirectory else file.mkdirs()
    }

    /**
     * 判断文件是否存在，不存在则判断是否创建成功
     *
     * @param filePath
     */
    fun createOrExistsFile(filePath: String?): Boolean {
        return createOrExistsFile(getFileByPath(filePath))
    }

    /**
     * 判断文件是否存在，不存在则判断是否创建成功
     *
     * @param file
     */
    fun createOrExistsFile(file: File?): Boolean {
        if (file == null) return false
        if (file.exists()) return file.isFile
        return if (!createOrExistsDir(file.parentFile)) false else try {
            file.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }

    /**
     * 判断文件是否存在，存在则在创建之前删除
     *
     * @param filePath
     */
    fun createFileByDeleteOldFile(filePath: String?): Boolean {
        return createFileByDeleteOldFile(getFileByPath(filePath))
    }

    /**
     * 判断文件是否存在，存在则在创建之前删除
     *
     * @param file
     */
    fun createFileByDeleteOldFile(file: File?): Boolean {
        if (file == null) return false
        // file exists and unsuccessfully delete then return false
        if (file.exists() && !file.delete()) return false
        return if (!createOrExistsDir(file.parentFile)) false else try {
            file.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }

    /**
     * 删除文件或目录
     *
     * @param filePath
     */
    fun delete(filePath: String?): Boolean {
        return delete(getFileByPath(filePath))
    }

    /**
     * 删除文件
     *
     * @param file
     */
    fun delete(file: File?): Boolean {
        if (file == null) return false
        return if (file.isDirectory) {
            deleteDir(file)
        } else deleteFile(file)
    }

    /**
     * 删除目录
     *
     * @param dirPath
     */
    fun deleteDir(dirPath: String?): Boolean {
        return deleteDir(getFileByPath(dirPath))
    }

    /**
     * 删除目录
     *
     * @param dir
     */
    fun deleteDir(dir: File?): Boolean {
        if (dir == null) return false
        // dir doesn't exist then return true
        if (!dir.exists()) return true
        // dir isn't a directory then return false
        if (!dir.isDirectory) return false
        val files = dir.listFiles()
        if (files != null && files.size != 0) {
            for (file in files) {
                if (file.isFile) {
                    if (!file.delete()) return false
                } else if (file.isDirectory) {
                    if (!deleteDir(file)) return false
                }
            }
        }
        return dir.delete()
    }

    /**
     * 删除文件
     *
     * @param srcFilePath
     */
    fun deleteFile(srcFilePath: String?): Boolean {
        return deleteFile(getFileByPath(srcFilePath))
    }

    /**
     * 删除文件
     *
     * @param file
     */
    fun deleteFile(file: File?): Boolean {
        return file != null && (!file.exists() || file.isFile && file.delete())
    }

    /**
     * 删除目录下所有文件
     *
     * @param dirPath
     */
    fun deleteAllInDir(dirPath: String?): Boolean {
        return deleteAllInDir(getFileByPath(dirPath))
    }

    /**
     * 删除目录下所有文件
     *
     * @param dir
     */
    fun deleteAllInDir(dir: File?): Boolean {
        return deleteFilesInDirWithFilter(dir) { true }
    }

    /**
     * 删除目录下所有文件
     *
     * @param dirPath
     */
    fun deleteFilesInDir(dirPath: String?): Boolean {
        return deleteFilesInDir(getFileByPath(dirPath))
    }

    /**
     * 删除目录下所有文件
     *
     * @param dir
     */
    fun deleteFilesInDir(dir: File?): Boolean {
        return deleteFilesInDirWithFilter(dir) { pathname -> pathname.isFile }
    }

    /**
     * 删除目录下所有过滤的文件
     *
     * @param dirPath
     * @param filter
     */
    fun deleteFilesInDirWithFilter(dirPath: String?, filter: FileFilter): Boolean {
        return deleteFilesInDirWithFilter(getFileByPath(dirPath), filter)
    }

    /**
     * 删除目录下所有过滤的文件
     *
     * @param dir
     * @param filter
     */
    fun deleteFilesInDirWithFilter(dir: File?, filter: FileFilter): Boolean {
        if (dir == null) return false
        // dir doesn't exist then return true
        if (!dir.exists()) return true
        // dir isn't a directory then return false
        if (!dir.isDirectory) return false
        val files = dir.listFiles()
        if (files != null && files.size != 0) {
            for (file in files) {
                if (filter.accept(file)) {
                    if (file.isFile) {
                        if (!file.delete()) return false
                    } else if (file.isDirectory) {
                        if (!deleteDir(file)) return false
                    }
                }
            }
        }
        return true
    }

    /**
     * 获取目录下所有文件
     *
     * @param dirPath
     */
    fun listFilesInDir(dirPath: String?): List<File?>? {
        return listFilesInDir(dirPath, false)
    }

    /**
     * 获取目录下所有文件
     *
     * @param dir
     */
    fun listFilesInDir(dir: File?): List<File?>? {
        return listFilesInDir(dir, false)
    }

    /**
     * 获取目录下所有文件
     *
     * @param dirPath
     * @param isRecursive
     */
    fun listFilesInDir(dirPath: String?, isRecursive: Boolean): List<File?>? {
        return listFilesInDir(getFileByPath(dirPath), isRecursive)
    }

    /**
     * 获取目录下所有文件
     *
     * @param dir
     * @param isRecursive
     */
    fun listFilesInDir(dir: File?, isRecursive: Boolean): List<File?>? {
        return listFilesInDirWithFilter(dir, { true }, isRecursive)
    }

    /**
     * 获取目录下所有过滤的文件
     *
     * @param dirPath
     * @param filter
     */
    fun listFilesInDirWithFilter(dirPath: String?, filter: FileFilter): List<File?>? {
        return listFilesInDirWithFilter(getFileByPath(dirPath), filter, false)
    }

    /**
     * 获取目录下所有过滤的文件
     *
     * @param dir
     * @param filter
     */
    fun listFilesInDirWithFilter(dir: File?, filter: FileFilter): List<File?>? {
        return listFilesInDirWithFilter(dir, filter, false)
    }

    /**
     * 获取目录下所有过滤的文件
     *
     * @param dirPath
     * @param filter
     * @param isRecursive
     */
    fun listFilesInDirWithFilter(
        dirPath: String?,
        filter: FileFilter,
        isRecursive: Boolean
    ): List<File?>? {
        return listFilesInDirWithFilter(getFileByPath(dirPath), filter, isRecursive)
    }

    /**
     * 获取目录下所有过滤的文件
     *
     * @param dir
     * @param filter
     * @param isRecursive
     */
    fun listFilesInDirWithFilter(
        dir: File?,
        filter: FileFilter,
        isRecursive: Boolean
    ): List<File?>? {
        if (!isDir(dir)) return null
        val list: MutableList<File?> = ArrayList()
        val files = dir!!.listFiles()
        if (files != null && files.size != 0) {
            for (file in files) {
                if (filter.accept(file)) {
                    list.add(file)
                }
                if (isRecursive && file.isDirectory) {
                    list.addAll(listFilesInDirWithFilter(file, filter, true)!!)
                }
            }
        }
        return list
    }

    /**
     * 获取文件最后修改的时间
     *
     * @param filePath
     */
    fun getFileLastModified(filePath: String?): Long {
        return getFileLastModified(getFileByPath(filePath))
    }

    /**
     * 获取文件最后修改的时间
     *
     * @param file
     */
    fun getFileLastModified(file: File?): Long {
        return file?.lastModified() ?: -1
    }

    fun copyFile(fromPath: String?, toPath: String?) {
        val f = File(fromPath)
        val o = File(toPath)
        if (f.exists()) {
            var outF: FileChannel? = null
            var intF: FileChannel? = null
            try {
                outF = FileOutputStream(o).channel
                intF = FileInputStream(f).channel
                intF.transferTo(0, f.length(), outF)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                try {
                    outF?.close()
                    intF?.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun copyAssets(context: Context, assets: String?, targetPath: String?): Boolean {
        val file = File(targetPath)
        if (!file.exists()) {
            /*
             * 模型文件不存在
             * The model file does not exist
             * */
            try {
                if (!file.createNewFile()) {
                    return false
                }
                val `in` = context.assets.open(assets!!)
                val out: OutputStream = FileOutputStream(file)
                val buffer = ByteArray(4096)
                var n: Int
                while (`in`.read(buffer).also { n = it } > 0) {
                    out.write(buffer, 0, n)
                }
                `in`.close()
                out.close()
            } catch (e: IOException) {
                file.delete()
                return false
            }
        }
        return true
    }


}