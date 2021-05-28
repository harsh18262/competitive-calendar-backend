# competitive-calendar-api
The Backend/Internal API microservice for competitve calendar project

## API Endpoints:
+ **/getcontests**  - get all the available contests
   - Method : `GET`

+  **/gettime** - get time at which database was last updated(this is currently in IST)
    - Method : `GET`


+  **/deleteall** - can be used to delete all the entries in the database.To be used with caution.(it also has a confirmation parameter)
    - parameter : `confirmation=[string(yes or no)]`
    - Method : `DELETE`

+ **/delete** - can be used to delete an entry using it's id.
    - parameter : `id=[integer]`
    - Method : `DELETE`

+ **/update** - used to update the database with latest contests
    - parameter : `platform=[string]`
    - Method : `POST`

    - **Note** -> in update the platform parameter can be codechef, hackerrank, hackerearth , codeforces or all



