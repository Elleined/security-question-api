# Run with docker
## dev
1. Run the MySQL Server Container
    - Go to deployment > dev folder
    - Supply the right environment variables in .env first!
    - Run this command
```
docker compose up -d
```
2. Run the local Security Question API project
   - Supply the right environment variables in .env.development (deployment/dev) first
   - Add it to IDE environment variables

## prod
1. Run the Project
    - Go to deployment > prod folder
    - Supply the right environment variables in .env first!
    - Run this command
```
docker compose up -d
```