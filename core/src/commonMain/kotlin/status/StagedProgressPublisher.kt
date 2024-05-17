@file:JsExport

package status

import kotlinx.JsExport


interface StagedProgressPublisher<in T: Any> {
    fun stages(vararg value: String): List<ProgressStagePublisher<T>>
}