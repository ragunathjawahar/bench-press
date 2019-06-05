package io.redgreen.benchpress.github.view

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import io.redgreen.benchpress.github.domain.GitHubModel
import org.junit.Test

class GitHubViewRendererTest {
    @Test
    fun `it can render square loading screen when fetching square repos`() {
        // given
        val view = mock<GitHubView>()
        val viewRenderer = GitHubViewRenderer(view)
        val fetchingSquareReposModel = GitHubModel.LOADING

        // when
        viewRenderer.render(fetchingSquareReposModel)

        // then
        verify(view).showLoading()
        verify(view).hideSearchBar()
        verify(view).hideRetry()

        verifyNoMoreInteractions(view)
    }
}
