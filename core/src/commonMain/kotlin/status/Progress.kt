@file:JsExport

package status

import kotlinx.JsExport

interface Progress<out T> {
    val done: T
    val left: T
    val total: T

    val fraction: ProgressionSplit
    val percentage: ProgressionSplit
}