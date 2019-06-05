package io.redgreen.benchpress.github.view

import io.redgreen.benchpress.github.domain.Repo

interface GitHubView {
    fun showLoading()
    fun hideSearchBar()
    fun hideRetry()
    fun hideLoading()
    fun showSearchBar()
    fun showRepos(repos: List<Repo>)
    fun hideClearButton()
    fun showClearButton()
    fun showRetryForSquareRepos()
    fun disableSearchBar()
    fun hideRepos()
    fun hideNoResults()
}
