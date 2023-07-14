package com.mdoroz.rest.webservices.restfulwebservices.person;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    //VERSIONING BY URL

    @GetMapping("/v1/person")
    public PersonV1 getFristVersionOfPerson(){
        return new PersonV1("Michal Doroz");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionOfPerson(){
        return new PersonV2(new Name("Michal", "Doroz"));
    }

    //VERSIONING BY REQUEST PARAMETERS IN URL

    @GetMapping(path = "/person", params = "v1")
    public PersonV1 getFristVersionOfPersonRequestParameter(){
        return new PersonV1("Michal Doroz");
    }

    @GetMapping(path = "/person", params = "v2")
    public PersonV2 getSecondVersionOfPersonRequestParameter(){
        return new PersonV2(new Name("Michal", "Doroz"));
    }

    //VERSIONING BY HEADERS

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getFristVersionOfPersonRequestHeader(){
        return new PersonV1("Michal Doroz");
    }

    @GetMapping(path = "/person", headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionOfPersonRequestHeader(){
        return new PersonV2(new Name("Michal", "Doroz"));
    }

    //MEDIA TYPE VERSIONING

    @GetMapping(path = "/person/accept", produces = "application/person-v1+json")
    public PersonV1 getFristVersionOfPersonAcceptHeader(){
        return new PersonV1("Michal Doroz");
    }

    @GetMapping(path = "/person/accept", produces = "application/person-v2+json")
    public PersonV2 getSecondVersionOfPersonAcceptHeader(){
        return new PersonV2(new Name("Michal", "Doroz"));
    }
}
