CREATE TABLE IF NOT EXISTS CARS
(
    ID          int AUTO_INCREMENT PRIMARY KEY,
    model        VARCHAR(255) ,
    Details        VARCHAR(255),
    status        VARCHAR(255),
    fuelType        VARCHAR(255),
    brand        VARCHAR(255) ,
    seatingCapacity        VARCHAR(255),
    mileage     int,
    carNumber   int NOT NULL
);