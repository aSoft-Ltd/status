package status.internal

import status.Progress
import status.ProgressStagePublisher
import status.StagedProgressPublisher

@PublishedApi
internal object SilentStagedProgressPublisherImpl : StagedProgressPublisher<Any> {
    object ProgressStagePublisherImpl : ProgressStagePublisher<Any> {
        override fun invoke(progress: Progress<Any>) {}

        override fun update(progress: Progress<Any>) {}

        override val name by lazy { "No Stage" }
        override val index by lazy { 0 }
    }

    override fun stages(vararg value: String) = value.map { ProgressStagePublisherImpl }
}