def updateCheckStatus(String commitId, String status, String description, String redirect, String check) {
    withCredentials([usernamePassword(credentialsId: 'grsky360-token', passwordVariable: 'password', usernameVariable: '')]) {
        sh '''
        set +x

        curl -X PUT https://api.github.com/repos/iliocz/checks/branches/master/protection/required_status_checks/contexts \
             -u ":$password" \
             -H "Content-Type: application/json" \
             -d '[
                ''' + check + '''
             ]'

        curl -X POST "https://api.github.com/repos/iliocz/checks/statuses/''' + commitId + '''" \
             -u ":$password" \
             -H 'Content-Type: application/json' \
             -d '{
                "state": "''' + status + '''",
                "target_url": "''' + redirect + '''",
                "description": "''' + description + '''",
                "context": "''' + check + '''"
             }'
        '''
    }
}

node {
    def payload = readJSON text: "${env.payload}"
    GIT_COMMIT = "${payload.after}"
    GIT_BRANCH = "${payload.ref}".replaceAll("refs/heads/", "")

    stage('build') {
        git([
            url: 'git@github.com:iliocz/checks.git',
            branch: GIT_BRANCH,
            credentialsId: 'grsky360-ssh'
        ])
    }
    stage('ci/check') {
        //>> ci/check
        updateCheckStatus(GIT_COMMIT, 'pending', 'pending build', env.BUILD_URL, 'ci/check')
    }
    stage('test') {
        try {
            sh "./gradlew test --no-daemon"
            updateCheckStatus(GIT_COMMIT, 'success', 'build success', env.BUILD_URL, 'ci/check')
        } catch (e) {
            updateCheckStatus(GIT_COMMIT, 'failure', 'build failure', env.BUILD_URL, 'ci/check')
            throw e
        }
    }
}
