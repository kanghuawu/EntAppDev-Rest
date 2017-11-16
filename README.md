# EntAppDev-Rest

Grant privilege to database user account
1. Login as root
2. Create database cmpe275
3. Grant privilege to user account
````
> GRANT ALL ON cmpe275.* TO 'cmpe275'@'%' IDENTIFIED BY 'password';
```` 

Initialize lazy fetching session
* https://stackoverflow.com/questions/15359306/how-to-load-lazy-fetched-items-from-hibernate-jpa-in-my-controller