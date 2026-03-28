-- 1. Insert 5 Courses
INSERT INTO courses (name, hours, fees, description) VALUES
('Java Fullstack', 120, 500, 'Comprehensive Java development from core to Spring Boot.'),
('Python for Data Science', 90, 450, 'Data analysis, visualization, and machine learning basics.'),
('Web Design Mastery', 60, 300, 'Modern HTML, CSS, and UI/UX principles.'),
('Database Administration', 80, 400, 'Mastering SQL, indexing, and query optimization.'),
('Cloud Computing', 100, 550, 'AWS and Azure infrastructure management.');

-- 2. Insert 3 Classes (Linked to Courses 1, 2, and 3)
INSERT INTO classes (course_id, start_date, months) VALUES
(1, '2026-04-01', 4),
(2, '2026-04-15', 3),
(3, '2026-05-01', 2);

-- 3. Insert 15 Students
INSERT INTO students (name, phone, email) VALUES
('Alice Johnson', '09123456781', 'alice@example.com'),
('Bob Smith', '09123456782', 'bob@example.com'),
('Charlie Brown', '09123456783', 'charlie@example.com'),
('Diana Prince', '09123456784', 'diana@example.com'),
('Ethan Hunt', '09123456785', 'ethan@example.com'),
('Fiona Gallagher', '09123456786', 'fiona@example.com'),
('George Miller', '09123456787', 'george@example.com'),
('Hannah Abbott', '09123456788', 'hannah@example.com'),
('Ian Wright', '09123456789', 'ian@example.com'),
('Julia Roberts', '09123456790', 'julia@example.com'),
('Kevin Hart', '09123456791', 'kevin@example.com'),
('Laura Palmer', '09123456792', 'laura@example.com'),
('Mike Wazowski', '09123456793', 'mike@example.com'),
('Nina Simone', '09123456794', 'nina@example.com'),
('Oscar Wilde', '09123456795', 'oscar@example.com'),
('Aung Aung', '09123456796', 'aung@example.com');

-- 4. Insert 20 Registrations (Mapping Students to Classes)
-- Class 1 (Java) - 8 Students
INSERT INTO registrations (classes_id, students_id) VALUES (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8);
-- Class 2 (Python) - 7 Students
INSERT INTO registrations (classes_id, students_id) VALUES (2, 9), (2, 10), (2, 11), (2, 12), (2, 13), (2, 14), (2, 15);
-- Class 3 (Web Design) - 5 Students
INSERT INTO registrations (classes_id, students_id) VALUES (3, 1), (3, 3), (3, 5), (3, 7), (3, 9);