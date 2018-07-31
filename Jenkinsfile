
pipeline {
    
    agent {    	
    	label 'Slave_Induccion'      
    }

    //Opciones especificas de Pipeline dentro del Pipeline
    options { 
        //Mantener artefactos y salida de consola para el # especÃ­fico de ejecucionesrecientes del Pipeline.
        buildDiscarder(logRotator(numToKeepStr: '3'))
        //No permitir ejecuciones concurrentes de PipelinedisableConcurrentBuilds()  
    }

    //Una seccion que define las herramientas para autoinstalar y poner en la PATH  tools 
    tools {   
        jdk 'JDK8_Centos' //Preinstalada en la Configuracion del Master    
        gradle 'Gradle4.5_Centos' //Preinstalada en la Configuracion del Master  
    }

    stages {
        
        stage('Build') {      
            steps {       
                echo "------------>Build<------------"
                sh 'gradle clean'                   
                sh 'gradle build --stacktrace --scan'
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
