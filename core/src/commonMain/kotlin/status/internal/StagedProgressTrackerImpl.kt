package status.internal

import status.Progress
import status.ProgressStage
import status.ProgressStagePublisher
import status.StagedProgressReport
import status.StagedProgressTracker

@PublishedApi
internal class StagedProgressTrackerImpl<T : Any> : StagedProgressTracker<T> {

    override var report = StagedProgressReportImpl<T>(mutableMapOf())

    private inner class ProgressStagePublisherImpl(
        override val name: String,
        override val index: Int
    ) : ProgressStagePublisher<T> {
        override fun invoke(progress: Progress<T>) {
            report.stages[this] = progress
            progressReportHandlers.forEach { it(report) }
        }

        override fun update(progress: Progress<T>) = invoke(progress)
    }

    private var stagesCreatedHandlers = mutableListOf<(List<ProgressStage>) -> Unit>()
    private var progressReportHandlers = mutableListOf<(StagedProgressReport<T>) -> Unit>()

    override fun onStagesCreated(callback: (List<ProgressStage>) -> Unit) = callback.also { stagesCreatedHandlers.add(it) }

    override fun onProgress(callback: (StagedProgressReport<T>) -> Unit) = callback.also { progressReportHandlers.add(it) }

    override fun stages(vararg value: String): List<ProgressStagePublisher<T>> = value.mapIndexed { index, name ->
        ProgressStagePublisherImpl(name, index)
    }.also {
        report = StagedProgressReportImpl(it.associateWith { null }.toMutableMap())
        stagesCreatedHandlers.forEach { handler -> handler(it) }
    }

    override fun removeProgressListener(callback: ((StagedProgressReport<T>) -> Unit)?) {
        if (callback == null) return
        progressReportHandlers.remove(callback)
    }

    override fun removeStagesCreatedListener(callback: ((List<ProgressStage>) -> Unit)?) {
        if (callback == null) return
        stagesCreatedHandlers.remove(callback)
    }
}