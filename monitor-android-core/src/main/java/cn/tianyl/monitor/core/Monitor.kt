package cn.tianyl.monitor.core

import android.app.Application
import android.os.Handler
import android.os.Looper
import cn.tianyl.monitor.core.evilmethod.EvilMethodCanary


/**
 * @author tianyl
 * @since 2022/3/7
 */
public object Monitor {

    val vsyncMs = 16L
    val TAG = "Monitor-Log"

    private lateinit var app: Application
    lateinit var mainLooper: Looper

    private val handler by lazy {
        Handler(monitorThread.looper)
    }

    val monitorThread = MonitorThread()

    private var monitorRunning = false

    val evilThresholdMs = 1000L

    fun init(app: Application) {
        this.app = app
        this.mainLooper = app.mainLooper

        monitorThread.start()
        startMonitor(app)
    }

    private fun startMonitor(app: Application) {
        if (!monitorRunning) {
            monitorRunning = true
        }
        EvilMethodCanary().start()
    }

    private fun stopMonitor() {
        Looper.getMainLooper().setMessageLogging(null)
    }

    fun messageDispatchStarting(runnable: Runnable) {
        handler.postDelayed(runnable, evilThresholdMs)
    }

    fun messageDispatched(runnable: Runnable) {
        handler.removeCallbacks(runnable)
    }
}

class MonitorThread : Thread() {

    init {
        name = "monitor-thread"
    }

    lateinit var looper: Looper

    override fun run() {
        super.run()
        Looper.prepare()
        looper = Looper.myLooper()!!
        Looper.loop()
    }
}