package io.redgreen.benchpress.github

sealed class GitHubEffect

object FetchSquareReposEffect : GitHubEffect()

data class SearchReposEffect(val keyword: String) : GitHubEffect()
