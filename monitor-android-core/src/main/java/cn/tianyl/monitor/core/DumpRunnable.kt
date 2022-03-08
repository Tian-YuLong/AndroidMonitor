package cn.tianyl.monitor.core

import android.os.Looper
import android.util.Log


/**
 * @author tianyl
 * @since 2022/3/8
 */
class DumpRunnable : Runnable {

    override fun run() {
        val msg = """
            ---------------------> Monitor.TAG START <---------------------
            ${dump()}
            ---------------------> Monitor.TAG END <---------------------
        """.trimIndent()
        Log.d(Monitor.TAG, msg)
    }

    private fun dump(): String {
        val stackTrace = Looper.getMainLooper().thread.stackTrace
        val sb = StringBuilder()
        for (s in stackTrace) {
            sb.append(s.toString().trim() + "\n");
        }
        return sb.toString()
    }

}