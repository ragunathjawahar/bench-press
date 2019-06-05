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
    private val squareRepos = listOf(
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
            .squareReposFetched(squareRepos)

        // when
        viewRenderer.render(squareReposFetchedModel)

        // then
        verify(view).hideLoading()
        verify(view).showSearchBar()
        verify(view).showRepos(squareRepos)
        verify(view).hideClearButton()

        verifyNoMoreInteractions(view)
    }

    @Test
    fun `it can render square repos loaded state (keyword is non-empty)`() {
        // given
        val squareReposFetchedModel = GitHubModel
            .LOADING
            .squareReposFetched(squareRepos)
            .keywordChanged("butterknife")

        // when
        viewRenderer.render(squareReposFetchedModel)

        // then
        verify(view).hideLoading()
        verify(view).showSearchBar()
        verify(view).showRepos(squareRepos)
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
            .squareReposFetched(squareRepos)
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

    @Test
    fun `it can render no results found for search keyword`() {
        // given
        val noReposFoundModel = GitHubModel
            .LOADING
            .squareReposFetched(squareRepos)
            .keywordChanged("i-don't-exist")
            .searchingRepos()
            .noReposFound()

        // when
        viewRenderer.render(noReposFoundModel)

        // then
        verify(view).hideLoading()
        verify(view).enableSearchBar()
        verify(view).showNoResults()

        verifyNoMoreInteractions(view)
    }

    @Test
    fun `it can render repos from search result`() {
        // given
        val searchResultRepos = listOf(Repo("RxJava", "Reactive and functional programming library for Java.", 30000))

        val reposFoundModel = GitHubModel
            .LOADING
            .squareReposFetched(squareRepos)
            .keywordChanged("rxjava")
            .searchingRepos()
            .searchReposFound(searchResultRepos)

        // when
        viewRenderer.render(reposFoundModel)

        // then
        verify(view).hideLoading()
        verify(view).showRepos(searchResultRepos)
        verify(view).enableSearchBar()

        verifyNoMoreInteractions(view)
    }

    @Test
    fun `it can render unable to fetch search repos`() {
        // given
        val unableToFetchReposModel = GitHubModel
            .LOADING
            .squareReposFetched(squareRepos)
            .keywordChanged("agera")
            .searchingRepos()
            .unableToFetchRepos()

        // when
        viewRenderer.render(unableToFetchReposModel)

        // then
        verify(view).hideLoading()
        verify(view).enableSearchBar()
        verify(view).showRetryForSearchFailed()

        verifyNoMoreInteractions(view)
    }
}
