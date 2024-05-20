@file:JsExport

package status.internal

import kotlinx.JsExport
import status.PercentProgress
import status.Progress
import status.ProgressStage
import status.StageReport
import status.StagedProgressReport

internal class StagedProgressReportImpl<T>(
    override val stages: MutableMap<ProgressStage, Progress<T>?>
) : StagedProgressReport<T> {
    override var current: StageReport<T>? = null
    override val overall: Progress<Double>
        get() {
            if (stages.isEmpty()) return PercentProgress(0)
            return PercentProgress(stages.values.map { it?.percentage?.done ?: 0.0 }.average())
        }
}