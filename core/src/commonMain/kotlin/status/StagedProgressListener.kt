@file:JsExport

package status

import kotlinx.JsExport


interface StagedProgressListener<out T> {

    val report: StagedProgressReport<T>

    fun onProgress(callback: (StagedProgressReport<T>) -> Unit) : (StagedProgressReport<@UnsafeVariance T>) -> Unit

    fun onStagesCreated(callback: (List<ProgressStage>) -> Unit) : (List<ProgressStage>) -> Unit

    fun removeProgressListener(callback: ((StagedProgressReport<T>) -> Unit)?)
    fun removeStagesCreatedListener(callback: ((List<ProgressStage>) -> Unit)?)
}