## Quick text comparison checker
This project is creating as a part of engineering project: 'Components for detection of similar text content dedicated to Polish language'.

### Requirements
- JDK 1.8 or greater
- Moven 3

### Installation
```
TODO
```

### Deployment
```
TODO
```

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

#### Get single article
Request:
```
Method:
GET

URL:
http://localhost:8060/core/article/{articleId}

for example
http://localhost:8060/core/article/5d9f3abf33e80051a4f4f6c4
```

Response:
```
{
    "_id": "5d9f3abf33e80051a4f4f6c4",
    "title": "Single text provided",
    "author": "guest",
    "description": "Single text to compare",
    "content": "..."
}
```

#### Get all articles
```
Method:
GET

URL:
http://localhost:8060/core/article
```

Response:
```
[
    {
        "_id": "5d9f3abf33e80051a4f4f6c4",
        "title": "Single text provided",
        "author": "guest",
        "description": "Single text to compare",
        "content": "..."
    },
    {
        "_id": "5dacba88c1e9f72e009ef9c7",
        "title": "Single text provided",
        "author": "guest",
        "description": "Single text to compare",
        "content": "..."
    },
    ...
]
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

Response:
```
[
    {
        "id": 84191,
        "percentage": 44,
        "firstArticleID": "5db094fe7613c028243309b1",
        "secondArticleID": "5d9f3abf33e80051a4f4f6c4",
        "firstArticleShortContent": "text",
        "secondArticleShortContent": "..."
    },
    {
        "id": 79964,
        "percentage": 21,
        "firstArticleID": "5db094fe7613c028243309b1",
        "secondArticleID": "5d9f3aca33e80051a4f4f6c8",
        "firstArticleShortContent": "...",
        "secondArticleShortContent": "..."
    },
    {
        "id": 84069,
        "percentage": 37,
        "firstArticleID": "5db094fe7613c028243309b1",
        "secondArticleID": "5d9f3ae933e80051a4f4f6d2",
        "firstArticleShortContent": "...",
        "secondArticleShortContent": "..."
    },
    ...
]
```
#### Compare two articles
Example request
```
Method:
GET

URL:
http://localhost:8060/core/compare/two
    ?articleId1={articleId1}
    &articleId2={articleId2}

for example:
http://localhost:8060/core/compare/two
    ?articleId1=5da6364203cab63aa0af956a
    &articleId2=5d9f3b0f33e80051a4f4f6df
```

Response:
```
{
    "id": 71461,
    "percentage": 34,
    "firstArticleContent": "...",
    "secondArticleContent": "...",
    "suspiciousWords": [
        {
            "firstArticleStart": 0,
            "firstArticleEnd": 476,
            "secondArticleStart": 1832,
            "secondArticleEnd": 2308
        }
    ]
}
```

