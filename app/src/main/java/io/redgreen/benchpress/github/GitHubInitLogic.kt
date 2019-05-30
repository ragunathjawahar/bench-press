package io.redgreen.benchpress.github

import com.spotify.mobius.First
import com.spotify.mobius.Init

object GitHubInitLogic : Init<GitHubModel, GitHubEffect> {
    override fun init(model: GitHubModel): First<GitHubModel, GitHubEffect> {
        return First.first(GitHubModel.LOADING, setOf(FetchSquareReposEffect))
    }
}
