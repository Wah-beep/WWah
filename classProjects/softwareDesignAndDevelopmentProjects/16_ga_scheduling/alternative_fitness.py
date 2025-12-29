def alternative_fitness(individual, students, courses, ideal_enrollment):
    
    fitness = 0
    course_counts = {course: 0 for course in courses}
    
    for i, course in enumerate(individual):
        # Add fitness based on student preference (higher rank is better)
        fitness += students[i].get_preference(course)
        # Track course enrollments
        course_counts[course] += 1
    
    # Penalize deviations from ideal enrollment more harshly
    for course, count in course_counts.items():
        fitness -= abs(count - ideal_enrollment) ** 2  # Squared penalty
    
    return fitness