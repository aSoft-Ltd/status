@file:JsExport

package status

import kotlinx.JsExport

data class StageReport<out T>(
    val stage: ProgressStage,
    val report: Progress<T>?
)