
pipeline {
    
    agent {    	
    	docker  { image 'mongo' }          
    }

    //Opciones especÃ­ficas de Pipeline dentro del Pipeline
    options { 
        //Mantener artefactos y salida de consola para el # especÃ­fico de ejecucionesrecientes del Pipeline.
        buildDiscarder(logRotator(numToKeepStr: '3'))
        //No permitir ejecuciones concurrentes de PipelinedisableConcurrentBuilds()  
    }

    //Una secciÃ³n que define las herramientas para â€œautoinstalarâ€� y poner en la PATH  tools 
    tools {   
        jdk 'JDK8_Centos' //Preinstalada en la ConfiguraciÃ³n del Master    
        gradle 'Gradle4.5_Centos' //Preinstalada en la ConfiguraciÃ³n del Master  
    }

    stages {

        stage('Checkout') {
            steps { 
                echo "------------>Checkout<------------"
                checkout([$class: 'GitSCM', branches: [[name: '*/master', name: '*/develop']],
                        doGenerateSubmoduleConfigurations: false, extensions: [], 
                        gitTool:'Git_Centos',
                        submoduleCfg: [], 
                        userRemoteConfigs: [[credentialsId:'GitHub_davidblj', url:'https://github.com/davidblj/Parqueadero']]])
            	sh 'gradle clean'
            } 
        }
        
        stage('Build') {      
            steps {       
                echo "------------>Build<------------"                   
                sh 'gradle build'
            }
        }

        stage('Unit Tests') {      
            steps {
                echo "------------>Unit Tests<------------" 
                sh 'gradle test'     
            }    
        }  

        stage('Integration Tests') {      
            steps {        
                echo "------------>Integration Tests<------------"      
            }   
        }                 

        stage('Static Code Analysis') {      
            steps {        
                echo '------------>Anlisis de codigo estatico<------------'        
                withSonarQubeEnv('Sonar') { 
                    sh "${tool name: 'SonarScanner', type:'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner"
                }
            }
        }
       
    }

    post {    
        always { 
            echo 'This will always run'    
        }            
        success {   
            echo 'This will run only if successful'
            junit '**/build/test-results/test/*.xml'    
        }    
        failure {      
            echo 'This will run only if failed'    
        }    
        unstable {      
            echo 'This will run only if the run was marked as unstable'
        }    
        changed {
            echo 'This will run only if the state of the Pipeline has changed'      
            echo 'For example, if the Pipeline was previously failing but is now successful'    
        }  
    }
}
