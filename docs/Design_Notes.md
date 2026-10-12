# Design Notes

## Why ArrayList instead of array?

Arrays in java have a fixed size, we have to tell the size when creating it. In this project we dont know how many students, courses or enrollments will be added by the admin. With an array I would have to pick a max size (like 100) and keep a separate count variable.

ArrayList grows automatically when we add items, and it has useful methods like `add()`, `remove()`, `size()`, `isEmpty()` and `get()`. So the code becomes shorter and easier. That's why I used `ArrayList<Student>`, `ArrayList<Course>` and `ArrayList<Enrollment>` in the service classes.

## Where I used static and why

- **IdGenerator** - the counters (`studentIdCounter`, `courseIdCounter` etc) are `static` because there should be only one counter for the whole program. If they were instance variables, every new IdGenerator object would start from the beginning and we would get duplicate ids. The methods like `getNextStudentId()` are also static so we can call them directly without making an object.
- **InputValidator** - all methods are static because they are just helper methods, they don't need any object data.
- **Enrollment status constants** - `ACTIVE`, `COMPLETED`, `CANCELLED` are `public static final` so they are same for all enrollments and can't be changed.
- **Main** - the Scanner and the service objects are static so that all the static menu methods can use them.

## Where I used inheritance and what I gained

`Person` is the base class with common fields: `id`, `firstName`, `lastName`, `email`.

- `Student extends Person` and adds `batch` and `active`.
- `Trainer extends Person` and adds `expertise`.

Benefits:
- I didn't have to write the common fields and getters/setters again in Student and Trainer (code reuse).
- Both constructors use `super(...)` to set the common fields.
- `getDisplayName()` is defined in Person and **overridden** in Student (shows batch) and Trainer (shows "Trainer" and expertise). So the same method call gives different output depending on the object - this is polymorphism.

## Other things

- Students are not deleted, they are only deactivated (`active = false`), so old enrollment data still makes sense.
- Custom exceptions `EntityNotFoundException` and `InvalidInputException` are thrown from service classes and caught in Main, so the program shows a message instead of crashing.
- `readInt()` in Main uses try-catch for `NumberFormatException` so if user types letters it asks again.
