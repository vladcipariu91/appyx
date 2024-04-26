package com.bumble.appyx.sandbox.client.sharedelement

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.navigation.transition.sharedElement
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.samples.common.profile.Profile
import com.bumble.appyx.samples.common.profile.ProfileImage

class ProfileVerticalListNode(
    private val profileId: Int,
    private val onProfileClick: (Int) -> Unit,
    buildContext: BuildContext
) : Node(buildContext) {

    @OptIn(ExperimentalSharedTransitionApi::class)
    @Composable
    override fun View(modifier: Modifier) {
        val state = rememberLazyListState(initialFirstVisibleItemIndex = profileId)
        LazyColumn(
            state = state,
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp),
        ) {
            repeat(10) { pageId ->
                item(key = pageId) {
                    Row(
                        modifier = Modifier
                            .clickable {
                                onProfileClick(pageId)
                            }
                    ) {
                        val profile = Profile.allProfiles[pageId]
                        ProfileImage(
                            profile.drawableRes, modifier = Modifier
                                .requiredSize(64.dp)
                                .sharedElement(key = "$pageId image")
                                .clip(CircleShape)
                        )
                        Text(
                            text = "${profile.name}, ${profile.age}",
                            color = Color.Black,
                            fontSize = 32.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .sharedElement(key = "$pageId text")
                                .padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}
