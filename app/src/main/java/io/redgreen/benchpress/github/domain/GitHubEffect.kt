package io.redgreen.benchpress.github.domain

sealed class GitHubEffect

object FetchSquareReposEffect : GitHubEffect()

data class SearchReposEffect(val keyword: String) : GitHubEffect()
