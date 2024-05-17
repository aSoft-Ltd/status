@file:JsExport

package status.internal

import kotlinx.JsExport
import status.PercentProgress
import status.Progress
import status.ProgressStage
import status.StagedProgressReport

internal class StagedProgressReportImpl<T>(
    override val stages: MutableMap<ProgressStage, Progress<T>?>
) : StagedProgressReport<T> {
    override val overall by lazy {
        if (stages.isEmpty()) return@lazy PercentProgress(0)
        PercentProgress(stages.values.map { it?.percentage?.done ?: 0.0 }.average())
    }
}