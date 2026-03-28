SET FOREIGN_KEY_CHECKS = 0;
--
-- Courses Table
--
DROP TABLE IF EXISTS courses;
CREATE TABLE courses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(40) UNIQUE NOT NULL,
    hours INT NOT NULL,
    fees INT NOT NULL,
    description VARCHAR(255) 
);

--
-- Classes Table
--
DROP TABLE IF EXISTS classes;
CREATE TABLE classes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    course_id INT NOT NULL,
    start_date DATE NOT NULL,
    months INT NOT NULL,
    CONSTRAINT classes_course FOREIGN KEY (course_id) REFERENCES courses(id) 
);

--
-- Students Table
--
DROP TABLE IF EXISTS students;
CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(40) NOT NULL,
    phone VARCHAR(16) NOT NULL,
    email VARCHAR(60) NOT NULL
);

--
-- Registration Table
--
DROP TABLE IF EXISTS registrations;
CREATE TABLE registrations (
    classes_id INT NOT NULL,
    students_id INT NOT NULL,
    issue_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (classes_id, students_id),
    CONSTRAINT registration_class FOREIGN KEY (classes_id) REFERENCES classes(id),
    CONSTRAINT registration_student FOREIGN KEY (students_id) REFERENCES students(id)
);

SET FOREIGN_KEY_CHECKS = 1;

