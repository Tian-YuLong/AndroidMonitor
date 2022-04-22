package cn.tianyl.monitor

import android.util.Log


/**
 * @author tianyl
 * @since 2022/3/10
 */
object HookHelper {

    private val TAG = "HookHelper"

    fun hackActivityThreadH() {
        try {
            val activityThreadClz = Class.forName("android.app.ActivityThread")
            val mHField = activityThreadClz.getDeclaredField("mH")

            // 3.4.1 获取ActivityThread实例

            val atClz = Class.forName("android.app.ActivityThread")
            val scatField = atClz.getDeclaredField("sCurrentActivityThread")
            scatField.isAccessible = true
            val sCurrentActivityThread = scatField[null]

            // 3.4.2 获取ActivityThread中的mH
            val mhField = atClz.getDeclaredField("mH")
            mhField.isAccessible = true
            val mHandler = mhField[sCurrentActivityThread]

            // 3.4 设置当前对象（也就是ActivityThread）的mH的成员变量
            val handlerClass = Class.forName("android.os.Handler")
            val mCallbackField = handlerClass.getDeclaredField("mCallback")
            mCallbackField.isAccessible = true
            mCallbackField[mHandler] = ActivityThreadHandleCallBack()
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
    }
}