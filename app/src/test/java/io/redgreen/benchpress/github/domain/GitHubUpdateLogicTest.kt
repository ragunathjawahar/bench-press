package io.redgreen.benchpress.github.domain

import com.spotify.mobius.test.NextMatchers.*
import com.spotify.mobius.test.UpdateSpec
import com.spotify.mobius.test.UpdateSpec.assertThatNext
import org.junit.Test

class GitHubUpdateLogicTest {
    private val updateSpec = UpdateSpec<GitHubModel, GitHubEvent, GitHubEffect>(GitHubUpdateLogic)

    private val loadingModel = GitHubModel.LOADING

    private val repos = listOf(
        Repo(
            "Aardvark",
            "Aardvark is a library that makes it dead simple to create actionable bug reports.",
            221
        )
    )

    private val reposFetchedModel = loadingModel.squareReposFetched(repos)

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

    @Test
    fun `when user types a keyword, then update the model`() {
        val keyword = "Hello World!"

        updateSpec
            .given(reposFetchedModel)
            .`when`(KeywordChangedEvent(keyword))
            .then(
                assertThatNext(
                    hasModel(reposFetchedModel.keywordChanged(keyword)),
                    hasNoEffects()
                )
            )
    }

    @Test
    fun `when user hits search, then search for repos using the keyword`() {
        val keyword = "retrofit"
        val keywordChangedModel = reposFetchedModel
            .keywordChanged(keyword)

        updateSpec
            .given(keywordChangedModel)
            .`when`(SearchEvent)
            .then(
                assertThatNext(
                    hasModel(keywordChangedModel.searchRepos()),
                    hasEffects(SearchReposEffect(keyword) as GitHubEffect)
                )
            )
    }

    @Test
    fun `when search finds results, then show repos on the screen`() {
        val keyword = "retrofit"
        val searchingReposModel = reposFetchedModel
            .keywordChanged(keyword)
            .searchRepos()

        val resultRepos = listOf(
            Repo(
                "Retrofit",
                "Description for Retrofit",
                5000
            )
        )

        updateSpec
            .given(searchingReposModel)
            .`when`(SearchReposFoundEvent(resultRepos))
            .then(
                assertThatNext(
                    hasModel(searchingReposModel.searchReposFound(resultRepos)),
                    hasNoEffects()
                )
            )
    }

    @Test
    fun `when search does not have any results, then show no repos found`() {
        val keyword = "whatever-i-don't-exist"
        val searchingReposModel = reposFetchedModel
            .keywordChanged(keyword)
            .searchRepos()

        updateSpec
            .given(searchingReposModel)
            .`when`(NoReposFoundEvent)
            .then(
                assertThatNext(
                    hasModel(searchingReposModel.noReposFound()),
                    hasNoEffects()
                )
            )
    }

    @Test
    fun `when search fails due to unknown error, then show retry`() {
        val keyword = "compose"
        val searchingReposModel = reposFetchedModel
            .keywordChanged(keyword)
            .searchRepos()

        updateSpec
            .given(searchingReposModel)
            .`when`(UnableToFetchReposEvent)
            .then(
                assertThatNext(
                    hasModel(searchingReposModel.unableToFetchRepos()),
                    hasNoEffects()
                )
            )
    }

    @Test
    fun `when user clears the keyword, then show initially fetched square repos`() {
        val keyword = "retrofit"
        val resultRepos = listOf(
            Repo(
                "Retrofit",
                "Description for Retrofit",
                5000
            )
        )
        val reposFoundModel = reposFetchedModel
            .keywordChanged(keyword)
            .searchRepos()
            .searchReposFound(resultRepos)

        updateSpec
            .given(reposFoundModel)
            .`when`(KeywordClearedEvent)
            .then(
                assertThatNext(
                    hasModel(reposFoundModel.clearKeyword()),
                    hasNoEffects()
                )
            )
    }
}
