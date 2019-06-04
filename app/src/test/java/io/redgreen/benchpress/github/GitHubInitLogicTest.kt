package io.redgreen.benchpress.github

import com.spotify.mobius.test.FirstMatchers.hasEffects
import com.spotify.mobius.test.FirstMatchers.hasModel
import com.spotify.mobius.test.InitSpec
import com.spotify.mobius.test.InitSpec.assertThatFirst
import org.junit.Test

class GitHubInitLogicTest {
    private val initSpec = InitSpec<GitHubModel, GitHubEffect>(GitHubInitLogic)

    @Test
    fun `when user opens the screen for the first time, then fetch Square's repositories`() {
        initSpec
            .`when`(GitHubModel.LOADING)
            .then(
                assertThatFirst(
                    hasModel(GitHubModel.LOADING),
                    hasEffects(FetchSquareReposEffect as GitHubEffect)
                )
            )
    }

    @Test
    fun `when user restores the screen and we were still fetching Square's repositories, then re-initiate the call`() {
        initSpec
            .`when`(GitHubModel.LOADING)
            .then(
                assertThatFirst(
                    hasModel(GitHubModel.LOADING),
                    hasEffects(FetchSquareReposEffect as GitHubEffect)
                )
            )
    }

    // TODO 1. Restoring last known state as is.
    // TODO 2. Restoring search-in-progress.
}
