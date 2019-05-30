package io.redgreen.benchpress.github

import io.redgreen.benchpress.architecture.AsyncOp
import io.redgreen.benchpress.architecture.AsyncOp.*

data class GitHubModel(
    val squareReposAsyncOp: AsyncOp,
    val squareRepos: List<Repo> = emptyList()
) {
    companion object {
        val LOADING = GitHubModel(squareReposAsyncOp = IN_FLIGHT)
    }

    fun unableToFetchSquareRepos(): GitHubModel {
        return copy(squareReposAsyncOp = FAILED)
    }

    fun fetchingSquareRepos(): GitHubModel {
        return copy(squareReposAsyncOp = IN_FLIGHT)
    }

    fun squareReposFetched(repos: List<Repo>): GitHubModel {
        return copy(squareReposAsyncOp = SUCCEEDED, squareRepos = repos)
    }
}
