package cn.tianyl.monitor

import android.app.Application
import cn.tianyl.monitor.core.Monitor


/**
 * @author tianyl
 * @since 2022/3/7
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Monitor.init(this)
    }
}