@file:Suppress("NOTHING_TO_INLINE")

package status

import status.internal.StagedProgressTrackerImpl

inline fun <T : Any> StagedProgressTracker(): StagedProgressTracker<T> = StagedProgressTrackerImpl()