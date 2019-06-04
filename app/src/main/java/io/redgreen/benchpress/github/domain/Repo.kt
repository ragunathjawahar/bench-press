package io.redgreen.benchpress.github.domain

// Repo => name, description, stargazers_count
data class Repo(
    val name: String,
    val description: String,
    val stars: Int
)
