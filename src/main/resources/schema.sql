-- TimeDeal 데이터베이스 스키마

-- 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS timedeal DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE timedeal;

-- 상품 테이블
CREATE TABLE IF NOT EXISTS products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 사용자 테이블
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 프로모션 테이블
CREATE TABLE IF NOT EXISTS promotions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL,
    discount_rate INT NOT NULL,
    total_quantity INT NOT NULL,
    issued_quantity INT DEFAULT 0,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 사용자 구매 상품 테이블
CREATE TABLE IF NOT EXISTS user_products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    purchased_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 테스트 데이터 삽입
INSERT INTO users (username, password, role) VALUES
('user', 'user', 'USER'),
('admin', 'admin', 'ADMIN');

INSERT INTO products (name, description, price) VALUES
('노트북', '고성능 게이밍 노트북', 1500000),
('마우스', '무선 게이밍 마우스', 80000),
('키보드', '기계식 키보드', 150000);

INSERT INTO promotions (product_id, status, discount_rate, total_quantity, issued_quantity, start_time, end_time) VALUES
(1, 'SCHEDULED', 30, 100, 99, DATE_ADD(NOW(), INTERVAL 10 SECOND), DATE_ADD(NOW(), INTERVAL 40 SECOND)),
(2, 'SCHEDULED', 20, 150, 88, DATE_ADD(NOW(), INTERVAL 20 SECOND), DATE_ADD(NOW(), INTERVAL 50 SECOND)),
(3, 'SCHEDULED', 15, 200, 77, DATE_ADD(NOW(), INTERVAL 30 SECOND), DATE_ADD(NOW(), INTERVAL 60 SECOND));
