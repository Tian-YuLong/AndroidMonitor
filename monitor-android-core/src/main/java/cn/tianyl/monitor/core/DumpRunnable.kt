package cn.tianyl.monitor.core

import android.os.Looper
import android.util.Log


/**
 * @author tianyl
 * @since 2022/3/8
 */
class DumpRunnable : Runnable {

    override fun run() {
        Log.d(Monitor.TAG, dump())
    }

    private fun dump(): String {
        val stackTrace = Looper.getMainLooper().thread.stackTrace
        val sb = StringBuilder()
        sb.append("---------------------> Monitor.TAG START <---------------------\n")
        for (s in stackTrace) {
            sb.append(s.toString().trim() + "\n");
        }
        sb.append("---------------------> Monitor.TAG END <---------------------")
        return sb.toString()
    }

}