package cn.tianyl.monitor.core.evilmethod

import android.app.Activity
import android.util.Log
import android.view.ViewTreeObserver
import cn.tianyl.monitor.core.Monitor
import cn.tianyl.monitor.core.lifecycle.EmptyActivityLifecycleCallbacks


/**
 * @author tianyl
 * @since 2022/3/7
 */
object EvilMethodCanary : LooperPrinter.Observer, EmptyActivityLifecycleCallbacks {


    private val filterList = arrayOf(
        "android.app.ActivityThread.performLaunchActivity",
        "android.app.Activity.performCreate",
        "android.view.View.measure",
    )

    /**
     * 如果是一些常见的耗时函数，不在此处输出，例如onCreate，measure之类的
     *
     */
    fun filter(msg: String): Boolean {
        for (filter in filterList) {
            if (msg.contains(filter)) {
                return true
            }
        }
        return false
    }

    private val dumpRunnable: DumpRunnable = DumpRunnable()

    fun start() {
        // 观察主线程
        Monitor.mainLooper.setMessageLogging(LooperPrinter().apply {
            observer = this@EvilMethodCanary
        })
    }

    override fun messageDispatchStarting() {
//        Log.d("--------------------->", "messageDispatchStarting")
        Monitor.messageDispatchStarting(dumpRunnable)
    }

    override fun messageDispatched() {
//        Log.d("--------------------->", "messageDispatched")
        Monitor.messageDispatched(dumpRunnable)
    }

//    override fun onActivityResumed(activity: Activity) {
//        super.onActivityResumed(activity)
//        activity.window.decorView.viewTreeObserver.addOnWindowFocusChangeListener(ViewTreeObserver.OnWindowFocusChangeListener() {
//            fun onWindowFocusChanged(b:Boolean) {
//                if (b) {
////                    <!--界面可见后，开始侦测FPS-->
//                    resumeTrack();
//                    activity.getWindow().getDecorView().getViewTreeObserver().removeOnWindowFocusChangeListener(this);
//                }
//
//    }
//
//    override fun onActivityPaused(activity: Activity) {
//        super.onActivityPaused(activity)
//    }
}