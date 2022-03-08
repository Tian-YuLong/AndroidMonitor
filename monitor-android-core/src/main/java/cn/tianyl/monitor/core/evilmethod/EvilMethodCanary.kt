package cn.tianyl.monitor.core.evilmethod

import cn.tianyl.monitor.core.DumpRunnable
import cn.tianyl.monitor.core.Monitor


/**
 * @author tianyl
 * @since 2022/3/7
 */
class EvilMethodCanary() : LooperPrinter.Observer {

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
}