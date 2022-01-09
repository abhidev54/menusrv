# menusrv
# This service is used to upload restaurant info, upload menu, sync full menu and update stock.

# Build process using docker

1- go to project folder and run command "mvn clean install"<br />

2- docker build -t takeaway/menusrv .<br />

3- docker run -p 8080:8080 takeaway/menusrv<br />

** Start service using single command (Recommended) <br />
4- docker-compose up