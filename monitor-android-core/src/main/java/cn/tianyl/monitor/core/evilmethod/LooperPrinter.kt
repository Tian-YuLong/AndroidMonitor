package cn.tianyl.monitor.core.evilmethod

import android.util.Printer


/**
 * @author tianyl
 * @since 2022/3/7
 */
class LooperPrinter : Printer {

    var observer: Observer? = null

    override fun println(s: String?) {
        if (s?.get(0) == '>') {
            // 函数调用开始
            observer?.messageDispatchStarting()
        } else if (s?.get(0) == '<') {
            observer?.messageDispatched()
        } else {
        }
    }

    interface Observer {

        fun messageDispatchStarting()

        fun messageDispatched()

    }
}