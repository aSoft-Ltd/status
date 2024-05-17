@file:Suppress("NOTHING_TO_INLINE")

package status

import status.internal.NumberProgressImpl
import kotlin.math.max
import kotlin.math.min

inline fun PercentProgress(percent: Number): Progress<Double> {
    val pi = percent.toDouble()
    val pf = max(0.0, pi)
    val value = min(pf, 100.0)
    return NumberProgressImpl(value, 100.0 - value)
}

inline fun Progress(done: Number, total: Number): Progress<Double> {
    val di = done.toDouble()
    val ti = total.toDouble()
    val df = min(di, ti)
    val tf = max(di, ti)
    return NumberProgressImpl(df, tf)
}