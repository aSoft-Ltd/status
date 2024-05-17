package status

interface ProgressStagePublisher<in T : Any> : ProgressStage {

    operator fun invoke(progress: Progress<T>)

    fun update(progress: Progress<T>)
}