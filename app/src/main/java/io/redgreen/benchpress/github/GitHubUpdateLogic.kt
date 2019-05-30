package io.redgreen.benchpress.github

import com.spotify.mobius.Next
import com.spotify.mobius.Update

object GitHubUpdateLogic : Update<GitHubModel, GitHubEvent, GitHubEffect> {
    override fun update(model: GitHubModel, event: GitHubEvent): Next<GitHubModel, GitHubEffect> {
        return if (event is UnableToFetchSquareReposEvent) {
            Next.next(model.unableToFetchSquareRepos())
        } else {
            TODO("Unknown event $event")
        }
    }
}
