@file:JsExport

package status

import kotlinx.JsExport

interface StagedProgressReport<out T> {
    val stages: Map<ProgressStage, Progress<T>?>
    val current: StageReport<T>?
    val overall: Progress<Double>
}