package io.redgreen.benchpress.github

sealed class GitHubEvent

object UnableToFetchSquareReposEvent : GitHubEvent()

object RetryFetchSquareReposEvent : GitHubEvent()

data class SquareReposFetchedEvent(val repos: List<Repo>) : GitHubEvent()
