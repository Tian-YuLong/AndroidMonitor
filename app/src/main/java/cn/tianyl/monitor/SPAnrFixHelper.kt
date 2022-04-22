package cn.tianyl.monitor

import android.util.Log
import java.util.concurrent.ConcurrentLinkedQueue


/**
 * @author tianyl
 * @since 2022/3/10
 */
object SPAnrFixHelper {

    val TAG = "SPAnrFixHelper"

    val CLASS_QUEUED_WORK = "android.app.QueuedWork"
    val PENDING_WORK_FINISHERS = "sPendingWorkFinishers"


    var sPendingWorkFinishers: ConcurrentLinkedQueue<Runnable>? = null

    fun fixBlock() {
        if (sPendingWorkFinishers == null) {
            getPendingWorkFinishers()
        }
        sPendingWorkFinishers?.clear()
    }

    fun getPendingWorkFinishers() {
        try {
            val clz = Class.forName(CLASS_QUEUED_WORK)
            val field = clz.getDeclaredField(PENDING_WORK_FINISHERS)
            field.isAccessible = true
            sPendingWorkFinishers = field.get(null) as ConcurrentLinkedQueue<Runnable>
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
    }
}