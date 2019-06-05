package io.redgreen.benchpress.github.view

import io.redgreen.benchpress.github.domain.GitHubModel

class GitHubViewRenderer(val view: GitHubView) {
    fun render(model: GitHubModel) {
        view.showLoading()
        view.hideSearchBar()
        view.hideRetry()
    }
}