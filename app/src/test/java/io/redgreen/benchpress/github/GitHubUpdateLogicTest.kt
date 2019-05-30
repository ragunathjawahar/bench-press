package io.redgreen.benchpress.github

import com.spotify.mobius.test.NextMatchers.hasModel
import com.spotify.mobius.test.NextMatchers.hasNoEffects
import com.spotify.mobius.test.UpdateSpec
import com.spotify.mobius.test.UpdateSpec.assertThatNext
import org.junit.Test

class GitHubUpdateLogicTest {
    @Test
    fun `when fetch Square's repositories API call fails, then show unable to fetch repositories`() {
        val updateSpec = UpdateSpec<GitHubModel, GitHubEvent, GitHubEffect>(GitHubUpdateLogic)
        val loadingModel = GitHubModel.LOADING

        updateSpec
            .given(loadingModel)
            .`when`(UnableToFetchSquareReposEvent)
            .then(
                assertThatNext(
                    hasModel(loadingModel.unableToFetchSquareRepos()),
                    hasNoEffects()
                )
            )
    }
}