import kommander.expect
import status.PercentProgress
import status.Progress
import status.StagedProgressTracker
import kotlin.test.Test

class ProgressTests {
    @Test
    fun should_show_accurate_progress() {
        val progress: Progress<Double> = PercentProgress(35)
        // Then
        expect(progress.percentage.done).toBe(35.0)
    }

    @Test
    fun should_show_accurate_progress_percentage_on_a_staged_progress() {
        val tracker = StagedProgressTracker<Double>()

        val progress = mutableListOf<Double>()
        val listener = tracker.onProgress { progress.add(it.overall.percentage.done) }

        val (reading, uploading) = tracker.stages("Reading", "Uploading")
        val total = 10_000
        for (i in 1..total) {
            reading(Progress(i, total))
        }

        for (i in 1..total) {
            uploading(Progress(i, total))
        }
        tracker.removeProgressListener(listener)
        expect(progress.size).toBe(2 * total)
        expect(progress.any { it <= 100 }).toBe(true)
    }
}