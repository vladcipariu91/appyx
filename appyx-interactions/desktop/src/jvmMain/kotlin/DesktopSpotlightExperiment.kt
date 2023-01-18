import NavTarget.Child1
import NavTarget.Child2
import NavTarget.Child3
import NavTarget.Child4
import NavTarget.Child5
import NavTarget.Child6
import NavTarget.Child7
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.bumble.appyx.interactions.Logger
import com.bumble.appyx.transitionmodel.spotlight.Spotlight
import com.bumble.appyx.transitionmodel.spotlight.SpotlightModel
import com.bumble.appyx.transitionmodel.spotlight.interpolator.SpotlightSlider
import com.bumble.appyx.transitionmodel.spotlight.operation.first
import com.bumble.appyx.transitionmodel.spotlight.operation.last
import com.bumble.appyx.transitionmodel.spotlight.operation.next
import com.bumble.appyx.transitionmodel.spotlight.operation.previous

@ExperimentalMaterialApi
@Composable
fun DesktopSpotlightExperiment() {
    val coroutineScope = rememberCoroutineScope()
    val spotlight = Spotlight(
        scope = coroutineScope,
        model = SpotlightModel(
            items = listOf(
                Child1,
                Child2,
                Child3,
                Child4,
                Child5,
                Child6,
                Child7
            )
        ),
        interpolator = { SpotlightSlider(it) },
        gestureFactory = { SpotlightSlider.Gestures(it) },
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow)
    )

    Column(
        Modifier
            .fillMaxWidth()
            .background(appyx_dark)
    ) {
        Children(
            interactionModel = spotlight,
            modifier = Modifier
                .weight(0.9f)
                .padding(
                    horizontal = 64.dp,
                    vertical = 12.dp
                ),
            element = {
                Element(
                    frameModel = it,
                    modifier = Modifier
                        .fillMaxSize()
                        .pointerInput(it.navElement.key) {
                            detectDragGestures(
                                onDrag = { change, dragAmount ->
                                    change.consume()
                                    spotlight.onDrag(dragAmount, this)
                                },
                                onDragEnd = {
                                    Logger.log("drag", "end")
                                    spotlight.onDragEnd()
                                }
                            )
                        }
                )
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { spotlight.first() }) {
                Text("First")
            }
            Button(onClick = { spotlight.previous(spring(stiffness = Spring.StiffnessLow)) }) {
                Text("Prev")
            }
            Button(onClick = { spotlight.next(spring(stiffness = Spring.StiffnessMedium)) }) {
                Text("Next")
            }
            Button(onClick = { spotlight.last() }) {
                Text("Last")
            }
        }
    }
}
