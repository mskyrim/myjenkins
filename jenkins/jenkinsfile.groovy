pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'feature/oren/CONTINUITY-34030-spinteg-transaction', credentialsId: 'bitbucket-ssh-agent-access-key', url: 'ssh://git@dev-bitbucket.fircosoft.net/cty/continuity.git'
            }

        }
        stage('Build') {
            steps {
                // Run Maven on a Unix agent.
                sh "cd orchestrator && ./gradlew distXTestsOREN"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    archiveArtifacts 'orchestrator/apps/orchestrator/build/dist/*.zip'
                }
            }
        }
    }
}
