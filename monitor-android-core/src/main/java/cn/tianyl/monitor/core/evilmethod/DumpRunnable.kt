package cn.tianyl.monitor.core.evilmethod

import android.os.Looper
import android.util.Log
import cn.tianyl.monitor.core.Monitor


/**
 * @author tianyl
 * @since 2022/3/8
 */
class DumpRunnable : Runnable {

    override fun run() {
        val msg = dump()
        if (msg.isNotEmpty()) {
            Log.d(Monitor.TAG, msg)
        }
    }

    private fun dump(): String {
        val stackTrace = Looper.getMainLooper().thread.stackTrace
        val sb = StringBuilder()
        sb.append("--------------------->\n")
        sb.append("---------------------> Monitor.TAG START <---------------------\n")
        for (s in stackTrace) {
            if (!EvilMethodCanary.filter(s.toString())) {
                sb.append(s.toString().trim() + "\n");
            } else {
                return ""
            }
        }
        sb.append("---------------------> Monitor.TAG END <---------------------")
        return sb.toString()
    }

}