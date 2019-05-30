package io.redgreen.benchpress.github

import com.spotify.mobius.test.NextMatchers.*
import com.spotify.mobius.test.UpdateSpec
import com.spotify.mobius.test.UpdateSpec.assertThatNext
import org.junit.Test

class GitHubUpdateLogicTest {
    private val updateSpec = UpdateSpec<GitHubModel, GitHubEvent, GitHubEffect>(GitHubUpdateLogic)
    private val loadingModel = GitHubModel.LOADING

    @Test
    fun `when fetch Square's repositories API call fails, then show unable to fetch repositories`() {
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

    @Test
    fun `when user tries to retry fetching Square's repositories, then show loading and make the API call`() {
        val unableToFetchSquareReposModel = loadingModel
            .unableToFetchSquareRepos()

        updateSpec
            .given(unableToFetchSquareReposModel)
            .`when`(RetryFetchSquareReposEvent)
            .then(
                assertThatNext(
                    hasModel(unableToFetchSquareReposModel.fetchingSquareRepos()),
                    hasEffects(FetchSquareReposEffect as GitHubEffect)
                )
            )
    }

    @Test
    fun `when Square's repos were fetched successfully, then display it as a list`() {
        val repos = listOf(
            Repo("Aardvark", "Aardvark is a library that makes it dead simple to create actionable bug reports.", 221)
        )

        updateSpec
            .given(loadingModel)
            .`when`(SquareReposFetchedEvent(repos))
            .then(
                assertThatNext(
                    hasModel(loadingModel.squareReposFetched(repos)),
                    hasNoEffects()
                )
            )
    }
}