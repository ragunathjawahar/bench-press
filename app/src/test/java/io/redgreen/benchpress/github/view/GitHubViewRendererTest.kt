package io.redgreen.benchpress.github.view

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import io.redgreen.benchpress.github.domain.GitHubModel
import io.redgreen.benchpress.github.domain.Repo
import org.junit.Test

class GitHubViewRendererTest {
    private val view = mock<GitHubView>()
    private val viewRenderer = GitHubViewRenderer(view)
    private val repos = listOf(
        Repo("Retrofit", "A typesafe HTTP client for Java and Android.", 20400)
    )

    @Test
    fun `it can render square loading screen when fetching square repos`() {
        // given
        val fetchingSquareReposModel = GitHubModel.LOADING

        // when
        viewRenderer.render(fetchingSquareReposModel)

        // then
        verify(view).showLoading()
        verify(view).hideSearchBar()
        verify(view).hideRetry()

        verifyNoMoreInteractions(view)
    }

    @Test
    fun `it can render square repos loaded state (keyword is empty)`() {
        // given
        val squareReposFetchedModel = GitHubModel
            .LOADING
            .squareReposFetched(repos)

        // when
        viewRenderer.render(squareReposFetchedModel)

        // then
        verify(view).hideLoading()
        verify(view).showSearchBar()
        verify(view).showRepos(repos)
        verify(view).hideClearButton()

        verifyNoMoreInteractions(view)
    }

    @Test
    fun `it can render square repos loaded state (keyword is non-empty)`() {
        // given
        val squareReposFetchedModel = GitHubModel
            .LOADING
            .squareReposFetched(repos)
            .keywordChanged("butterknife")

        // when
        viewRenderer.render(squareReposFetchedModel)

        // then
        verify(view).hideLoading()
        verify(view).showSearchBar()
        verify(view).showRepos(repos)
        verify(view).showClearButton()

        verifyNoMoreInteractions(view)
    }

    @Test
    fun `it can render failed state when unable to fetch square repos`() {
        // given
        val unableToFetchSquareRepos = GitHubModel
            .LOADING
            .unableToFetchSquareRepos()

        // when
        viewRenderer.render(unableToFetchSquareRepos)

        // then
        verify(view).hideLoading()
        verify(view).showRetryForSquareRepos()

        verifyNoMoreInteractions(view)
    }

    @Test
    fun `it can render searching for repos state`() {
        // given
        val searchingReposModel = GitHubModel
            .LOADING
            .squareReposFetched(repos)
            .keywordChanged("agera")
            .searchingRepos()

        // when
        viewRenderer.render(searchingReposModel)

        // then
        verify(view).showLoading()
        verify(view).disableSearchBar()
        verify(view).hideRepos()
        verify(view).hideNoResults()
        verify(view).hideRetry()

        verifyNoMoreInteractions(view)
    }
}
