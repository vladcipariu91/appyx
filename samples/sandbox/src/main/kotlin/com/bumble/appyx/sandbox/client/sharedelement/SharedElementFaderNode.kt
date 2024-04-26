package com.bumble.appyx.sandbox.client.sharedelement

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.composable.Children
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.core.node.ParentNode
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.backstack.operation.push
import com.bumble.appyx.navmodel.backstack.operation.replace
import com.bumble.appyx.navmodel.backstack.transitionhandler.rememberBackstackFader
import kotlinx.parcelize.Parcelize

class SharedElementFaderNode(
    buildContext: BuildContext,
    private val backStack: BackStack<NavTarget> = BackStack(
        savedStateMap = buildContext.savedStateMap,
        initialElement = NavTarget.HorizontalList(0)
    )
) : ParentNode<SharedElementFaderNode.NavTarget>(
    navModel = backStack,
    buildContext = buildContext,
) {


    sealed class NavTarget : Parcelable {
        @Parcelize
        data class FullScreen(val profileId: Int) : NavTarget()

        @Parcelize
        data class HorizontalList(val profileId: Int = 0) : NavTarget()

        @Parcelize
        data class VerticalList(val profileId: Int = 0) : NavTarget()
    }

    override fun resolve(navTarget: NavTarget, buildContext: BuildContext): Node {
        return when (navTarget) {
            is NavTarget.HorizontalList -> ProfileHorizontalListNode(
                onProfileClick = { id ->
                    backStack.push(NavTarget.FullScreen(id))
                },
                selectedId = navTarget.profileId,
                buildContext = buildContext
            )

            is NavTarget.VerticalList -> ProfileVerticalListNode(
                onProfileClick = { id ->
                    backStack.replace(NavTarget.HorizontalList(id))
                },
                profileId = navTarget.profileId,
                buildContext = buildContext
            )

            is NavTarget.FullScreen -> FullScreenNode(
                onClick = { id ->
                    backStack.push(NavTarget.VerticalList(id))
                },
                profileId = navTarget.profileId,
                buildContext = buildContext
            )
        }
    }

    @SuppressLint("UnusedContentLambdaTargetStateParameter")
    @Composable
    override fun View(modifier: Modifier) {
        Children(
            navModel = backStack,
            withSharedElementTransition = true,
            transitionHandler = rememberBackstackFader(transitionSpec = { tween(300) })
        )
    }
}
