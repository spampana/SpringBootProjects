Spring Boot DataRest build on top of the Spring Data, and it’s taking the advantages of Spring HATEOAS. 
We can say that Spring Boot Data Rest combines the Spring Data and Spring HATEOS to provide the hypermedia-based Restful front end.
<br/>
<br/>
we have used @Data and @NoArgsConstructor annotations to eliminate the boilerplate code like getters and setters.
<br/>
<br/>
The ItemRepository interface allows you to perform various CRUD operations on Item object. 
This repository gets CRUD operations from the PagingAndSortingRepository – it internally uses Spring Data Commons.

Apart from the basic CRUD operations, if we wanted to expose any domain related operations, you can always free to define your custom JPA 
operations or even use any JPQL operations inside the ItemRepository like below findByCategory().
<br/>
<br/>
@RepositoryRestResource creates HATEOAS service with Spring JPA and the operations will be exposed in HATEOAS format.

<br/>
<br/>

<b>Access application</b>
<img src="https://www.onlinetutorialspoint.com/wp-content/uploads/2019/05/Spring-Boot-Data-Rest-Example-2-min.png"/>
<br/>
You can observe, there were two endpoints available /items with three different options (page, size, sort) and /profile which provides the application metadata.
<br/>

<b>Currently, there are no items in our database, let’s create some of them.</b>
<img src="https://www.onlinetutorialspoint.com/wp-content/uploads/2019/05/Spring-Boot-Data-Rest-Example-4-min.png"/>
<br/>


<b>Getting all list of Items:</b>
<br/>
Now you can access all the available items using the http://localhost:8080/items/ endpoint.
<pre>
{
    "_embedded": {
        "items": [
            {
                "name": "Thinking in Java",
                "category": "Books",
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/items/1"
                    },
                    "item": {
                        "href": "http://localhost:8080/items/1"
                    }
                }
            },
            {
                "name": "Hibernate in Action",
                "category": "Books",
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/items/2"
                    },
                    "item": {
                        "href": "http://localhost:8080/items/2"
                    }
                }
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/items{?page,size,sort}",
            "templated": true
        },
        "profile": {
            "href": "http://localhost:8080/profile/items"
        },
        "search": {
            "href": "http://localhost:8080/items/search"
        }
    },
    "page": {
        "size": 20,
        "totalElements": 2,
        "totalPages": 1,
        "number": 0
    }
</pre>
<br/>
On the above output, we can observe that, along with the items list, there is one more /search endpoint available. It is used to call the custom endpoints. Let’s try to access our custom endpoint getItemByCategory();
<br/>


<b>http://localhost:8080/items/search/findByCategory?category=Books</b>
<img src="https://www.onlinetutorialspoint.com/wp-content/uploads/2019/05/Spring-Boot-DataRest-Example-5-min.png"/>
<br/>

