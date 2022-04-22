package cn.tianyl.monitor

import android.os.Handler
import android.os.Message
import android.util.Log


/**
 * @author tianyl
 * @since 2022/3/10
 */
class ActivityThreadHandleCallBack : Handler.Callback {

    private val TAG = "ATHandleCallBack"

    private val SERVICE_ARGS = 115
    private val STOP_SERVICE = 116
    private val SLEEPING = 137
    private val STOP_ACTIVITY_SHOW = 103
    private val STOP_ACTIVITY_HIDE = 104
    private val ENTER_ANIMATION_COMPLETE = 149


    override fun handleMessage(msg: Message): Boolean {
        Log.e(TAG, "handleMessage")
        when (msg.what) {
            SERVICE_ARGS, STOP_SERVICE, SLEEPING, STOP_ACTIVITY_SHOW,
            STOP_ACTIVITY_HIDE, ENTER_ANIMATION_COMPLETE -> SPAnrFixHelper.fixBlock()
        }
        return false
    }

}