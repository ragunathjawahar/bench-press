package io.redgreen.benchpress.github

sealed class GitHubEvent

object UnableToFetchSquareReposEvent : GitHubEvent()

object RetryFetchSquareReposEvent : GitHubEvent()

data class SquareReposFetchedEvent(val repos: List<Repo>) : GitHubEvent()

data class KeywordChangedEvent(val keyword: String) : GitHubEvent()

object SearchEvent : GitHubEvent()

data class SearchReposFoundEvent(val repos: List<Repo>) : GitHubEvent()

object NoReposFoundEvent : GitHubEvent()

object UnableToFetchReposEvent : GitHubEvent()
