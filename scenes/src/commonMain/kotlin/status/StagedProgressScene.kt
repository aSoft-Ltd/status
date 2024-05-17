@file:JsExport

package status

import cinematic.mutableLiveOf
import kotlinx.JsExport

class StagedProgressScene<T : Any>(private val tracker: StagedProgressListener<T>) {
    val report = mutableLiveOf(tracker.report)

    private var onStageCreated: ((List<ProgressStage>) -> Unit)? = null
    private var onProgress: ((StagedProgressReport<T>) -> Unit)? = null

    fun initialize() {
        onStageCreated = tracker.onStagesCreated { report.value = tracker.report }
        onProgress = tracker.onProgress { report.value = it }
    }

    fun deInitialize() {
        report.stopAll()
        tracker.removeProgressListener(onProgress)
        tracker.removeStagesCreatedListener(onStageCreated)
        onStageCreated = null
        onProgress = null
    }
}