CREATE SEQUENCE IF NOT EXISTS app_user_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE app_user
(
    id        BIGINT       NOT NULL,
    username  VARCHAR(255) NOT NULL,
    lastname  VARCHAR(255) NOT NULL,
    firstname VARCHAR(255),
    country   VARCHAR(255),
    password  VARCHAR(255),
    role      VARCHAR(255),
    CONSTRAINT pk_app_user PRIMARY KEY (id)
);

CREATE TABLE event
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name     VARCHAR(255)                            NOT NULL,
    date     TIMESTAMP WITHOUT TIME ZONE,
    status   VARCHAR(255),
    venue_id BIGINT                                  NOT NULL,
    CONSTRAINT pk_event PRIMARY KEY (id)
);

CREATE TABLE event_seat
(
    id               BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    seat_id          BIGINT                                  NOT NULL,
    event_section_id BIGINT,
    status           VARCHAR(255)                            NOT NULL,
    ticket_id        BIGINT,
    CONSTRAINT pk_event_seat PRIMARY KEY (id)
);

CREATE TABLE event_section
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    section_id BIGINT                                  NOT NULL,
    event_id   BIGINT,
    price      DECIMAL,
    CONSTRAINT pk_event_section PRIMARY KEY (id)
);

CREATE TABLE seat
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    number     INTEGER                                 NOT NULL,
    row        VARCHAR(255)                            NOT NULL,
    section_id BIGINT,
    CONSTRAINT pk_seat PRIMARY KEY (id)
);

CREATE TABLE section
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name     VARCHAR(255)                            NOT NULL,
    venue_id BIGINT                                  NOT NULL,
    CONSTRAINT pk_section PRIMARY KEY (id)
);

CREATE TABLE ticket
(
    id      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    user_id BIGINT                                  NOT NULL,
    total   DECIMAL                                 NOT NULL,
    CONSTRAINT pk_ticket PRIMARY KEY (id)
);

CREATE TABLE venue
(
    id      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name    VARCHAR(255)                            NOT NULL,
    city    VARCHAR(255),
    address VARCHAR(255),
    CONSTRAINT pk_venue PRIMARY KEY (id)
);

ALTER TABLE app_user
    ADD CONSTRAINT uc_app_user_username UNIQUE (username);

ALTER TABLE event
    ADD CONSTRAINT FK_EVENT_ON_VENUE FOREIGN KEY (venue_id) REFERENCES venue (id);

ALTER TABLE event_seat
    ADD CONSTRAINT FK_EVENT_SEAT_ON_EVENT_SECTION FOREIGN KEY (event_section_id) REFERENCES event_section (id);

ALTER TABLE event_seat
    ADD CONSTRAINT FK_EVENT_SEAT_ON_SEAT FOREIGN KEY (seat_id) REFERENCES seat (id);

ALTER TABLE event_seat
    ADD CONSTRAINT FK_EVENT_SEAT_ON_TICKET FOREIGN KEY (ticket_id) REFERENCES ticket (id);

ALTER TABLE event_section
    ADD CONSTRAINT FK_EVENT_SECTION_ON_EVENT FOREIGN KEY (event_id) REFERENCES event (id);

ALTER TABLE event_section
    ADD CONSTRAINT FK_EVENT_SECTION_ON_SECTION FOREIGN KEY (section_id) REFERENCES section (id);

ALTER TABLE seat
    ADD CONSTRAINT FK_SEAT_ON_SECTION FOREIGN KEY (section_id) REFERENCES section (id);

ALTER TABLE section
    ADD CONSTRAINT FK_SECTION_ON_VENUE FOREIGN KEY (venue_id) REFERENCES venue (id);

ALTER TABLE ticket
    ADD CONSTRAINT FK_TICKET_ON_USER FOREIGN KEY (user_id) REFERENCES app_user (id);