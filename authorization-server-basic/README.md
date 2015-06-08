curl commands to try:

#1 successfully obtain a token

curl -X POST -H "Authorization: Basic bXktY2xpZW50LXdpdGgtc2VjcmV0OnNlY3JldA==" -H "Cache-Control: no-cache" -H "Content-Type: application/x-www-form-urlencoded" -d 'grant_type=client_credentials&scope=write' http://localhost:8080/oauth/token

#2 use invalid credentials to obtain a token

curl -X POST -H "Authorization: Basic bXktY2xpZW50LXdpdGgtc2VjcmV0Og==" -H "Cache-Control: no-cache" -H "Content-Type: application/x-www-form-urlencoded" -d 'grant_type=client_credentials&scope=write' http://localhost:8080/oauth/token

