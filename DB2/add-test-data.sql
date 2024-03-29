INSERT INTO Course (CourseName, Subject, IntroductionText, Difficulty)
VALUES ('TestCourse', 'Testing', 'You will be testing the database', 1)

INSERT INTO Course (CourseName, Subject, IntroductionText, Difficulty)
VALUES ('Programmeren 1', 'Programmeren', 'You will learn the very basics of programming', 1)

INSERT INTO Course (CourseName, Subject, IntroductionText, Difficulty)
VALUES ('Onderzoeksvaardigheden', 'Onderzoek', 'You will be conducting research', 2)

INSERT INTO Student (Email, Name, Birthday, Sex, Adress, Residence, Country)
VALUES ('j.arbuckle@student.avans.nl', 'Jon Arbuckle', convert(date,'28/07/1951', 103), 'No', '711 Maple Street', 'Muncie,  2595 BG', 'United States')

INSERT INTO Student (Email, Name, Birthday, Sex, Adress, Residence, Country)
VALUES ('g.arbuckle@student.avans.nl', 'Garfield Arbuckle', convert(date,'19/07/1978', 103), 'Cat', '711 Maple Street', 'Muncie,  2595 BG', 'United States')

INSERT INTO Student (Email, Name, Birthday, Sex, Adress, Residence, Country)
VALUES ('o.arbuckle@student.avans.nl', 'Odie Arbuckle', convert(date,'08/08/1978', 103), 'Dog', '711 Maple Street', 'Muncie,  2595 BG', 'United States')

INSERT INTO ContentItem (CourseName, PublicationDate, Status, ContentItemID)
VALUES ('TestCourse', convert(date, '14/04/2023', 103), 'InProgress', 1)

INSERT INTO ContentItem (CourseName, PublicationDate, Status, ContentItemID)
VALUES ('Programmeren 1', convert(date, '14/04/2023', 103), 'InProgress', 2)

INSERT INTO ContentItem (CourseName, PublicationDate, Status, ContentItemID)
VALUES ('Programmeren 1', convert(date, '14/04/2023', 103), 'InProgress', 3)

INSERT INTO ContentItem (CourseName, PublicationDate, Status, ContentItemID)
VALUES ('Programmeren 1', convert(date, '14/04/2023', 103), 'InProgress', 4)

INSERT INTO ContentItem (CourseName, PublicationDate, Status, ContentItemID)
VALUES ('Programmeren 1', convert(date, '14/04/2023', 103), 'InProgress', 5)

INSERT INTO Module (ContentItemID, Title, Version, Description, ContactName, ContactEmail, TrackingID)
VALUES (1, 'The basics of testing', 1, 'In this module you will learn how to create, read, update and delete data', 'John Test', 'j.test@avans.nl', 1)

INSERT INTO Webcast (Title, ContentItemID, Description, Speaker, Organisation, ViewCount)
VALUES ('Programmeren: The basics', 2, 'Een korte video over de basics', 'Arno Broeders', 'Avans', 42069)

INSERT INTO Webcast (Title, ContentItemID, Description, Speaker, Organisation, ViewCount)
VALUES ('Programmeren: Variables', 3, 'Een korte video over de basics', 'Arno Broeders', 'Avans', 69420)

INSERT INTO Webcast (Title, ContentItemID, Description, Speaker, Organisation, ViewCount)
VALUES ('Programmeren: Functions', 4, 'Een korte video over de basics', 'Arno Broeders', 'Avans', 8383)

INSERT INTO Webcast (Title, ContentItemID, Description, Speaker, Organisation, ViewCount)
VALUES ('Programmeren (optional): Complex algorithms', 5, 'Een lange video of zeer complexe algoritmes', 'Arno Broeders', 'Avans', 4)

INSERT INTO Enrollment (StudentEmail, CourseName, EnrollmentDate)
VALUES ('j.arbuckle@student.avans.nl', 'Programmeren 1', convert(date, '14/04/2023', 103))

INSERT INTO Enrollment (StudentEmail, CourseName, EnrollmentDate)
VALUES ('g.arbuckle@student.avans.nl', 'Programmeren 1', convert(date, '14/04/2023', 103))

INSERT INTO Enrollment (StudentEmail, CourseName, EnrollmentDate)
VALUES ('o.arbuckle@student.avans.nl', 'TestCourse', convert(date, '14/04/2023', 103))

INSERT INTO Enrollment (StudentEmail, CourseName, EnrollmentDate)
VALUES ('j.arbuckle@student.avans.nl', 'TestCourse', convert(date, '14/04/2023', 103))

INSERT INTO Enrollment (StudentEmail, CourseName, EnrollmentDate)
VALUES ('g.arbuckle@student.avans.nl', 'TestCourse', convert(date, '14/04/2023', 103))

INSERT INTO RecommendedCourse (CourseName)
VALUES ('TestCourse')

INSERT INTO CoursesRecommendedToCourse (MainCourse, CourseBeingRecommended)
VALUES ('Programmeren 1', 'TestCourse')

INSERT INTO StudentContentItemProgress (StudentEmail, ContentItemID, Progress)
VALUES ('o.arbuckle@student.avans.nl', 1, 50);

INSERT INTO StudentContentItemProgress (StudentEmail, ContentItemID, Progress)
VALUES ('j.arbuckle@student.avans.nl', 1, 100);

INSERT INTO StudentContentItemProgress (StudentEmail, ContentItemID, Progress)
VALUES ('g.arbuckle@student.avans.nl', 1, 20);