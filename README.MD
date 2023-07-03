# The API for booking and find industries

Методы описаны в swagger.yml

## Run
1) you need docker

2) be in directory repository

Example: C:\projects\sun>

3) docker-compose build

4) docker-compose up

## Examples of use:

1. GET "GetIndustryByParam". Return industry by parameters. Also can return all industries, if no parameters

Curl request for cmd Windows

Input: curl "http://localhost:8080/industry?city=Nizhnevartovsk&street=Lenina&home=25&building=A&target=sell&area=230&aim=talking"

Output: [{"id":22,"addressId":14,"personId":3,"city":"Nizhnevartovsk","street":"Lenina","home":25,"building":"A","firstName":"Vasiliy","lastName":"Sidorov","phoneNumber":88005553535,"target":"sell","area":200,"aim":"talking"}]

2. GET "getIndustryById". Return industry by id

Curl request for cmd Windows

Input: curl "http://localhost:8080/industry/23"

Output: {"id":23,"addressId":15,"personId":5,"city":"Samara","street":"Lenina","home":25,"building":"A","firstName":"Lena","lastName":"Sidorova","phoneNumber":88006663536,"target":"sell","area":250,"aim":"talking"}

3. CREATE "createIndustry"

Curl request for cmd Windows. Return created industry

Input: curl "http://localhost:8080/industry" -H "Content-Type: application/json" -d "{\"city\": \"Mosco\",\"street\": \"Leni\",\"home\": 53,\"building\": \"k\",\"firstName\": \"Vasiy\",\"lastName\": \"Sidorov\",\"phoneNumber\": 88005553535,\"target\": \"sell\",\"area\": 200,\"aim\": \"working\"}"

Output: {"id":2,"addressId":2,"personId":1,"city":"Mosco","street":"Leni","home":53,"building":"k","firstName":"Vasiy","lastName":"Sidorov","phoneNumber":88005553535,"target":"sell","area":200,"aim":"working"}

4. DELETE "deleteIndustryById"

Curl request for cmd Windows

Input: curl -X DELETE "http://localhost:8080/industry/4"

Output: {"id":4,"addressId":2,"personId":2,"city":"Dolgon","street":"FDG","home":53,"building":"A","firstName":"Vas","lastName":"Sidorov","phoneNumber":88005553535,"target":"sell","area":200,"aim":"singing"}

5. GET "getAllPerson". Return all people

Curl request for cmd Windows

Input: curl "http://localhost:8080/person"

Output: [{"id":1,"firstName":"Vasil","lastName":"Sidorov","phoneNumber":88005553535},{"id":2,"firstName":"Vas","lastName":"Sidorov","phoneNumber":88005553535},{"id":3,"firstName":"Vasiliy","lastName":"Sidorov","phoneNumber":88005553535},{"id":4,"firstName":"FFFFFFFFFFF","lastName":"FFFFFFFFFFF","phoneNumber":333333333},{"id":5,"firstName":"Lena","lastName":"Sidorova","phoneNumber":88006663536}]

6. GET "getAllAddress". Return All addresses

Curl request for cmd Windows

Input: curl "http://localhost:8080/address"

Output: [{"id":3,"city":"Dolgonuii","street":"FDG","home":53,"building":"A"},{"id":4,"city":"Dolgonuii","street":"FDjkG","home":53,"building":"A"},{"id":5,"city":"Dolgonuijki","street":"FDjkG","home":53,"building":"A"},{"id":6,"city":"Moscow","street":"Lenina","home":53,"building":"A"},{"id":7,"city":"Moscow","street":"Lenina","home":53,"building":""},{"id":12,"city":"Moscow","street":"Lenin","home":53,"building":"A"},{"id":13,"city":"Moscow","street":"Leni","home":53,"building":"A"},{"id":14,"city":"Nizhnevartovsk","street":"Lenina","home":25,"building":"A"},{"id":15,"city":"Samara","street":"Lenina","home":25,"building":"A"},{"id":16,"city":"Volgograd","street":"Lenina","home":25,"building":"A"}]