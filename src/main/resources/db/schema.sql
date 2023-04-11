DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(25) NOT NULL,
    email     VARCHAR(50) NOT NULL,
    image_url VARCHAR(70),
    status    ENUM('Online', 'Offline') NOT NULL
);