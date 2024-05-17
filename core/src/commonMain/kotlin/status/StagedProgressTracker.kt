@file:JsExport

package status

import kotlinx.JsExport


interface StagedProgressTracker<T : Any> : StagedProgressListener<T>, StagedProgressPublisher<T>