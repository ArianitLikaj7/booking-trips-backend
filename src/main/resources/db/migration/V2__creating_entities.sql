CREATE TABLE trips(
                      id SERIAL PRIMARY KEY ,
                      created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                      created_by INTEGER,
                      origin VARCHAR(255),
                      destination VARCHAR(255),
                      available_seats INTEGER,
                      total_seats INTEGER,
                      route VARCHAR(255),
                      price DECIMAL,
                      title VARCHAR,
                      description VARCHAR,
                      type_of_trip VARCHAR,
                      FOREIGN KEY (created_by) REFERENCES users(id)
);

CREATE TABLE reservations(
                             id SERIAL PRIMARY KEY ,
                             created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                             updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                             user_id INTEGER,
                             trip_id INTEGER,
                             seat_number INTEGER,
                             FOREIGN KEY (user_id) REFERENCES users(id),
                             FOREIGN KEY (trip_id) REFERENCES trips(id)
);