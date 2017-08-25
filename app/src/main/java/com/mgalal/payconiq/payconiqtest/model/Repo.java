package com.mgalal.payconiq.payconiqtest.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by fujitsu-lap on 25/08/2017.
 *
 * Simple class for modeling a repo:
 * Example Json format :
 * *********************************
          {
         "id": 3070104,
         "name": "abs.io",
         "full_name": "JakeWharton/abs.io",
         "owner": {
         "login": "JakeWharton",
         "id": 66577,
         "avatar_url": "https://avatars0.githubusercontent.com/u/66577?v=4",
         "gravatar_id": "",
         "url": "https://api.github.com/users/JakeWharton",
         "html_url": "https://github.com/JakeWharton",
         "followers_url": "https://api.github.com/users/JakeWharton/followers",
         "following_url": "https://api.github.com/users/JakeWharton/following{/other_user}",
         "gists_url": "https://api.github.com/users/JakeWharton/gists{/gist_id}",
         "starred_url": "https://api.github.com/users/JakeWharton/starred{/owner}{/repo}",
         "subscriptions_url": "https://api.github.com/users/JakeWharton/subscriptions",
         "organizations_url": "https://api.github.com/users/JakeWharton/orgs",
         "repos_url": "https://api.github.com/users/JakeWharton/repos",
         "events_url": "https://api.github.com/users/JakeWharton/events{/privacy}",
         "received_events_url": "https://api.github.com/users/JakeWharton/received_events",
         "type": "User",
         "site_admin": false
         },
         "private": false,
         "html_url": "https://github.com/JakeWharton/abs.io",
         "description": "Simple URL shortener for ActionBarSherlock using node.js and express.",
         "fork": false,
         "url": "https://api.github.com/repos/JakeWharton/abs.io",
         "forks_url": "https://api.github.com/repos/JakeWharton/abs.io/forks",
         "keys_url": "https://api.github.com/repos/JakeWharton/abs.io/keys{/key_id}",
         "collaborators_url": "https://api.github.com/repos/JakeWharton/abs.io/collaborators{/collaborator}",
         "teams_url": "https://api.github.com/repos/JakeWharton/abs.io/teams",
         "hooks_url": "https://api.github.com/repos/JakeWharton/abs.io/hooks",
         "issue_events_url": "https://api.github.com/repos/JakeWharton/abs.io/issues/events{/number}",
         "events_url": "https://api.github.com/repos/JakeWharton/abs.io/events",
         "assignees_url": "https://api.github.com/repos/JakeWharton/abs.io/assignees{/user}",
         "branches_url": "https://api.github.com/repos/JakeWharton/abs.io/branches{/branch}",
         "tags_url": "https://api.github.com/repos/JakeWharton/abs.io/tags",
         "blobs_url": "https://api.github.com/repos/JakeWharton/abs.io/git/blobs{/sha}",
         "git_tags_url": "https://api.github.com/repos/JakeWharton/abs.io/git/tags{/sha}",
         "git_refs_url": "https://api.github.com/repos/JakeWharton/abs.io/git/refs{/sha}",
         "trees_url": "https://api.github.com/repos/JakeWharton/abs.io/git/trees{/sha}",
         "statuses_url": "https://api.github.com/repos/JakeWharton/abs.io/statuses/{sha}",
         "languages_url": "https://api.github.com/repos/JakeWharton/abs.io/languages",
         "stargazers_url": "https://api.github.com/repos/JakeWharton/abs.io/stargazers",
         "contributors_url": "https://api.github.com/repos/JakeWharton/abs.io/contributors",
         "subscribers_url": "https://api.github.com/repos/JakeWharton/abs.io/subscribers",
         "subscription_url": "https://api.github.com/repos/JakeWharton/abs.io/subscription",
         "commits_url": "https://api.github.com/repos/JakeWharton/abs.io/commits{/sha}",
         "git_commits_url": "https://api.github.com/repos/JakeWharton/abs.io/git/commits{/sha}",
         "comments_url": "https://api.github.com/repos/JakeWharton/abs.io/comments{/number}",
         "issue_comment_url": "https://api.github.com/repos/JakeWharton/abs.io/issues/comments{/number}",
         "contents_url": "https://api.github.com/repos/JakeWharton/abs.io/contents/{+path}",
         "compare_url": "https://api.github.com/repos/JakeWharton/abs.io/compare/{base}...{head}",
         "merges_url": "https://api.github.com/repos/JakeWharton/abs.io/merges",
         "archive_url": "https://api.github.com/repos/JakeWharton/abs.io/{archive_format}{/ref}",
         "downloads_url": "https://api.github.com/repos/JakeWharton/abs.io/downloads",
         "issues_url": "https://api.github.com/repos/JakeWharton/abs.io/issues{/number}",
         "pulls_url": "https://api.github.com/repos/JakeWharton/abs.io/pulls{/number}",
         "milestones_url": "https://api.github.com/repos/JakeWharton/abs.io/milestones{/number}",
         "notifications_url": "https://api.github.com/repos/JakeWharton/abs.io/notifications{?since,all,participating}",
         "labels_url": "https://api.github.com/repos/JakeWharton/abs.io/labels{/name}",
         "releases_url": "https://api.github.com/repos/JakeWharton/abs.io/releases{/id}",
         "deployments_url": "https://api.github.com/repos/JakeWharton/abs.io/deployments",
         "created_at": "2011-12-29T18:02:34Z",
         "updated_at": "2017-08-09T14:39:21Z",
         "pushed_at": "2011-12-29T18:02:44Z",
         "git_url": "git://github.com/JakeWharton/abs.io.git",
         "ssh_url": "git@github.com:JakeWharton/abs.io.git",
         "clone_url": "https://github.com/JakeWharton/abs.io.git",
         "svn_url": "https://github.com/JakeWharton/abs.io",
         "homepage": "http://abs.io",
         "size": 108,
         "stargazers_count": 6,
         "watchers_count": 6,
         "language": "JavaScript",
         "has_issues": true,
         "has_projects": true,
         "has_downloads": true,
         "has_wiki": false,
         "has_pages": false,
         "forks_count": 1,
         "mirror_url": null,
         "open_issues_count": 0,
         "forks": 1,
         "open_issues": 0,
         "watchers": 6,
         "default_branch": "master"
         }


 also
 "message": "API rate limit exceeded for 41.42.117.27. (But here's the good news: Authenticated requests get a higher rate limit. Check out the documentation for more details.)",
 "documentation_url": "https://developer.github.com/v3/#rate-limiting"
 */

public class Repo extends RealmObject{

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isPrivateRepo() {
        return privateRepo;
    }

    public void setPrivateRepo(boolean privateRepo) {
        this.privateRepo = privateRepo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    private Owner owner;
    private String name;
    @SerializedName("full_name")
    private String fullName;
    @PrimaryKey
    private long id;
    @SerializedName("private")
    private boolean privateRepo;
    private String description;
    @SerializedName("html_url")
    private String url;


}
