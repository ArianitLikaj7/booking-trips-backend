CREATE TABLE favorites (
                           id SERIAL PRIMARY KEY,
                           user_id BIGINT NOT NULL,
                           trip_id BIGINT NOT NULL,
                           created_at TIMESTAMP DEFAULT NOW(),
                           UNIQUE(user_id, trip_id),
                           FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                           FOREIGN KEY (trip_id) REFERENCES trips(id) ON DELETE CASCADE
);
