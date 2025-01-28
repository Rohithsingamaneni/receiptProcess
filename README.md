# Fetch challenge

This is a Spring Boot application containerized using Docker. The application serves and exposes RESTful APIs for submitting receipt information and calculating points per the challenge mentioned.

## How to run this application
1. Install Docker:.
2. Clone this Repository:
3. Build the Docker Image and run:
    docker build -t receipt-processor .
    docker run -p 8080:8080 receipt-processor
    http://localhost:8080-- to access the application

## Testing Endpoints

http://localhost:8080/receipts/process--POST
JSON: {
  "retailer": "M&M Corner Market",
  "purchaseDate": "2022-03-20",
  "purchaseTime": "14:33",
  "items": [
    {
      "shortDescription": "Gatorade",
      "price": "2.25"
    },{
      "shortDescription": "Gatorade",
      "price": "2.25"
    },{
      "shortDescription": "Gatorade",
      "price": "2.25"
    },{
      "shortDescription": "Gatorade",
      "price": "2.25"
    }
  ],
  "total": "9.00"
}

output: {
  "id": "7fb1377b-b223-49d9-a31a-5a02701dd310"
}

http://localhost:8080/receipts/7fb1377b-b223-49d9-a31a-5a02701dd310/points
output: {
    "points": 109
}



