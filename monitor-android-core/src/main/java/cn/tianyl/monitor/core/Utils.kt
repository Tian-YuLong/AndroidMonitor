package cn.tianyl.monitor.core


/**
 * @author tianyl
 * @since 2022/3/8
 */
class Utils {

    fun getStack(): String {
        val trace = Throwable().stackTrace
        return getStack(trace)
    }

    fun getStack(trace: Array<StackTraceElement>?): String {
        return getStack(trace, "", -1)
    }

    fun getStack(trace: Array<StackTraceElement>?, preFixStr: String?, limit: Int): String {
        var limit = limit
        if (trace == null || trace.size < 3) {
            return ""
        }
        if (limit < 0) {
            limit = Int.MAX_VALUE
        }
        val t = StringBuilder(" \n")
        var i = 3
        while (i < trace.size - 3 && i < limit) {
            t.append(preFixStr)
            t.append("at ")
            t.append(trace[i].className)
            t.append(":")
            t.append(trace[i].methodName)
            t.append("(" + trace[i].lineNumber + ")")
            t.append("\n")
            i++
        }
        return t.toString()
    }
}