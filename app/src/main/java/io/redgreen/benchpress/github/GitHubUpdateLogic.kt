package io.redgreen.benchpress.github

import com.spotify.mobius.Next
import com.spotify.mobius.Update

object GitHubUpdateLogic : Update<GitHubModel, GitHubEvent, GitHubEffect> {
    override fun update(model: GitHubModel, event: GitHubEvent): Next<GitHubModel, GitHubEffect> {
        return when (event) {
            is UnableToFetchSquareReposEvent -> Next.next(model.unableToFetchSquareRepos())
            is RetryFetchSquareReposEvent -> Next.next(model.fetchingSquareRepos(), setOf<GitHubEffect>(FetchSquareReposEffect))
            else -> TODO("Unknown event $event")
        }
    }
}
