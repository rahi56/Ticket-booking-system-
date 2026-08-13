-- Enterprise Express Ticket Booking System — H2 Schema
-- All tables managed via schema.sql; JPA ddl-auto=none

-- Users table (extends BaseEntity: created_at, updated_at)
CREATE TABLE IF NOT EXISTS app_users (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name  VARCHAR(100)  NOT NULL,
    last_name   VARCHAR(100)  NOT NULL,
    email       VARCHAR(150)  NOT NULL UNIQUE,
    password    VARCHAR(255)  NOT NULL,
    phone       VARCHAR(20),
    role        VARCHAR(20)   NOT NULL,
    created_at  TIMESTAMP,
    updated_at  TIMESTAMP
);

-- Operator table (no BaseEntity — no audit fields)
CREATE TABLE IF NOT EXISTS operator (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR(255) NOT NULL UNIQUE,
    email           VARCHAR(255) NOT NULL UNIQUE,
    phone           VARCHAR(255) NOT NULL UNIQUE,
    address         VARCHAR(255),
    license_number  VARCHAR(255),
    active          BOOLEAN      NOT NULL DEFAULT TRUE
);

-- Route table (no BaseEntity — no audit fields)
CREATE TABLE IF NOT EXISTS route (
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    source              VARCHAR(255) NOT NULL,
    destination         VARCHAR(255) NOT NULL,
    distance            DOUBLE       NOT NULL,
    estimated_duration  INT          NOT NULL
);

-- Bus table (no BaseEntity — no audit fields)
CREATE TABLE IF NOT EXISTS bus (
    id                   BIGINT AUTO_INCREMENT PRIMARY KEY,
    bus_number           VARCHAR(255) NOT NULL,
    registration_number  VARCHAR(255) NOT NULL,
    bus_type             VARCHAR(255),
    total_seats          INT          NOT NULL,
    operator_id          BIGINT,
    FOREIGN KEY (operator_id) REFERENCES operator(id)
);

-- Trip table (no BaseEntity — no audit fields)
CREATE TABLE IF NOT EXISTS trip (
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    bus_id           BIGINT,
    route_id         BIGINT,
    journey_date     DATE      NOT NULL,
    departure_time   TIME      NOT NULL,
    arrival_time     TIME      NOT NULL,
    fare             DOUBLE    NOT NULL,
    available_seats  INT       NOT NULL,
    FOREIGN KEY (bus_id)   REFERENCES bus(id),
    FOREIGN KEY (route_id) REFERENCES route(id)
);

-- Booking table (no BaseEntity — no audit fields)
CREATE TABLE IF NOT EXISTS booking (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id      BIGINT,
    trip_id      BIGINT,
    seat_number  INT    NOT NULL,
    total_fare   DOUBLE,
    status       VARCHAR(50),
    FOREIGN KEY (user_id)  REFERENCES app_users(id),
    FOREIGN KEY (trip_id)  REFERENCES trip(id)
);
