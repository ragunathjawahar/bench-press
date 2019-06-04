package io.redgreen.benchpress.github.domain

import io.redgreen.benchpress.architecture.AsyncOp
import io.redgreen.benchpress.architecture.AsyncOp.*

data class GitHubModel(
    val squareReposAsyncOp: AsyncOp,
    val squareRepos: List<Repo> = emptyList(),
    val keyword: String = "",
    val searchReposAsyncOp: AsyncOp = IDLE,
    val searchRepos: List<Repo> = emptyList()
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

    fun keywordChanged(keyword: String): GitHubModel {
        return copy(keyword = keyword)
    }

    fun searchRepos(): GitHubModel {
        return copy(searchReposAsyncOp = IN_FLIGHT)
    }

    fun searchReposFound(resultRepos: List<Repo>): GitHubModel {
        return copy(searchReposAsyncOp = SUCCEEDED, searchRepos = resultRepos)
    }

    fun noReposFound(): GitHubModel {
        return copy(searchReposAsyncOp = SUCCEEDED, searchRepos = emptyList())
    }

    fun unableToFetchRepos(): GitHubModel {
        return copy(searchReposAsyncOp = FAILED, searchRepos = emptyList())
    }

    fun clearKeyword(): GitHubModel {
        TODO("not implemented")
    }
}
