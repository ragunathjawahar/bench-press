package io.redgreen.benchpress.github.view

import io.redgreen.benchpress.github.domain.Repo

interface GitHubView {
    fun showLoading()
    fun hideLoading()

    fun showSearchBar()
    fun hideSearchBar()
    fun disableSearchBar()
    fun enableSearchBar()

    fun showRepos(repos: List<Repo>)
    fun hideRepos()

    fun showClearButton()
    fun hideClearButton()

    fun showRetryForSquareRepos()
    fun hideRetryForSquareRepos()

    fun showRetryForSearchFailed()
    fun hideRetryForSearchFailed()

    fun showNoResults()
    fun hideNoResults()
}
