CREATE TABLE request_log(
                            id              BIGSERIAL PRIMARY KEY NOT NULL,
                            create_date     TIMESTAMP NOT NULL,
                            update_date     TIMESTAMP NOT NULL,
                            active          BOOLEAN NOT NULL,
                            request         TEXT,
                            response        TEXT,
                            path            TEXT,
                            code            INT,
                            duration        BIGINT
);