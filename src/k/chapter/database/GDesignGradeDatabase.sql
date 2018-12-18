/* Question:
 * Imagine a simple database storing information for students grades.
 * Design what this database might look like provide a SQL query to return
 * a list of the honor roll students (top 10%), sorted by their grade point
 * average.*/

/* Database Table Structure as mentioned below
 *  STUDENT TABLE
 *   - StudentID
 *   - StudentName
 *   - ClassID
 *
 *  CLASS TABLE
 *   - ClassID
 *   - ClassName
 *
 *  COURSE TABLE
 *   - CourseID
 *   - CourseName
 *
 *  CLASSCOURSEMAP TABLE
 *   - ClassID
 *   - CourseID
 *
 *  STUDENTGRADE TABLE
 *   - ID
 *   - StudentID
 *   - CourseID
 *   - Marks
 */
-- Oracle 11g Express Edition
-- Put your Oracle SQL statement here and execute it
SELECT banner AS "oracle version" FROM v$version;
DROP TABLE student;
DROP TABLE class;
DROP TABLE course;
DROP TABLE classcoursemap;
DROP TABLE studentgrade;

CREATE TABLE student (studentid INT, studentname VARCHAR(50), classid INT);
INSERT INTO student (studentid, studentname, classid)
VALUES
  (1, "manoj", 1);

INSERT INTO student (studentid, studentname, classid)
VALUES
  (2, "kumaran", 1);

INSERT INTO student (studentid, studentname, classid)
VALUES
  (3, "rajesh", 1);

INSERT INTO student (studentid, studentname, classid)
VALUES
  (4, "anil", 2);

INSERT INTO student (studentid, studentname, classid)
VALUES
  (5, "nikhil", 2);

INSERT INTO student (studentid, studentname, classid)
VALUES
  (6, "bharath", 2);

INSERT INTO student (studentid, studentname, classid)
VALUES
  (7, "roger", 3);

INSERT INTO student (studentid, studentname, classid)
VALUES
  (8, "antony", 3);

INSERT INTO student (studentid, studentname, classid)
VALUES
  (9, "quereshi", 3);

INSERT INTO student (studentid, studentname, classid)
VALUES
  (10, "abinand", 4);
INSERT INTO student (studentid, studentname, classid)
VALUES
  (11, "stefen", 4);

INSERT INTO student (studentid, studentname, classid)
VALUES
  (12, "arivu", 4);

CREATE TABLE class (classid INT, classname VARCHAR(50));
INSERT INTO class (classid, classname)
VALUES
  (1, "class a - xi standard (biology)");

INSERT INTO class (classid, classname)
VALUES
  (2, "class b - xi standard (computer)");

INSERT INTO class (classid, classname)
VALUES
  (3, "class c - xi standard (statistics)");

INSERT INTO class (classid, classname)
VALUES
  (4, "class d - xi standard (commerce)");

CREATE TABLE course (courseid INT, coursename VARCHAR(30));
INSERT INTO course (courseid, coursename)
VALUES
  (1, "english");

INSERT INTO course (courseid, coursename)
VALUES
  (2, "tamil");

INSERT INTO course (courseid, coursename)
VALUES
  (3, "maths");

INSERT INTO course (courseid, coursename)
VALUES
  (4, "physics");

INSERT INTO course (courseid, coursename)
VALUES
  (5, "chemistry");

INSERT INTO course (courseid, coursename)
VALUES
  (6, "biology");

INSERT INTO course (courseid, coursename)
VALUES
  (7, "computer science");

INSERT INTO course (courseid, coursename)
VALUES
  (8, "statistics");

INSERT INTO course (courseid, coursename)
VALUES
  (9, "commerce");

INSERT INTO course (courseid, coursename)
VALUES
  (10, "iti");

CREATE TABLE classcoursemap (classid INT, courseid INT);
INSERT INTO classcoursemap (classid, courseid)
VALUES
  (1, 1);

INSERT INTO classcoursemap (classid, courseid)
VALUES
  (1, 2);

INSERT INTO classcoursemap (classid, courseid)
VALUES
  (1, 3);

INSERT INTO classcoursemap (classid, courseid)
VALUES
  (1, 4);

INSERT INTO classcoursemap (classid, courseid)
VALUES
  (1, 5);

INSERT INTO classcoursemap (classid, courseid)
VALUES
  (1, 6);

INSERT INTO classcoursemap (classid, courseid)
VALUES
  (2, 1);

INSERT INTO classcoursemap (classid, courseid)
VALUES
  (2, 2);

INSERT INTO classcoursemap (classid, courseid)
VALUES
  (2, 3);

INSERT INTO classcoursemap (classid, courseid)
VALUES
  (2, 4);

INSERT INTO classcoursemap (classid, courseid)
VALUES
  (2, 5);

INSERT INTO classcoursemap (classid, courseid)
VALUES
  (2, 7);

INSERT INTO classcoursemap (classid, courseid)
VALUES
  (3, 1);

INSERT INTO classcoursemap (classid, courseid)
VALUES
  (3, 2);

INSERT INTO classcoursemap (classid, courseid)
VALUES
  (3, 3);

INSERT INTO classcoursemap (classid, courseid)
VALUES
  (3, 4);

INSERT INTO classcoursemap (classid, courseid)
VALUES
  (3, 5);

INSERT INTO classcoursemap (classid, courseid)
VALUES
  (3, 8);

INSERT INTO classcoursemap (classid, courseid)
VALUES
  (4, 1);

INSERT INTO classcoursemap (classid, courseid)
VALUES
  (4, 2);

INSERT INTO classcoursemap (classid, courseid)
VALUES
  (4, 3);

INSERT INTO classcoursemap (classid, courseid)
VALUES
  (4, 4);

INSERT INTO classcoursemap (classid, courseid)
VALUES
  (4, 5);

INSERT INTO classcoursemap (classid, courseid)
VALUES
  (4, 9);

CREATE TABLE studentgrade (id INT, studentid INT, courseid INT, marks INT);
INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (1, 1, 1, 76);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (2, 1, 2, 69);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (3, 1, 3, 80);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (4, 1, 4, 86);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (5, 1, 5, 82);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (6, 1, 6, 87);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (7, 2, 1, 79);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (8, 2, 2, 81);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (9, 2, 3, 86);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (10, 2, 4, 80);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (11, 2, 5, 90);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (12, 2, 6, 90);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (13, 3, 1, 95);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (14, 3, 2, 90);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (15, 3, 3, 79);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (16, 3, 4, 80);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (17, 3, 5, 80);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (18, 3, 6, 85);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (19, 4, 1, 89);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (20, 4, 2, 86);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (21, 4, 3, 80);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (22, 4, 4, 85);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (23, 4, 5, 70);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (24, 4, 7, 78);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (25, 5, 1, 70);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (26, 5, 2, 58);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (27, 5, 3, 90);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (28, 5, 4, 90);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (29, 5, 5, 89);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (30, 5, 7, 86);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (31, 6, 1, 85);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (32, 6, 2, 81);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (33, 6, 3, 80);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (34, 6, 4, 70);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (35, 6, 5, 70);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (36, 6, 7, 81);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (37, 7, 1, 76);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (38, 7, 2, 90);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (39, 7, 3, 70);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (40, 7, 4, 80);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (41, 7, 5, 82);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (42, 7, 8, 79);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (43, 8, 1, 80);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (44, 8, 2, 89);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (45, 8, 3, 80);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (46, 8, 4, 76);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (47, 8, 5, 73);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (48, 8, 8, 79);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (49, 9, 1, 90);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (50, 9, 2, 90);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (51, 9, 3, 78);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (52, 9, 4, 70);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (53, 9, 5, 78);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (54, 9, 8, 77);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (55, 10, 1, 80);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (56, 10, 2, 86);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (57, 10, 3, 96);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (58, 10, 4, 80);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (59, 10, 5, 70);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (60, 10, 9, 72);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (61, 11, 1, 90);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (62, 11, 2, 95);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (63, 11, 3, 80);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (64, 11, 4, 84);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (65, 11, 5, 79);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (66, 11, 9, 70);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (67, 12, 1, 80);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (68, 12, 2, 77);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (69, 12, 3, 80);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (70, 12, 4, 88);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (71, 12, 5, 90);

INSERT INTO studentgrade (id, studentid, courseid, marks)
VALUES
  (72, 12, 9, 74);


SELECT studentgrade.studentid AS StudentID,
  Floor(Sum(studentgrade.marks) / 6) AS Total
FROM
  studentgrade
GROUP BY
  studentgrade.studentid
ORDER BY
  total DESC;

SELECT
  Count(*)
FROM
  (SELECT
      studentgrade.studentid
    FROM
      studentgrade
    GROUP BY
      studentgrade.studentid
  );

SELECT Result.studentid,
  total
FROM
  (
    SELECT
      studentgrade.studentid AS StudentID,
      Floor(
          Sum(studentgrade.marks) / 6
      ) AS Total
    FROM
      studentgrade
    GROUP BY
      studentgrade.studentid
    ORDER BY
      total DESC
  ) Result
WHERE
  rownum <= Ceil(
      (
        (SELECT Count(*) FROM
        (SELECT studentgrade.studentid FROM studentgrade GROUP BY studentgrade.studentid)) * 10
      ) / 100
  );