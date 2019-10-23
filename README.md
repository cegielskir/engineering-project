## Quick text comparison checker
This project is creating as a part of engineering project: 'Components for detection of similar text content dedicated to Polish language'.

### Requirements
- JDK 1.8 or greater
- Moven 3

### Service API requests
#### Adding single article
Example request
```
Method: 
POST

URL:
http://localhost:8060/core/article

Body:
{
    "title": "Single text provided",
    "author": "guest",
    "description": "Single text to compare",
    "content": "text"
}
```
Response:
```
HTTP STATUS:
200 OK

Body:
{
    "_id": "5db094fe7613c028243309b1",
    "title": "Single text provided",
    "author": "guest",
    "description": "Single text to compare",
    "content": "text",
    "date": null,
    "url": null,
    "numberOfViews": null,
    "downloadTime": null
}
```


#### Compare single article with many articles available in database
Example request:
```
Method:
GET

URL:
http://localhost:8060/core/compare/{articleId}?metric={choosenMetric}

for example
http://localhost:8060/core/compare/5db094fe7613c028243309b1?metric=Dice


```