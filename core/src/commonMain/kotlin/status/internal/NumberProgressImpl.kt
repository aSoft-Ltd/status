package status.internal

import status.Progress
import status.ProgressionSplit
import kotlin.math.round

@PublishedApi
internal class NumberProgressImpl(
    done: Number,
    total: Number
) : Progress<Double> {
    override val done by lazy { done.toRounded() }
    override val total by lazy { total.toRounded() }
    override val left by lazy { this.total - this.done }
    override val fraction by lazy {
        val value = if (this.total == 0.0) 0.0 else (this.done / this.total).toRounded()
        ProgressionSplit(value, 1 - value)
    }
    override val percentage by lazy { fraction * 100 }

    private fun Number.toRounded() = round(toDouble() * 100) / 100
}