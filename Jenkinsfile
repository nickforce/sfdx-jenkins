#!groovy

node {


	
	echo 'print java version'
	testRun = command "java -version"
	testRun2 = command "mvn --version"


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
	else if(current_build_branch == 'qa') {
		SF_AUTH_URL = env.SFDX_AUTH_URL_qa
	}
	else if(current_build_branch == 'uat') {
		SF_AUTH_URL = env.SFDX_AUTH_URL_uat
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
	// Check out code from source control git
	// -------------------------------------------------------------------------

   	stage('checkout source') {
		echo 'Pulling...' + env.BRANCH_NAME
		checkout scm
	}
	// this variable must match name of Maven Global Tool Plugin in Jenkins
	def mvn_version = 'MAVEN_HOME'
	withEnv( ["PATH+MAVEN=${tool mvn_version}/bin"] ) {   
		dir("selenium/Maven_Automation_Project") {
			echo 'selenium dir'
			sh 'pwd'
			sh 'ls -ll'
			sh 'mvn clean install'
		}
  	}

	dir("selenium/Maven_Automation_Project/target") {
		echo 'selenium target dir'
		sh 'pwd'
		sh 'ls -ll'
		sh 'java -jar com.test-1.0-SNAPSHOT.jar'
	}

	echo "auth URL below ##############################"
	echo SF_AUTH_URL

	echo "env.BRANCH_NAME below ##############################"
	echo env.BRANCH_NAME

	writeFile file: 'authjenkinsci.txt', text: SF_AUTH_URL
	sh 'ls -l authjenkinsci.txt'
	sh 'cat authjenkinsci.txt'
	
	
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
	rc4 = command "${toolbelt}/sfdx force:source:deploy -c --wait 10 --sourcepath ${DEPLOYDIR} --testlevel ${TEST_LEVEL} -u targetEnvironment"
	if (rc4 != 0) {
		error 'There was an issue deploying. Check ORG deployment status page for details'
	}


	// run tests
	rc3 = command "${toolbelt}/sfdx force:apex:test:run -u targetEnvironment --wait 10"
	if (rc3 != 0) {
		error 'There was an issue running apex tests. Check ORG for details'
	}
	
	// check for success deploy/build to this point - 
	// launch Selenium scripts. >
}

def command(script) {
    if (isUnix()) {
        return sh(returnStatus: true, script: script);
    } else {
		return bat(returnStatus: true, script: script);
    }
}














	
