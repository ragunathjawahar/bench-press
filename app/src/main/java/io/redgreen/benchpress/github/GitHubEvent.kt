package io.redgreen.benchpress.github

sealed class GitHubEvent

object UnableToFetchSquareReposEvent : GitHubEvent()
