
pipeline {
    agent any
    stage('Checkout') {
        steps { 
            echo "------------>Checkout<------------"
            checkout([$class: 'GitSCM', branches: [[name: '*/master']],
                    doGenerateSubmoduleConfigurations: false, extensions: [], 
                    gitTool:'Git_Centos',
                    submoduleCfg: [], 
                    userRemoteConfigs: [[credentialsId:'GitHub_davidblj', url:'https://github.com/davidblj/Parqueadero']]])
        }
    }
}
