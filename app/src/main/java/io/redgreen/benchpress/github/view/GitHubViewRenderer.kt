package io.redgreen.benchpress.github.view

import io.redgreen.benchpress.architecture.AsyncOp.*
import io.redgreen.benchpress.github.domain.GitHubModel

class GitHubViewRenderer(val view: GitHubView) {
    fun render(model: GitHubModel) {
        with(view) {
            if (model.searchReposAsyncOp == SUCCEEDED && model.searchRepos.isEmpty()) {
                hideLoading()
                enableSearchBar()
                showNoResults()
            } else if (model.searchReposAsyncOp == FAILED) {
                hideLoading()
                enableSearchBar()
                showRetryForSearchFailed()
            } else if (model.searchReposAsyncOp == IN_FLIGHT) {
                showLoading()
                disableSearchBar()
                hideRepos()
                hideNoResults()
                hideRetry()
            } else if (model.squareReposAsyncOp == SUCCEEDED) {
                hideLoading()
                showSearchBar()
                showRepos(model.squareRepos)
                if (model.keyword.isEmpty()) {
                    hideClearButton()
                } else {
                    showClearButton()
                }
            } else if(model.squareReposAsyncOp == FAILED) {
                hideLoading()
                showRetryForSquareRepos()
            } else {
                showLoading()
                hideSearchBar()
                hideRetry()
            }
        }
    }
}
