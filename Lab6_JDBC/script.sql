create database TIMETABLE


USE TIMETABLE;

-- Таблица "Teachers" (Преподаватели)
CREATE TABLE IF NOT EXISTS Teachers (
  teacher_id INT AUTO_INCREMENT PRIMARY KEY,
  teacher_name VARCHAR(255) NOT NULL,
  num_students INT NOT NULL
);

-- Таблица "Subjects" (Предметы)
CREATE TABLE IF NOT EXISTS Subjects (
  subject_id INT AUTO_INCREMENT PRIMARY KEY,
  subject_name VARCHAR(255) NOT NULL,
  class_day VARCHAR(255) NOT NULL,
  class_time TIME NOT NULL,
  classroom VARCHAR(255) NOT NULL,
  teacher_id INT NOT NULL,
  FOREIGN KEY (teacher_id) REFERENCES Teachers(teacher_id)
);
INSERT INTO Teachers (teacher_name, num_students)
VALUES
  ('Иванов Иван Иванович', 30),
  ('Петров Петр Петрович', 25),
  ('Сидорова Мария Алексеевна', 20);

-- Примеры данных для таблицы "Subjects"
INSERT INTO Subjects (subject_name, class_day, class_time, classroom, teacher_id)
VALUES
  ('Математика', 'Понедельник', '10:00:00', '101', 1),
  ('Физика', 'Вторник', '14:30:00', '201', 2),
  ('История', 'Среда', '09:45:00', '301', 3);

