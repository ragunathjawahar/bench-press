package io.redgreen.benchpress.github.view

import io.redgreen.benchpress.architecture.AsyncOp
import io.redgreen.benchpress.github.domain.GitHubModel

class GitHubViewRenderer(val view: GitHubView) {
    fun render(model: GitHubModel) {
        with(view) {
            if (model.squareReposAsyncOp == AsyncOp.SUCCEEDED) {
                hideLoading()
                showSearchBar()
                showRepos(model.squareRepos)
                if (model.keyword.isEmpty()) {
                    hideClearButton()
                } else {
                    showClearButton()
                }
            } else if(model.squareReposAsyncOp == AsyncOp.FAILED){
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
