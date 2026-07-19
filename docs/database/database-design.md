# Database Design

## Entità

- User
- Course
- Exam
- Task
- Note
- StudySession

## Definizione entità
- User:
    id
    username
    password
    firstName
    lastName
    createdAt
    updatedAt

- Course:
    id
    name
    professor
    cfu
    semester
    academicYear
    userId

- Exam:
    id
    name
    date
    grade
    status (planned, passed, failed)
    courseId

- Task:
    id
    title
    description
    deadline
    priority
    completed
    userId

- StudySession:
    id
    studyDate
    durationMinutes
    studyChapter
    courseId

- Note:
    id
    title
    content
    createdAt
    updatedAt
    courseId

## Relazioni
User 1:N Course
User 1:N Task
Course 1:N Exam
Course 1:N Note
Course 1:N StudySession