package io.redgreen.benchpress.github

import com.spotify.mobius.test.FirstMatchers.hasEffects
import com.spotify.mobius.test.FirstMatchers.hasModel
import com.spotify.mobius.test.InitSpec
import com.spotify.mobius.test.InitSpec.assertThatFirst
import org.junit.Test

class GitHubInitLogicTest {
    @Test
    fun `when user opens the screen for the first time, then make a network call`() {
        val initSpec = InitSpec<GitHubModel, GitHubEffect>(GitHubInitLogic)

        initSpec
            .`when`(GitHubModel.LOADING)
            .then(
                assertThatFirst(
                    hasModel(GitHubModel.LOADING),
                    hasEffects(FetchSquareReposEffect as GitHubEffect)
                )
            )
    }
}