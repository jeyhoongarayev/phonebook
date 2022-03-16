# phonebook

For Jenkins configurations

    Firstly, jenkins should be installed locally or  docker image should be pulled(in this case you need to make Docker in Docker configuration)
    After that on Jenkins server the following plugins should be installed: Maven Integration, Pipeline utility steps, Docker Plugin, SonarScanner plugin, Kubernetes plugin and etc.(You might add more if it is needed in your Operation System or Computer)
    After these create new pipeline job on Jenkins. You should choose "Pipeline script from SCM" and add your repository url. Since the repository is public, you don't need to add credentials.
    Then go to Global Tool Configuration and add the following :  maven installer, sonarqube installer and docker installer.
    After that, you should go to Configure System. After that add sonarqube url and sonarqube project token as a secret text
    Then you should add your docker hub password as a secret text

For Kubernetes configurations

    Firstly, install k8s locally or just pull docker image and run k8s container
    Then apply k8s files in following order(The same order goes for backend and frontend):

    kubectl apply -f namespace.yml
    kubectl apply -f config.yml
    kubectl apply -f deployment.yml
    kubectl apply -f service.yml
    kubectl apply -f autoscaling.yml
