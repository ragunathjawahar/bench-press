package io.redgreen.benchpress.github

import io.redgreen.benchpress.architecture.AsyncOp

data class GitHubModel(
    val squareReposAsyncOp: AsyncOp
) {
    companion object {
        val LOADING = GitHubModel(squareReposAsyncOp = AsyncOp.IN_FLIGHT)
    }
}
