package io.redgreen.benchpress.github

import com.spotify.mobius.Next
import com.spotify.mobius.Next.next
import com.spotify.mobius.Update

object GitHubUpdateLogic : Update<GitHubModel, GitHubEvent, GitHubEffect> {
    override fun update(model: GitHubModel, event: GitHubEvent): Next<GitHubModel, GitHubEffect> {
        return when (event) {
            is UnableToFetchSquareReposEvent -> next(model.unableToFetchSquareRepos())
            is RetryFetchSquareReposEvent -> next(model.fetchingSquareRepos(), setOf<GitHubEffect>(FetchSquareReposEffect))
            is SquareReposFetchedEvent -> next(model.squareReposFetched(event.repos))
            is KeywordChangedEvent -> next(model.keywordChanged(event.keyword))
            else -> TODO("Unknown event $event")
        }
    }
}
