-- Insert values into the 'faculty' table
INSERT INTO faculty (id, description, name)
VALUES (nextval('faculty_id_seq'), 'Faculty of Science', 'Science Faculty'),
       (nextval('faculty_id_seq'),'Faculty of Engineering', 'Engineering Faculty'),
       (nextval('faculty_id_seq'),'Faculty of Arts', 'Arts Faculty');

-- Insert values into the 'speciality' table
INSERT INTO speciality (id, description, name, faculty_id)
VALUES (nextval('speciality_id_seq'),'Physics', 'Physics Speciality', 1),
       (nextval('speciality_id_seq'),'Radio technic', 'Radio technic Speciality', 1),
       (nextval('speciality_id_seq'),'Computer Science', 'Computer Science Speciality', 2),
       (nextval('speciality_id_seq'),'Backend Specialist', 'Backend Speciality', 2),
       (nextval('speciality_id_seq'),'History', 'History Speciality', 3),
       (nextval('speciality_id_seq'),'Literature', 'Literature Speciality', 3);

-- Insert 1000 values into the 'student' table
-- Assuming 'course' is an integer, 'group_number' is an integer, 'receipt_date' is the current timestamp, and 'record_book_number' is unique for each student.

DO
$$
    DECLARE
        i INT := 1;
    BEGIN
        FOR i IN 1..1000
            LOOP
                INSERT INTO student (id, course, group_number, name, receipt_date, record_book_number, speciality_id)
                VALUES (nextval('student_id_seq'), -- id (auto-incremented)
                        (i % 4) + 1, -- course (values 1 to 4 for different courses)
                        (i % 5) + 1, -- group_number (values 1 to 5 for different groups)
                        'Student ' || i, -- name (e.g., 'Student 1', 'Student 2', etc.)
                        CURRENT_TIMESTAMP, -- receipt_date (current timestamp)
                        1000000 + i, -- record_book_number (unique for each student)
                        (i % 3) + 1 -- speciality_id (linking to the 3 specialities, rotating between 1, 2, and 3)
                       );
            END LOOP;
    END
$$;