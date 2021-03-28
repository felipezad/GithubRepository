package com.example.domain

import com.example.annotations.WhenNullReturnEmpty
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GitHubResponseItem(
    @Json(name = "archive_url")
    @WhenNullReturnEmpty
    val archiveUrl: String = "",
    @Json(name = "archived")
    val archived: Boolean = false,
    @Json(name = "assignees_url")
    @WhenNullReturnEmpty
    val assigneesUrl: String = "",
    @Json(name = "blobs_url")
    @WhenNullReturnEmpty
    val blobsUrl: String = "",
    @Json(name = "branches_url")
    @WhenNullReturnEmpty
    val branchesUrl: String = "",
    @Json(name = "clone_url")
    @WhenNullReturnEmpty
    val cloneUrl: String = "",
    @Json(name = "collaborators_url")
    @WhenNullReturnEmpty
    val collaboratorsUrl: String = "",
    @Json(name = "comments_url")
    @WhenNullReturnEmpty
    val commentsUrl: String = "",
    @Json(name = "commits_url")
    @WhenNullReturnEmpty
    val commitsUrl: String = "",
    @Json(name = "compare_url")
    @WhenNullReturnEmpty
    val compareUrl: String = "",
    @Json(name = "contents_url")
    @WhenNullReturnEmpty
    val contentsUrl: String = "",
    @Json(name = "contributors_url")
    @WhenNullReturnEmpty
    val contributorsUrl: String = "",
    @Json(name = "created_at")
    @WhenNullReturnEmpty
    val createdAt: String = "",
    @Json(name = "default_branch")
    @WhenNullReturnEmpty
    val defaultBranch: String = "",
    @Json(name = "deployments_url")
    @WhenNullReturnEmpty
    val deploymentsUrl: String = "",
    @Json(name = "description")
    @WhenNullReturnEmpty
    val description: String = "",
    @Json(name = "disabled")
    val disabled: Boolean = false,
    @Json(name = "downloads_url")
    @WhenNullReturnEmpty
    val downloadsUrl: String = "",
    @Json(name = "events_url")
    @WhenNullReturnEmpty
    val eventsUrl: String = "",
    @Json(name = "fork")
    val fork: Boolean = false,
    @Json(name = "forks")
    val forks: Int = 0,
    @Json(name = "forks_count")
    val forksCount: Int = 0,
    @Json(name = "forks_url")
    @WhenNullReturnEmpty
    val forksUrl: String = "",
    @Json(name = "full_name")
    @WhenNullReturnEmpty
    val fullName: String = "",
    @Json(name = "git_commits_url")
    @WhenNullReturnEmpty
    val gitCommitsUrl: String = "",
    @Json(name = "git_refs_url")
    @WhenNullReturnEmpty
    val gitRefsUrl: String = "",
    @Json(name = "git_tags_url")
    @WhenNullReturnEmpty
    val gitTagsUrl: String = "",
    @Json(name = "git_url")
    @WhenNullReturnEmpty
    val gitUrl: String = "",
    @Json(name = "has_downloads")
    val hasDownloads: Boolean = false,
    @Json(name = "has_issues")
    val hasIssues: Boolean = false,
    @Json(name = "has_pages")
    val hasPages: Boolean = false,
    @Json(name = "has_projects")
    val hasProjects: Boolean = false,
    @Json(name = "has_wiki")
    val hasWiki: Boolean = false,
    @Json(name = "homepage")
    @WhenNullReturnEmpty
    val homepage: String = "",
    @Json(name = "hooks_url")
    @WhenNullReturnEmpty
    val hooksUrl: String = "",
    @Json(name = "html_url")
    @WhenNullReturnEmpty
    val htmlUrl: String = "",
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "issue_comment_url")
    @WhenNullReturnEmpty
    val issueCommentUrl: String = "",
    @Json(name = "issue_events_url")
    @WhenNullReturnEmpty
    val issueEventsUrl: String = "",
    @Json(name = "issues_url")
    @WhenNullReturnEmpty
    val issuesUrl: String = "",
    @Json(name = "keys_url")
    @WhenNullReturnEmpty
    val keysUrl: String = "",
    @Json(name = "labels_url")
    @WhenNullReturnEmpty
    val labelsUrl: String = "",
    @Json(name = "language")
    @WhenNullReturnEmpty
    val language: String = "",
    @Json(name = "languages_url")
    @WhenNullReturnEmpty
    val languagesUrl: String = "",
    @Json(name = "merges_url")
    @WhenNullReturnEmpty
    val mergesUrl: String = "",
    @Json(name = "milestones_url")
    @WhenNullReturnEmpty
    val milestonesUrl: String = "",
    @Json(name = "mirror_url")
    @WhenNullReturnEmpty
    val mirrorUrl: String? = "",
    @Json(name = "name")
    @WhenNullReturnEmpty
    val name: String = "",
    @Json(name = "node_id")
    @WhenNullReturnEmpty
    val nodeId: String = "",
    @Json(name = "notifications_url")
    @WhenNullReturnEmpty
    val notificationsUrl: String = "",
    @Json(name = "open_issues")
    val openIssues: Int = 0,
    @Json(name = "open_issues_count")
    val openIssuesCount: Int = 0,
    @Json(name = "owner")
    val owner: Owner = Owner(),
    @Json(name = "permissions")
    val permissions: Permissions = Permissions(),
    @Json(name = "private")
    val `private`: Boolean = false,
    @Json(name = "pulls_url")
    @WhenNullReturnEmpty
    val pullsUrl: String = "",
    @Json(name = "pushed_at")
    @WhenNullReturnEmpty
    val pushedAt: String = "",
    @Json(name = "releases_url")
    @WhenNullReturnEmpty
    val releasesUrl: String = "",
    @Json(name = "size")
    val size: Int = 0,
    @Json(name = "ssh_url")
    @WhenNullReturnEmpty
    val sshUrl: String = "",
    @Json(name = "stargazers_count")
    val stargazersCount: Int = 0,
    @Json(name = "stargazers_url")
    @WhenNullReturnEmpty
    val stargazersUrl: String = "",
    @Json(name = "statuses_url")
    @WhenNullReturnEmpty
    val statusesUrl: String = "",
    @Json(name = "subscribers_url")
    @WhenNullReturnEmpty
    val subscribersUrl: String = "",
    @Json(name = "subscription_url")
    @WhenNullReturnEmpty
    val subscriptionUrl: String = "",
    @Json(name = "svn_url")
    @WhenNullReturnEmpty
    val svnUrl: String = "",
    @Json(name = "tags_url")
    @WhenNullReturnEmpty
    val tagsUrl: String = "",
    @Json(name = "teams_url")
    @WhenNullReturnEmpty
    val teamsUrl: String = "",
    @Json(name = "trees_url")
    @WhenNullReturnEmpty
    val treesUrl: String = "",
    @Json(name = "updated_at")
    @WhenNullReturnEmpty
    val updatedAt: String = "",
    @Json(name = "url")
    @WhenNullReturnEmpty
    val url: String = "",
    @Json(name = "watchers")
    val watchers: Int = 0,
    @Json(name = "watchers_count")
    val watchersCount: Int = 0
)

@JsonClass(generateAdapter = true)
data class Owner(
    @Json(name = "avatar_url")
    @WhenNullReturnEmpty
    val avatarUrl: String = "",
    @Json(name = "events_url")
    @WhenNullReturnEmpty
    val eventsUrl: String = "",
    @Json(name = "followers_url")
    @WhenNullReturnEmpty
    val followersUrl: String = "",
    @Json(name = "following_url")
    @WhenNullReturnEmpty
    val followingUrl: String = "",
    @Json(name = "gists_url")
    @WhenNullReturnEmpty
    val gistsUrl: String = "",
    @Json(name = "gravatar_id")
    @WhenNullReturnEmpty
    val gravatarId: String = "",
    @Json(name = "html_url")
    @WhenNullReturnEmpty
    val htmlUrl: String = "",
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "login")
    @WhenNullReturnEmpty
    val login: String = "",
    @Json(name = "node_id")
    @WhenNullReturnEmpty
    val nodeId: String = "",
    @Json(name = "organizations_url")
    @WhenNullReturnEmpty
    val organizationsUrl: String = "",
    @Json(name = "received_events_url")
    @WhenNullReturnEmpty
    val receivedEventsUrl: String = "",
    @Json(name = "repos_url")
    @WhenNullReturnEmpty
    val reposUrl: String = "",
    @Json(name = "site_admin")
    val siteAdmin: Boolean = false,
    @Json(name = "starred_url")
    @WhenNullReturnEmpty
    val starredUrl: String = "",
    @Json(name = "subscriptions_url")
    @WhenNullReturnEmpty
    val subscriptionsUrl: String = "",
    @Json(name = "type")
    @WhenNullReturnEmpty
    val type: String = "",
    @Json(name = "url")
    @WhenNullReturnEmpty
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class Permissions(
    @Json(name = "admin")
    val admin: Boolean = false,
    @Json(name = "pull")
    val pull: Boolean = false,
    @Json(name = "push")
    val push: Boolean = false
)