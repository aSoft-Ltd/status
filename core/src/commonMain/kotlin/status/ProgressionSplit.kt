@file:JsExport

package status

import kotlinx.JsExport

data class ProgressionSplit(
    val done: Double,
    val left: Double,
) {
    operator fun times(other: Int) = ProgressionSplit(done * other, left * other)
}