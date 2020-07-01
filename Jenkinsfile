#!groovy

node {

	echo 'test print env variables'
	echo sh(returnStdout: true, script: 'env')
	//
	echo "${BUILD_URL}/consoleText"
	//
	def current_build_branch = env.BRANCH_NAME
	echo 'CURRENT BUILD BRANCH NAME'
	echo current_build_branch
	

	def SF_AUTH_URL
	if(current_build_branch == 'master') {
		SF_AUTH_URL = env.SFDX_AUTH_URL
	}
	else if(current_build_branch == 'dev') {
		SF_AUTH_URL = env.SFDX_AUTH_URL_dev
	}
	else { // PR the current branch will be teh name of the PR

	}
	echo SF_AUTH_URL



	def DEPLOYDIR='/var/lib/jenkins/workspace/new_pipeline_master/force-app/main/default'
	echo DEPLOYDIR
	echo pwd
	sh 'ls -ll /var/lib/jenkins/workspace/new_pipeline_master'
	def wk1 = env.WORKSPACE
	echo wk1

	def TEST_LEVEL='RunLocalTests'
	def SF_INSTANCE_URL=env.SF_INSTANCE_URL ?: "https://login.salesforce.com"
	echo SF_INSTANCE_URL

	def toolbelt = tool 'toolbelt'
	echo toolbelt
	// -------------------------------------------------------------------------
	// Check out code from source control git --
	// -------------------------------------------------------------------------

   	stage('checkout source') {
		echo 'Pulling...' + env.BRANCH_NAME
		checkout scm
	}

	echo "auth URL below ##############################"
	echo SF_AUTH_URL

	echo "env.BRANCH_NAME below ##############################"
	echo env.BRANCH_NAME

	writeFile file: 'authjenkinsci.txt', text: SF_AUTH_URL
	sh 'ls -l authjenkinsci.txt'
	sh 'cat authjenkinsci.txt'
	
	/*
	rc = command "${toolbelt}/sfdx --help"
	if (rc != 0) {
		error 'SFDX CLI Jenkins tool initalize failed.'
	}

	

	// auth
	rc2 = command "${toolbelt}/sfdx force:auth:sfdxurl:store -f authjenkinsci.txt -a targetEnvironment"
	if (rc2 != 0) {
		error 'SFDX CLI Authorization to target env has failed.'
	}
	
	// deploy full build  --dev-debug
	rc4 = command "${toolbelt}/sfdx force:source:deploy --wait 10 --sourcepath ${DEPLOYDIR} --testlevel ${TEST_LEVEL} -u targetEnvironment"
	if (rc4 != 0) {
		error 'There was an issue deploying. Check ORG deployment status page for details'
	}


	// run tests
	rc3 = command "${toolbelt}/sfdx force:apex:test:run -u targetEnvironment --wait 10"
	if (rc3 != 0) {
		error 'There was an issue running apex tests. Check ORG for details'
	}
	*/
}

def command(script) {
    if (isUnix()) {
        return sh(returnStatus: true, script: script);
    } else {
		return bat(returnStatus: true, script: script);
    }
}














	
