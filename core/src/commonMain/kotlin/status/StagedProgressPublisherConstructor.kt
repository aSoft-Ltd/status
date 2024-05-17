@file:JsExport
@file:Suppress("NOTHING_TO_INLINE")

package status

import kotlinx.JsExport
import status.internal.SilentStagedProgressPublisherImpl


inline fun SilentStagedProgressPublisher(): StagedProgressPublisher<Any> = SilentStagedProgressPublisherImpl