package io.redgreen.benchpress.github

import io.redgreen.benchpress.architecture.AsyncOp
import io.redgreen.benchpress.architecture.AsyncOp.FAILED

data class GitHubModel(
    val squareReposAsyncOp: AsyncOp
) {
    companion object {
        val LOADING = GitHubModel(squareReposAsyncOp = AsyncOp.IN_FLIGHT)
    }

    fun unableToFetchSquareRepos(): GitHubModel {
        return copy(squareReposAsyncOp = FAILED)
    }
}
